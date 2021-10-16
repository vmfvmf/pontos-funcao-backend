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

import com.vmf.dto.TabelaDto;
import com.vmf.entities.Tabela;
import com.vmf.service.AbstractService;
import com.vmf.service.TabelaService;

@RestController
public class TabelaController extends AbstractController<TabelaDto, Tabela>{

	@Autowired
	private TabelaService tabelaService;
		
	@GetMapping("/tabelas")
	public List<TabelaDto> findAllTabelas(TabelaDto filtro) {
		return super.findAll(filtro);
	}

	@GetMapping("/tabelas/{id}")
	public TabelaDto getTabelaById(@PathVariable long id) throws Exception {
		return super.getEntityById(id);
	}

	@PostMapping("/tabelas")
	public TabelaDto novoTabela(@RequestBody TabelaDto dto) throws Exception {
		return super.newEntity(dto);
	}
	
	@PutMapping("/tabelas")
	public TabelaDto editarSistema(@RequestBody TabelaDto dto) throws Exception {
		return super.editEntity(dto);
	}
	
	@DeleteMapping("/tabelas/{id}")
	public void deleteTabela(@PathVariable long id) {
		super.deleteEntity(id);
	}

	@Override
	protected AbstractService<TabelaDto, Tabela> getService() {
		return tabelaService;
	}

}
