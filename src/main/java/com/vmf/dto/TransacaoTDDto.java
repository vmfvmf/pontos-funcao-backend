package com.vmf.dto;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.vmf.enums.ContagemDadoSituacaoEnum;

public class TransacaoTDDto extends AbstractBaseDto {
	private ColunaDto coluna;
	private LocalDate criado; 
	private LocalDate modificado;
	
	@JsonIgnore
	private Boolean compararVersao; 
	
	private ContagemDadoSituacaoEnum alteradoDadoContagem;
	
	@JsonIgnore
	private ColunaDto entidadeOrigem;

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

	public Boolean getCompararVersao() {
		return compararVersao;
	}

	public void setCompararVersao(Boolean compararVersao) {
		this.compararVersao = compararVersao;
	}

	public ContagemDadoSituacaoEnum getAlteradoDadoContagem() {
		return alteradoDadoContagem;
	}

	public void setAlteradoDadoContagem(ContagemDadoSituacaoEnum alteradoDadoContagem) {
		this.alteradoDadoContagem = alteradoDadoContagem;
	}

	public ColunaDto getEntidadeOrigem() {
		return entidadeOrigem;
	}

	public void setEntidadeOrigem(ColunaDto entidadeOrigem) {
		this.entidadeOrigem = entidadeOrigem;
	}	
}
