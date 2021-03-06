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

import java.math.BigDecimal;
import java.math.BigInteger;

import javax.ejb.EJB;
import javax.ws.rs.core.Response;

import org.sistemafinanciero.rest.VariableSistemaREST;
import org.sistemafinanciero.service.nt.VariableSistemaServiceNT;

public class VariableSistemaRESTService implements VariableSistemaREST {

	@EJB
	private VariableSistemaServiceNT variableSistemaServiceNT;
	
	@Override
	public Response findVariable(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Response findTasaByMoneda(BigInteger idMonedaRecibida,
			BigInteger idMonedaEntregada) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Response findMayorCuantia(BigInteger idMoneda) {
		BigDecimal valor = variableSistemaServiceNT.getMayorCuantia(idMoneda);
		Response response = Response.status(Response.Status.OK).entity(valor).build();
		return response;		
	}

}
