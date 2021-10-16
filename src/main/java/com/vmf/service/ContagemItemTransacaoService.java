package com.vmf.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vmf.dto.ContagemItemTransacaoDto;
import com.vmf.entities.AbstractContagemItem;
import com.vmf.entities.Coluna;
import com.vmf.entities.ContagemItemTransacao;
import com.vmf.entities.TransacaoTD;
import com.vmf.mappers.AbstractMapperBase;
import com.vmf.mappers.ContagemItemTransacaoConverter;

@Service("contagemItemTransacaoService")
public class ContagemItemTransacaoService extends AbstractService<ContagemItemTransacaoDto, ContagemItemTransacao> {
	@Autowired
	private TransacaoTDService transacaoTDService;
	
	@Autowired
	private ContagemItemTransacaoConverter mapper;
	
	@Override
	public void prepareToSave(ContagemItemTransacao entity) {
		// TODO Auto-generated method stub
		
	}
	
	public void ajustaEntidadeModificada(ContagemItemTransacao entidade) {
		for (TransacaoTD td : entidade.getTransacaoTDs()) {
			td.setItemTransacao(entidade);
			transacaoTDService.verificaEntidadeModificada(td);
			
			if (entidade.getContagem().clonada) {
				entidade.getContagem()
				.getArquivosReferenciados().stream().forEach(arq -> {
					 arq.getTabelas().forEach(tabela -> {
						 Optional<Coluna> tdColuna =  tabela.getColunas().stream()
						.filter(coluna -> Objects.equals(coluna.getColunaOrigem(),(td.getTransacaoTDOrigem().getColuna())))
						.findFirst();
						 if (tdColuna.isPresent()) {
							 td.setColuna(tdColuna.get());
						 }
					});
				});
			}
		}	
		
		if (entidade.getId() == null) {
			entidade.setCriado(LocalDate.now());
			if(entidade.isAcao() == null) {
				entidade.setAcao(false);
			}
			if(entidade.isMensagem() == null) {
				entidade.setMensagem(false);
			}
			return;
		}
		ContagemItemTransacao original = findById(entidade.getId()).get();
				
		if (!entidade.equals(original)) {
			entidade.setModificado(LocalDate.now());
		}
	}

	public void trataEsbocoIncrementoVersao(ContagemItemTransacao transacao) {
		AbstractContagemItem transParent = findById(transacao.getId()).get();
		transacao.setContagemItemOrigem(transParent);
		setCriadoModificadoId(transacao);
		
		for (TransacaoTD td : transacao.getTransacaoTDs()) {
			td.setItemTransacao(transacao);
			
			transacaoTDService.setITrataEsbocoIncrementoVersaoSetTDColuna((td2) -> {
				List<Coluna> novasColunas = new ArrayList<>();
				
				transacao.getContagem().getArquivosReferenciados()
				.forEach(arquivo -> arquivo.getTabelas().forEach(tabela -> novasColunas.addAll(tabela.getColunas())));
				
				td.setColuna(novasColunas.stream()
						.filter(col -> col.getColunaOrigem().getId().equals(td2.getTransacaoTDOrigem().getColuna().getId()))
						.findFirst().get());
			});
			
			transacaoTDService.trataEsbocoIncrementoVersao(td);
		}
	}

	@Override
	public AbstractMapperBase<ContagemItemTransacaoDto, ContagemItemTransacao> getMapper() {
		return mapper;
	}
}
