package com.vmf.cpf.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vmf.cpf.dto.SistemaDto;
import com.vmf.cpf.entities.Sistema;
import com.vmf.cpf.mappers.AbstractMapperBase;
import com.vmf.cpf.mappers.SistemaMapper;

@Service("sistemaService")
public class SistemaService extends AbstractService<SistemaDto, Sistema>{

	@Autowired
	private SistemaMapper mapper;
	
	@Override
	public void prepareToSave(Sistema entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public AbstractMapperBase<SistemaDto, Sistema> getMapper() {
		return mapper;
	}

}
