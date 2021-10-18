package com.vmf.cpf.mappers;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;

import org.modelmapper.AbstractConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vmf.cpf.dto.ContagemItemArquivoReferenciadoDto;
import com.vmf.cpf.dto.TabelaDto;
import com.vmf.cpf.entities.ContagemItemArquivoReferenciado;
import com.vmf.cpf.entities.Tabela;
import com.vmf.cpf.enums.ContagemDadoSituacaoEnum;
import com.vmf.cpf.service.ContagemItemArquivoReferenciadoService;

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
//		getModelMapper().addMappings(new PropertyMap<ContagemItemArquivoReferenciadoDto, ContagemItemArquivoReferenciado>() {
//            @Override
//            protected void configure() {
//                skip(destination.getContagem());
//            }
//        });
		
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
					getService().findOrigemDaSelecionada(atual.getEntidadeOrigem().getId(), compararOrigem);
			
			if (origemDaSelecionada != null) {
				atual.checkComparacao(origemDaSelecionada);
				
				atual.getTabelas().forEach(tab -> {
					tabelaMapperConverter.compararComVersaoAntiga(tab, origemDaSelecionada.getTabelas());
				});
				checkTabelasExcluidas(atual, origemDaSelecionada);
			} else {
				setArquivoReferenciadoNovoEmComparacaoAVersaoOrigem(atual);
			}
		} else {
			setArquivoReferenciadoNovoEmComparacaoAVersaoOrigem(atual);
		}
	}
	
	private void checkTabelasExcluidas(ContagemItemArquivoReferenciadoDto atual, ContagemItemArquivoReferenciado origemDaSelecionada) {
		List<TabelaDto> tabelas = new ArrayList<>();
		
		tabelas.addAll(atual.getTabelas().stream().filter(pred -> pred.getEntidadeOrigem() != null)
				.collect(Collectors.toList()));
		
		List<Tabela> tabelasExcluidas = origemDaSelecionada.getTabelas().stream()
		.filter(pred -> !pred.checkIsOrigemDaLista(tabelas))
		.collect(Collectors.toList());
		
		for(Tabela tab : tabelasExcluidas) {
			TabelaDto tabExcluida = tabelaMapperConverter.convertToDto(tab);
			tabExcluida.setAlteradoDadoContagem(ContagemDadoSituacaoEnum.EXCLUIDO);
			tabExcluida.getColunas().forEach(col -> col.setAlteradoDadoContagem(ContagemDadoSituacaoEnum.EXCLUIDO));
			atual.getTabelas().add(tabExcluida);
		}
	}

	@Override
	public ContagemItemArquivoReferenciadoService getService() {
		return service;
	}
	
	private void setArquivoReferenciadoNovoEmComparacaoAVersaoOrigem(ContagemItemArquivoReferenciadoDto atual) {
		atual.setAlteradoDadoContagem(ContagemDadoSituacaoEnum.NOVO);
		atual.getTabelas().forEach(tab -> {
			tab.setAlteradoDadoContagem(ContagemDadoSituacaoEnum.NOVO);
			tab.getColunas().forEach(col -> {
				col.setAlteradoDadoContagem(ContagemDadoSituacaoEnum.NOVO);
			});
		});
	}
}
