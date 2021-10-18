package com.vmf.cpf.mappers;
import java.util.List;
import java.util.Optional;

import org.modelmapper.AbstractConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vmf.cpf.dto.TransacaoTDDto;
import com.vmf.cpf.entities.TransacaoTD;
import com.vmf.cpf.enums.ContagemDadoSituacaoEnum;
import com.vmf.cpf.service.TransacaoTDService;

@Service("transacaoTDMapperConverter")
public class TransacaoTDMapperConverter extends AbstractMapperBase<TransacaoTDDto, TransacaoTD> {
	
	@Autowired
	private TransacaoTDService transacaoTDService; 

	public AbstractConverter<TransacaoTD, TransacaoTDDto> convertToDto() {
		return new AbstractConverter<TransacaoTD, TransacaoTDDto>(){			
			@Override
			protected TransacaoTDDto convert(TransacaoTD source) {
				TransacaoTDDto dto = convertToDto(source);
				return dto;
			}			
		};
	}	
	
	public AbstractConverter<TransacaoTDDto, TransacaoTD> convertToEntity() {
		return new AbstractConverter<TransacaoTDDto, TransacaoTD>(){
			@Override
			protected TransacaoTD convert(TransacaoTDDto source) {
				TransacaoTD entidade = convertToEntity(source);
				if (entidade.getId() != null) {
					Optional<TransacaoTD> origem = transacaoTDService.findById(entidade.getId());
					if (origem.isPresent()) {
						entidade.setEntidadeOrigem(origem.get().getEntidadeOrigem());
					}
				}
				return entidade;
			}			
					
		};
	}

	@Override
	public TransacaoTD convertToEntity(TransacaoTDDto dto) {
		return convertToTarget(dto, TransacaoTD.class);
	}

	@Override
	public TransacaoTDDto convertToDto(TransacaoTD entity) {
		return convertToTarget(entity, TransacaoTDDto.class);
	}
	
	public void compararComVersaoAntiga(TransacaoTDDto atual, List<TransacaoTD> compararOrigem) {
		if (atual.getEntidadeOrigem() == null) {
			atual.setAlteradoDadoContagem(ContagemDadoSituacaoEnum.NOVO);
		} 
	}

	@Override
	public TransacaoTDService getService() {
		return transacaoTDService;
	}	
}