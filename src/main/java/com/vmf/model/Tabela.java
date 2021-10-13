package com.vmf.model;

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


@Entity
@Table
public class Tabela extends Base {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8933834758570620231L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "nome", nullable = false)
	private String nome;
	
	@OneToMany(mappedBy = "tabela", cascade = {CascadeType.ALL}, orphanRemoval = true)
	private List<Coluna> colunas = new ArrayList<>();
	
	@ManyToOne
	@JoinColumn(name="contagem_item_id")
	private ContagemItemArquivoReferenciado arquivoReferenciado;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<Coluna> getColunas() {
		return colunas;
	}

	public void setColunas(List<Coluna> colunas) {
		this.colunas = colunas;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public ContagemItemArquivoReferenciado getArquivoReferenciado() {
		return arquivoReferenciado;
	}

	public void setArquivoReferenciado(ContagemItemArquivoReferenciado arquivoReferenciado) {
		this.arquivoReferenciado = arquivoReferenciado;
	}

	
}
