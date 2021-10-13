package com.vmf.dto;

public class TransacaoTDDto extends AbstractBaseDto {
	private ColunaDto coluna;

	public ColunaDto getColuna() {
		return coluna;
	}

	public void setColuna(ColunaDto coluna) {
		this.coluna = coluna;
	}
}
