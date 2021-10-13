package com.example.demo.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.example.demo.enums.ContagemEstado;


public class ContagemDto extends AbstractBaseDto{
	private SistemaDto sistema;
	private String contador;
	private Date dataContagem;
	private String escopo;
	private Integer totalPontosFuncao;
	private ProjetoDto projeto;
	private SprintDto sprint;
	private ContagemEstado estado;
	private Integer versao;
	private List<ContagemItemTransacaoDto> transacoes = new ArrayList<>();
	private List<ContagemItemArquivoReferenciadoDto> arquivosReferenciados = new ArrayList<>();
	
	public Integer getTotalPontosFuncao() {
		return totalPontosFuncao;
	}

	public void setTotalPontosFuncao(Integer totalPontosFuncao) {
		this.totalPontosFuncao = totalPontosFuncao;
	}

	public SprintDto getSprint() {
		return sprint;
	}

	public void setSprint(SprintDto sprint) {
		this.sprint = sprint;
	}
	
	public ProjetoDto getProjeto() {
		return projeto;
	}

	public void setProjeto(ProjetoDto projeto) {
		this.projeto = projeto;
	}

	public SistemaDto getSistema() {
		return sistema;
	}

	public void setSistema(SistemaDto sistema) {
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

	public List<ContagemItemTransacaoDto> getTransacoes() {
		return transacoes;
	}

	public void setTransacoes(List<ContagemItemTransacaoDto> transacoes) {
		this.transacoes = transacoes;
	}

	public List<ContagemItemArquivoReferenciadoDto> getArquivosReferenciados() {
		return arquivosReferenciados;
	}

	public void setArquivosReferenciados(List<ContagemItemArquivoReferenciadoDto> arquivosReferenciados) {
		this.arquivosReferenciados = arquivosReferenciados;
	}
	
}
