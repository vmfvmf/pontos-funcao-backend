package com.vmf.entities;

import java.time.LocalDate;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.vmf.interfaces.IHaveCriadoModificadoId;


@Entity
@Table(name = "coluna")
public class Coluna extends AbstractBase implements IHaveCriadoModificadoId {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8933834758570620231L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "nome", nullable = false)
	private String nome;
	
	@ManyToOne
	@JoinColumn(name="tabela_id")
	private Tabela tabela;
	
	@OneToOne
	@JoinColumn(name="coluna_origem_id")
	private Coluna colunaOrigem;
	
	@Column
	private LocalDate criado;
	
	@Column
	private LocalDate modificado;
	
	public void setNome(String nome) {
		this.nome = nome;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public Tabela getTabela() {
		return tabela;
	}

	public void setTabela(Tabela tabela) {
		this.tabela = tabela;
	}

	public Coluna getColunaOrigem() {
		return colunaOrigem;
	}

	public void setColunaOrigem(Coluna colunaOrigem) {
		this.colunaOrigem = colunaOrigem;
	}

	public LocalDate getCriado() {
		return criado;
	}

	public void setCriado(LocalDate criado) {
		this.criado = criado;
	}

	public LocalDate getModificado() {
		return modificado;
	}

	public void setModificado(LocalDate modificado) {
		this.modificado = modificado;
	}
	
	public Boolean equals (Coluna other) {
		Boolean equals = false;
		
		equals = getId().equals(other.getId());
		equals = Objects.equals(getColunaOrigem(), other.getColunaOrigem()) || equals;
		equals = getNome().equals(other.getNome()) || equals;
		equals = getTabela().equals(other.getTabela()) || equals;
		
		return equals;
	}
	
	@Override
	public Coluna clone() {
		Coluna nova = new Coluna();
		nova.setCriado(LocalDate.now());
		nova.setNome(getNome());
		nova.setColunaOrigem(calculateColunaOrigem());		
		
		return nova;
	}
	
	private Coluna calculateColunaOrigem() {
		return  this.getColunaOrigem() != null && this.equals(this.getColunaOrigem())
				?  this.getColunaOrigem() : this;
	}
}
