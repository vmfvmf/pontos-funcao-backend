package com.vmf.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.vmf.dto.GrupoDto;
import com.vmf.entities.Grupo;
import com.vmf.mappers.AbstractMapperBase;
import com.vmf.mappers.GrupoMapper;
import com.vmf.service.AbstractService;
import com.vmf.service.GrupoTransacaoService;

@RestController
public class GrupoController extends AbstractController<GrupoDto, Grupo>{
	
	@Autowired
	private GrupoTransacaoService service;
	
	@Autowired
	private GrupoMapper mapper;
		
	@GetMapping("/grupos")
	public List<GrupoDto> findAllGrupos() {
		return super.findAll();
	}

	@GetMapping("/grupos/{id}")
	public GrupoDto getGrupoById(@PathVariable Long id) throws Exception {
		return super.getEntityById(id);
	}

	@PostMapping("/grupos")
	public GrupoDto newGrupo(@RequestBody GrupoDto dto) throws Exception {
		return super.newEntity(dto);
	}
	
	@PutMapping("/grupos")
	public GrupoDto editarGrupo(@RequestBody GrupoDto dto) throws Exception {
		return super.editEntity(dto);
	}
	
	@DeleteMapping("/grupos/{id}")
	public void deleteGrupo(@PathVariable Long id) {
		super.deleteEntity(id);
	}


	@Override
	protected AbstractMapperBase<GrupoDto, Grupo> getMapper() {
		return mapper;
	}

	@Override
	protected AbstractService<Grupo> getService() {
		return service;
	}
}