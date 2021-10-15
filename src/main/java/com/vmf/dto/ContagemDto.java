package com.vmf.dto;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.vmf.enums.ContagemEscopoEnum;
import com.vmf.enums.ContagemEstado;


public class ContagemDto extends AbstractBaseDto{
	private SistemaDto sistema;
	private String contador;
	private LocalDate dataContagem;
	private ContagemEscopoEnum escopo;
	private Integer totalPontosFuncao;
	private ProjetoDto projeto;
	private SprintDto sprint;
	private ContagemEstado estado;
	private Integer versao;
	private List<ContagemItemTransacaoDto> transacoes = new ArrayList<>();
	private List<ContagemItemArquivoReferenciadoDto> arquivosReferenciados = new ArrayList<>();
	private LocalDate criado; 
	private LocalDate modificado;
	private Boolean ultimaVersao;
	
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

	public LocalDate getDataContagem() {
		return dataContagem;
	}

	public void setDataContagem(LocalDate dataContagem) {
		this.dataContagem = dataContagem;
	}

	public ContagemEscopoEnum getEscopo() {
		return escopo;
	}

	public void setEscopo(ContagemEscopoEnum escopo) {
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

	public Boolean getUltimaVersao() {
		return ultimaVersao;
	}

	public void setUltimaVersao(Boolean ultimaVersao) {
		this.ultimaVersao = ultimaVersao;
	}	
}
