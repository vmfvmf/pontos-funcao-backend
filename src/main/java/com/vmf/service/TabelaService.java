package com.vmf.service;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vmf.entities.Coluna;
import com.vmf.entities.Tabela;

@Service("tabelaService")
public class TabelaService extends AbstractService<Tabela>{
	@Autowired
	private ColunaService colunaService;

	@Override
	public void prepareToSave(Tabela entity) {
		entity.getColunas().forEach(c -> {
			c.setTabela(entity);
		});
		if (entity.getId() == null) {
			entity.setCriado(LocalDate.now());
		}
	}

	public void ajustaEntidadeModificada(Tabela entidade) {
		// VERIFICA ANTES PRA NÃO REPETIR
		for (Coluna coluna : entidade.getColunas()) {
			coluna.setTabela(entidade);
			colunaService.verificaEntidadeModificada(coluna);
		}
		
		if (entidade.getId() == null) {
			entidade.setCriado(LocalDate.now());
			return;
		}
		
		Tabela original = findById(entidade.getId()).get();
		
		if (!entidade.equals(original)) {
			entidade.setModificado(LocalDate.now());
		}
	}

	public void trataEsbocoIncrementoVersao(Tabela tabela) {
		Tabela tabParent = findById(tabela.getId()).get();
		tabela.setTabelaOrigem(tabParent);
		setCriadoModificadoId(tabela);
		
		for (Coluna coluna : tabela.getColunas()) {
			coluna.setTabela(tabela);
			colunaService.trataEsbocoIncrementoVersao(coluna);
		}
	}
}
