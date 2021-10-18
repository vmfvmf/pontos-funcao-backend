package com.vmf.cpf.mappers;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vmf.cpf.dto.ColunaDto;
import com.vmf.cpf.dto.ContagemDto;
import com.vmf.cpf.dto.ContagemItemArquivoReferenciadoDto;
import com.vmf.cpf.dto.ContagemItemTransacaoDto;
import com.vmf.cpf.dto.TabelaDto;
import com.vmf.cpf.dto.TransacaoTDDto;
import com.vmf.cpf.entities.Contagem;
import com.vmf.cpf.entities.ContagemItemArquivoReferenciado;
import com.vmf.cpf.entities.ContagemItemTransacao;
import com.vmf.cpf.entities.TransacaoTD;
import com.vmf.cpf.enums.ContagemDadoSituacaoEnum;
import com.vmf.cpf.enums.ContagemEscopoEnum;
import com.vmf.cpf.service.ContagemItemArquivoReferenciadoService;
import com.vmf.cpf.service.ContagemItemTransacaoService;
import com.vmf.cpf.service.ContagemService;

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
		
		if (entity.getId() != null) {
			dto.setEntidade(entity);
		}
		
		if (dto.getEscopo() == ContagemEscopoEnum.SPRINT) {
			dto.setProjeto(dto.getSprint().getProjeto());
		}
		
		return dto;
	}		
	
	public ContagemDto convertCompararVersoes(Contagem atual, Contagem anterior) throws Exception {	
		ContagemDto atualDto = convertToDto(atual);
		
		atualDto.getArquivosReferenciados().forEach(arq -> {
			arquivoRefferenciadoService.getMapper().compararComVersaoAntiga(arq, anterior.getArquivosReferenciados());			
		});
		
		checkArquivosReferenciadosExcluidos(atualDto, anterior);
		
		atualDto.getTransacoes().forEach(trans -> {
			transacaoService.getMapper().compararComVersaoAntiga(trans, anterior.getTransacoes());
			
			List<ColunaDto> colunas = new ArrayList<>();
			
			atualDto.getArquivosReferenciados().forEach(arq -> arq.getTabelas().forEach(tab -> {
				colunas.addAll(tab.getColunas().stream()
						.filter(col -> col.getAlteradoDadoContagem() == null) // NÃO FOI ALTERADA, MODIFICADA, EXCLUIDA
						.collect(Collectors.toList())); 
			}));
			
			List<TransacaoTDDto> excluidos = trans.getTransacaoTDs().stream()
					.filter(td -> td.getAlteradoDadoContagem() != null && 
						tdPrecisaAtualizarColuna(td, atualDto.getArquivosReferenciados()) &&
						td.getAlteradoDadoContagem().equals(ContagemDadoSituacaoEnum.EXCLUIDO))
					.collect(Collectors.toList());			
			
			for(TransacaoTDDto tdExcluido :  excluidos) {
				Optional<ContagemItemTransacao> transE = anterior.getTransacoes().stream()
						.filter(pred -> pred.getId() == trans.getEntidadeOrigem().getId()).findFirst();
				
				if (transE.isEmpty()) {
					continue;
				}
				
				TransacaoTD tdExcluidoE = transE.get().getTransacaoTDs().stream().filter(td -> td.getId() == tdExcluido.getId())
						.findFirst().get();	
				
				
				Optional<ColunaDto> colDoExcluido = colunas.stream()
						.filter(col -> col.getEntidadeOrigem().checkIsOrigem(tdExcluidoE.getColuna()))
						.findFirst();
				
				if (colDoExcluido.isPresent()) {
					tdExcluido.setColuna(colDoExcluido.get());
				}
			};
			
			List<TransacaoTDDto> excluidosReinseridosExcluidos = trans.getTransacaoTDs().stream()
					.filter(td -> td.getAlteradoDadoContagem() != null && 
							td.getAlteradoDadoContagem() == ContagemDadoSituacaoEnum.NOVO &&
							excluidos.stream()
								.anyMatch(pred -> pred.getColuna().getId() == td.getColuna().getId()))
					.collect(Collectors.toList());
			
			trans.getTransacaoTDs().removeAll(excluidosReinseridosExcluidos);
			
			// REMOVE OS REINSERIDOS MARCADOS COMO NOVO
			trans.getTransacaoTDs().stream()
			.filter(td -> td.getAlteradoDadoContagem() != null && 
				td.getAlteradoDadoContagem().equals(ContagemDadoSituacaoEnum.EXCLUIDO) &&
				excluidosReinseridosExcluidos.stream()
					.anyMatch(pred -> pred.getColuna().getId() == td.getColuna().getId()))
			.forEach(pred -> pred.setAlteradoDadoContagem(null));
		});
		checkTransacoesExcluidas(atualDto, anterior);
		
		return atualDto;
	}
	
	private void checkArquivosReferenciadosExcluidos(ContagemDto atual, Contagem origem) {
		List<ContagemItemArquivoReferenciadoDto> arquivos = new ArrayList<>();
		
		arquivos.addAll(atual.getArquivosReferenciados().stream().filter(pred -> pred.getEntidadeOrigem() != null)
				.collect(Collectors.toList()));
		
		List<ContagemItemArquivoReferenciado> arquivosExcluidas = origem.getArquivosReferenciados().stream()
		.filter(pred -> !pred.checkIsOrigemDaLista(arquivos))
		.collect(Collectors.toList());
		
		for(ContagemItemArquivoReferenciado arquivo : arquivosExcluidas) {
			ContagemItemArquivoReferenciadoDto arquivoExcluido = arquivoRefferenciadoService.getMapper().convertToDto(arquivo);
			arquivoExcluido.setAlteradoDadoContagem(ContagemDadoSituacaoEnum.EXCLUIDO);
			arquivoExcluido.getTabelas().forEach(tab -> {
				tab.setAlteradoDadoContagem(ContagemDadoSituacaoEnum.EXCLUIDO);
				tab.getColunas().forEach(col -> col.setAlteradoDadoContagem(ContagemDadoSituacaoEnum.EXCLUIDO));
			});
			atual.getArquivosReferenciados().add(arquivoExcluido);
		}
	}
	
	private void checkTransacoesExcluidas(ContagemDto atual, Contagem origem) {
		List<ContagemItemTransacaoDto> transacoes = new ArrayList<>();
		
		transacoes.addAll(atual.getTransacoes().stream().filter(pred -> pred.getEntidadeOrigem() != null)
				.collect(Collectors.toList()));
		
		List<ContagemItemTransacao> transacoesExcluidas = origem.getTransacoes().stream()
		.filter(pred -> !pred.checkIsOrigemDaLista(transacoes))
		.collect(Collectors.toList());
		
		for(ContagemItemTransacao transacao : transacoesExcluidas) {
			ContagemItemTransacaoDto transacaoExcluida = transacaoService.getMapper().convertToDto(transacao);
			transacaoExcluida.setAlteradoDadoContagem(ContagemDadoSituacaoEnum.EXCLUIDO);
			transacaoExcluida.getTransacaoTDs().forEach(td -> {
				td.setAlteradoDadoContagem(ContagemDadoSituacaoEnum.EXCLUIDO);
			});
			atual.getTransacoes().add(transacaoExcluida);
		}
	}
	
	private boolean tdPrecisaAtualizarColuna(TransacaoTDDto td,
			List<ContagemItemArquivoReferenciadoDto> arquivosReferenciados) {
		for(ContagemItemArquivoReferenciadoDto arq : arquivosReferenciados) {
			for(TabelaDto tab : arq.getTabelas()) {
				for (ColunaDto col : tab.getColunas()) {
					if (td.getColuna().getId() == col.getId()) {
						return false;
					}
				}
			}
		}
		return true;
	}

	@Override
	public ContagemService getService() {
		return service;
	}
		
}
