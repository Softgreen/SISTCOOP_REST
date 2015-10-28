package org.sistemafinanciero.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.sistemafinanciero.entity.Agencia;
import org.sistemafinanciero.entity.Caja;
import org.sistemafinanciero.entity.PersonaNatural;

@Path("/session/account")
public interface SessionAccountREST {

    @GET
    @Path("/agencia")
    @Produces(MediaType.APPLICATION_JSON)
    public Agencia getAgencia();

    @GET
    @Path("/caja")
    @Produces(MediaType.APPLICATION_JSON)
    public Caja getCaja();
    
    @GET
    @Path("/persona")
    @Produces(MediaType.APPLICATION_JSON)
    public PersonaNatural getPersonaNatural();

}