package com.example.demo.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;


@Entity
@Table
public class Tabela extends Base {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8933834758570620231L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(name = "nome", nullable = false)
	private String nome;

	@JsonBackReference(value="funcaoDados_tabelas")
	@ManyToOne
	@JoinColumn(name="funcaoDados_id", nullable=false)
	private FuncaoDados funcaoDados;
	
	@JsonManagedReference(value="tabela_colunas")
	@OneToMany(mappedBy = "tabela")
	private List<Coluna> colunas;

	public List<Coluna> getColunas() {
		return colunas;
	}

	public void setColunas(List<Coluna> colunas) {
		this.colunas = colunas;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	public FuncaoDados getFuncaoDados() {
		return funcaoDados;
	}

	public void setFuncaoDados(FuncaoDados funcaoDados) {
		this.funcaoDados = funcaoDados;
	}

	
}
