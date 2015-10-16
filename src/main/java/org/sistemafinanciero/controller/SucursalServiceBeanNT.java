package org.sistemafinanciero.controller;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;
import javax.inject.Named;

import org.hibernate.Hibernate;
import org.sistemafinanciero.dao.DAO;
import org.sistemafinanciero.dao.QueryParameter;
import org.sistemafinanciero.entity.Agencia;
import org.sistemafinanciero.entity.PersonaNatural;
import org.sistemafinanciero.entity.Sucursal;
import org.sistemafinanciero.entity.TipoDocumento;
import org.sistemafinanciero.service.nt.SucursalServiceNT;

@Named
@Stateless
@Remote(SucursalServiceNT.class)
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class SucursalServiceBeanNT implements SucursalServiceNT {

	@Inject
	private DAO<Object, Sucursal> sucursalDAO;

	@Override
	public Sucursal findById(BigInteger id) {
		return sucursalDAO.find(id);
	}

	@Override
	public List<Sucursal> findAll() {
		return sucursalDAO.findAll();
	}

	@Override
	public int count() {
		return sucursalDAO.count();
	}

	@Override
	public List<Agencia> getAgencias(BigInteger idSucursal) {
		Sucursal sucursal = sucursalDAO.find(idSucursal);
		Set<Agencia> agencias = sucursal.getAgencias();
		return new ArrayList<Agencia>(agencias);
	}

    @Override
    public List<Sucursal> findAll(String filterText, Integer offset, Integer limit) {
        List<Sucursal> result = null;

        if (filterText == null)
            filterText = "";
        if (offset == null) {
            offset = 0;
        }
        offset = Math.abs(offset);
        if (limit != null) {
            limit = Math.abs(limit);
        }

        Integer offSetInteger = offset.intValue();
        Integer limitInteger = (limit != null ? limit.intValue() : null);

        QueryParameter queryParameter = QueryParameter.with("filterText", '%' + filterText.toUpperCase() + '%');
        result = sucursalDAO.findByNamedQuery(Sucursal.FindByFilterText, queryParameter.parameters(), offSetInteger, limitInteger);

        return result;
    }

}
