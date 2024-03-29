package com.vmf.cpf.filtro;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.vmf.cpf.entities.Projeto;
import com.vmf.cpf.entities.Sprint;

@JsonIgnoreProperties(ignoreUnknown = true)
public class FiltroSprintDto  {
	private long id;
	private int numero;
	private Date dataInicio;
	private Date dataFim;
	private int diasUteis;
	private Projeto dedId;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public int getNumero() {
		return numero;
	}
	public void setNumero(int numero) {
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
	public int getDiasUteis() {
		return diasUteis;
	}
	public void setDiasUteis(int diasUteis) {
		this.diasUteis = diasUteis;
	}
	public Projeto getDedId() {
		return dedId;
	}
	public void setDedId(Projeto dedId) {
		this.dedId = dedId;
	}

	public Sprint getSprint() {
		return new Sprint();
	}
    
}