package com.example.demo.dto;

import java.util.List;

public class ContagemItemTransacaoDto extends AbstractContagemItemDto{
	private List<TransacaoTDDto> transacaoTDs;
	private GrupoDto grupo;
	private Boolean acao;
	private Boolean mensagem;

	public List<TransacaoTDDto> getTransacaoTDs() {
		return transacaoTDs;
	}

	public void setTransacaoTDs(List<TransacaoTDDto> transacaoTDs) {
		this.transacaoTDs = transacaoTDs;
	}

	public GrupoDto getGrupo() {
		return grupo;
	}

	public void setGrupo(GrupoDto grupo) {
		this.grupo = grupo;
	}

	public Boolean isAcao() {
		return acao;
	}

	public void setAcao(Boolean acao) {
		this.acao = acao;
	}

	public Boolean isMensagem() {
		return mensagem;
	}

	public void setMensagem(Boolean mensagem) {
		this.mensagem = mensagem;
	}	
	
}
