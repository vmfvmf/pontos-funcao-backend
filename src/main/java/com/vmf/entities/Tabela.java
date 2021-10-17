package com.vmf.entities;

import java.time.LocalDate;
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
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.vmf.interfaces.IHaveCriadoModificadoId;


@Entity
@Table
public class Tabela extends AbstractBase implements IHaveCriadoModificadoId {

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
	
	@OneToOne
	@JoinColumn(name="tabela_origem_id")
	private Tabela entidadeOrigem;
	
	@Column
	private LocalDate criado;
	
	@Column
	private LocalDate modificado;
	
	private transient Boolean compararVersao = false;

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

	public Tabela getEntidadeOrigem() {
		return entidadeOrigem;
	}

	public void setEntidadeOrigem(Tabela tabelaOrigem) {
		this.entidadeOrigem = tabelaOrigem;
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

	public Boolean getCompararVersao() {
		return compararVersao;
	}

	public void setCompararVersao(Boolean compararVersao) {
		this.compararVersao = compararVersao;
	}

	@Override
	public Tabela clone() {
		Tabela nova = new Tabela();
		nova.setCriado(LocalDate.now());
		nova.setNome(getNome());
		nova.setEntidadeOrigem(this);		
		
		for(Coluna coluna : getColunas()) {
			Coluna novaColuna = coluna.clone();
			nova.getColunas().add(novaColuna);
		}
		
		return nova;
	}
	
	public Tabela findOrigemDaSelecionada(
			List<Tabela> compararOrigem) {
		if (compararOrigem.contains(this)) {
			return this;
		}
		if (this.getEntidadeOrigem() == null) {
			return null;
		}
		return this.getEntidadeOrigem().findOrigemDaSelecionada(compararOrigem);
	}
}
