package com.vmf.mappers;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import org.modelmapper.AbstractConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vmf.dto.ColunaDto;
import com.vmf.dto.TabelaDto;
import com.vmf.entities.Coluna;
import com.vmf.entities.Tabela;
import com.vmf.enums.ContagemDadoSituacaoEnum;
import com.vmf.service.TabelaService;

@Service("tabelaMapperConverter")
public class TabelaMapperConverter extends AbstractMapperBase<TabelaDto, Tabela> {	
	@Autowired
	private TabelaService tabelaService; 
	
	@Autowired
	ColunaConverter colunaConverter;
	
	@PostConstruct
	private void init() {
		getModelMapper().addConverter(colunaConverter.convertToDto());
		getModelMapper().addConverter(colunaConverter.convertToEntity());
	}

	public AbstractConverter<Tabela, TabelaDto> convertToDto() {
		return new AbstractConverter<Tabela, TabelaDto>(){			
			@Override
			protected TabelaDto convert(Tabela source) {
				TabelaDto dto = convertToDto(source);
				return dto;
			}			
		};
	}	
	
	public AbstractConverter<TabelaDto, Tabela> convertToEntity() {
		return new AbstractConverter<TabelaDto, Tabela>(){
			@Override
			protected Tabela convert(TabelaDto source) {
				Tabela entidade = convertToEntity(source);
				if (entidade.getId() != null) {
					Tabela origem = tabelaService.findById(entidade.getId()).get();
					entidade.setEntidadeOrigem(origem.getEntidadeOrigem());
				}
				return entidade;
			}			
					
		};
	}

	@Override
	public Tabela convertToEntity(TabelaDto dto) {
		return convertToTarget(dto, Tabela.class);
	}

	@Override
	public TabelaDto convertToDto(Tabela entity) {		
		TabelaDto dto = convertToTarget(entity, TabelaDto.class);		
		return dto;
	}
	
	public void compararComVersaoAntiga(TabelaDto atual, List<Tabela> compararOrigem) {
		if (atual.getEntidadeOrigem() != null) {
			Tabela origemDaSelecionada = atual.getEntidadeOrigem().findOrigemDaSelecionada(compararOrigem);
			
			atual.checkComparacao(origemDaSelecionada);
			
			atual.getColunas().forEach(col -> {
				colunaConverter.compararComVersaoAntiga(col, origemDaSelecionada.getColunas());
			});
			List<ColunaDto> colunas = new ArrayList<>();

			colunas.addAll(atual.getColunas().stream().filter(pred -> pred.getEntidadeOrigem() != null)
					.collect(Collectors.toList()));
			
			List<Coluna> colunasExcluidas = origemDaSelecionada.getColunas().stream()
			.filter(pred -> !pred.checkIsOrigemDaLista(pred, colunas))
			.collect(Collectors.toList());
			
			for(Coluna col : colunasExcluidas) {
				ColunaDto colExcluida = colunaConverter.convertToDto(col);
				colExcluida.setAlteradoDadoContagem(ContagemDadoSituacaoEnum.EXCLUIDO);
				atual.getColunas().add(colExcluida);
			};
		} else {
			atual.setAlteradoDadoContagem(ContagemDadoSituacaoEnum.NOVO);
			atual.getColunas().forEach(col -> {
				col.setAlteradoDadoContagem(ContagemDadoSituacaoEnum.NOVO);
			});
		}
		
	}

	@Override
	public TabelaService getService() {
		return tabelaService;
	}
}