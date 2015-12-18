package org.sistemafinanciero.rest;

import java.math.BigInteger;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

@Path("/reporteCajaBancos")
public interface ReporteCajaBancosRest {

	@GET
    @Path("/montoCajaPorAgencias")
    @Produces({ "application/xml", "application/json" })
    public Response reporteCajaPorAgencias(@QueryParam("idmoneda") BigInteger idMoneda,
            @QueryParam("idagencia") BigInteger idAgencia);
	
	@GET
    @Path("/montoTotalCaja")
    @Produces({ "application/xml", "application/json" })
    public Response reporteTotalCaja(@QueryParam("idmoneda") BigInteger idMoneda);
	
	@GET
    @Path("/bancos")
    @Produces({ "application/xml", "application/json" })
    public Response getTotalBancos(@QueryParam("idMoneda") BigInteger idMoneda);
	
	/*
    @GET
    @Path("/debeHaber")
    @Produces({ "application/xml", "application/json" })
    public Response reporteDebeHaber(@QueryParam("fecha") Long fecha,
            @QueryParam("tipo") TipoDebeHaber tipoDebeHaber, @QueryParam("idMoneda") BigInteger idMoneda);

    @GET
    @Path("/debeHaber/pdf")
    @Produces({ "application/xml", "application/json" })
    public Response reporteDebeHaberPdf(@QueryParam("fecha") Long fecha);

    @GET
    @Path("/debeHaber/total")
    @Produces({ "application/xml", "application/json" })
    public Response reporteDebeHaberTotales(@QueryParam("fecha") Long fecha,
            @QueryParam("tipo") TipoDebeHaber tipoDebeHaber, @QueryParam("idMoneda") BigInteger idMoneda);

    @GET
    @Path("/debeHaber/historial")
    @Produces({ "application/xml", "application/json" })
    public Response reporteDebeHaberHistorialTotales(@QueryParam("fecha") Long desde,
            @QueryParam("fecha") Long hasta, @QueryParam("tipo") TipoDebeHaber tipoDebeHaber,
            @QueryParam("idMoneda") BigInteger idMoneda);
            
	*/
	
}
