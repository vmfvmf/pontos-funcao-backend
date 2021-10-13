package com.vmf.enums;

public enum ContagemEstado {
	E("E"),
	V("V");
	
	private String descricao;
	
	ContagemEstado(String descricao) {
		this.descricao = descricao;
	}
	
	public String getDescricao() {
		return descricao;
	}
}
