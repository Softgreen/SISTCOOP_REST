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
		
		BigDecimal montoBovedas = (BigDecimal) queryBoveda.getSingleResult();
		BigDecimal montoCajas = (BigDecimal) queryCaja.getSingleResult();
		
		BigDecimal montoBovedasCajas = montoBovedas.add(montoCajas);
		
		return montoBovedasCajas;
	}

	@Override
	public BigDecimal getReporteTotalCaja(BigInteger idMoneda) {
		Query queryBovedas = em.getEm().createQuery("select sum(dhb.cantidad * md.valor) from DetalleHistorialBoveda dhb inner join dhb.historialBoveda hb inner join hb.boveda b inner join b.moneda m inner join dhb.monedaDenominacion md where m.idMoneda = :idmoneda and hb.estado = :estado");
		queryBovedas.setParameter("idmoneda", idMoneda);
		queryBovedas.setParameter("estado", true);
		
		Query queryCajas = em.getEm().createQuery("select sum(bc.saldo) from BovedaCaja bc inner join bc.boveda b inner join b.moneda m where m.idMoneda = :idmoneda");
		queryCajas.setParameter("idmoneda", idMoneda);
		
		BigDecimal montoBovedas = (BigDecimal) queryBovedas.getSingleResult();
		BigDecimal montoCajas = (BigDecimal) queryCajas.getSingleResult();
		
		BigDecimal montoBovedasCajas = montoBovedas.add(montoCajas);
		
		
		
		return montoBovedasCajas;
	}

    
	/*
	@Inject
    private DAO<Object, DebeHaber> debeHaberDAO;

    @Inject
    private EntityManagerProducer em;
    
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
        List<DebeHaber> list = debeHaberDAO.findByNamedQuery(DebeHaber.findTipoFecha,
                queryParameter.parameters());
        return list;
    }

    @Override
    public List<DebeHaber> getDebeHaberHistorialTotal(Date desdeReporte, Date hastaReporte,
            BigInteger idMoneda, TipoDebeHaber tipoDebeHaber) {
        // TODO Auto-generated method stub
        return null;
    }
*/
}
