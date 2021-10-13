package com.example.demo.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table
public class Sprint extends Base {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6696627300095246781L;	

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "numero", nullable = false)
	private Integer numero;
	
	@Column(name = "data_inicio")
	private Date dataInicio;
	
	@Column(name = "data_fim")
	private Date dataFim;
	
	@Column(name = "dias_uteis")
	private Integer diasUteis;
	
	@ManyToOne()
	@JoinColumn(name="projeto_id", nullable=false)
	private Projeto projeto;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public Projeto getProjeto() {
		return projeto;
	}

	public void setProjeto(Projeto projeto) {
		this.projeto = projeto;
	}

	public Integer getNumero() {
		return numero;
	}

	public void setNumero(Integer numero) {
		this.numero = numero;
	}

	public Date getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(Date dataInicio) {
		this.dataInicio = dataInicio;
	}

	public Date getDataFim() {
		return dataFim;
	}

	public void setDataFim(Date dataFim) {
		this.dataFim = dataFim;
	}

	public Integer getDiasUteis() {
		return diasUteis;
	}

	public void setDiasUteis(Integer diasUteis) {
		this.diasUteis = diasUteis;
	}

}
