package org.sistemafinanciero.controller;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.Query;

import org.sistemafinanciero.dao.DAO;
import org.sistemafinanciero.dao.QueryParameter;
import org.sistemafinanciero.entity.CuentaBancariaView;
import org.sistemafinanciero.entity.DebeHaber;
import org.sistemafinanciero.entity.type.TipoDebeHaber;
import org.sistemafinanciero.service.nt.ReporteCajaBancosServiceNT;
import org.sistemafinanciero.service.nt.ReportesServiceNT;
import org.sistemafinanciero.service.nt.VariableSistemaServiceNT;
import org.sistemafinanciero.util.DateUtils;
import org.sistemafinanciero.util.EntityManagerProducer;

@Named
@Stateless
@Remote(ReportesServiceNT.class)
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class ReportesServiceBeanNT implements ReportesServiceNT {

	@Inject
	private DAO<Object, DebeHaber> debeHaberDAO;

	@Inject
	private EntityManagerProducer em;

	@EJB
	private ReporteCajaBancosServiceNT reporteCajaBancosServiceNT;

	@EJB
	private VariableSistemaServiceNT variableSistemaServiceNT;

	@Override
	public List<DebeHaber> getDebeHaber(Date fecha, BigInteger idMoneda, TipoDebeHaber tipoDebeHaber) {
		if (fecha == null) {
			return null;
		}
		QueryParameter queryParameter = QueryParameter.with("idMoneda", idMoneda).and("tipo", tipoDebeHaber)
				.and("fecha", fecha);
		List<DebeHaber> list = debeHaberDAO.findByNamedQuery(DebeHaber.findIdMonedaTipoFecha,
				queryParameter.parameters());
		return list;
	}

	@Override
	public List<DebeHaber> getDebeHaber(Date fecha, TipoDebeHaber tipoDebeHaber) {
		if (fecha == null) {
			return null;
		}
		QueryParameter queryParameter = QueryParameter.with("tipo", tipoDebeHaber).and("fecha", fecha);
		List<DebeHaber> list = debeHaberDAO.findByNamedQuery(DebeHaber.findTipoFecha, queryParameter.parameters());
		return list;
	}

	@Override
	public BigDecimal getDebeHaberTotal(Date fechaReporte, BigInteger idMoneda, TipoDebeHaber tipoDebeHaber) {
		Date desde = DateUtils.getDateIn00Time(fechaReporte);
		Date hasta = DateUtils.getDateIn00Time(DateUtils.sumarRestarDiasFecha(fechaReporte, 1));

		Query query = em.getEm().createQuery(
				("SELECT SUM(dh.monto) FROM DebeHaber dh WHERE dh.idMoneda =:idMoneda AND dh.tipo = :tipo AND dh.fecha BETWEEN :desde AND :hasta"));
		query.setParameter("idMoneda", idMoneda);
		query.setParameter("tipo", tipoDebeHaber);
		query.setParameter("desde", desde);
		query.setParameter("hasta", hasta);

		return (BigDecimal) query.getSingleResult();
	}

	@Override
	public List<DebeHaber> getDebeHaberHistorialTotal(Date desdeReporte, Date hastaReporte, BigInteger idMoneda,
			TipoDebeHaber tipoDebeHaber) {
		Date desde = DateUtils.getDateIn00Time(desdeReporte);
		Date hasta = DateUtils.getDateIn00Time(DateUtils.sumarRestarDiasFecha(hastaReporte, 1));

		Query query = em.getEm().createQuery(
				("SELECT dh.fecha, SUM(dh.monto) FROM DebeHaber dh WHERE dh.idMoneda =:idMoneda AND dh.tipo =:tipo AND dh.fecha BETWEEN :desde AND :hasta GROUP BY dh.fecha ORDER BY dh.fecha"));
		query.setParameter("idMoneda", idMoneda);
		query.setParameter("tipo", tipoDebeHaber);
		query.setParameter("desde", desde);
		query.setParameter("hasta", hasta);

		// Resultados de la base de datos
		List<Object[]> rows = query.getResultList();
		List<DebeHaber> dbResult = new ArrayList<>(rows.size());
		for (Object[] row : rows) {
			DebeHaber debeHaber = new DebeHaber();
			debeHaber.setFecha((Date) row[0]);
			debeHaber.setMonto((BigDecimal) row[1]);
			dbResult.add(debeHaber);
		}

		// Poner a cero todos los que no existen
		Calendar start = Calendar.getInstance();
		start.setTime(desde);
		Calendar end = Calendar.getInstance();
		end.setTime(hasta);
		List<DebeHaber> allDates = new ArrayList<>();
		for (Date date = start.getTime(); start.before(end); start.add(Calendar.DATE, 1), date = start.getTime()) {
			DebeHaber debeHaber = new DebeHaber();
			debeHaber.setFecha(date);
			debeHaber.setMonto(BigDecimal.ZERO);
			allDates.add(debeHaber);
		}

		// Unir ambas listas
		for (DebeHaber debeHaber : allDates) {
			for (DebeHaber debeHaberBD : dbResult) {
				if (DateUtils.getDateIn00Time(debeHaber.getFecha())
						.compareTo(DateUtils.getDateIn00Time(debeHaberBD.getFecha())) == 0) {
					debeHaber.setMonto(debeHaberBD.getMonto());
					break;
				}
			}
		}

		return allDates;
	}

	@Override
	public BigDecimal getTotalCuentasPorCobrar(BigInteger idMoneda, Date fechaReporte) {
		// Ctas por cobrar = prestamos + sobregiros (todas las cuentas de ahorro
		// y corriente activos con saldo negativo)
		Query query = em.getEm().createQuery(
				("SELECT SUM(cb.saldo) FROM CuentaBancariaView cb WHERE cb.saldo < 0 AND cb.idMoneda = :idMoneda AND cb.estadoCuenta != 'INACTIVO'"));
		query.setParameter("idMoneda", idMoneda);
		Object obj1 = query.getSingleResult();
		return obj1 != null ? ((BigDecimal) obj1).abs() : BigDecimal.ZERO;
	}

	@Override
	public BigDecimal getTotalCuentasPorPagar(BigInteger idMoneda, Date fechaReporte) {
		// Ctas por pagar = todas las cuentas de ahorro corriente y plazo fijo
		// que tienen saldos positivos
		Query query = em.getEm().createQuery(
				("SELECT SUM(cb.saldo) FROM CuentaBancariaView cb WHERE cb.saldo > 0 AND cb.idMoneda = :idMoneda AND cb.estadoCuenta != 'INACTIVO'"));
		query.setParameter("idMoneda", idMoneda);
		Object obj1 = query.getSingleResult();
		return obj1 != null ? ((BigDecimal) obj1).abs() : BigDecimal.ZERO;
	}

	@Override
	public BigDecimal getPatrimonio(BigInteger idMoneda, Date fechaReporte) {
		// TODO Auto-generated method stub
		Query queryIngresos = em.getEm().createQuery(
				"SELECT sum(t.monto) FROM TransaccionBovedaOtroView T WHERE t.idMoneda = :idMoneda AND t.estado = TRUE AND t.entidad = 'PATRIMONIO' AND t.tipoTransaccion = 'INGRESO'");
		queryIngresos.setParameter("idMoneda", idMoneda);

		Query queryEgresos = em.getEm().createQuery(
				"SELECT sum(t.monto) FROM TransaccionBovedaOtroView T WHERE t.idMoneda = :idMoneda AND t.estado = TRUE AND t.entidad = 'PATRIMONIO' AND t.tipoTransaccion = 'EGRESO'");
		queryEgresos.setParameter("idMoneda", idMoneda);

		Object obj1 = queryIngresos.getSingleResult();
		Object obj2 = queryEgresos.getSingleResult();

		BigDecimal montoIngresos = obj1 != null ? (BigDecimal) obj1 : BigDecimal.ZERO;
		BigDecimal montoEgresos = obj2 != null ? (BigDecimal) obj2 : BigDecimal.ZERO;

		return montoIngresos.abs().subtract(montoEgresos.abs());
	}

	@Override
	public BigDecimal getTotalUtilidad(BigInteger idMoneda, Date fechaReporte) {
		if (idMoneda != null) {
			return getTotalUtilidadByMoneda(idMoneda, fechaReporte);
		}

		// Utilidad = caja + bancos + ctas por cobrar - ctas por pagar -
		// patrimonio

		BigDecimal utilidadSoles = getTotalUtilidadByMoneda(BigInteger.ONE, fechaReporte);		
		BigDecimal utilidadDolares = getTotalUtilidadByMoneda(BigInteger.ZERO, fechaReporte);
		BigDecimal utilidadEuros = getTotalUtilidadByMoneda(new BigInteger("2"), fechaReporte);

		// Obteniendo tasas de conversion para calculo de utilidades
		BigDecimal tasaVentaDolares = variableSistemaServiceNT.getTasaCambio(BigInteger.ZERO, BigInteger.ONE);
		BigDecimal tasaVentaEuros = variableSistemaServiceNT.getTasaCambio(new BigInteger("2"), BigInteger.ONE);
		
		// Convertir
		utilidadDolares = utilidadDolares.multiply(tasaVentaDolares);
		utilidadEuros = utilidadEuros.multiply(tasaVentaEuros);
		
		return utilidadSoles.add(utilidadDolares).add(utilidadEuros);
	}

	public BigDecimal getTotalUtilidadByMoneda(BigInteger idMoneda, Date fechaReporte) {
		// Utilidad = caja + bancos + ctas por cobrar - ctas por pagar -
		// patrimonio - sumatoria de retiros en utilidades
		BigDecimal utilidad = BigDecimal.ZERO;
		BigDecimal caja = reporteCajaBancosServiceNT.getReporteTotalCaja(idMoneda);
		BigDecimal bancos = reporteCajaBancosServiceNT.getTotalBancos(idMoneda);
		BigDecimal ctasPorCobrar = getTotalCuentasPorCobrar(idMoneda, null);
		BigDecimal ctasPorPagar = getTotalCuentasPorPagar(idMoneda, null);
		BigDecimal patrimonio = getPatrimonio(idMoneda, null);

		Query queryEgresosUtilidad = em.getEm().createQuery(
				"SELECT sum(t.monto) FROM TransaccionBovedaOtroView T WHERE t.idMoneda = :idMoneda AND t.estado = TRUE AND t.entidad = 'UTILIDAD' AND t.tipoTransaccion = 'EGRESO'");
		queryEgresosUtilidad.setParameter("idMoneda", idMoneda);
		Object obj1 = queryEgresosUtilidad.getSingleResult();
		BigDecimal montoEgresoUtilidad = obj1 != null ? (BigDecimal) obj1 : BigDecimal.ZERO;

		// Calculo de utilidades
		utilidad = caja.add(bancos).add(ctasPorCobrar).subtract(ctasPorPagar).subtract(patrimonio.abs())
				.subtract(montoEgresoUtilidad.abs());
		return utilidad;
	}

}
