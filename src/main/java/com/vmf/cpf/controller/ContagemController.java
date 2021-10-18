package com.vmf.cpf.controller;

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

import com.vmf.cpf.dto.ContagemDto;
import com.vmf.cpf.entities.Contagem;
import com.vmf.cpf.service.AbstractService;
import com.vmf.cpf.service.ContagemService;

@Transactional
@RestController
public class ContagemController extends AbstractController<ContagemDto, Contagem>{

	@Autowired
	private ContagemService contagemService;
	
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
	public ContagemDto getContagemById(@PathVariable Long id) throws Exception {
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
	
	@GetMapping("/contagens/{id}/comparar-versao-anterior/{idVersaoAnterior}")
	public ContagemDto compararContagens(@PathVariable Long id, @PathVariable Long idVersaoAnterior) throws Exception {
		return contagemService.compararVersoes(id, idVersaoAnterior);
	}
	
	
	@DeleteMapping("/contagens/{id}")
	public void delete(@PathVariable Long id) {
		super.deleteEntity(id);
	}

	@Override
	protected AbstractService<ContagemDto, Contagem> getService() {
		return contagemService;
	}

}
