package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.SistemaDto;
import com.example.demo.mappers.AbstractMapperBase;
import com.example.demo.mappers.SistemaMapper;
import com.example.demo.model.Sistema;
import com.example.demo.service.AbstractService;
import com.example.demo.service.SistemaService;

@RestController
public class SistemaController extends AbstractController<SistemaDto, Sistema> {

	@Autowired
	private SistemaService sistemaService;
	
	@Autowired
	private SistemaMapper sistemaMapper;

	@GetMapping("/sistemas")
	public List<SistemaDto> findAllSistemas() {
		return super.findAll();
	}

	@GetMapping("/sistemas/{id}")
	public SistemaDto getSistemaById(@PathVariable long id) throws Exception {
		return super.getEntityById(id);
	}

	@PostMapping("/sistemas")
	public SistemaDto create(@RequestBody SistemaDto dto) throws Exception {
		return super.newEntity(dto);
	}
	
	@PutMapping("/sistemas")
	public SistemaDto edit(@RequestBody SistemaDto dto) throws Exception {
		return super.editEntity(dto);
	}

	@DeleteMapping("/sistemas/{id}")
	public void deleteSistema(@PathVariable Long id) {
		super.deleteEntity(id);
	}

	@Override
	protected AbstractMapperBase<SistemaDto, Sistema> getMapper() {
		return sistemaMapper;
	}

	@Override
	protected AbstractService<Sistema> getService() {
		return sistemaService;
	}
}
