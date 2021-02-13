package com.example.demo.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.example.demo.enums.EscopoContagemEnum;
import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@NamedQuery(name = "Contagem.joinSistema",
query = "SELECT c FROM Contagem c INNER JOIN FETCH c.sistema s where c.escopo = ?1")
@Table(uniqueConstraints=
        @UniqueConstraint(columnNames={"sistema_id", "sprint_id", "escopo"}))
public class Contagem extends Base {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2825945051996560941L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@JsonBackReference(value="sistema_contagens")
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="sistema_id", nullable=false)
	private Sistema sistema;

	@JsonBackReference(value="sprint")
	@ManyToOne()
	@JoinColumn(name="sprint_id", nullable=false)
	private Sprint sprint;
	
	@Column(name = "contador", nullable = false)
	private String contador;
	
	@Column(name = "data_contagem", nullable = false)
	private Date dataContagem;
	
	@Column(name = "escopo", nullable = false) // "0=sistema, 1=projeto, 2=sprint"
	private EscopoContagemEnum escopo;
	
	@OneToMany(mappedBy = "contagem")
	private List<FuncaoDados> funcaoDados = new ArrayList<FuncaoDados>();

	public List<FuncaoDados> getFuncaoDados() {
		return funcaoDados;
	}

	public void setFuncaoDados(List<FuncaoDados> funcaoDados) {
		this.funcaoDados = funcaoDados;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Sistema getSistema() {
		return sistema;
	}

	public void setSistema(Sistema sistema) {
		this.sistema = sistema;
	}

	public Sprint getSprint() {
		return sprint;
	}

	public void setSprint(Sprint sprint) {
		this.sprint = sprint;
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

	public EscopoContagemEnum getEscopo() {
		return escopo;
	}

	public void setEscopo(EscopoContagemEnum escopo) {
		this.escopo = escopo;
	}
	
}
