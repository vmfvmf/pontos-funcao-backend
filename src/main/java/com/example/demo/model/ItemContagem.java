package com.example.demo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.example.demo.enums.TipoItemContagemEnum;
import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table
public abstract class ItemContagem extends Base {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2621619970365647172L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@JsonBackReference(value="contagem")
	@ManyToOne()
	@JoinColumn(name="contagem_id", nullable=false)
	private Contagem contagem;
	
	@Column(name = "tipo", nullable = false)
	private TipoItemContagemEnum tipo;
	
	@Column(name = "valor")
	private Integer valor;
	
	@Column(name = "nome")
	private String nome;
	
	@Column(name = "td")
	private Integer td;
	
	@Column(name = "contado")
	private Boolean contado;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public TipoItemContagemEnum getTipo() {
		return tipo;
	}

	public void setTipo(TipoItemContagemEnum tipo) {
		this.tipo = tipo;
	}

	public Integer getValor() {
		return valor;
	}

	public void setValor(Integer valor) {
		this.valor = valor;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Integer getTd() {
		return td;
	}

	public void setTd(Integer td) {
		this.td = td;
	}

	public Boolean getContado() {
		return contado;
	}

	public void setContado(Boolean contado) {
		this.contado = contado;
	}
	
}