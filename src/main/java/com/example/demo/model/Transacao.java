package com.example.demo.model;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table
public class Transacao extends ItemContagem{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1822603001509468294L;
	
	@JsonBackReference(value="grupo_transacoes")
	@ManyToOne
	@JoinColumn(name="grupo_id")
	private GrupoTransacao grupo;
	
//	@JsonManagedReference(value="transacao_td")
//	@OneToMany(mappedBy = "transacao")
//	private List<TransacaoTD> transacoesTD;
		

	public GrupoTransacao getGrupo() {
		return grupo;
	}

	public void setGrupo(GrupoTransacao grupo) {
		this.grupo = grupo;
	}

//	public List<TransacaoTD> getTransacoesTD() {
//		return transacoesTD;
//	}
//
//	public void setTransacoesTD(List<TransacaoTD> transacoesTD) {
//		this.transacoesTD = transacoesTD;
//	}

}
