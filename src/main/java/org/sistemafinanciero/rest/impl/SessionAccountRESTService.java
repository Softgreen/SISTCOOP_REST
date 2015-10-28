/*
 * JBoss, Home of Professional Open Source
 * Copyright 2013, Red Hat, Inc. and/or its affiliates, and individual
 * contributors by the @authors tag. See the copyright.txt in the
 * distribution for a full listing of individual contributors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.sistemafinanciero.rest.impl;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.core.Context;

import org.jboss.resteasy.spi.HttpRequest;
import org.keycloak.KeycloakSecurityContext;
import org.keycloak.representations.AccessToken;
import org.sistemafinanciero.entity.Agencia;
import org.sistemafinanciero.entity.Caja;
import org.sistemafinanciero.entity.PersonaNatural;
import org.sistemafinanciero.entity.Trabajador;
import org.sistemafinanciero.exception.NonexistentEntityException;
import org.sistemafinanciero.rest.SessionAccountREST;
import org.sistemafinanciero.service.nt.AgenciaServiceNT;
import org.sistemafinanciero.service.nt.PersonaNaturalServiceNT;
import org.sistemafinanciero.service.nt.TrabajadorServiceNT;

@Stateless
public class SessionAccountRESTService implements SessionAccountREST {

    @EJB
    private AgenciaServiceNT agenciaServiceNT;
    
    @EJB
    private PersonaNaturalServiceNT personaNaturalServiceNT;

    @EJB
    private TrabajadorServiceNT trabajadorServiceNT;

    @Context
    private HttpRequest httpRequest;

    @Override
    public Agencia getAgencia() {
        // Just to show how to user info from access token in REST endpoint
        KeycloakSecurityContext securityContext = (KeycloakSecurityContext) httpRequest
                .getAttribute(KeycloakSecurityContext.class.getName());
        AccessToken accessToken = securityContext.getToken();

        // Obtener Usuario
        String usuario = accessToken.getPreferredUsername();
        Trabajador trabajador = trabajadorServiceNT.findByUsuario(usuario);
        if (trabajador != null) {
            Agencia agencia = trabajador.getAgencia();
            if (agencia != null) {
                return agenciaServiceNT.findById(agencia.getIdAgencia());
            } else {
                return null;
            }
        } else {
            return null;
        }
    }

    @Override
    public PersonaNatural getPersonaNatural() {
        // Just to show how to user info from access token in REST endpoint
        KeycloakSecurityContext securityContext = (KeycloakSecurityContext) httpRequest
                .getAttribute(KeycloakSecurityContext.class.getName());
        AccessToken accessToken = securityContext.getToken();

        // Obtener Usuario
        String usuario = accessToken.getPreferredUsername();
        Trabajador trabajador = trabajadorServiceNT.findByUsuario(usuario);
        if (trabajador != null) {
            PersonaNatural personaNatural = trabajador.getPersonaNatural();            
            return personaNaturalServiceNT.findById(personaNatural.getIdPersonaNatural());
        } else {
            return null;
        }
    }

    @Override
    public Caja getCaja() {
        // Just to show how to user info from access token in REST endpoint
        KeycloakSecurityContext securityContext = (KeycloakSecurityContext) httpRequest
                .getAttribute(KeycloakSecurityContext.class.getName());
        AccessToken accessToken = securityContext.getToken();

        // Obtener Usuario
        String usuario = accessToken.getPreferredUsername();
        Trabajador trabajador = trabajadorServiceNT.findByUsuario(usuario);
        try {
            if (trabajador != null) {
                Caja caja = trabajadorServiceNT.findByTrabajador(trabajador.getIdTrabajador());
                return caja;
            } else {
                return null;
            }
        } catch (NonexistentEntityException e) {
            throw new NotFoundException();
        }
    }

}
