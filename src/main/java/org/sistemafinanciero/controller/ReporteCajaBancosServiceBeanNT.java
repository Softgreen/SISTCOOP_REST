package org.sistemafinanciero.controller;

import java.math.BigDecimal;
import java.math.BigInteger;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.Query;

import org.sistemafinanciero.service.nt.ReporteCajaBancosServiceNT;
import org.sistemafinanciero.util.EntityManagerProducer;

@Named
@Stateless
@Remote(ReporteCajaBancosServiceNT.class)
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class ReporteCajaBancosServiceBeanNT implements ReporteCajaBancosServiceNT {

	@Inject
    private EntityManagerProducer em;
	
	@Override
	public BigDecimal getReporteCajaPorAgencia(BigInteger idMoneda, BigInteger idAgencia) {
		Query queryBoveda = em.getEm().createQuery("select sum(dhb.cantidad * md.valor) from DetalleHistorialBoveda dhb inner join dhb.historialBoveda hb inner join hb.boveda b inner join b.agencia a inner join b.moneda m inner join dhb.monedaDenominacion md where a.idAgencia = :idagencia and m.idMoneda = :idmoneda and hb.estado = :estado");
		queryBoveda.setParameter("idmoneda", idMoneda);
		queryBoveda.setParameter("idagencia", idAgencia);
		queryBoveda.setParameter("estado", true);
		
		Query queryCaja = em.getEm().createQuery("select sum(bc.saldo) from BovedaCaja bc inner join bc.boveda b inner join b.moneda m inner join b.agencia a where a.idAgencia = :idagencia and m.idMoneda = :idmoneda");
		queryCaja.setParameter("idmoneda", idMoneda);
		queryCaja.setParameter("idagencia", idAgencia);
		
		Object objBov = queryBoveda.getSingleResult();
		Object objCaj = queryCaja.getSingleResult();
		
		BigDecimal montoBovedas = objBov != null ? (BigDecimal) objBov : BigDecimal.ZERO;
		BigDecimal montoCajas = objCaj != null ? (BigDecimal) objCaj : BigDecimal.ZERO;
		
		return montoBovedas.add(montoCajas);
	}

	@Override
	public BigDecimal getReporteTotalCaja(BigInteger idMoneda) {
		Query queryBovedas = em.getEm().createQuery("select sum(dhb.cantidad * md.valor) from DetalleHistorialBoveda dhb inner join dhb.historialBoveda hb inner join hb.boveda b inner join b.moneda m inner join dhb.monedaDenominacion md where m.idMoneda = :idmoneda and hb.estado = :estado");
		queryBovedas.setParameter("idmoneda", idMoneda);
		queryBovedas.setParameter("estado", true);
		
		Query queryCajas = em.getEm().createQuery("select sum(bc.saldo) from BovedaCaja bc inner join bc.boveda b inner join b.moneda m where m.idMoneda = :idmoneda");
		queryCajas.setParameter("idmoneda", idMoneda);
		
		Object objBovedas = queryBovedas.getSingleResult();
		Object objCajas = queryCajas.getSingleResult();
		
		BigDecimal montoBovedas = objBovedas != null ? (BigDecimal) objBovedas : BigDecimal.ZERO;
		BigDecimal montoCajas = objCajas != null ? (BigDecimal) objCajas : BigDecimal.ZERO;
		
		return montoBovedas.add(montoCajas);
	}

	@Override
	public BigDecimal getTotalBancos(BigInteger idMoneda) {				
		Query queryIngresos = em.getEm().createQuery("SELECT sum(t.monto) FROM TransaccionBovedaOtroView T WHERE t.moneda.idMoneda = :idMoneda AND t.estado = TRUE AND t.entidad = 'BANCOS' AND t.tipoTransaccion = 'INGRESO'");
		queryIngresos.setParameter("idMoneda", idMoneda);		
		
		Query queryEgresos = em.getEm().createQuery("SELECT sum(t.monto) FROM TransaccionBovedaOtroView T WHERE t.moneda.idMoneda = :idMoneda AND t.estado = TRUE AND t.entidad = 'BANCOS' AND t.tipoTransaccion = 'EGRESO'");
		queryEgresos.setParameter("idMoneda", idMoneda);
		
		Object obj1 = queryIngresos.getSingleResult();
		Object obj2 = queryEgresos.getSingleResult();

		BigDecimal montoIngresos = obj1 != null ? (BigDecimal) obj1 : BigDecimal.ZERO;
		BigDecimal montoEgresos = obj2 != null ? (BigDecimal) obj2 : BigDecimal.ZERO;
				
		return montoIngresos.subtract(montoEgresos);
	}

}
