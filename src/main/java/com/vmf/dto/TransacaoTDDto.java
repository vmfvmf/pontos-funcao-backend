package com.vmf.dto;

import java.time.LocalDate;

import com.vmf.enums.ContagemDadoSituacaoEnum;

public class TransacaoTDDto extends AbstractBaseDto {
	private ColunaDto coluna;
	private LocalDate criado; 
	private LocalDate modificado;
	
	private ContagemDadoSituacaoEnum alteradoDadoContagem;

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

	public ContagemDadoSituacaoEnum getAlteradoDadoContagem() {
		return alteradoDadoContagem;
	}

	public void setAlteradoDadoContagem(ContagemDadoSituacaoEnum alteradoDadoContagem) {
		this.alteradoDadoContagem = alteradoDadoContagem;
	}
}
