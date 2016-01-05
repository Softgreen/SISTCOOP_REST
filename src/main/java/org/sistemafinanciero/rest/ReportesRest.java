package org.sistemafinanciero.rest;

import java.math.BigInteger;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

import org.sistemafinanciero.entity.type.TipoDebeHaber;

@Path("/reportes")
public interface ReportesRest {

	@GET
	@Path("/debeHaber")
	@Produces({ "application/xml", "application/json" })
	public Response reporteDebeHaber(@QueryParam("fecha") Long fecha, @QueryParam("tipo") TipoDebeHaber tipoDebeHaber,
			@QueryParam("idMoneda") BigInteger idMoneda);

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
	public Response reporteDebeHaberHistorialTotales(@QueryParam("desde") Long desde, @QueryParam("hasta") Long hasta,
			@QueryParam("tipo") TipoDebeHaber tipoDebeHaber, @QueryParam("idMoneda") BigInteger idMoneda);

	@GET
	@Path("cuentasPorCobrar")
	@Produces({ "application/xml", "application/json" })
	public Response reporteCuentasPorCobrar(@QueryParam("idMoneda") BigInteger idMoneda,
			@QueryParam("fecha") Long fecha);

	@GET
	@Path("cuentasPorPagar")
	@Produces({ "application/xml", "application/json" })
	public Response reporteCuentasPorPagar(@QueryParam("idMoneda") BigInteger idMoneda,
			@QueryParam("fecha") Long fecha);

	@GET
	@Path("patrimonio")
	@Produces({ "application/xml", "application/json" })
	public Response reportePatrimonio(@QueryParam("idMoneda") BigInteger idMoneda, @QueryParam("fecha") Long fecha);

	@GET
	@Path("utilidad")
	@Produces({ "application/xml", "application/json" })
	public Response reporteUtilidad(@QueryParam("idMoneda") BigInteger idMoneda, @QueryParam("fecha") Long fecha);

	@GET
	@Path("/utilidad/historial")
	@Produces({ "application/xml", "application/json" })
	public Response reporteUtilidadHistorial(@QueryParam("desde") Long desde, @QueryParam("hasta") Long hasta);

}