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

import com.vmf.dto.SprintDto;
import com.vmf.mappers.AbstractMapperBase;
import com.vmf.mappers.SprintMapper;
import com.vmf.model.Sprint;
import com.vmf.service.AbstractService;
import com.vmf.service.SprintService;

@RestController
public class SprintController extends AbstractController<SprintDto, Sprint>{

	@Autowired
	private SprintService sprintService;
	
	@Autowired
	private SprintMapper sprintMapper;

	@GetMapping("/sprints")
	public List<SprintDto> findAllSprints(SprintDto filtro) {
		return super.findAll(filtro);
	}

	@GetMapping("/sprints/{id}")
	public SprintDto getSprintById(@PathVariable long id) throws Exception {
		return super.getEntityById(id);
	}

	@PostMapping("/sprints")
	public SprintDto novoSprint(@RequestBody SprintDto dto) throws Exception {
		return super.newEntity(dto);
	}
	
	@PutMapping("/sprints")
	public SprintDto editarSistema(@RequestBody SprintDto dto) throws Exception {
		return super.editEntity(dto);
	}
	
	@DeleteMapping("/sprints/{id}")
	public void deleteSprint(@PathVariable long id) {
		super.deleteEntity(id);
	}

	@Override
	protected AbstractMapperBase<SprintDto, Sprint> getMapper() {
		return sprintMapper;
	}

	@Override
	protected AbstractService<Sprint> getService() {
		return sprintService;
	}

}
