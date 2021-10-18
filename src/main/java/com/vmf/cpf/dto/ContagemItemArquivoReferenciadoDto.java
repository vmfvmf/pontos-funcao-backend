package com.vmf.cpf.dto;

import java.util.List;

import com.vmf.cpf.entities.ContagemItemArquivoReferenciado;

public class ContagemItemArquivoReferenciadoDto extends AbstractContagemItemDto<ContagemItemArquivoReferenciado> {

	private List<TabelaDto> tabelas;
	
	public List<TabelaDto> getTabelas() {
		return tabelas;
	}

	public void setTabelas(List<TabelaDto> tabelas) {
		this.tabelas = tabelas;
	}	

	public void checkComparacao(ContagemItemArquivoReferenciado entidadeAnterior) {
		super.checkSuperComparacao(entidadeAnterior);
	}
}
