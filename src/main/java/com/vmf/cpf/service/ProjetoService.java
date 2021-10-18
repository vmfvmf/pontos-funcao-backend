package com.vmf.cpf.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vmf.cpf.dto.ProjetoDto;
import com.vmf.cpf.entities.Projeto;
import com.vmf.cpf.mappers.AbstractMapperBase;
import com.vmf.cpf.mappers.ProjetoMapper;

@Service("projetoService")
public class ProjetoService extends AbstractService<ProjetoDto, Projeto> {

	@Autowired
	private ProjetoMapper mapper;
	
	@Override
	public void prepareToSave(Projeto entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public AbstractMapperBase<ProjetoDto, Projeto> getMapper() {
		return mapper;
	}
	
}
