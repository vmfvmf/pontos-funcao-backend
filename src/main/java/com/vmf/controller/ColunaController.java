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

import com.vmf.dto.ColunaDto;
import com.vmf.entities.Coluna;
import com.vmf.mappers.AbstractMapperBase;
import com.vmf.mappers.ColunaConverter;
import com.vmf.service.AbstractService;
import com.vmf.service.ColunaService;

@RestController
public class ColunaController extends AbstractController<ColunaDto, Coluna>{

	@Autowired
	private ColunaService colunaService;
	
	@Autowired
	private ColunaConverter mapper;

	@GetMapping("/colunas")
	public List<ColunaDto> findAllColunas(ColunaDto filtro) {
		return super.findAll(filtro);
	}

	@GetMapping("/colunas/{id}")
	public ColunaDto getColunaById(@PathVariable long id) throws Exception {
		return super.getEntityById(id);
	}

	@PostMapping("/colunas")
	public ColunaDto novoColuna(@RequestBody ColunaDto dto) throws Exception {
		return super.newEntity(dto);
	}
	
	@PutMapping("/colunas")
	public ColunaDto editarSistema(@RequestBody ColunaDto dto) throws Exception {
		return super.editEntity(dto);
	}
	
	@DeleteMapping("/colunas/{id}")
	public void deleteColuna(@PathVariable long id) {
		super.deleteEntity(id);
	}

	@Override
	protected AbstractMapperBase<ColunaDto, Coluna> getMapper() {
		return mapper;
	}

	@Override
	protected AbstractService<Coluna> getService() {
		return colunaService;
	}

}