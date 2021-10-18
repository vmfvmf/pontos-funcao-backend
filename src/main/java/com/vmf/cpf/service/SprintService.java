package com.vmf.cpf.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vmf.cpf.dto.SprintDto;
import com.vmf.cpf.entities.Sprint;
import com.vmf.cpf.mappers.AbstractMapperBase;
import com.vmf.cpf.mappers.SprintMapper;

@Service("sprintService")
public class SprintService extends AbstractService<SprintDto, Sprint>{
	@Autowired
	private SprintMapper mapper;
	
	@Override
	public void prepareToSave(Sprint entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public AbstractMapperBase<SprintDto, Sprint> getMapper() {
		return mapper;
	}

	
}
