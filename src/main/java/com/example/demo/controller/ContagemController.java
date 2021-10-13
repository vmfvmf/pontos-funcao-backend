package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.ContagemDto;
import com.example.demo.mappers.AbstractMapperBase;
import com.example.demo.mappers.ContagemMapper;
import com.example.demo.model.Contagem;
import com.example.demo.service.AbstractService;
import com.example.demo.service.ContagemService;

@RestController
public class ContagemController extends AbstractController<ContagemDto, Contagem>{

	@Autowired
	private ContagemService contagemService;
	
	@Autowired
	private ContagemMapper contagemMapper;
	
	@GetMapping("/contagens")
	public List<ContagemDto> findAllContagens(ContagemDto filtro) {
		return super.findAll(filtro);
	}
	
	@GetMapping("/contagens/{id}")
	public ContagemDto getContagemById(@PathVariable Integer id) throws Exception {
		return super.getEntityById(id);
	}
	
	@PostMapping("/contagens")
	public ContagemDto novo(@RequestBody ContagemDto dto) throws Exception {
		return super.newEntity(dto);
	}
	
	@PostMapping("/contagens/{id}/versionar")
	public ContagemDto versionar(@PathVariable Long id) throws Exception {
		((ContagemService)getService()).setVersionar();
		return super.editEntity(id);
	}
	
	@DeleteMapping("/contagens/{id}")
	public void delete(@PathVariable Long id) {
		super.deleteEntity(id);
	}

	@Override
	protected AbstractMapperBase<ContagemDto, Contagem> getMapper() {
		return contagemMapper;
	}

	@Override
	protected AbstractService<Contagem> getService() {
		return contagemService;
	}

}
