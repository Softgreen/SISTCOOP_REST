package org.sistemafinanciero.rest.impl;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ws.rs.core.Response;

import org.sistemafinanciero.entity.PendienteCaja;
import org.sistemafinanciero.entity.PendienteCajaView;
import org.sistemafinanciero.entity.dto.VoucherPendienteCaja;
import org.sistemafinanciero.rest.PendienteREST;
import org.sistemafinanciero.service.nt.PendienteServiceNT;

public class PendienteRESTService implements PendienteREST {

	@EJB
	private PendienteServiceNT pendienteServiceNT;

	@Override
	public Response findAll() {
		List<PendienteCaja> list = pendienteServiceNT.findAll();
		Response response = Response.status(Response.Status.OK).entity(list).build();
		return response;
	}
	
	@Override
	public Response findAll(BigInteger idAgencia) {
		List<PendienteCajaView> list = pendienteServiceNT.findAllView(idAgencia);
		Response response = Response.status(Response.Status.OK).entity(list).build();
		return response;
	}

	@Override
	public Response findById(BigInteger id) {
		PendienteCaja pendiente = pendienteServiceNT.findById(id);
		Response response = Response.status(Response.Status.OK).entity(pendiente).build();
		return response;
	}

	@Override
	public Response getVoucherPendienteCaja(BigInteger idPendienteCaja) {
		VoucherPendienteCaja voucher = pendienteServiceNT.getVoucherPendienteCaja(idPendienteCaja);
		Response response = Response.status(Response.Status.OK).entity(voucher).build();
		return response;
	}

	@Override
	public Response pendienteHistorial(Long desde, Long hasta, BigInteger idMoneda) {
		Date desdeReporte = new Date(desde);
		Date hastaReporte = new Date(hasta);

		List<PendienteCajaView> result = pendienteServiceNT.getPendienteHistorial(desdeReporte, hastaReporte, idMoneda);
		Response response = Response.status(Response.Status.OK).entity(result).build();
		return response;
	}
	
	@Override
	public Response pendienteSobrante(BigInteger idMoneda) {
		BigDecimal result = pendienteServiceNT.getPendienteSobrante(idMoneda);
		Response response = Response.status(Response.Status.OK).entity(result).build();
		return response;
	}

	@Override
	public Response pendienteFaltante(BigInteger idMoneda) {
		BigDecimal result = pendienteServiceNT.getPendienteFaltante(idMoneda);
		Response response = Response.status(Response.Status.OK).entity(result).build();
		return response;
	}
}
