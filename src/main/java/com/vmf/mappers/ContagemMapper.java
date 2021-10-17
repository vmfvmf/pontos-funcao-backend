package com.vmf.mappers;
import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vmf.dto.ContagemDto;
import com.vmf.entities.Contagem;
import com.vmf.enums.ContagemEscopoEnum;
import com.vmf.service.ContagemItemArquivoReferenciadoService;
import com.vmf.service.ContagemItemTransacaoService;
import com.vmf.service.ContagemService;

@Service("contagemMapper")
public class ContagemMapper extends AbstractMapperBase<ContagemDto, Contagem> {
	@Autowired
	private ContagemService service;

	@Autowired
	private ContagemItemTransacaoService transacaoService;
	
	@Autowired
	private ContagemItemArquivoReferenciadoService arquivoRefferenciadoService;
		
	@PostConstruct
	private void init() {
		getModelMapper().addConverter(((ContagemItemArquivoReferenciadoConverter)arquivoRefferenciadoService.getMapper())
				.convertToDto());
		getModelMapper().addConverter(((ContagemItemTransacaoConverter)transacaoService.getMapper())
				.convertToDto());
		
		getModelMapper().addConverter(((ContagemItemArquivoReferenciadoConverter)arquivoRefferenciadoService.getMapper())
			.convertToEntity());
		getModelMapper().addConverter(((ContagemItemTransacaoConverter)transacaoService.getMapper()).convertToEntity());
	}
	
	public ContagemMapper () {
		super();		
	}
	
	public Contagem convertToEntity(ContagemDto dto) {
		Contagem convertida = super.convertToTarget(dto, Contagem.class);
		if (dto.getId() != null) {
			Contagem original = service.findById(dto.getId()).get();
			convertida.setEntidadeOrigem(original.getEntidadeOrigem());
		}
		return convertida;
	}
	
	public ContagemDto convertToDto(Contagem entity) {
		ContagemDto dto = super.convertToTarget(entity, ContagemDto.class);
		
		if (dto.getEscopo() == ContagemEscopoEnum.SPRINT) {
			dto.setProjeto(dto.getSprint().getProjeto());
		}
		if (dto.getCompararVersao() && entity.getEntidadeOrigem() != null) {
			dto.checkComparacao(entity.getEntidadeOrigem());
		}
		
		return dto;
	}		
	
	public ContagemDto convertCompararVersoes(Contagem atual, Contagem anterior) throws Exception {	
		ContagemDto atualDto = convertToDto(atual);
		
		atualDto.getArquivosReferenciados().forEach(arq -> {
			arquivoRefferenciadoService.getMapper().compararComVersaoAntiga(arq, anterior.getArquivosReferenciados());
		});
		
		return atualDto;
	}

	@Override
	public ContagemService getService() {
		return service;
	}
		
}
