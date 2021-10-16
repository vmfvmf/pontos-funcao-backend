package com.vmf.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.vmf.entities.ContagemItemTransacao;
import com.vmf.interfaces.IDtoComparaVersao;

public class ContagemItemTransacaoDto extends AbstractContagemItemDto
implements IDtoComparaVersao<ContagemItemTransacao, TransacaoTDDto> {
	private List<TransacaoTDDto> transacaoTDs;
	private GrupoDto grupo;
	private Boolean acao;
	private Boolean mensagem;
	
	private GrupoDto alteradoGrupo;
	private Boolean alteradoAcao;
	private Boolean alteradoMensagem;

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

	public GrupoDto getAlteradoGrupo() {
		return alteradoGrupo;
	}

	public void setAlteradoGrupo(GrupoDto alteradoGrupo) {
		this.alteradoGrupo = alteradoGrupo;
	}

	public Boolean getAlteradoAcao() {
		return alteradoAcao;
	}

	public void setAlteradoAcao(Boolean alteradoAcao) {
		this.alteradoAcao = alteradoAcao;
	}

	public Boolean getAlteradoMensagem() {
		return alteradoMensagem;
	}

	public void setAlteradoMensagem(Boolean alteradoMensagem) {
		this.alteradoMensagem = alteradoMensagem;
	}


	@Override
	public void checkComparacao(ContagemItemTransacao entidadeAnterior) {
		super.checkComparacao(entidadeAnterior);
	}

	@JsonIgnore
	@Override
	public List<TransacaoTDDto> getObjetos() {
		return getTransacaoTDs();
	}

	
}
