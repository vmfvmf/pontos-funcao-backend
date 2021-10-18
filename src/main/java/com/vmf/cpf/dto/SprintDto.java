package com.vmf.cpf.dto;

import java.util.Date;

public class SprintDto extends AbstractBaseDto {
	private Integer numero;
	private Date dataInicio;
	private Date dataFim;
	private Integer diasUteis;
	private ProjetoDto projeto;
	
	
	public ProjetoDto getProjeto() {
		return projeto;
	}

	public void setProjeto(ProjetoDto projeto) {
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
