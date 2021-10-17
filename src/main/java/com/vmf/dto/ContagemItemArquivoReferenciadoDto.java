package com.vmf.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.vmf.entities.ContagemItemArquivoReferenciado;
import com.vmf.interfaces.IDtoComparaVersao;

public class ContagemItemArquivoReferenciadoDto extends AbstractContagemItemDto<ContagemItemArquivoReferenciado>
implements IDtoComparaVersao<ContagemItemArquivoReferenciado, TabelaDto> {

	private List<TabelaDto> tabelas;

	public List<TabelaDto> getTabelas() {
		return tabelas;
	}

	public void setTabelas(List<TabelaDto> tabelas) {
		this.tabelas = tabelas;
	}	

	@JsonIgnore
	@Override
	public List<TabelaDto> getObjetos() {
		return getTabelas();
	}

	public void checkComparacao(ContagemItemArquivoReferenciado entidadeAnterior) {
		super.checkSuperComparacao(entidadeAnterior);
	}

}
