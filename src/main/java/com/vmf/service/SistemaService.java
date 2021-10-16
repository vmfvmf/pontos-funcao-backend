package com.vmf.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vmf.dto.SistemaDto;
import com.vmf.entities.Sistema;
import com.vmf.mappers.AbstractMapperBase;
import com.vmf.mappers.SistemaMapper;

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
