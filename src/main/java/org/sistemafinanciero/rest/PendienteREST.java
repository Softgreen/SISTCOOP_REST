package org.sistemafinanciero.rest;

import java.math.BigInteger;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

@Path("/pendiente")
public interface PendienteREST {

	@GET
	@Produces({ "application/xml", "application/json" })
	public Response findAll();
	
	@GET
	@Path("/reportePendienteCaja")
	@Produces({ "application/xml", "application/json" })
	public Response findAll(@QueryParam("idAgencia") BigInteger idAgencia);

	@GET
	@Path("/{id}")
	@Produces({ "application/xml", "application/json" })
	public Response findById(@PathParam("id") BigInteger id);

	@GET
	@Path("/{id}/voucher")
	@Consumes({ "application/xml", "application/json" })
	@Produces({ "application/xml", "application/json" })
	public Response getVoucherPendienteCaja(@PathParam("id") BigInteger idPendienteCaja);
	
	@GET
	@Path("/historialPendiente")
	@Produces({ "application/xml", "application/json" })
	public Response pendienteHistorial(@QueryParam("desde") Long desde, @QueryParam("hasta") Long hasta, @QueryParam("idMoneda") BigInteger idMoneda);
	
	@GET
	@Path("/pendienteSobrante")
	@Produces({ "application/xml", "application/json" })
	public Response pendienteSobrante(@QueryParam("idMoneda") BigInteger idMoneda);
	
	@GET
	@Path("/pendienteFaltante")
	@Produces({ "application/xml", "application/json" })
	public Response pendienteFaltante(@QueryParam("idMoneda") BigInteger idMoneda);
}
