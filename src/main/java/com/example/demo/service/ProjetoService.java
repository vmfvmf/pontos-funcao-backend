package com.example.demo.service;

import org.springframework.stereotype.Service;

import com.example.demo.model.Projeto;

@Service("projetoService")
public class ProjetoService extends AbstractService<Projeto> {

	@Override
	public void prepareToSave(Projeto entity) {
		// TODO Auto-generated method stub
		
	}
	
}
