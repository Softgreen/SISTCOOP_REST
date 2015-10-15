package org.sistemafinanciero.entity;

// Generated 02-may-2014 11:48:28 by Hibernate Tools 4.0.0

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import org.sistemafinanciero.entity.type.EstadoSobreGiro;

/**
 * Accionista generated by hbm2java
 */
@Entity
@Table(name = "SOBRE_GIRO", schema = "C##BDSISTEMAFINANCIERO")
@XmlRootElement(name = "sobreGiro")
@XmlAccessorType(XmlAccessType.NONE)
@NamedQueries({
        @NamedQuery(name = SobreGiro.FindAll, query = "SELECT p FROM SobreGiro p ORDER BY p.estado"),
        @NamedQuery(name = SobreGiro.FindByFilterTextPN, query = "SELECT s FROM SobreGiro s INNER JOIN s.socio so INNER JOIN so.personaNatural p WHERE s.estado IN (:estados) AND ( p.numeroDocumento LIKE :filterText OR UPPER(CONCAT(p.apellidoPaterno,' ', p.apellidoMaterno,' ',p.nombres)) LIKE :filterText)"),
        @NamedQuery(name = SobreGiro.FindByFilterTextPJ, query = "SELECT s FROM SobreGiro s INNER JOIN s.socio so INNER JOIN so.personaJuridica p WHERE s.estado IN (:estados) AND ( p.numeroDocumento LIKE :filterText OR UPPER(p.razonSocial) LIKE :filterText)") })
public class SobreGiro implements java.io.Serializable {

    private static final long serialVersionUID = 1L;
    public final static String FindAll = "SobreGiro.FindAll";
    public final static String FindByFilterTextPN = "SobreGiro.FindByFilterTextPN";
    public final static String FindByFilterTextPJ = "SobreGiro.FindByFilterTextPJ";

    private BigInteger idSobreGiro;

    private Socio socio;

    private Moneda moneda;
    private BigDecimal monto;
    private BigDecimal interes;

    private Date fechaCreacion;
    private Date fechaLimitePago;

    private EstadoSobreGiro estado;

    private Set historialPagos = new HashSet(0);
    
    public SobreGiro() {

    }

    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SOBREGIRO_SEQ")
    @SequenceGenerator(name = "SOBREGIRO_SEQ", initialValue = 1, allocationSize = 1, sequenceName = "SOBREGIRO_SEQ")
    @XmlElement(name = "id")
    @Id
    @Column(name = "ID_SOBRE_GIRO", unique = true, nullable = false, precision = 22, scale = 0)
    public BigInteger getIdSobreGiro() {
        return this.idSobreGiro;
    }

    public void setIdSobreGiro(BigInteger idSobreGiro) {
        this.idSobreGiro = idSobreGiro;
    }

    @XmlElement(name = "socio")
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_SOCIO", nullable = false)
    public Socio getSocio() {
        return socio;
    }

    public void setSocio(Socio socio) {
        this.socio = socio;
    }

    @XmlElement
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_MONEDA", nullable = false)
    public Moneda getMoneda() {
        return moneda;
    }

    public void setMoneda(Moneda moneda) {
        this.moneda = moneda;
    }

    @XmlElement
    @Column(name = "MONTO", nullable = false, precision = 18)
    public BigDecimal getMonto() {
        return monto;
    }

    public void setMonto(BigDecimal monto) {
        this.monto = monto;
    }

    @XmlElement
    @Column(name = "INTERES", nullable = false, precision = 18)
    public BigDecimal getInteres() {
        return interes;
    }

    public void setInteres(BigDecimal interes) {
        this.interes = interes;
    }

    @XmlElement
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "FECHA_CREACION", nullable = false, length = 7)
    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    @XmlElement
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "FECHA_LIMITE_PAGO", nullable = false, length = 7)
    public Date getFechaLimitePago() {
        return fechaLimitePago;
    }

    public void setFechaLimitePago(Date fechaLimitePago) {
        this.fechaLimitePago = fechaLimitePago;
    }

    @XmlElement
    @Enumerated(EnumType.STRING)
    @Column(name = "ESTADO", nullable = false, length = 20, columnDefinition = "nvarchar2")
    public EstadoSobreGiro getEstado() {
        return this.estado;
    }

    public void setEstado(EstadoSobreGiro estado) {
        this.estado = estado;
    }

    @XmlTransient
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "sobreGiro")
    public Set<HistorialPagoSobreGiro> getHistorialPagos() {
        return this.historialPagos;
    }

    public void setHistorialPagos(Set historialPagos) {
        this.historialPagos = historialPagos;
    }
    
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((idSobreGiro == null) ? 0 : idSobreGiro.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        SobreGiro other = (SobreGiro) obj;
        if (idSobreGiro == null) {
            if (other.idSobreGiro != null)
                return false;
        } else if (!idSobreGiro.equals(other.idSobreGiro))
            return false;
        return true;
    }

}
