package com.vmf.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vmf.dto.GrupoDto;
import com.vmf.entities.Grupo;
import com.vmf.mappers.AbstractMapperBase;
import com.vmf.mappers.GrupoMapper;

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
