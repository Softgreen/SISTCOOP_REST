package org.sistemafinanciero.service.nt;

import java.math.BigInteger;
import java.util.List;

import javax.ejb.Remote;

import org.sistemafinanciero.entity.PendienteCaja;
import org.sistemafinanciero.entity.PendienteCajaView;
import org.sistemafinanciero.entity.dto.VoucherPendienteCaja;

@Remote
public interface PendienteServiceNT extends AbstractServiceNT<PendienteCaja> {
	
	public List<PendienteCajaView> findAllView(BigInteger idAgencia);

	public VoucherPendienteCaja getVoucherPendienteCaja(BigInteger idPendienteCaja);

}
