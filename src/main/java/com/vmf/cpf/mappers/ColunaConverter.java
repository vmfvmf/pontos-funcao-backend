package com.vmf.cpf.mappers;
import java.util.List;

import org.modelmapper.AbstractConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vmf.cpf.dto.ColunaDto;
import com.vmf.cpf.entities.Coluna;
import com.vmf.cpf.enums.ContagemDadoSituacaoEnum;
import com.vmf.cpf.service.ColunaService;

@Service("colunaConverter")
public class ColunaConverter extends AbstractMapperBase<ColunaDto, Coluna> {
	
	@Autowired
	private ColunaService service; 

	public AbstractConverter<Coluna, ColunaDto> convertToDto() {
		return new AbstractConverter<Coluna, ColunaDto>(){			
			@Override
			protected ColunaDto convert(Coluna source) {
				ColunaDto dto = convertToDto(source);
				return dto;
			}			
		};
	}	
	
	public AbstractConverter<ColunaDto, Coluna> convertToEntity() {
		return new AbstractConverter<ColunaDto, Coluna>(){
			@Override
			protected Coluna convert(ColunaDto source) {
				Coluna entidade = convertToEntity(source);
				if (entidade.getId() != null) {
					Coluna origem = service.findById(entidade.getId()).get();
					entidade.setEntidadeOrigem(origem.getEntidadeOrigem());
				}
				return entidade;
			}			
					
		};
	}

	@Override
	public Coluna convertToEntity(ColunaDto dto) {
		return convertToTarget(dto, Coluna.class);
	}

	@Override
	public ColunaDto convertToDto(Coluna entity) {
		ColunaDto dto = convertToTarget(entity, ColunaDto.class);
		return dto;
	}
	
	public void compararComVersaoAntiga(ColunaDto atual, List<Coluna> compararOrigem) {
		if (atual.getEntidadeOrigem() != null) {
			final Coluna origemDaSelecionada = 
					getService().findOrigemDaSelecionada(atual.getEntidadeOrigem().getId(), compararOrigem);
			atual.checkComparacao(origemDaSelecionada);
		} else {
			atual.setAlteradoDadoContagem(ContagemDadoSituacaoEnum.NOVO);
		}
	}

	@Override
	public ColunaService getService() {
		return service;
	}

}
