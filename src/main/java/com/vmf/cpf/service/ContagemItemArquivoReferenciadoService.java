package com.vmf.cpf.service;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vmf.cpf.dto.ContagemItemArquivoReferenciadoDto;
import com.vmf.cpf.entities.ContagemItemArquivoReferenciado;
import com.vmf.cpf.entities.Tabela;
import com.vmf.cpf.mappers.ContagemItemArquivoReferenciadoConverter;

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
		ContagemItemArquivoReferenciado arqParent = findById(arquivo.getId()).get();
		arquivo.setEntidadeOrigem(arqParent);
		setCriadoModificadoId(arquivo);
		
		for (Tabela tabela : arquivo.getTabelas()) {
			tabela.setArquivoReferenciado(arquivo);
			tabelaService.trataEsbocoIncrementoVersao(tabela);
		}
	}

	@Override
	public ContagemItemArquivoReferenciadoConverter getMapper() {
		return mapper;
	}
}
