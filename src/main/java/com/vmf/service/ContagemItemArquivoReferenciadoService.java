package com.vmf.service;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vmf.dto.ContagemItemArquivoReferenciadoDto;
import com.vmf.entities.AbstractContagemItem;
import com.vmf.entities.ContagemItemArquivoReferenciado;
import com.vmf.entities.Tabela;
import com.vmf.mappers.AbstractMapperBase;
import com.vmf.mappers.ContagemItemArquivoReferenciadoConverter;

@Service("abstractContagemItemService")
public class ContagemItemArquivoReferenciadoService extends AbstractService<ContagemItemArquivoReferenciadoDto, ContagemItemArquivoReferenciado> {

	@Autowired
	private TabelaService tabelaService;
	
	@Autowired
	private ContagemItemArquivoReferenciadoConverter mapper;
	
	@Override
	public void prepareToSave(ContagemItemArquivoReferenciado entity) {
		// TODO Auto-generated method stub
		
	}
	
	public void ajustaEntidadeModificada(ContagemItemArquivoReferenciado entidade) {

		// VERIFICA ANTES PARA NÃO PRECISAR REPETIR
		for (Tabela tabela : entidade.getTabelas()) {
			tabela.setArquivoReferenciado(entidade);
			tabelaService.ajustaEntidadeModificada(tabela);
		}
		
		if (entidade.getId() == null) {
			entidade.setCriado(LocalDate.now());
			return;
		}
		
		ContagemItemArquivoReferenciado original = findById(entidade.getId()).get();
		
		if (!entidade.equals(original)) {
			entidade.setModificado(LocalDate.now());
		}
	}

	public void trataEsbocoIncrementoVersao(ContagemItemArquivoReferenciado arquivo) {
		AbstractContagemItem arqParent = findById(arquivo.getId()).get();
		arquivo.setContagemItemOrigem(arqParent);
		setCriadoModificadoId(arquivo);
		
		for (Tabela tabela : arquivo.getTabelas()) {
			tabela.setArquivoReferenciado(arquivo);
			tabelaService.trataEsbocoIncrementoVersao(tabela);
		}
	}

	@Override
	public AbstractMapperBase<ContagemItemArquivoReferenciadoDto, ContagemItemArquivoReferenciado> getMapper() {
		return mapper;
	}
}
