package com.example.demo.model;

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

@Table
@Entity
public class ContagemItem extends Base {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2621619970365647172L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(name = "nome")
	private String nome;
	
	@Column(name = "tipo")
	private String tipo;
		
	@Column(name = "td")
	private Integer td;
	
	@Column(name = "tr")
	private Integer tr;	
	
	@Column(name = "complexidade") // baixa média alta
	private String complexidade;
	
	@Column(name = "pf")
	private Integer pf;
		
	@Column(name = "subtipo") // EE, SE, CE para Transação / ALI, AIE para ArquivoLógico
	private String subtipo;
	
	@ManyToOne
	@JoinColumn(name="contagem_id")
	private Contagem contagem;
	
	@ManyToOne
	@JoinColumn(name="grupo_id")
	private Grupo grupo;
	
	@JsonIgnoreProperties("contagemItem")
	@OneToMany(mappedBy = "contagemItem", cascade = {CascadeType.ALL})
	private List<Tabela> tabelas;
	
	
	@JsonIgnoreProperties("contagemItem")
	@OneToMany(mappedBy = "contagemItem", cascade = {CascadeType.ALL})
	private List<TransacaoTD> transacaoTDs;

	public List<TransacaoTD> getTransacaoTDs() {
		return transacaoTDs;
	}

	public void setTransacaoTDs(List<TransacaoTD> transacaoTDs) {
		this.transacaoTDs = transacaoTDs;
	}

	public Grupo getGrupo() {
		return grupo;
	}

	public void setGrupo(Grupo grupo) {
		this.grupo = grupo;
	}

	public String getSubtipo() {
		return subtipo;
	}

	public void setSubtipo(String subtipo) {
		this.subtipo = subtipo;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	
	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
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
	
	public Integer getTr() {
		return tr;
	}

	public void setTr(Integer tr) {
		this.tr = tr;
	}

	public String getComplexidade() {
		return complexidade;
	}

	public void setComplexidade(String complexidade) {
		this.complexidade = complexidade;
	}

	public Integer getPf() {
		return pf;
	}

	public void setPf(Integer pf) {
		this.pf = pf;
	}

	public Contagem getContagem() {
		return contagem;
	}

	public void setContagem(Contagem contagem) {
		this.contagem = contagem;
	}

	public List<Tabela> getTabelas() {
		return tabelas;
	}

	public void addToTabelas(Tabela tabela) {
		this.tabelas.add(tabela);
	}
	
}
