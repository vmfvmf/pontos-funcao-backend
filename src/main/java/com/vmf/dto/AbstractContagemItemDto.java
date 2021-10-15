package com.vmf.dto;

import java.time.LocalDate;

import com.vmf.enums.ComplexidadeItemEnum;
import com.vmf.enums.ContagemItemFuncaoEnum;

public abstract class AbstractContagemItemDto extends AbstractBaseDto{
	private String nome;
	private Integer td;
	private Integer tr;	
	private ComplexidadeItemEnum complexidade;
	private Integer pf;
	private ContagemItemFuncaoEnum funcao;
	private LocalDate criado;
	private LocalDate modificado;

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

	public ComplexidadeItemEnum getComplexidade() {
		return complexidade;
	}

	public void setComplexidade(ComplexidadeItemEnum complexidade) {
		this.complexidade = complexidade;
	}

	public Integer getPf() {
		return pf;
	}

	public void setPf(Integer pf) {
		this.pf = pf;
	}

	public ContagemItemFuncaoEnum getFuncao() {
		return funcao;
	}

	public void setFuncao(ContagemItemFuncaoEnum funcao) {
		this.funcao = funcao;
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
	
}
