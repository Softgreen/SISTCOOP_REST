package org.sistemafinanciero.service.nt;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;

import javax.ejb.Remote;

import org.sistemafinanciero.entity.DebeHaber;
import org.sistemafinanciero.entity.type.TipoDebeHaber;

@Remote
public interface ReportesServiceNT {

    public List<DebeHaber> getDebeHaber(Date fecha, BigInteger idMoneda, TipoDebeHaber tipoDebeHaber);

    public List<DebeHaber> getDebeHaber(Date fecha, TipoDebeHaber tipoDebeHaber);

    public BigDecimal getDebeHaberTotal(Date fechaReporte, BigInteger idMoneda, TipoDebeHaber tipoDebeHaber);

    public List<DebeHaber> getDebeHaberHistorialTotal(Date desdeReporte, Date hastaReporte,
            BigInteger idMoneda, TipoDebeHaber tipoDebeHaber);

	public BigDecimal getTotalCuentasPorCobrar(BigInteger idMoneda, Date fechaReporte);

	public BigDecimal getTotalCuentasPorPagar(BigInteger idMoneda, Date fechaReporte);

	public BigDecimal getPatrimonio(BigInteger idMoneda, Date fechaReporte);
	
	public BigDecimal getTotalUtilidad(BigInteger idMoneda, Date fechaReporte);

}
