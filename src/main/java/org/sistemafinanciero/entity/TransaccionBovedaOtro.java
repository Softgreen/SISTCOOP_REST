package org.sistemafinanciero.entity;

// Generated 02-may-2014 11:48:28 by Hibernate Tools 4.0.0

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * TransaccionBovedaOtro generated by hbm2java
 */
@Entity
@Table(name = "TRANSACCION_BOVEDA_OTRO", schema = "C##BDSISTEMAFINANCIERO")
public class TransaccionBovedaOtro implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private BigInteger idTransaccionBovedaOtro;
	private Entidad entidad;
	private HistorialBoveda historialBoveda;
	private Date fecha;
	private Date hora;
	private BigDecimal saldoDisponible;
	private String observacion;
	private int estado;
	private String tipoTransaccion;
	private Set transaccionBovedaOtroDetalls = new HashSet(0);

	public TransaccionBovedaOtro() {
	}

	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="TRANSBOVEDAOTRO_SEQ")
	@SequenceGenerator(name="TRANSBOVEDAOTRO_SEQ", initialValue=1, allocationSize=1, sequenceName="TRANSBOVEDAOTRO_SEQ")
	@Id
	@Column(name = "ID_TRANSACCION_BOVEDA_OTRO", unique = true, nullable = false, precision = 22, scale = 0)
	public BigInteger getIdTransaccionBovedaOtro() {
		return this.idTransaccionBovedaOtro;
	}

	public void setIdTransaccionBovedaOtro(BigInteger idTransaccionBovedaOtro) {
		this.idTransaccionBovedaOtro = idTransaccionBovedaOtro;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_ENTIDAD", nullable = false)
	public Entidad getEntidad() {
		return this.entidad;
	}

	public void setEntidad(Entidad entidad) {
		this.entidad = entidad;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_HISTORIAL_BOVEDA", nullable = false)
	public HistorialBoveda getHistorialBoveda() {
		return this.historialBoveda;
	}

	public void setHistorialBoveda(HistorialBoveda historialBoveda) {
		this.historialBoveda = historialBoveda;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "FECHA", nullable = false, length = 7)
	public Date getFecha() {
		return this.fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	@Column(name = "HORA", nullable = false)
	public Date getHora() {
		return this.hora;
	}

	public void setHora(Date hora) {
		this.hora = hora;
	}

	@Column(name = "SALDO_DISPONIBLE", nullable = false, precision = 18)
	public BigDecimal getSaldoDisponible() {
		return this.saldoDisponible;
	}

	public void setSaldoDisponible(BigDecimal saldoDisponible) {
		this.saldoDisponible = saldoDisponible;
	}

	@Column(name = "OBSERVACION", length = 140, columnDefinition = "nvarchar2")
	public String getObservacion() {
		return this.observacion;
	}

	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}

	@Column(name = "ESTADO", nullable = false, precision = 22, scale = 0)
	public boolean getEstado() {
		return this.estado == 1;
	}

	public void setEstado(boolean estado) {
		this.estado = (estado == true ? 1 : 0);
	}

	@Column(name = "TIPO_TRANSACCION", nullable = false, length = 40, columnDefinition = "nvarchar2")
	public String getTipoTransaccion() {
		return this.tipoTransaccion;
	}

	public void setTipoTransaccion(String tipoTransaccion) {
		this.tipoTransaccion = tipoTransaccion;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "transaccionBovedaOtro")
	public Set<TransaccionBovedaOtroDetall> getTransaccionBovedaOtroDetalls() {
		return this.transaccionBovedaOtroDetalls;
	}

	public void setTransaccionBovedaOtroDetalls(Set transaccionBovedaOtroDetalls) {
		this.transaccionBovedaOtroDetalls = transaccionBovedaOtroDetalls;
	}

}
