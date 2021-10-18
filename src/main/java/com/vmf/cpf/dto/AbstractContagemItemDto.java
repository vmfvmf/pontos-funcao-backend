package com.vmf.cpf.dto;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.vmf.cpf.entities.AbstractContagemItem;
import com.vmf.cpf.enums.ComplexidadeItemEnum;
import com.vmf.cpf.enums.ContagemDadoSituacaoEnum;
import com.vmf.cpf.enums.ContagemItemFuncaoEnum;
import com.vmf.cpf.interfaces.IHaveCriadoModificadoId;
import com.vmf.cpf.interfaces.IHaveEntidadeOrigem;

public abstract class AbstractContagemItemDto<T> extends AbstractBaseDto implements IHaveCriadoModificadoId, IHaveEntidadeOrigem<T> {
	
	private String nome;
	private Integer td;
	private Integer tr;	
	private ComplexidadeItemEnum complexidade;
	private Integer pf;
	private ContagemItemFuncaoEnum funcao;
	private LocalDate criado;
	private LocalDate modificado;
	
	@JsonIgnore
	private Boolean compararVersao;
	
	private ContagemDadoSituacaoEnum alteradoDadoContagem;
	private String alteradoNome;
	private Integer alteradoTd;
	private Integer alteradoTr;	
	private ComplexidadeItemEnum alteradoComplexidade;
	private Integer alteradoPf;
	private ContagemItemFuncaoEnum alteradoFuncao;
	
	@JsonIgnore
	private AbstractContagemItem<T> entidadeOrigem;

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
	
	public String getAlteradoNome() {
		return alteradoNome;
	}

	public void setAlteradoNome(String alteradoNome) {
		this.alteradoNome = alteradoNome;
	}

	public Integer getAlteradoTd() {
		return alteradoTd;
	}

	public void setAlteradoTd(Integer alteradoTd) {
		this.alteradoTd = alteradoTd;
	}

	public Integer getAlteradoTr() {
		return alteradoTr;
	}

	public void setAlteradoTr(Integer alteradoTr) {
		this.alteradoTr = alteradoTr;
	}

	public ComplexidadeItemEnum getAlteradoComplexidade() {
		return alteradoComplexidade;
	}

	public Boolean getCompararVersao() {
		return compararVersao;
	}

	public void setCompararVersao(Boolean compararVersao) {
		this.compararVersao = compararVersao;
	}

	public void setAlteradoComplexidade(ComplexidadeItemEnum alteradoComplexidade) {
		this.alteradoComplexidade = alteradoComplexidade;
	}

	public Integer getAlteradoPf() {
		return alteradoPf;
	}

	public void setAlteradoPf(Integer alteradoPf) {
		this.alteradoPf = alteradoPf;
	}

	public ContagemItemFuncaoEnum getAlteradoFuncao() {
		return alteradoFuncao;
	}

	public void setAlteradoFuncao(ContagemItemFuncaoEnum alteradoFuncao) {
		this.alteradoFuncao = alteradoFuncao;
	}

	public ContagemDadoSituacaoEnum getAlteradoDadoContagem() {
		return alteradoDadoContagem;
	}

	public void setAlteradoDadoContagem(ContagemDadoSituacaoEnum alteradoDadoContagem) {
		this.alteradoDadoContagem = alteradoDadoContagem;
	}	
	

	public AbstractContagemItem<T> getEntidadeOrigem() {
		return entidadeOrigem;
	}

	public void setEntidadeOrigem(AbstractContagemItem<T> entidadeOrigem) {
		this.entidadeOrigem = entidadeOrigem;
	}

	protected void checkSuperComparacao(AbstractContagemItem<T> anterior) {
		if (!getNome().equals(anterior.getNome())) {
			setAlteradoNome(anterior.getNome());
			setAlteradoDadoContagem(ContagemDadoSituacaoEnum.ALTERADO);
		}
		if (!getTd().equals(anterior.getTd())) {
			setAlteradoTd(anterior.getTd());
			setAlteradoDadoContagem(ContagemDadoSituacaoEnum.ALTERADO);
		}
		if (!getTr().equals(anterior.getTr())) {
			setAlteradoTr(anterior.getTr());
			setAlteradoDadoContagem(ContagemDadoSituacaoEnum.ALTERADO);			
		}
		if (!getComplexidade().equals(anterior.getComplexidade())) {
			setAlteradoComplexidade(anterior.getComplexidade());
			setAlteradoDadoContagem(ContagemDadoSituacaoEnum.ALTERADO);			
		}
		if (!getPf().equals(anterior.getPontosFuncao())) {
			setAlteradoPf(anterior.getPontosFuncao());
			setAlteradoDadoContagem(ContagemDadoSituacaoEnum.ALTERADO);			
		}
		if (!getFuncao().equals(anterior.getFuncao())) {
			setAlteradoFuncao(anterior.getFuncao());
			setAlteradoDadoContagem(ContagemDadoSituacaoEnum.ALTERADO);			
		}
	}
	
}
