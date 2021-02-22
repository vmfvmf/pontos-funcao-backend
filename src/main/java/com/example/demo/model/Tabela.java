package com.example.demo.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


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

	@ManyToOne
	@JsonIgnoreProperties("tabelas")
	@JoinColumn(name="contagem_item_id")
	private ContagemItem contagemItem;
	
	@JsonIgnoreProperties("tabela")
	@OneToMany(mappedBy = "tabela", cascade = {CascadeType.ALL})
	private List<Coluna> colunas = new ArrayList<>();

	public List<Coluna> getColunas() {
		return colunas;
	}

	public void setColunas(List<Coluna> colunas) {
		this.colunas = colunas;
	}

	public ContagemItem getContagemItem() {
		return contagemItem;
	}

	public void setContagemItem(ContagemItem contagemItem) {
		this.contagemItem = contagemItem;
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

	
}
