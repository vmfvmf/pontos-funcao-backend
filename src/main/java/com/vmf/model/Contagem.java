package com.vmf.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.vmf.enums.ContagemEstado;

@Entity
@Table
public class Contagem extends Base {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2825945051996560941L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name="sistema_id", nullable=false)
	private Sistema sistema;
	
	@Column(name = "contador", nullable = false)
	private String contador;
	
	@Column(name = "data_contagem", nullable = false)
	private Date dataContagem;
	
	@Column(name = "total_pontos_funcao")
	private Integer totalPontosFuncao;
	
	@Column(name = "escopo", nullable = false) // "0=sistema, 1=projeto, 2=sprint"
	private String escopo;
	
	@OneToOne
	@JoinColumn(name="projeto_id")
	private Projeto projeto;

	@ManyToOne
	@JoinColumn(name="sprint_id")
	private Sprint sprint;
	
	@Column
	@Enumerated(EnumType.STRING)
	private ContagemEstado estado;
	
	@OneToMany(cascade = {CascadeType.ALL}, orphanRemoval = true)
	@JoinColumn(name = "contagem_id")
	private List<ContagemItemTransacao> transacoes = new ArrayList<>();
	
	@OneToMany(cascade = {CascadeType.ALL}, orphanRemoval = true)
	@JoinColumn(name = "contagem_id")
	private List<ContagemItemArquivoReferenciado> arquivosReferenciados = new ArrayList<>();
	
	@Column
	private Integer versao;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getTotalPontosFuncao() {
		return totalPontosFuncao;
	}

	public void setTotalPontosFuncao(Integer totalPontosFuncao) {
		this.totalPontosFuncao = totalPontosFuncao;
	}

	public Sprint getSprint() {
		return sprint;
	}

	public void setSprint(Sprint sprint) {
		this.sprint = sprint;
	}
		
	public Projeto getProjeto() {
		return projeto;
	}

	public void setProjeto(Projeto projeto) {
		this.projeto = projeto;
	}

	public Sistema getSistema() {
		return sistema;
	}

	public void setSistema(Sistema sistema) {
		this.sistema = sistema;
	}

	public String getContador() {
		return contador;
	}

	public void setContador(String contador) {
		this.contador = contador;
	}

	public Date getDataContagem() {
		return dataContagem;
	}

	public void setDataContagem(Date dataContagem) {
		this.dataContagem = dataContagem;
	}

	public String getEscopo() {
		return escopo;
	}

	public void setEscopo(String escopo) {
		this.escopo = escopo;
	}

	public ContagemEstado getEstado() {
		return estado;
	}

	public void setEstado(ContagemEstado estado) {
		this.estado = estado;
	}

	public Integer getVersao() {
		return versao;
	}

	public void setVersao(Integer versao) {
		this.versao = versao;
	}

	public List<ContagemItemTransacao> getTransacoes() {
		return transacoes;
	}

	public void setTransacoes(List<ContagemItemTransacao> transacoes) {
		this.transacoes = transacoes;
	}

	public List<ContagemItemArquivoReferenciado> getArquivosReferenciados() {
		return arquivosReferenciados;
	}

	public void setArquivosReferenciados(List<ContagemItemArquivoReferenciado> arquivosReferenciados) {
		this.arquivosReferenciados = arquivosReferenciados;
	}
	
}
