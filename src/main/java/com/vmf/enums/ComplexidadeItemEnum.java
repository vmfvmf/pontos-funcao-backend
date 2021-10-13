package com.vmf.enums;

public enum ComplexidadeItemEnum {
	BAIXA("BAIXA"),
	MEDIA("MEDIA"),
	ALTA("ALTA");
	
	private String descricao;
	
	ComplexidadeItemEnum(String descricao) {
		this.descricao = descricao;
	}
	
	public String getDescricao() {
		return descricao;
	}
}
