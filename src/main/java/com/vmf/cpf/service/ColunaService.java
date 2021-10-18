package com.vmf.cpf.service;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vmf.cpf.dto.ColunaDto;
import com.vmf.cpf.entities.Coluna;
import com.vmf.cpf.mappers.AbstractMapperBase;
import com.vmf.cpf.mappers.ColunaConverter;

@Service("colunaService")
public class ColunaService extends AbstractService<ColunaDto, Coluna> {

	@Autowired
	private ColunaConverter mapper;
	
	@Override
	public void prepareToSave(Coluna entity) {
		// TODO Auto-generated method stub
		
	}
	
	public void verificaEntidadeModificada(Coluna entidade) {
		if (entidade.getId() == null) {
			entidade.setCriado(LocalDate.now());
			return;
		}
		
		Coluna original = findById(entidade.getId()).get();
		
		if (!entidade.equals(original)) {
			entidade.setModificado(LocalDate.now());
		}
	}

	public void trataEsbocoIncrementoVersao(Coluna coluna) {
		Coluna colParent = findById(coluna.getId()).get();
		coluna.setEntidadeOrigem(colParent);
		setCriadoModificadoId(coluna);
	}

	@Override
	public AbstractMapperBase<ColunaDto, Coluna> getMapper() {
		return mapper;
	}

}