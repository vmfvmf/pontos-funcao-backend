package com.vmf.mappers;
import java.util.List;

import com.vmf.dto.ContagemItemTransacaoDto;
import com.vmf.model.ContagemItemTransacao;

public class ContagemItemTransacaoMapper extends AbstractMapperBase<ContagemItemTransacaoDto, ContagemItemTransacao> {		
	public ContagemItemTransacao convertToEntity(ContagemItemTransacaoDto dto) {
		ContagemItemTransacao convertido = super.convertToTarget(dto, ContagemItemTransacao.class);
		convertido.getTransacaoTDs().forEach(td -> td.setItemTransacao(convertido));
		return convertido;
	}
	
	public ContagemItemTransacaoDto convertToDto(ContagemItemTransacao entity) {
		return super.convertToTarget(entity, ContagemItemTransacaoDto.class);
	}
	
	public List<ContagemItemTransacao> convertToEntityList(List<ContagemItemTransacaoDto> dtos) {
		return super.mapList(dtos, ContagemItemTransacao.class);
	}
	
	public List<ContagemItemTransacaoDto> convertToDtoList(List<ContagemItemTransacao> entitys) {
		return super.mapList(entitys, ContagemItemTransacaoDto.class);
	}
}
