package com.example.demo.mappers;
import java.util.List;

import com.example.demo.dto.ContagemItemArquivoReferenciadoDto;
import com.example.demo.model.ContagemItemArquivoReferenciado;

public class ContagemItemArquivoReferenciadoMapper extends AbstractMapperBase<ContagemItemArquivoReferenciadoDto, ContagemItemArquivoReferenciado> {		
	public ContagemItemArquivoReferenciado convertToEntity(ContagemItemArquivoReferenciadoDto dto) {
		return super.convertToTarget(dto, ContagemItemArquivoReferenciado.class);
	}
	
	public ContagemItemArquivoReferenciadoDto convertToDto(ContagemItemArquivoReferenciado entity) {
		return super.convertToTarget(entity, ContagemItemArquivoReferenciadoDto.class);
	}
	
	public List<ContagemItemArquivoReferenciado> convertToEntityList(List<ContagemItemArquivoReferenciadoDto> dtos) {
		return super.mapList(dtos, ContagemItemArquivoReferenciado.class);
	}
	
	public List<ContagemItemArquivoReferenciadoDto> convertToDtoList(List<ContagemItemArquivoReferenciado> entitys) {
		return super.mapList(entitys, ContagemItemArquivoReferenciadoDto.class);
	}
}
