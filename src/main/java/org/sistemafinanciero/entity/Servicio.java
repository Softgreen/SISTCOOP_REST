package org.sistemafinanciero.entity;

// Generated 02-may-2014 11:48:28 by Hibernate Tools 4.0.0

import java.math.BigInteger;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 * Moneda generated by hbm2java
 */
@Entity
@Table(name = "SERVICIO", schema = "C##BDSISTEMAFINANCIERO")
@XmlRootElement(name = "tipoServicio")
@XmlAccessorType(XmlAccessType.NONE)
public class Servicio implements java.io.Serializable {

	private BigInteger idServicio;
	private String denominacion;
	private String descripcion;
	private TipoServicio tipoServicio;
	private Set valorTasasInteres = new HashSet(0);

	public Servicio() {
	}	

	@XmlElement(name="id")
	@Id
	@Column(name = "ID_SERVICIO", unique = true, nullable = false, precision = 22, scale = 0)
	public BigInteger getIdServicio() {
		return this.idServicio;
	}

	public void setIdServicio(BigInteger idServicio) {
		this.idServicio = idServicio;
	}

	@XmlElement
	@Column(name = "DENOMINACION", length = 30, columnDefinition = "nvarchar2")
	public String getDenominacion() {
		return this.denominacion;
	}

	public void setDenominacion(String denominacion) {
		this.denominacion = denominacion;
	}

	@XmlElement
	@Column(name = "DESCRIPCION", length = 50, columnDefinition = "nvarchar2")
	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}	

	@XmlTransient
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_TIPO_SERVICIO", nullable = false)
	public TipoServicio getTipoServicio() {
		return this.tipoServicio;
	}

	public void setTipoServicio(TipoServicio tipoServicio) {
		this.tipoServicio = tipoServicio;
	}
	
	@XmlTransient
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "tasaInteres")
	public Set<ValorTasaInteres> getValorTasasInteres() {
		return this.valorTasasInteres;
	}

	public void setValorTasasInteres(Set valorTasasInteres) {
		this.valorTasasInteres = valorTasasInteres;
	}
	
}
