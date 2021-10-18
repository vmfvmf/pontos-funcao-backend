package com.vmf.cpf.dto;

import java.util.List;

import com.vmf.cpf.entities.ContagemItemTransacao;
import com.vmf.cpf.enums.ContagemDadoSituacaoEnum;

public class ContagemItemTransacaoDto extends AbstractContagemItemDto<ContagemItemTransacao> {
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

	public void checkComparacao(ContagemItemTransacao entidadeAnterior) {
		ContagemItemTransacao anteriorTransacao = entidadeAnterior;
		super.checkSuperComparacao(entidadeAnterior);
		if (!isAcao().equals(anteriorTransacao.isAcao())) {
			setAlteradoAcao(anteriorTransacao.isAcao());
			setAlteradoDadoContagem(ContagemDadoSituacaoEnum.ALTERADO);			
		}
		if (!isMensagem().equals(anteriorTransacao.isMensagem())) {
			setAlteradoMensagem(anteriorTransacao.isMensagem());	
			setAlteradoDadoContagem(ContagemDadoSituacaoEnum.ALTERADO);	
		}
	}
	
}
