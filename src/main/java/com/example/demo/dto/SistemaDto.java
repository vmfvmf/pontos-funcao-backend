package com.example.demo.dto;

public class SistemaDto extends AbstractBaseDto {
	private String nome;
	private String versao;
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getVersao() {
		return versao;
	}

	public void setVersao(String versao) {
		this.versao = versao;
	}
	
}
