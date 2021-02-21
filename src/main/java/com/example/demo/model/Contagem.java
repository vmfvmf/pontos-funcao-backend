package com.example.demo.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@NamedQuery(name = "Contagem.joinSistema",
query = "SELECT c FROM Contagem c INNER JOIN FETCH c.sistema s where c.escopo = ?1")
@Table
public class Contagem extends Base {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2825945051996560941L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@ManyToOne
	@JoinColumn(name="sistema_id", nullable=false)
	@JsonIgnoreProperties("contagens")
	private Sistema sistema;
	
	@Column(name = "contador", nullable = false)
	private String contador;
	
	@Column(name = "data_contagem", nullable = false)
	private Date dataContagem;
	
	@Column(name = "escopo", nullable = false) // "0=sistema, 1=projeto, 2=sprint"
	private String escopo;
	
	@Column
	private Integer totalPf;
	
	@OneToOne
	@JoinColumn(name="ded_id")
	@JsonIgnoreProperties("contagem")
	private Ded ded;

	@ManyToOne
	@JoinColumn(name="sprint_id")
	@JsonIgnoreProperties("contagem")
	private Sprint sprint;

	public Sprint getSprint() {
		return sprint;
	}

	public void setSprint(Sprint sprint) {
		this.sprint = sprint;
	}
	
	public Ded getDed() {
		return ded;
	}

	public void setDed(Ded ded) {
		this.ded = ded;
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

	public Integer getTotalPf() {
		return totalPf;
	}

	public void setTotalPf(Integer totalPf) {
		this.totalPf = totalPf;
	}
	
}
