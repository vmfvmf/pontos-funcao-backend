package com.vmf.cpf.mappers;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import org.modelmapper.AbstractConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vmf.cpf.dto.ContagemItemTransacaoDto;
import com.vmf.cpf.dto.TransacaoTDDto;
import com.vmf.cpf.entities.ContagemItemTransacao;
import com.vmf.cpf.entities.TransacaoTD;
import com.vmf.cpf.enums.ContagemDadoSituacaoEnum;
import com.vmf.cpf.service.ContagemItemTransacaoService;

@Service("contagemItemTransacaoConverter")
public class ContagemItemTransacaoConverter extends AbstractMapperBase<ContagemItemTransacaoDto, ContagemItemTransacao> {
	
	@Autowired
	private ContagemItemTransacaoService service; 
	
	@Autowired
	TransacaoTDMapperConverter transacaoTDMapperConverter;

	public ContagemItemTransacaoConverter () {
		super();
	}
	
	@PostConstruct
	private void init() {
		getModelMapper().addConverter(transacaoTDMapperConverter.convertToDto());
		getModelMapper().addConverter(transacaoTDMapperConverter.convertToEntity());
	}

	public AbstractConverter<ContagemItemTransacao, ContagemItemTransacaoDto> convertToDto() {
		return new AbstractConverter<ContagemItemTransacao, ContagemItemTransacaoDto>(){			
			@Override
			protected ContagemItemTransacaoDto convert(ContagemItemTransacao source) {
				return convertToDto(source);
			}			
		};
	}	
	
	public AbstractConverter<ContagemItemTransacaoDto, ContagemItemTransacao> convertToEntity() {
		return new AbstractConverter<ContagemItemTransacaoDto, ContagemItemTransacao>(){
			@Override
			protected ContagemItemTransacao convert(ContagemItemTransacaoDto source) {
				ContagemItemTransacao entidade = convertToEntity(source);
				if (entidade.getId() != null) {
					ContagemItemTransacao origem = service.findById(entidade.getId()).get();
					entidade.setEntidadeOrigem(origem.getEntidadeOrigem());
				}
				return entidade;
			}			
					
		};
	}	
	
	@Override
	public ContagemItemTransacao convertToEntity(ContagemItemTransacaoDto dto) {
		return convertToTarget(dto, ContagemItemTransacao.class);
	}

	@Override
	public ContagemItemTransacaoDto convertToDto(ContagemItemTransacao entity) {
		ContagemItemTransacaoDto dto = convertToTarget(entity, ContagemItemTransacaoDto.class);		
		return dto;
	}
	
	public void compararComVersaoAntiga(ContagemItemTransacaoDto atual, List<ContagemItemTransacao> compararOrigem) {		
		if (atual.getEntidadeOrigem() != null) {
			final ContagemItemTransacao origemDaSelecionada = 
					getService().findOrigemDaSelecionada(atual.getEntidadeOrigem().getId(), compararOrigem);
			
			if (origemDaSelecionada != null) {
				atual.checkComparacao(origemDaSelecionada);
				
				atual.getTransacaoTDs().forEach(tab -> {
					transacaoTDMapperConverter.compararComVersaoAntiga(tab, origemDaSelecionada.getTransacaoTDs());
				});
				checkTdsExcluidos(atual, origemDaSelecionada);
			} else {
				setTransacaoNovaEmComparacaoAVersaoOrigem(atual);
			}
		} else {
			setTransacaoNovaEmComparacaoAVersaoOrigem(atual);
		}
	}
	
	private void setTransacaoNovaEmComparacaoAVersaoOrigem(ContagemItemTransacaoDto atual) {
		atual.setAlteradoDadoContagem(ContagemDadoSituacaoEnum.NOVO);
		atual.getTransacaoTDs().forEach(tab -> {
			tab.setAlteradoDadoContagem(ContagemDadoSituacaoEnum.NOVO);
		});
	}
	
	private void checkTdsExcluidos(ContagemItemTransacaoDto atual, ContagemItemTransacao origemDaSelecionada) {
		List<TransacaoTDDto> tds = new ArrayList<>();
		
		tds.addAll(atual.getTransacaoTDs().stream().filter(pred -> pred.getEntidadeOrigem() != null)
				.collect(Collectors.toList()));
		
		List<TransacaoTD> tdsExcluidas = origemDaSelecionada.getTransacaoTDs().stream()
		.filter(pred -> !pred.checkIsOrigemDaLista(pred, tds))
		.collect(Collectors.toList());
		
		for(TransacaoTD td : tdsExcluidas) {
			TransacaoTDDto tdExcluido = transacaoTDMapperConverter.convertToDto(td);
			tdExcluido.setAlteradoDadoContagem(ContagemDadoSituacaoEnum.EXCLUIDO);
			atual.getTransacaoTDs().add(tdExcluido);
		}
	}

	
	
	@Override
	public ContagemItemTransacaoService getService() {
		return service;
	}
}
