package com.vmf.service;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.stereotype.Service;

import com.vmf.entities.TransacaoTD;
import com.vmf.interfaces.ITrataEsbocoIncrementoVersaoSetTDColuna;

@Configurable
@Service("transacaoTDService")
public class TransacaoTDService extends AbstractService<TransacaoTD>{
	private ITrataEsbocoIncrementoVersaoSetTDColuna iTrataEsbocoIncrementoVersaoSetTDColuna;

	@Override
	public void prepareToSave(TransacaoTD entity) {
		// TODO Auto-generated method stub
		
	}

	public void setITrataEsbocoIncrementoVersaoSetTDColuna(ITrataEsbocoIncrementoVersaoSetTDColuna iTrataEsbocoIncrementoVersaoSetTDColuna) {
		this.iTrataEsbocoIncrementoVersaoSetTDColuna = iTrataEsbocoIncrementoVersaoSetTDColuna;
	}
	
	public Boolean verificaEntidadeModificada(TransacaoTD entidade) {
		Boolean modificada = false;
		if (entidade.getId() == null) {
			entidade.setCriado(LocalDate.now());
			modificada = true;
		}
		return modificada;
	}

	public void trataEsbocoIncrementoVersao(TransacaoTD td) {
		TransacaoTD tdparent = findById(td.getId()).get();
		td.setTransacaoTDOrigem(tdparent);
		setCriadoModificadoId(td);
		
		iTrataEsbocoIncrementoVersaoSetTDColuna.execute(td);
	}

}
