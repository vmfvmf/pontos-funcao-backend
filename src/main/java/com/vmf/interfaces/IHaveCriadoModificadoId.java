package com.vmf.interfaces;

import java.time.LocalDate;

public interface IHaveCriadoModificadoId {
	void setCriado(LocalDate date);
	void setModificado(LocalDate date);
	void setId(Long id);
}
