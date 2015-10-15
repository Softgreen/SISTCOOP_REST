package org.sistemafinanciero.entity;

// Generated 02-may-2014 11:48:28 by Hibernate Tools 4.0.0

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import com.sun.org.apache.xpath.internal.operations.Bool;

/**
 * Titular generated by hbm2java
 */
@Entity
@Table(name = "TITULAR", schema = "C##BDSISTEMAFINANCIERO")
@XmlRootElement(name = "titular")
@XmlAccessorType(XmlAccessType.NONE)
public class Titular implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private BigInteger idTitular;
	private PersonaNatural personaNatural;
	private CuentaBancaria cuentaBancaria;
	private Date fechaInicio;
	private Date fechaFin;
	private int estado;

	public Titular() {
	}

	public Titular(BigInteger idTitular, PersonaNatural personaNatural,
			CuentaBancaria cuentaBancaria, Date fechaInicio, boolean estado) {
		this.idTitular = idTitular;
		this.personaNatural = personaNatural;
		this.cuentaBancaria = cuentaBancaria;
		this.fechaInicio = fechaInicio;
		this.estado = (estado ? 1 : 0);
	}

	public Titular(BigInteger idTitular, PersonaNatural personaNatural,
			CuentaBancaria cuentaBancaria, Date fechaInicio, Date fechaFin,
			boolean estado) {
		this.idTitular = idTitular;
		this.personaNatural = personaNatural;
		this.cuentaBancaria = cuentaBancaria;
		this.fechaInicio = fechaInicio;
		this.fechaFin = fechaFin;
		this.estado = (estado ? 1 : 0);
	}

	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="TITULAR_SEQ")
	@SequenceGenerator(name="TITULAR_SEQ", initialValue=1, allocationSize=1, sequenceName="TITULAR_SEQ")
	@XmlElement(name = "id")
	@Id
	@Column(name = "ID_TITULAR", unique = true, nullable = false, precision = 22, scale = 0)
	public BigInteger getIdTitular() {
		return this.idTitular;
	}

	public void setIdTitular(BigInteger idTitular) {
		this.idTitular = idTitular;
	}

	@XmlElement
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_PERSONA_NATURAL", nullable = false)
	public PersonaNatural getPersonaNatural() {
		return this.personaNatural;
	}

	public void setPersonaNatural(PersonaNatural personaNatural) {
		this.personaNatural = personaNatural;
	}

	@XmlTransient
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_CUENTA_BANCARIA", nullable = false)
	public CuentaBancaria getCuentaBancaria() {
		return this.cuentaBancaria;
	}

	public void setCuentaBancaria(CuentaBancaria cuentaBancaria) {
		this.cuentaBancaria = cuentaBancaria;
	}

	@XmlElement
	@Temporal(TemporalType.DATE)
	@Column(name = "FECHA_INICIO", nullable = false, length = 7)
	public Date getFechaInicio() {
		return this.fechaInicio;
	}

	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	@XmlElement
	@Temporal(TemporalType.DATE)
	@Column(name = "FECHA_FIN", length = 7)
	public Date getFechaFin() {
		return this.fechaFin;
	}

	public void setFechaFin(Date fechaFin) {
		this.fechaFin = fechaFin;
	}

	@XmlElement
	@Column(name = "ESTADO", nullable = false, precision = 22, scale = 0)
	public boolean getEstado() {
		return (this.estado == 1 ? true : false);
	}

	public void setEstado(boolean estado) {
		this.estado = (estado ? 1 : 0);
	}

	@Override
	public boolean equals(Object obj) {
		if ((obj == null) || !(obj instanceof Titular)) {
			return false;
		}
		final Titular other = (Titular) obj;
		return other.getPersonaNatural().equals(this.personaNatural);
	}

	@Override
	public int hashCode() {
		if(this.personaNatural != null)		
			return this.personaNatural.hashCode();
		else 
			return 0;
	}
}
