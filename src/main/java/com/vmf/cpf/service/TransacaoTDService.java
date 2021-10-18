package com.vmf.cpf.service;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.stereotype.Service;

import com.vmf.cpf.dto.TransacaoTDDto;
import com.vmf.cpf.entities.TransacaoTD;
import com.vmf.cpf.interfaces.ITrataEsbocoIncrementoVersaoSetTDColuna;
import com.vmf.cpf.mappers.AbstractMapperBase;
import com.vmf.cpf.mappers.TransacaoTDMapperConverter;

@Configurable
@Service("transacaoTDService")
public class TransacaoTDService extends AbstractService<TransacaoTDDto, TransacaoTD>{
	private ITrataEsbocoIncrementoVersaoSetTDColuna iTrataEsbocoIncrementoVersaoSetTDColuna;
	
	@Autowired
	private TransacaoTDMapperConverter mapper;

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
		td.setEntidadeOrigem(tdparent);
		setCriadoModificadoId(td);
		
		iTrataEsbocoIncrementoVersaoSetTDColuna.execute(td);
	}

	@Override
	public AbstractMapperBase<TransacaoTDDto, TransacaoTD> getMapper() {
		return mapper;
	}

}
