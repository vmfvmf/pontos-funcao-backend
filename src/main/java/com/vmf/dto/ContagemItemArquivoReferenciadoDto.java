package com.vmf.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.vmf.entities.AbstractContagemItem;
import com.vmf.entities.ContagemItemArquivoReferenciado;
import com.vmf.interfaces.IDtoComparaVersao;

public class ContagemItemArquivoReferenciadoDto extends AbstractContagemItemDto 
implements IDtoComparaVersao<ContagemItemArquivoReferenciado, TabelaDto> {

	private List<TabelaDto> tabelas;

	public List<TabelaDto> getTabelas() {
		return tabelas;
	}

	public void setTabelas(List<TabelaDto> tabelas) {
		this.tabelas = tabelas;
	}	

	@Override
	public void checkComparacao(ContagemItemArquivoReferenciado anterior) {
		super.checkComparacao((AbstractContagemItem)anterior);
	}

	@JsonIgnore
	@Override
	public List<TabelaDto> getObjetos() {
		return getTabelas();
	}

}
