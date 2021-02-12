package com.example.demo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.example.demo.enums.FuncaoTransacaoEnum;

@Entity
@Table
public class Transacao extends ItemContagem{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1822603001509468294L;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="grupo_id")
	private GrupoTransacao grupo;
	
	@Column(name = "funcao")
	private FuncaoTransacaoEnum funcao;
	
	@Column(name = "ar")
	private Integer AR;

	public GrupoTransacao getGrupo() {
		return grupo;
	}

	public void setGrupo(GrupoTransacao grupo) {
		this.grupo = grupo;
	}

	public FuncaoTransacaoEnum getFuncao() {
		return funcao;
	}

	public void setFuncao(FuncaoTransacaoEnum funcao) {
		this.funcao = funcao;
	}

	public Integer getAR() {
		return AR;
	}

	public void setAR(Integer aR) {
		AR = aR;
	}
}
