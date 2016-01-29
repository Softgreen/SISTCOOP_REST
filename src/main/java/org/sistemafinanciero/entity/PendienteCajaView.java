package org.sistemafinanciero.entity;

// Generated 02-may-2014 11:48:28 by Hibernate Tools 4.0.0

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.sistemafinanciero.entity.type.TipoPendienteCaja;

/**
 * PendienteCajaView generated by hbm2java
 */
@Entity
@Table(name = "PENDIENTES_CAJA_VIEW", schema = "C##BDSISTEMAFINANCIERO")
@XmlRootElement(name = "pendientecajaview")
@XmlAccessorType(XmlAccessType.NONE)
@NamedQueries({@NamedQuery(name = PendienteCajaView.findByIdAgencia, query = "SELECT pc FROM PendienteCajaView pc WHERE pc.idAgencia = :idAgencia ORDER BY pc.fecha, pc.hora")})
public class PendienteCajaView implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	
	public final static String findByIdAgencia = "PendienteCajaView.findByIdAgencia";

	private BigInteger idPendienteCaja;
	private Date fecha;
	private Date hora;
	private BigDecimal monto;
	private TipoPendienteCaja tipoPendiente;
	private String trabajadorCrea;
	private String observacion;
	private String denominacionCaja;
	private String abreviaturaCaja;
	private BigInteger idMoneda;
	private BigInteger idAgencia;
	private String denominacionMoneda;
	private String simboloMoneda;

	public PendienteCajaView() {

	}

	@XmlElement(name = "id")
	@Id
	@Column(name = "ID_PENDIENTE_CAJA", unique = true, nullable = false, precision = 22, scale = 0)
	public BigInteger getIdPendienteCaja() {
		return idPendienteCaja;
	}

	public void setIdPendienteCaja(BigInteger idPendienteCaja) {
		this.idPendienteCaja = idPendienteCaja;
	}

	@XmlElement
	@Temporal(TemporalType.DATE)
	@Column(name = "FECHA", nullable = false, length = 7)
	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	@XmlElement
	@Column(name = "HORA", nullable = false)
	public Date getHora() {
		return hora;
	}

	public void setHora(Date hora) {
		this.hora = hora;
	}

	@XmlElement(name = "monto")
	@Column(name = "MONTO")
	public BigDecimal getMonto() {
		return monto;
	}

	public void setMonto(BigDecimal monto) {
		this.monto = monto;
	}

	@XmlElement
    @Enumerated(value = EnumType.STRING)
    @Column(name = "TIPO_PENDIENTE", nullable = false, length = 40, columnDefinition = "nvarchar2")
	public TipoPendienteCaja getTipoPendiente() {
		return tipoPendiente;
	}

	public void setTipoPendiente(TipoPendienteCaja tipoPendiente) {
		this.tipoPendiente = tipoPendiente;
	}

	@XmlElement(name = "trabajadorCrea")
	@Column(name = "TRABAJADOR_CREA", columnDefinition = "nvarchar2")
	public String getTrabajadorCrea() {
		return trabajadorCrea;
	}

	public void setTrabajadorCrea(String trabajadorCrea) {
		this.trabajadorCrea = trabajadorCrea;
	}

	@XmlElement(name = "observacion")
	@Column(name = "OBSERVACION", columnDefinition = "nvarchar2")
	public String getObservacion() {
		return observacion;
	}

	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}

	@XmlElement(name = "denominacionCaja")
	@Column(name = "DENOMINACION_CAJA", columnDefinition = "nvarchar2")
	public String getDenominacionCaja() {
		return denominacionCaja;
	}

	public void setDenominacionCaja(String denominacionCaja) {
		this.denominacionCaja = denominacionCaja;
	}

	@XmlElement(name = "abreviaturaCaja")
	@Column(name = "ABREVIATURA_CAJA", columnDefinition = "nvarchar2")
	public String getAbreviaturaCaja() {
		return abreviaturaCaja;
	}

	public void setAbreviaturaCaja(String abreviaturaCaja) {
		this.abreviaturaCaja = abreviaturaCaja;
	}

	@XmlElement(name="idMoneda")
	@Column(name = "ID_MONEDA")
	public BigInteger getIdMoneda() {
		return idMoneda;
	}

	public void setIdMoneda(BigInteger idMoneda) {
		this.idMoneda = idMoneda;
	}

	@XmlElement(name="idAgencia")
	@Column(name = "ID_AGENCIA")
	public BigInteger getIdAgencia() {
		return idAgencia;
	}

	public void setIdAgencia(BigInteger idAgencia) {
		this.idAgencia = idAgencia;
	}

	@XmlElement(name = "denominacionMoneda")
	@Column(name = "DENOMINACION_MONEDA", columnDefinition = "nvarchar2")
	public String getDenominacionMoneda() {
		return denominacionMoneda;
	}

	public void setDenominacionMoneda(String denominacionMoneda) {
		this.denominacionMoneda = denominacionMoneda;
	}

	@XmlElement(name = "simboloMoneda")
	@Column(name = "SIMBOLO_MONEDA", columnDefinition = "nvarchar2")
	public String getSimboloMoneda() {
		return simboloMoneda;
	}

	public void setSimboloMoneda(String simboloMoneda) {
		this.simboloMoneda = simboloMoneda;
	}

}
