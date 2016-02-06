package org.sistemafinanciero.rest.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import org.sistemafinanciero.entity.type.TipoPersona;

@XmlRootElement(name = "transaccionSobreGiroDTO")
@XmlAccessorType(XmlAccessType.PROPERTY)
public class TransaccionSobreGiroDTO implements Serializable {

    /**
	 * 
	 */
    private static final long serialVersionUID = 1L;

    private TipoPersona tipoPersona;
    private BigInteger idPersona;
    private BigInteger idMoneda;

    private BigDecimal monto;
    private BigDecimal interes;
    private Long fechaLimitePago;

    public BigInteger getIdPersona() {
        return idPersona;
    }

    public void setIdPersona(BigInteger idPersona) {
        this.idPersona = idPersona;
    }

    public BigInteger getIdMoneda() {
        return idMoneda;
    }

    public void setIdMoneda(BigInteger idMoneda) {
        this.idMoneda = idMoneda;
    }

    public BigDecimal getMonto() {
        return monto;
    }

    public void setMonto(BigDecimal monto) {
        this.monto = monto;
    }

    public BigDecimal getInteres() {
        return interes;
    }

    public void setInteres(BigDecimal interes) {
        this.interes = interes;
    }

    public Long getFechaLimitePago() {
        return fechaLimitePago;
    }

    public void setFechaLimitePago(Long fechaLimitePago) {
        this.fechaLimitePago = fechaLimitePago;
    }

	public TipoPersona getTipoPersona() {
		return tipoPersona;
	}

	public void setTipoPersona(TipoPersona tipoPersona) {
		this.tipoPersona = tipoPersona;
	}

}
