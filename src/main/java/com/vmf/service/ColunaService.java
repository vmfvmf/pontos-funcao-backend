package com.vmf.service;

import java.time.LocalDate;

import org.springframework.stereotype.Service;

import com.vmf.entities.Coluna;

@Service("colunaService")
public class ColunaService extends AbstractService<Coluna> {

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
		coluna.setColunaOrigem(colParent);
		setCriadoModificadoId(coluna);
	}

}