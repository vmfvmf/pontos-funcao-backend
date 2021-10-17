package com.vmf.mappers;
import javax.annotation.PostConstruct;

import org.modelmapper.AbstractConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vmf.dto.ContagemItemTransacaoDto;
import com.vmf.dto.TransacaoTDDto;
import com.vmf.entities.ContagemItemTransacao;
import com.vmf.enums.ContagemDadoSituacaoEnum;
import com.vmf.service.ContagemItemTransacaoService;

@Service("contagemItemTransacaoConverter")
public class ContagemItemTransacaoConverter extends AbstractMapperBase<ContagemItemTransacaoDto, ContagemItemTransacao> {
	
	@Autowired
	private ContagemItemTransacaoService service; 
	
	@Autowired
	TransacaoTDMapperConverter transacaoTDMapperConverter;
	
	@Autowired
	private GrupoMapper grupoMapper;
		
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
		if (entity.getCompararVersao()) {
			entity.getTransacaoTDs().forEach(each -> each.setCompararVersao(true));
		}
		ContagemItemTransacaoDto dto = convertToTarget(entity, ContagemItemTransacaoDto.class);
		if (entity.getCompararVersao()) {
			if (dto.getEntidadeOrigem() != null) {
				dto.checkComparacao((ContagemItemTransacao)entity.getEntidadeOrigem());
				checkTdsExcluidos(dto, (ContagemItemTransacao)entity.getEntidadeOrigem());
				if (!dto.getGrupo().getId().equals(((ContagemItemTransacao)entity.getEntidadeOrigem()).getGrupo().getId())) {
					dto.setAlteradoGrupo(
							grupoMapper.convertToDto(((ContagemItemTransacao)entity.getEntidadeOrigem()).getGrupo()));
					dto.setAlteradoDadoContagem(ContagemDadoSituacaoEnum.ALTERADO);
				}
			} else {
				dto.setAlteradoDadoContagem(ContagemDadoSituacaoEnum.NOVO);
			}
		}
		
		return dto;
	}

	private void checkTdsExcluidos(ContagemItemTransacaoDto dto, ContagemItemTransacao anterior) {
		anterior.getTransacaoTDs().forEach(tdEntidadeAnterior -> {
			Boolean existeEsseTd = dto.getTransacaoTDs().stream().anyMatch(pred -> pred.getId().equals(tdEntidadeAnterior.getId()));
			if (!existeEsseTd) {
				TransacaoTDDto td = transacaoTDMapperConverter.convertToDto(tdEntidadeAnterior);
				td.setAlteradoDadoContagem(ContagemDadoSituacaoEnum.EXCLUIDO);
				dto.getTransacaoTDs().add(td);
			}
		});
	}
	
	@Override
	public ContagemItemTransacaoService getService() {
		return service;
	}
}
