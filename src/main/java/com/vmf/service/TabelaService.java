package com.vmf.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.vmf.model.Tabela;

@Service("tabelaService")
public class TabelaService extends AbstractService<Tabela>{
	public List<Tabela> salvaEmLote(List<Tabela> tabelas) {
		return repository.saveAll(tabelas);
	}


	@Override
	public void prepareToSave(Tabela entity) {
		// TODO Auto-generated method stub
		
	}


}
