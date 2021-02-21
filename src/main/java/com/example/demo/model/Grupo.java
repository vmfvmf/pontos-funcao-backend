package com.example.demo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table
public class Grupo extends Base{
	/**
	 * 
	 */
	private static final long serialVersionUID = 8209734799987984125L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(name = "nome")
	private String nome;
			
	@ManyToOne
	@JoinColumn(name="contagem_id", nullable=false)
	@JsonIgnoreProperties("grupos")
	private Contagem contagem;

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

	public Contagem getContagem() {
		return contagem;
	}

	public void setContagem(Contagem contagem) {
		this.contagem = contagem;
	}
}
