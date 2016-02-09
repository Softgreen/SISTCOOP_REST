package org.sistemafinanciero.controller;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.Query;

import org.hibernate.Hibernate;
import org.sistemafinanciero.dao.DAO;
import org.sistemafinanciero.dao.QueryParameter;
import org.sistemafinanciero.entity.Agencia;
import org.sistemafinanciero.entity.BovedaCaja;
import org.sistemafinanciero.entity.Caja;
import org.sistemafinanciero.entity.Moneda;
import org.sistemafinanciero.entity.PendienteCaja;
import org.sistemafinanciero.entity.PendienteCajaView;
import org.sistemafinanciero.entity.dto.VoucherPendienteCaja;
import org.sistemafinanciero.service.nt.PendienteServiceNT;
import org.sistemafinanciero.util.DateUtils;
import org.sistemafinanciero.util.EntityManagerProducer;

@Named
@Stateless
@Remote(PendienteServiceNT.class)
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class PendienteServiceBeanNT implements PendienteServiceNT {

    @Inject
    private DAO<Object, PendienteCaja> pendienteCajaDAO;
    
    @Inject
    private DAO<Object, PendienteCajaView> pendienteCajaViewDAO;
    
    @Inject
	private EntityManagerProducer em;
    
    @Override
	public List<PendienteCajaView> findAllView(BigInteger idAgencia) {
		List<PendienteCajaView> list = null;
		
		if (idAgencia == null) {
            list = pendienteCajaViewDAO.findAll();
        } else {
            QueryParameter queryParameter = QueryParameter.with("idAgencia", idAgencia);
            List<PendienteCajaView> a = pendienteCajaViewDAO.findByNamedQuery(PendienteCajaView.findByIdAgencia, queryParameter.parameters());
            
            list = new ArrayList<PendienteCajaView>(a);
        }
        return list;
	}

    @Override
    public VoucherPendienteCaja getVoucherPendienteCaja(BigInteger idPendienteCaja) {
        VoucherPendienteCaja voucherPendienteCaja = new VoucherPendienteCaja();

        // recuperando pendiente
        PendienteCaja pendientecaja = pendienteCajaDAO.find(idPendienteCaja);
        Caja caja = pendientecaja.getHistorialCajaCreacion().getCaja();
        Set<BovedaCaja> list = caja.getBovedaCajas();
        Agencia agencia = null;
        for (BovedaCaja bovedaCaja : list) {
            agencia = bovedaCaja.getBoveda().getAgencia();
            break;
        }
        // poniendo los datos del pendiente
        voucherPendienteCaja.setAgenciaDenominacion(agencia.getDenominacion());
        voucherPendienteCaja.setAgenciaAbreviatura(agencia.getAbreviatura());
        voucherPendienteCaja.setCajaDenominacion(caja.getDenominacion());
        voucherPendienteCaja.setCajaAbreviatura(caja.getAbreviatura());
        voucherPendienteCaja.setIdPendienteCaja(idPendienteCaja);
        voucherPendienteCaja.setMonto(pendientecaja.getMonto());
        voucherPendienteCaja.setObservacion(pendientecaja.getObservacion());
        voucherPendienteCaja.setFecha(pendientecaja.getFecha());
        voucherPendienteCaja.setHora(pendientecaja.getHora());
        voucherPendienteCaja.setTipoPendiente(pendientecaja.getTipoPendiente());
        voucherPendienteCaja.setTrabajadorCrea(pendientecaja.getTrabajadorCrea());

        Moneda moneda = pendientecaja.getMoneda();
        Hibernate.initialize(moneda);

        voucherPendienteCaja.setMoneda(moneda);

        return voucherPendienteCaja;
    }

    @Override
    public PendienteCaja findById(BigInteger id) {
        PendienteCaja pendiente = pendienteCajaDAO.find(id);
        Moneda moneda = pendiente.getMoneda();
        Hibernate.initialize(moneda);
        return pendiente;
    }

    @Override
    public List<PendienteCaja> findAll() {
        return pendienteCajaDAO.findAll();
    }

    @Override
    public int count() {
        return pendienteCajaDAO.count();
    }

	@Override
	public List<PendienteCajaView> getPendienteHistorial(Date desdeReporte,
			Date hastaReporte, BigInteger idMoneda) {
		
		Date desde = DateUtils.getDateIn00Time(desdeReporte);
		Date hasta = DateUtils.getDateIn00Time(DateUtils.sumarRestarDiasFecha(hastaReporte, 1));

		QueryParameter queryParameter = QueryParameter.with("desde", desde).and("hasta", hasta).and("idMoneda", idMoneda);
		List<PendienteCajaView> pendientes = pendienteCajaViewDAO.findByNamedQuery(PendienteCajaView.findByDesdeHasta,
				queryParameter.parameters());
		
		return pendientes;
	}

	@Override
	public BigDecimal getPendienteSobrante(BigInteger idMoneda) {
		Query query = em.getEm().createQuery(("SELECT SUM(pcv.monto) FROM PendienteCajaView pcv WHERE pcv.tipoPendiente = 'SOBRANTE' AND pcv.idMoneda = :idMoneda"));
		query.setParameter("idMoneda", idMoneda);
		Object obj1 = query.getSingleResult();

		return obj1 != null ? ((BigDecimal) obj1).abs() : BigDecimal.ZERO;
	}

	@Override
	public BigDecimal getPendienteFaltante(BigInteger idMoneda) {
		Query query = em.getEm().createQuery(("SELECT SUM(pcv.monto) FROM PendienteCajaView pcv WHERE pcv.tipoPendiente = 'FALTANTE' AND pcv.idMoneda = :idMoneda"));
		query.setParameter("idMoneda", idMoneda);
		Object obj1 = query.getSingleResult();

		return obj1 != null ? ((BigDecimal) obj1) : BigDecimal.ZERO;
	}
}
