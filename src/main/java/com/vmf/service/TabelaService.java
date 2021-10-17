package com.vmf.service;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vmf.dto.TabelaDto;
import com.vmf.entities.Coluna;
import com.vmf.entities.Tabela;
import com.vmf.mappers.AbstractMapperBase;
import com.vmf.mappers.TabelaMapperConverter;

@Service("tabelaService")
public class TabelaService extends AbstractService<TabelaDto, Tabela>{
	
	@Autowired
	private TabelaMapperConverter mapper;
	
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
		tabela.setEntidadeOrigem(tabParent);
		setCriadoModificadoId(tabela);
		
		for (Coluna coluna : tabela.getColunas()) {
			coluna.setTabela(tabela);
			colunaService.trataEsbocoIncrementoVersao(coluna);
		}
	}

	@Override
	public AbstractMapperBase<TabelaDto, Tabela> getMapper() {
		return mapper;
	}
}
