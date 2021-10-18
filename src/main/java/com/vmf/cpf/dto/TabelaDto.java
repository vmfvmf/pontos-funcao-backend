package com.vmf.cpf.dto;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.vmf.cpf.entities.Tabela;
import com.vmf.cpf.enums.ContagemDadoSituacaoEnum;
import com.vmf.cpf.interfaces.IHaveCriadoModificadoId;

public class TabelaDto extends AbstractBaseDto implements IHaveCriadoModificadoId {
	private String nome;
	private List<ColunaDto> colunas = new ArrayList<>();
	private LocalDate criado; 
	private LocalDate modificado;
	
	@JsonIgnore
	private Boolean compararVersao;
	
	private ContagemDadoSituacaoEnum alteradoDadoContagem;
	private String alteradoNome;
	
	@JsonIgnore
	private Tabela entidadeOrigem;

	public List<ColunaDto> getColunas() {
		return colunas;
	}

	public void setColunas(List<ColunaDto> colunas) {
		this.colunas = colunas;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
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

	public Tabela getEntidadeOrigem() {
		return entidadeOrigem;
	}

	public void setEntidadeOrigem(Tabela entidadeOrigem) {
		this.entidadeOrigem = entidadeOrigem;
	}

	public void checkComparacao(Tabela anterior) {
		if (!getNome().equals(anterior.getNome())) {
			setAlteradoNome(anterior.getNome());
			setAlteradoDadoContagem(ContagemDadoSituacaoEnum.ALTERADO);
		}
	}
}
