package com.vmf.mappers;
import java.util.List;
import java.util.Optional;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vmf.dto.ContagemDto;
import com.vmf.entities.Contagem;
import com.vmf.enums.ContagemDadoSituacaoEnum;
import com.vmf.enums.ContagemEscopoEnum;
import com.vmf.interfaces.IDtoComparaVersao;
import com.vmf.interfaces.IGetId;
import com.vmf.interfaces.IHaveCriadoModificadoId;
import com.vmf.interfaces.IHaveEntidadeOrigem;
import com.vmf.interfaces.ISetAlteradoDadoContagem;
import com.vmf.service.AbstractService;
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
		
		return dto;
	}
	
	// E -> ex. ArquivoReferenciado
	// D -> ex. ArquivoReferenciadoDto
	// O -> ex. Tabela
	@SuppressWarnings("unchecked")
	private <D, E, O> void verificacaoCriacaoEdicaoComAnterior(List<E> listaObjetosDaEntidadeAnterior,
			IDtoComparaVersao<E, O> objAtual, AbstractService<D, E> service, Long objAtualIdOrigem) {
		E objAtualOrigem = service.findById(objAtualIdOrigem).get();
		Optional<E> existeAnterior = listaObjetosDaEntidadeAnterior.stream()
				.filter(pred -> service.verificaSeAnteriorEhAnterior(objAtualIdOrigem, ((IHaveEntidadeOrigem<E>)objAtualOrigem)))
						.findFirst();
		
		objAtual.checkComparacao(existeAnterior.get());
	}
	
	private <D, E, O> void trataVerificacaoComAnteriorExclusao(List<D> listaObjetosDoAtualDto,
			E objAnterior, AbstractMapperBase<D, E> mapper) {
		if (!listaObjetosDoAtualDto.stream().anyMatch(pred -> ((IHaveCriadoModificadoId) pred).getId()
				.equals(((IHaveCriadoModificadoId) objAnterior).getId()))) {
			D excluidoDto =  mapper.convertToDto(objAnterior);
			((ISetAlteradoDadoContagem) excluidoDto).setAlteradoDadoContagem(ContagemDadoSituacaoEnum.EXCLUIDO);
			listaObjetosDoAtualDto.add(excluidoDto);
		}
	}
	
	// D -> ArquivoReferenciadoDto
	// E -> ArquivoReferenciado
	@SuppressWarnings("unchecked")
	private <D, E> Long getOrigemId(D dtoAtual, List<E> listaDaEntidadeAtual) {
		 E origem = listaDaEntidadeAtual.stream()
				.filter(e -> ((IHaveCriadoModificadoId)e).getId().equals(((IHaveCriadoModificadoId)dtoAtual).getId()))
				.findFirst().get();
		 
		 E origemOrigem = ((IHaveEntidadeOrigem<E>)origem).getEntidadeOrigem();
		 if (origemOrigem != null) {
			 ((ISetAlteradoDadoContagem)dtoAtual).setAlteradoDadoContagem(ContagemDadoSituacaoEnum.NOVO);
			 return ((IGetId)origemOrigem).getId();
		 }
		 return null;
	}
	
	
	public ContagemDto convertCompararVersoes(Contagem atual, Contagem anterior) throws Exception {		
		ContagemDto atualDto = convertToDto(atual);
		
		atualDto.checkComparacao(anterior);
		
		
		// VERIFICA ALTERADOS E NOVOS - ARQUIVOS REFERENCIADOS
		atualDto.getArquivosReferenciados().forEach(arq -> {
			Long idOrigem = getOrigemId(arq, atual.getArquivosReferenciados());
			if (idOrigem != null) {
				verificacaoCriacaoEdicaoComAnterior(anterior.getArquivosReferenciados(), arq, arquivoRefferenciadoService, idOrigem);
			}			
		});
		
		// VERIFICA EXCLUIDOS - ARQUIVOS REFERENCIADOS
		anterior.getArquivosReferenciados().forEach(arq -> {
			trataVerificacaoComAnteriorExclusao(atualDto.getArquivosReferenciados(), arq, arquivoRefferenciadoService.getMapper());
		});
		
		// VERIFICA ALTERADOS E NOVOS - TRANSAÇÕES
		atualDto.getTransacoes().forEach(trans -> {
			Long idOrigem = getOrigemId(trans, atual.getTransacoes());
			if (idOrigem != null) {
				verificacaoCriacaoEdicaoComAnterior(anterior.getTransacoes(), trans, transacaoService, idOrigem);
			}
		});
				
		// VERIFICA EXCLUIDOS - TRANSAÇÕES
		anterior.getTransacoes().forEach(trans -> {
			trataVerificacaoComAnteriorExclusao(atualDto.getTransacoes(), trans, transacaoService.getMapper());
		});
		
		atualDto.setCompararVersao(true);
		
		return atualDto;
	}
		
}
