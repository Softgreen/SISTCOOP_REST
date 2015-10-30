package org.sistemafinanciero.rest.impl;

import java.math.BigDecimal;
import java.math.BigInteger;

import javax.ejb.EJB;
import javax.ws.rs.core.Response;

import org.sistemafinanciero.rest.ReporteCajaBancosRest;
import org.sistemafinanciero.service.nt.ReporteCajaBancosServiceNT;

public class ReporteCajaBancosRestService implements ReporteCajaBancosRest {

    @EJB
    private ReporteCajaBancosServiceNT reportesCajaBancosServiceNT;

	@Override
	public Response reporteCaja(BigInteger idMoneda, BigInteger idAgencia) {
		BigDecimal result = reportesCajaBancosServiceNT.getReporteCajaPorAgenciaMoneda(idMoneda, idAgencia);
		Response response = Response.status(Response.Status.OK).entity(result).build();
		return response;
	}

	@Override
	public Response reporteBancos(BigInteger idMoneda, BigInteger idAgencia) {
		BigDecimal result = reportesCajaBancosServiceNT.getReporteBancosPorAgenciaMoneda(idMoneda, idAgencia);
		Response response = Response.status(Response.Status.OK).entity(result).build();
		return response;
	}

    
    
	/*
    @Override
    public Response reporteDebeHaberTotales(Long fecha, TipoDebeHaber tipoDebeHaber, BigInteger idMoneda) {
        Date fechaReporte;
        if (fecha == null) {
            fechaReporte = Calendar.getInstance().getTime();
        } else {
            fechaReporte = new Date(fecha);
        }
        BigDecimal result = reportesServiceNT.getDebeHaberTotal(fechaReporte, idMoneda, tipoDebeHaber);
        Response response = Response.status(Response.Status.OK).entity(result).build();
        return response;
    }

    @Override
    public Response reporteDebeHaberHistorialTotales(Long desde, Long hasta, TipoDebeHaber tipoDebeHaber,
            BigInteger idMoneda) {
        Date desdeReporte = new Date(desde);
        Date hastaReporte = new Date(hasta);

        List<DebeHaber> result = reportesServiceNT.getDebeHaberHistorialTotal(desdeReporte, hastaReporte,
                idMoneda, tipoDebeHaber);
        Response response = Response.status(Response.Status.OK).entity(result).build();
        return response;
    }*/
}
