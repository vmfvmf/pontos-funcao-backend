package com.example.demo.service;

import org.springframework.stereotype.Service;

import com.example.demo.model.Sprint;

@Service("sprintService")
public class SprintService extends AbstractService<Sprint>{
	@Override
	public void prepareToSave(Sprint entity) {
		// TODO Auto-generated method stub
		
	}

	
}
