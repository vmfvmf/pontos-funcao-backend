package com.vmf.dto;

import java.time.LocalDate;

import com.vmf.enums.ContagemDadoSituacaoEnum;

public class ColunaDto extends AbstractBaseDto{
	private String nome;
	private LocalDate criado; 
	private LocalDate modificado;
	
	private ContagemDadoSituacaoEnum alteradoDadoContagem;
	private String alteradoNome;
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getNome() {
		return nome;
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

	public String getAlteradoNome() {
		return alteradoNome;
	}

	public void setAlteradoNome(String alteradoNome) {
		this.alteradoNome = alteradoNome;
	}

	public ContagemDadoSituacaoEnum getAlteradoDadoContagem() {
		return alteradoDadoContagem;
	}

	public void setAlteradoDadoContagem(ContagemDadoSituacaoEnum alteradoDadoContagem) {
		this.alteradoDadoContagem = alteradoDadoContagem;
	}

}
