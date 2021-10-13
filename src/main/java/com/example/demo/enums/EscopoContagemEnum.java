package com.example.demo.enums;

public enum EscopoContagemEnum {
	SISTEMA("SISTEMA"),
	PROJETO("PROJETO"),
	SPRINT("SPRINT");
	
	private String descricao;
	
	EscopoContagemEnum(String descricao) {
		this.descricao = descricao;
	}
	
	public String getDescricao() {
		return descricao;
	}
}
