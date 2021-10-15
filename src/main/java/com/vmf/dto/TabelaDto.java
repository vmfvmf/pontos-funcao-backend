package com.vmf.dto;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class TabelaDto extends AbstractBaseDto {
	private String nome;
	private List<ColunaDto> colunas = new ArrayList<>();
	private LocalDate criado; 
	private LocalDate modificado;

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
}
