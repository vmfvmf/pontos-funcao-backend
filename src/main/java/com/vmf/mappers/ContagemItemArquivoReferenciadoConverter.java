package com.vmf.mappers;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.transaction.Transactional;

import org.modelmapper.AbstractConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vmf.dto.ContagemItemArquivoReferenciadoDto;
import com.vmf.entities.ContagemItemArquivoReferenciado;
import com.vmf.enums.ContagemDadoSituacaoEnum;
import com.vmf.service.ContagemItemArquivoReferenciadoService;

@Transactional
@Service("contagemItemArquivoReferenciadoConverter")
public class ContagemItemArquivoReferenciadoConverter extends AbstractMapperBase<ContagemItemArquivoReferenciadoDto, ContagemItemArquivoReferenciado> {
	@Autowired
	private ContagemItemArquivoReferenciadoService service; 
	
	@Autowired 
	private TabelaMapperConverter tabelaMapperConverter;

	public ContagemItemArquivoReferenciadoConverter () {
		super();
	}
		
	@PostConstruct
	private void init() {
		getModelMapper().addConverter(tabelaMapperConverter.convertToDto());
		getModelMapper().addConverter(tabelaMapperConverter.convertToEntity());
	}
	
	public AbstractConverter<ContagemItemArquivoReferenciado, ContagemItemArquivoReferenciadoDto> convertToDto() {
		return new AbstractConverter<ContagemItemArquivoReferenciado, ContagemItemArquivoReferenciadoDto>(){			
			@Override
			protected ContagemItemArquivoReferenciadoDto convert(ContagemItemArquivoReferenciado source) {
				ContagemItemArquivoReferenciadoDto dto = convertToDto(source);
				return dto;
			}			
		};
	}	
	
	public AbstractConverter<ContagemItemArquivoReferenciadoDto, ContagemItemArquivoReferenciado> convertToEntity() {
		return new AbstractConverter<ContagemItemArquivoReferenciadoDto, ContagemItemArquivoReferenciado>(){
			@Override
			protected ContagemItemArquivoReferenciado convert(ContagemItemArquivoReferenciadoDto source) {
				ContagemItemArquivoReferenciado entidade = convertToEntity(source);
				if (entidade.getId() != null) {
					ContagemItemArquivoReferenciado origem = service.findById(entidade.getId()).get();
					entidade.setEntidadeOrigem(origem.getEntidadeOrigem());
				}
				return entidade;
			}			
					
		};
	}

	@Override
	public ContagemItemArquivoReferenciado convertToEntity(ContagemItemArquivoReferenciadoDto dto) {
		return convertToTarget(dto, ContagemItemArquivoReferenciado.class);
	}
	
	@Override
	public ContagemItemArquivoReferenciadoDto convertToDto(ContagemItemArquivoReferenciado entity) {
		ContagemItemArquivoReferenciadoDto dto = convertToTarget(entity, ContagemItemArquivoReferenciadoDto.class);
		
		return dto;
	}
	
	public void compararComVersaoAntiga(ContagemItemArquivoReferenciadoDto atual, List<ContagemItemArquivoReferenciado> compararOrigem) {		
		if (atual.getEntidadeOrigem() != null) {
			final ContagemItemArquivoReferenciado origemDaSelecionada = 
					atual.getEntidadeOrigem().findOrigemDaSelecionada(compararOrigem);
			
			atual.checkComparacao(origemDaSelecionada);
			
			atual.getTabelas().forEach(tab -> {
				tabelaMapperConverter.compararComVersaoAntiga(tab, origemDaSelecionada.getTabelas());
			});
		} else {
			atual.setAlteradoDadoContagem(ContagemDadoSituacaoEnum.NOVO);
			atual.getTabelas().forEach(tab -> {
				tab.setAlteradoDadoContagem(ContagemDadoSituacaoEnum.NOVO);
				tab.getColunas().forEach(col -> {
					col.setAlteradoDadoContagem(ContagemDadoSituacaoEnum.NOVO);
				});
			});
		}
	}

	@Override
	public ContagemItemArquivoReferenciadoService getService() {
		return service;
	}
}
