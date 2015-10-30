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
	public BigDecimal getReporteCajaPorAgenciaMoneda(BigInteger idMoneda, BigInteger idAgencia) {
		Query query = em.getEm().createQuery("SELECT sum(DISTINCT bc.saldo) + sum(DISTINCT dhb.cantidad*md.valor) FROM BOVEDA_CAJA bc INNER JOIN bc.boveda b INNER JOIN b.historial_boveda hb INNER JOIN hb.detalle_hitorial_boveda dhb INNER JOIN dhb.moneda_denominacion md WHERE d.id_agencia = :idagencia and b.id_moneda = :idmoneda and hb.estado = :estado");
		query.setParameter("idmoneda", idMoneda);
		query.setParameter("idagencia", idAgencia);
		query.setParameter("estado", 1);
		
		return (BigDecimal) query.getSingleResult();
	}

	@Override
	public BigDecimal getReporteBancosPorAgenciaMoneda(BigInteger idMoneda,
			BigInteger idAgencia) {
		// TODO Auto-generated method stub
		return null;
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
