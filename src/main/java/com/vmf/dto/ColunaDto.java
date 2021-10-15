package com.vmf.dto;

import java.time.LocalDate;

public class ColunaDto extends AbstractBaseDto{
	private String nome;
	private LocalDate criado; 
	private LocalDate modificado;
	
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

}
