package com.example.demo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@Table(name = "transacao_td")
@Entity
public class TransacaoTD extends Base {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2848952794406058442L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column
	private String tipo;
	
	@ManyToOne
	@JsonIgnoreProperties("transacaoTDs")
	@JoinColumn(name="contagem_item_id")
	private ContagemItem contagemItem;
	
	@OneToOne
	@JsonIgnoreProperties("tabela")
	@JoinColumn(name="coluna_id")
	private Coluna coluna;
	
	public Long getId() {
		return id;
	}

	public ContagemItem getContagemItem() {
		return contagemItem;
	}

	public void setContagemItem(ContagemItem contagemItem) {
		this.contagemItem = contagemItem;
	}

	public void setId(Long id) {
		this.id = id;
	}


	public Coluna getColuna() {
		return coluna;
	}

	public void setColuna(Coluna coluna) {
		this.coluna = coluna;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	
}
