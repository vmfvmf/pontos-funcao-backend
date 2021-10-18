package com.vmf.cpf.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vmf.cpf.dto.GrupoDto;
import com.vmf.cpf.entities.Grupo;
import com.vmf.cpf.mappers.AbstractMapperBase;
import com.vmf.cpf.mappers.GrupoMapper;

@Service("grupoTransacaoService")
public class GrupoTransacaoService extends AbstractService<GrupoDto, Grupo> {

	@Autowired
	private GrupoMapper mapper;
	
	@Override
	public void prepareToSave(Grupo entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public AbstractMapperBase<GrupoDto, Grupo> getMapper() {
		return mapper;
	}
	
}
