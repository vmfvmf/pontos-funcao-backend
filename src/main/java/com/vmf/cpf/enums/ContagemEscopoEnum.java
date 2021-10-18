package com.vmf.cpf.enums;

public enum ContagemEscopoEnum {
	SISTEMA("SISTEMA"),
	PROJETO("PROJETO"),
	SPRINT("SPRINT");
	
	private String descricao;
	
	ContagemEscopoEnum(String descricao) {
		this.descricao = descricao;
	}
	
	public String getDescricao() {
		return descricao;
	}
}
