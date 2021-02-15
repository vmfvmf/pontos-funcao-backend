package com.example.demo.model;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table
public class TransacaoTDMensagemTela extends TransacaoTD{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7388201368919204404L;
	
	@OneToOne
	private MensagemTela mensagemTela;

	public MensagemTela getMensagemTela() {
		return mensagemTela;
	}

	public void setMensagemTela(MensagemTela mensagemTela) {
		this.mensagemTela = mensagemTela;
	}
	
}
