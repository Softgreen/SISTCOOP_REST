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
		
		if (montoBovedas == null) {
			montoBovedas = new BigDecimal(0);
		}
		
		if (montoCajas == null) {
			montoCajas = new BigDecimal(0);
		}
		
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

}
