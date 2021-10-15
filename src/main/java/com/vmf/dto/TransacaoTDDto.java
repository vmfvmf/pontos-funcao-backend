package com.vmf.dto;

import java.time.LocalDate;

public class TransacaoTDDto extends AbstractBaseDto {
	private ColunaDto coluna;
	private LocalDate criado; 
	private LocalDate modificado;

	public ColunaDto getColuna() {
		return coluna;
	}

	public void setColuna(ColunaDto coluna) {
		this.coluna = coluna;
	}

	public LocalDate getCriado() {
		return criado;
	}

	public void setCriado(LocalDate criado) {
		this.criado = criado;
	}

	public LocalDate getModificado() {
		return modificado;
	}

	public void setModificado(LocalDate modificado) {
		this.modificado = modificado;
	}
}
