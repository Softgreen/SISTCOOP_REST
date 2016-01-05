package org.sistemafinanciero.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
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

/**
 * The persistent class for the CUENTA_BANCARIA_VIEW database table.
 * 
 */
@Entity
@Table(name = "UTILIDAD", schema = "C##BDSISTEMAFINANCIERO")
@XmlRootElement(name = "utilidad")
@XmlAccessorType(XmlAccessType.NONE)
@NamedQueries({
		@NamedQuery(name = Utilidad.findByDesdeHasta, query = "SELECT u FROM Utilidad u WHERE u.fecha BETWEEN :desde AND :hasta") })
public class Utilidad implements Serializable {
	private static final long serialVersionUID = 1L;

	public final static String findByDesdeHasta = "Utilidad.findByDesdeHasta";

	private BigInteger idUtilidad;

	private Date fecha;

	private BigDecimal cajaSoles;
	private BigDecimal cajaDolares;
	private BigDecimal cajaEuros;

	private BigDecimal bancosSoles;
	private BigDecimal bancosDolares;
	private BigDecimal bancosEuros;

	private BigDecimal cuentasPorCobrarSoles;
	private BigDecimal cuentasPorCobrarDolares;
	private BigDecimal cuentasPorCobrarEuros;

	private BigDecimal cuentasPorPagarSoles;
	private BigDecimal cuentasPorPagarDolares;
	private BigDecimal cuentasPorPagarEuros;

	private BigDecimal patrimonioSoles;
	private BigDecimal patrimonioDolares;
	private BigDecimal patrimonioEuros;

	private BigDecimal tipoCambioCompraDolares;
	private BigDecimal tipoCambioCompraEuros;
	// private BigDecimal tipoCambioVentaDolares;
	// private BigDecimal tipoCambioVentaEuros;

	private BigDecimal utilidadSoles;
	private BigDecimal utilidadDolares;
	private BigDecimal utilidadEuros;

	private BigDecimal utilidadTotal;

	@XmlElement(name = "id")
	@Id
	@Column(name = "ID_UTILIDAD", unique = true, nullable = false, precision = 22, scale = 0)
	public BigInteger getIdUtilidad() {
		return idUtilidad;
	}

	public void setIdUtilidad(BigInteger idUtilidad) {
		this.idUtilidad = idUtilidad;
	}

	@XmlElement
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "FECHA", nullable = false, length = 7)
	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	@XmlElement
	@Column(name = "CAJA_SOLES", nullable = false, precision = 18)
	public BigDecimal getCajaSoles() {
		return cajaSoles;
	}

	public void setCajaSoles(BigDecimal cajaSoles) {
		this.cajaSoles = cajaSoles;
	}

	@XmlElement
	@Column(name = "CAJA_DOLARES", nullable = false, precision = 18)
	public BigDecimal getCajaDolares() {
		return cajaDolares;
	}

	public void setCajaDolares(BigDecimal cajaDolares) {
		this.cajaDolares = cajaDolares;
	}

	@XmlElement
	@Column(name = "CAJA_EUROS", nullable = false, precision = 18)
	public BigDecimal getCajaEuros() {
		return cajaEuros;
	}

	public void setCajaEuros(BigDecimal cajaEuros) {
		this.cajaEuros = cajaEuros;
	}

	@XmlElement
	@Column(name = "BANCOS_SOLES", nullable = false, precision = 18)
	public BigDecimal getBancosSoles() {
		return bancosSoles;
	}

	public void setBancosSoles(BigDecimal bancosSoles) {
		this.bancosSoles = bancosSoles;
	}

	@XmlElement
	@Column(name = "BANCOS_DOLARES", nullable = false, precision = 18)
	public BigDecimal getBancosDolares() {
		return bancosDolares;
	}

	public void setBancosDolares(BigDecimal bancosDolares) {
		this.bancosDolares = bancosDolares;
	}

	@XmlElement
	@Column(name = "BANCOS_EUROS", nullable = false, precision = 18)
	public BigDecimal getBancosEuros() {
		return bancosEuros;
	}

	public void setBancosEuros(BigDecimal bancosEuros) {
		this.bancosEuros = bancosEuros;
	}

	@XmlElement
	@Column(name = "CUENTAS_POR_COBRAR_SOLES", nullable = false, precision = 18)
	public BigDecimal getCuentasPorCobrarSoles() {
		return cuentasPorCobrarSoles;
	}

	public void setCuentasPorCobrarSoles(BigDecimal cuentasPorCobrarSoles) {
		this.cuentasPorCobrarSoles = cuentasPorCobrarSoles;
	}

	@XmlElement
	@Column(name = "CUENTAS_POR_COBRAR_DOLARES", nullable = false, precision = 18)
	public BigDecimal getCuentasPorCobrarDolares() {
		return cuentasPorCobrarDolares;
	}

	public void setCuentasPorCobrarDolares(BigDecimal cuentasPorCobrarDolares) {
		this.cuentasPorCobrarDolares = cuentasPorCobrarDolares;
	}

	@XmlElement
	@Column(name = "CUENTAS_POR_COBRAR_EUROS", nullable = false, precision = 18)
	public BigDecimal getCuentasPorCobrarEuros() {
		return cuentasPorCobrarEuros;
	}

	public void setCuentasPorCobrarEuros(BigDecimal cuentasPorCobrarEuros) {
		this.cuentasPorCobrarEuros = cuentasPorCobrarEuros;
	}

	@XmlElement
	@Column(name = "CUENTAS_POR_PAGAR_SOLES", nullable = false, precision = 18)
	public BigDecimal getCuentasPorPagarSoles() {
		return cuentasPorPagarSoles;
	}

	public void setCuentasPorPagarSoles(BigDecimal cuentasPorPagarSoles) {
		this.cuentasPorPagarSoles = cuentasPorPagarSoles;
	}

	@XmlElement
	@Column(name = "CUENTAS_POR_PAGAR_DOLARES", nullable = false, precision = 18)
	public BigDecimal getCuentasPorPagarDolares() {
		return cuentasPorPagarDolares;
	}

	public void setCuentasPorPagarDolares(BigDecimal cuentasPorPagarDolares) {
		this.cuentasPorPagarDolares = cuentasPorPagarDolares;
	}

	@XmlElement
	@Column(name = "CUENTAS_POR_PAGAR_EUROS", nullable = false, precision = 18)
	public BigDecimal getCuentasPorPagarEuros() {
		return cuentasPorPagarEuros;
	}

	public void setCuentasPorPagarEuros(BigDecimal cuentasPorPagarEuros) {
		this.cuentasPorPagarEuros = cuentasPorPagarEuros;
	}

	@XmlElement
	@Column(name = "PATRIMONIO_SOLES", nullable = false, precision = 18)
	public BigDecimal getPatrimonioSoles() {
		return patrimonioSoles;
	}

	public void setPatrimonioSoles(BigDecimal patrimonioSoles) {
		this.patrimonioSoles = patrimonioSoles;
	}

	@XmlElement
	@Column(name = "PATRIMONIO_DOLARES", nullable = false, precision = 18)
	public BigDecimal getPatrimonioDolares() {
		return patrimonioDolares;
	}

	public void setPatrimonioDolares(BigDecimal patrimonioDolares) {
		this.patrimonioDolares = patrimonioDolares;
	}

	@XmlElement
	@Column(name = "PATRIMONIO_EUROS", nullable = false, precision = 18)
	public BigDecimal getPatrimonioEuros() {
		return patrimonioEuros;
	}

	public void setPatrimonioEuros(BigDecimal patrimonioEuros) {
		this.patrimonioEuros = patrimonioEuros;
	}

	@XmlElement
	@Column(name = "TIPO_CAMBIO_COMPRA_DOLARES", nullable = false, precision = 18)
	public BigDecimal getTipoCambioCompraDolares() {
		return tipoCambioCompraDolares;
	}

	public void setTipoCambioCompraDolares(BigDecimal tipoCambioCompraDolares) {
		this.tipoCambioCompraDolares = tipoCambioCompraDolares;
	}

	@XmlElement
	@Column(name = "TIPO_CAMBIO_COMPRA_EUROS", nullable = false, precision = 18)
	public BigDecimal getTipoCambioCompraEuros() {
		return tipoCambioCompraEuros;
	}

	public void setTipoCambioCompraEuros(BigDecimal tipoCambioCompraEuros) {
		this.tipoCambioCompraEuros = tipoCambioCompraEuros;
	}

	/*@XmlElement
	@Column(name = "TIPO_CAMBIO_VENTA_DOLARES", nullable = false, precision = 18)
	public BigDecimal getTipoCambioVentaDolares() {
		return tipoCambioVentaDolares;
	}

	public void setTipoCambioVentaDolares(BigDecimal tipoCambioVentaDolares) {
		this.tipoCambioVentaDolares = tipoCambioVentaDolares;
	}

	@XmlElement
	@Column(name = "TIPO_CAMBIO_VENTA_EUROS", nullable = false, precision = 18)
	public BigDecimal getTipoCambioVentaEuros() {
		return tipoCambioVentaEuros;
	}

	public void setTipoCambioVentaEuros(BigDecimal tipoCambioVentaEuros) {
		this.tipoCambioVentaEuros = tipoCambioVentaEuros;
	}*/
	
	@XmlElement
	@Column(name = "UTILIDAD_SOLES", nullable = false, precision = 18)
	public BigDecimal getUtilidadSoles() {
		return utilidadSoles;
	}

	public void setUtilidadSoles(BigDecimal utilidadSoles) {
		this.utilidadSoles = utilidadSoles;
	}

	@XmlElement
	@Column(name = "UTILIDAD_DOLARES", nullable = false, precision = 18)
	public BigDecimal getUtilidadDolares() {
		return utilidadDolares;
	}

	public void setUtilidadDolares(BigDecimal utilidadDolares) {
		this.utilidadDolares = utilidadDolares;
	}

	@XmlElement
	@Column(name = "UTILIDAD_EUROS", nullable = false, precision = 18)
	public BigDecimal getUtilidadEuros() {
		return utilidadEuros;
	}

	public void setUtilidadEuros(BigDecimal utilidadEuros) {
		this.utilidadEuros = utilidadEuros;
	}

	@XmlElement
	@Column(name = "UTILIDAD_TOTAL", nullable = false, precision = 18)
	public BigDecimal getUtilidadTotal() {
		return utilidadTotal;
	}

	public void setUtilidadTotal(BigDecimal utilidadTotal) {
		this.utilidadTotal = utilidadTotal;
	}

}