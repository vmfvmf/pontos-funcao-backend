package com.vmf.enums;

public enum ContagemItemFuncaoEnum {
	EE("EE"),
	CE("CE"),
	SE("SE"),
	ALI("ALI"),
	AIE("AIE");
	
	private String descricao;
	
	ContagemItemFuncaoEnum(String descricao) {
		this.descricao = descricao;
	}
	
	public String getDescricao() {
		return descricao;
	}
}
