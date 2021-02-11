package com.example.demo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.example.demo.enums.FuncaoArquivoLogico;

@Entity
@Table
public class ArquivoLogico extends ItemContagem{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1822603001509468294L;
	
	@Column(name = "funcao")
	private FuncaoArquivoLogico funcao;
	
	@Column(name = "tr")
	private Integer TR;

	public FuncaoArquivoLogico getFuncao() {
		return funcao;
	}

	public void setFuncao(FuncaoArquivoLogico funcao) {
		this.funcao = funcao;
	}

	public Integer getTR() {
		return TR;
	}

	public void setTR(Integer tR) {
		TR = tR;
	}
}
