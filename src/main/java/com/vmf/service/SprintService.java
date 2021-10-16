package com.vmf.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vmf.dto.SprintDto;
import com.vmf.entities.Sprint;
import com.vmf.mappers.AbstractMapperBase;
import com.vmf.mappers.SprintMapper;

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
