package org.sistemafinanciero.service.nt;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;

import javax.ejb.Remote;

import org.sistemafinanciero.entity.DebeHaber;
import org.sistemafinanciero.entity.TransaccionBovedaOtroView;
import org.sistemafinanciero.entity.Utilidad;
import org.sistemafinanciero.entity.type.Periodo;
import org.sistemafinanciero.entity.type.TipoDebeHaber;
import org.sistemafinanciero.rest.dto.UtilidadPorPeriodoDTO;

@Remote
public interface ReportesServiceNT {

	public List<DebeHaber> getDebeHaber(Date fecha, BigInteger idMoneda, TipoDebeHaber tipoDebeHaber);

	public List<DebeHaber> getDebeHaber(Date fecha, TipoDebeHaber tipoDebeHaber);

	public BigDecimal getDebeHaberTotal(Date fechaReporte, BigInteger idMoneda, TipoDebeHaber tipoDebeHaber);

	public List<DebeHaber> getDebeHaberHistorialTotal(Date desdeReporte, Date hastaReporte, BigInteger idMoneda,
			TipoDebeHaber tipoDebeHaber);

	public BigDecimal getTotalCuentasPorCobrar(BigInteger idMoneda, Date fechaReporte);

	public BigDecimal getTotalCuentasPorPagar(BigInteger idMoneda, Date fechaReporte);

	public BigDecimal getPatrimonio(BigInteger idMoneda, Date fechaReporte);

	public BigDecimal getTotalUtilidad(BigInteger idMoneda, Date fechaReporte);

	public List<Utilidad> getUtilidadHistorial(Date desdeReporte, Date hastaReporte);

	public List<TransaccionBovedaOtroView> getUtilidadMovimientos(Date desdeReporte, Date hastaReporte);

	public List<UtilidadPorPeriodoDTO> getUtilidadHistorial(Date desdeReporte, Date hastaReporte, Periodo periodo);

}
