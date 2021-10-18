package com.vmf.cpf.enums;

public enum ContagemDadoSituacaoEnum {
	NOVO("NOVO"),
	ALTERADO("ALTERADO"),
	EXCLUIDO("EXCLUIDO");
	
	private String descricao;
	
	ContagemDadoSituacaoEnum(String descricao) {
		this.descricao = descricao;
	}
	
	public String getDescricao() {
		return descricao;
	}
}
