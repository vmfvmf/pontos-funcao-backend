package com.example.demo.service;

import org.springframework.stereotype.Service;

import com.example.demo.model.Sistema;

@Service("sistemaService")
public class SistemaService extends AbstractService<Sistema>{


	@Override
	public void prepareToSave(Sistema entity) {
		// TODO Auto-generated method stub
		
	}

}
