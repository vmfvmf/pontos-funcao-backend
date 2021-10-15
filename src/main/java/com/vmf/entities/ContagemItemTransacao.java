package com.vmf.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.DiscriminatorOptions;

@Entity
@DiscriminatorValue("T")
@DiscriminatorOptions(force = true)
public class ContagemItemTransacao extends AbstractContagemItem {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@ManyToOne
	@JoinColumn(name="grupo_id")
	private Grupo grupo;
	
	@OneToMany(mappedBy = "itemTransacao", cascade = {CascadeType.ALL}, orphanRemoval = true)
	private List<TransacaoTD> transacaoTDs = new ArrayList<>();
		
	@Column(name="transacao_acao")
	private Boolean acao;
	
	@Column(name="transacao_mensagem")
	private Boolean mensagem;

	public Grupo getGrupo() {
		return grupo;
	}

	public void setGrupo(Grupo grupo) {
		this.grupo = grupo;
	}

	public List<TransacaoTD> getTransacaoTDs() {
		return transacaoTDs;
	}

	public void setTransacaoTDs(List<TransacaoTD> transacaoTDs) {
		this.transacaoTDs = transacaoTDs;
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
	
	@Override
	public ContagemItemTransacao clone() {
		ContagemItemTransacao nova = new ContagemItemTransacao();
		ContagemItemArquivoReferenciado.clone(nova, this);
		
		nova.setGrupo(getGrupo());
		nova.setAcao(isAcao());
		nova.setMensagem(isMensagem());
				
		for(TransacaoTD td : getTransacaoTDs()) {
			TransacaoTD novoTd = td.clone();
			nova.getTransacaoTDs().add(novoTd);
		};		
		
		return nova;
	}
}
