package org.sistemafinanciero.service.nt;

import java.math.BigDecimal;
import java.math.BigInteger;
import javax.ejb.Remote;

@Remote
public interface ReporteCajaBancosServiceNT {

    public BigDecimal getReporteCajaPorAgenciaMoneda(BigInteger idMoneda, BigInteger idAgencia);
    
    public BigDecimal getReporteBancosPorAgenciaMoneda(BigInteger idMoneda, BigInteger idAgencia);
	
	//public List<DebeHaber> getDebeHaber(Date fecha, BigInteger idMoneda, TipoDebeHaber tipoDebeHaber);

    //public List<DebeHaber> getDebeHaber(Date fecha, TipoDebeHaber tipoDebeHaber);

    //public BigDecimal getDebeHaberTotal(Date fechaReporte, BigInteger idMoneda, TipoDebeHaber tipoDebeHaber);

    //public List<DebeHaber> getDebeHaberHistorialTotal(Date desdeReporte, Date hastaReporte,
            //BigInteger idMoneda, TipoDebeHaber tipoDebeHaber);

}
