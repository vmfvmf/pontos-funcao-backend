package com.vmf.dto;

import java.util.ArrayList;
import java.util.List;

public class TabelaDto extends AbstractBaseDto {
	private String nome;
	private List<ColunaDto> colunas = new ArrayList<>();

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

	
}
