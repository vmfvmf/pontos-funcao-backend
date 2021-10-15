package com.vmf.controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.vmf.dto.ContagemDto;
import com.vmf.entities.Contagem;
import com.vmf.mappers.AbstractMapperBase;
import com.vmf.mappers.ContagemMapper;
import com.vmf.service.AbstractService;
import com.vmf.service.ContagemService;

@Transactional
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
	
	@GetMapping("/contagens/{id}/versoes")
	public List<ContagemDto> findAllVersoes(@PathVariable Long id, ContagemDto filtro) {
		Integer versao = filtro.getVersao();
		filtro.setId(null);
		filtro.setVersao(null);
		return super.findAll(filtro).stream()
				.filter(contagem -> contagem.getId() != id && contagem.getVersao() < versao)
				.collect(Collectors.toList());
	}
	
	@GetMapping("/contagens/{id}")
	public ContagemDto getContagemById(@PathVariable Integer id) throws Exception {
		return super.getEntityById(id);
	}
	
	@PostMapping("/contagens")
	public ContagemDto novo(@RequestBody ContagemDto dto) throws Exception {
		return super.newEntity(dto);
	}
	
	@GetMapping("/contagens/{id}/versionar")
	public ContagemDto versionar(@PathVariable Long id) throws Exception {
		contagemService.setVersionar();
		return super.editEntity(id);
	}
	
	@Transactional
	@GetMapping("/contagens/{id}/criar_novo_esboco_incremento_versao")
	public ContagemDto criarNovoEsbocoIncrementoVersao(@PathVariable Long id) throws Exception {
		contagemService.setCriarNovoEsbocoIncrementoVersao();
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
