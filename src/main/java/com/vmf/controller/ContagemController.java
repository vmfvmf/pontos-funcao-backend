package com.vmf.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.vmf.dto.ContagemDto;
import com.vmf.mappers.AbstractMapperBase;
import com.vmf.mappers.ContagemMapper;
import com.vmf.model.Contagem;
import com.vmf.service.AbstractService;
import com.vmf.service.ContagemService;

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
