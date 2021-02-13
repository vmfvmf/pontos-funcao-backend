package com.example.demo.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;


@Entity
@Table
public class FuncaoDados extends ItemContagem{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1822603001509468294L;
	
	
	@Column(name = "tr")
	private Integer TR;


	@JsonManagedReference(value="funcaoDados_tabelas")
	@OneToMany(mappedBy = "funcaoDados")
	private List<Tabela> tabelas;

	public List<Tabela> getTabelas() {
		return tabelas;
	}

	public void setTabelas(List<Tabela> tabelas) {
		this.tabelas = tabelas;
	}

	public Integer getTR() {
		return TR;
	}

	public void setTR(Integer tR) {
		TR = tR;
	}
}
