package com.vmf.dto;

import java.time.LocalDate;

import com.vmf.entities.AbstractContagemItem;
import com.vmf.enums.ComplexidadeItemEnum;
import com.vmf.enums.ContagemItemFuncaoEnum;
import com.vmf.enums.ContagemDadoSituacaoEnum;
import com.vmf.interfaces.IHaveCriadoModificadoId;

public abstract class AbstractContagemItemDto extends AbstractBaseDto implements IHaveCriadoModificadoId {
	
	private String nome;
	private Integer td;
	private Integer tr;	
	private ComplexidadeItemEnum complexidade;
	private Integer pf;
	private ContagemItemFuncaoEnum funcao;
	private LocalDate criado;
	private LocalDate modificado;
	
	private ContagemDadoSituacaoEnum alteradoDadoContagem;
	private Integer alteradoTd;
	private Integer alteradoTr;	
	private ComplexidadeItemEnum alteradoComplexidade;
	private Integer alteradoPf;
	private ContagemItemFuncaoEnum alteradoFuncao;

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
	

	public void checkComparacao(AbstractContagemItem anterior) {
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
		if (!getPf().equals(anterior.getPf())) {
			setPf(anterior.getPf());
			setAlteradoDadoContagem(ContagemDadoSituacaoEnum.ALTERADO);			
		}
		if (!getFuncao().equals(anterior.getFuncao())) {
			setFuncao(anterior.getFuncao());
			setAlteradoDadoContagem(ContagemDadoSituacaoEnum.ALTERADO);			
		}
	}
	
}
