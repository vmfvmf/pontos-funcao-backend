package com.vmf.dto;

import java.util.List;

public class ContagemItemArquivoReferenciadoDto extends AbstractContagemItemDto {

	private List<TabelaDto> tabelas;

	public List<TabelaDto> getTabelas() {
		return tabelas;
	}

	public void setTabelas(List<TabelaDto> tabelas) {
		this.tabelas = tabelas;
	}
	
}
