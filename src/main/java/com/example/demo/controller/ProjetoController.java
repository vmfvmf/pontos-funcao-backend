package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
//import org.springframework.hateoas.Resource;
//import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.ProjetoDto;
import com.example.demo.mappers.AbstractMapperBase;
import com.example.demo.mappers.ProjetoMapper;
import com.example.demo.model.Projeto;
import com.example.demo.service.AbstractService;
import com.example.demo.service.ProjetoService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class ProjetoController extends AbstractController<ProjetoDto, Projeto>{

	@Autowired
	private ProjetoService service;
	
	@Autowired
	private ProjetoMapper mapper;
		
	@GetMapping("/projetos")
	public List<ProjetoDto> findAllDeds() {
		return super.findAll();
	}

	@GetMapping("/projetos/{id}")
	public ProjetoDto getEntityById(@PathVariable long id) throws Exception {
		return super.getEntityById(id);
	}

	@PostMapping("/projetos")
	public ProjetoDto newEntity(@RequestBody ProjetoDto dto) throws Exception {
		return super.newEntity(dto);
	}
	
	@PutMapping("/projetos")
	public ProjetoDto editEntity(@RequestBody ProjetoDto dto) throws Exception {
		return super.editEntity(dto);
	}
	
	@DeleteMapping("/projetos/{id}")
	public void deleteEntity(@PathVariable Long id) {
		super.deleteEntity(id);
	}

	@Override
	protected AbstractMapperBase<ProjetoDto, Projeto> getMapper() {
		return mapper;
	}

	@Override
	protected AbstractService<Projeto> getService() {
		return service;
	}
}
