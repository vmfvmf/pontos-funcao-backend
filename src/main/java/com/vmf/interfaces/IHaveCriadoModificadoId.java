package com.vmf.interfaces;

import java.time.LocalDate;

public interface IHaveCriadoModificadoId extends IGetId{
	void setCriado(LocalDate date);
	void setModificado(LocalDate date);
	void setId(Long id);
}
