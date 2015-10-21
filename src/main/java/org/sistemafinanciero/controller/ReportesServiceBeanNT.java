package org.sistemafinanciero.controller;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.sistemafinanciero.dao.DAO;
import org.sistemafinanciero.dao.QueryParameter;
import org.sistemafinanciero.entity.DebeHaber;
import org.sistemafinanciero.entity.type.TipoDebeHaber;
import org.sistemafinanciero.service.nt.ReportesServiceNT;
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
    public List<DebeHaber> getDebeHaberHistorialTotal(Date desdeReporte, Date hastaReporte,
            BigInteger idMoneda, TipoDebeHaber tipoDebeHaber) {
        // TODO Auto-generated method stub
        return null;
    }

}
