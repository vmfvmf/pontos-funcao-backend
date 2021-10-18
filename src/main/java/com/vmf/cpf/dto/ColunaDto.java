package com.vmf.cpf.dto;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.vmf.cpf.entities.Coluna;
import com.vmf.cpf.enums.ContagemDadoSituacaoEnum;

public class ColunaDto extends AbstractBaseDto{
	private String nome;
	private LocalDate criado; 
	private LocalDate modificado;
	
	@JsonIgnore
	private Boolean compararVersao;
	
	private ContagemDadoSituacaoEnum alteradoDadoContagem;
	private String alteradoNome;
	
	@JsonIgnore
	private Coluna entidadeOrigem;
	
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

	public Boolean getCompararVersao() {
		return compararVersao;
	}

	public void setCompararVersao(Boolean compararVersao) {
		this.compararVersao = compararVersao;
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

	public Coluna getEntidadeOrigem() {
		return entidadeOrigem;
	}

	public void setEntidadeOrigem(Coluna entidadeOrigem) {
		this.entidadeOrigem = entidadeOrigem;
	}

	public void checkComparacao(Coluna anterior) {
		if (!getNome().equals(anterior.getNome())) {
			setAlteradoNome(anterior.getNome());
			setAlteradoDadoContagem(ContagemDadoSituacaoEnum.ALTERADO);
		}
	}

}
