package org.sistemafinanciero.service.nt;

import java.math.BigDecimal;
import java.math.BigInteger;
import javax.ejb.Remote;

@Remote
public interface ReporteCajaBancosServiceNT {

    public BigDecimal getReporteCajaPorAgencia(BigInteger idMoneda, BigInteger idAgencia);

	public BigDecimal getReporteTotalCaja(BigInteger idMoneda);

	public BigDecimal getTotalBancos(BigInteger idMoneda);
	
	//public List<DebeHaber> getDebeHaber(Date fecha, BigInteger idMoneda, TipoDebeHaber tipoDebeHaber);

    //public List<DebeHaber> getDebeHaber(Date fecha, TipoDebeHaber tipoDebeHaber);

    //public BigDecimal getDebeHaberTotal(Date fechaReporte, BigInteger idMoneda, TipoDebeHaber tipoDebeHaber);

    //public List<DebeHaber> getDebeHaberHistorialTotal(Date desdeReporte, Date hastaReporte,
            //BigInteger idMoneda, TipoDebeHaber tipoDebeHaber);

}
