package com.example.demo.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;


@Entity
@Table
public class FuncaoDados extends ItemContagem{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1822603001509468294L;
	
	@JsonManagedReference(value="funcaoDados_tabelas")
	@OneToMany(mappedBy = "funcaoDados")
	private List<Tabela> tabelas;

	@JsonBackReference(value="contagem_funcao_dados")
	@ManyToOne
	@JoinColumn(name="contagem_id", nullable=false)
	private Contagem contagem;
	
	public List<Tabela> getTabelas() {
		return tabelas;
	}

	public void setTabelas(List<Tabela> tabelas) {
		this.tabelas = tabelas;
	}

	public Contagem getContagem() {
		return contagem;
	}

	public void setContagem(Contagem contagem) {
		this.contagem = contagem;
	}

}
