package com.vmf.dto;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.vmf.enums.ContagemDadoSituacaoEnum;
import com.vmf.interfaces.IHaveCriadoModificadoId;

public class TabelaDto extends AbstractBaseDto implements IHaveCriadoModificadoId {
	private String nome;
	private List<ColunaDto> colunas = new ArrayList<>();
	private LocalDate criado; 
	private LocalDate modificado;
	
	private ContagemDadoSituacaoEnum alteradoDadoContagem;
	private String alteradoNome;
	private String alteradoColunas;

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

	public String getAlteradoNome() {
		return alteradoNome;
	}

	public void setAlteradoNome(String alteradoNome) {
		this.alteradoNome = alteradoNome;
	}

	public String getAlteradoColunas() {
		return alteradoColunas;
	}

	public void setAlteradoColunas(String alteradoColunas) {
		this.alteradoColunas = alteradoColunas;
	}

	public ContagemDadoSituacaoEnum getAlteradoDadoContagem() {
		return alteradoDadoContagem;
	}

	public void setAlteradoDadoContagem(ContagemDadoSituacaoEnum alteradoDadoContagem) {
		this.alteradoDadoContagem = alteradoDadoContagem;
	}
}
