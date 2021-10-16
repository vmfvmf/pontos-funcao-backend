package com.vmf.service;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vmf.dto.ContagemDto;
import com.vmf.entities.Contagem;
import com.vmf.entities.ContagemItemArquivoReferenciado;
import com.vmf.entities.ContagemItemTransacao;
import com.vmf.enums.ContagemEstado;
import com.vmf.mappers.AbstractMapperBase;
import com.vmf.mappers.ContagemMapper;

@Service("contagemService")
public class ContagemService extends AbstractService<ContagemDto, Contagem> {
	@Autowired
	private ContagemItemArquivoReferenciadoService arqService;
	
	@Autowired
	private ContagemItemTransacaoService contagemItemTransacaoService;
	
	@Autowired
	private ContagemMapper mapper;
	
	@Override
	public void prepareToSave(Contagem entity) {		
		entity.setTotalPontosFuncao(calcularTotalPontosFuncao(entity));
		
		for (ContagemItemArquivoReferenciado arquivoReferenciado : entity.getArquivosReferenciados()) {
			arquivoReferenciado.setContagem(entity);
			arqService.ajustaEntidadeModificada(arquivoReferenciado);
		}
		
		for (ContagemItemTransacao transacao : entity.getTransacoes()) {
			transacao.setContagem(entity);
			contagemItemTransacaoService.ajustaEntidadeModificada(transacao);
		}
		
		if (entity.getId() != null) {
			Contagem original = findById(entity.getId()).get();
			
			if(!entity.equals(original)) {
				entity.setModificado(LocalDate.now());
			}
		}
		
		preparaCriacaoEdicaoEntidade(entity);
	}
		
	private Integer calcularTotalPontosFuncao(Contagem entity) {
		return entity.getArquivosReferenciados().stream().reduce(0, (subTotal, element) -> subTotal + element.getPf(),
				Integer::sum)
				+ entity.getTransacoes().stream().reduce(0, (subTotal, element) -> subTotal + element.getPf(), Integer::sum);
	}

	private void preparaCriacaoEdicaoEntidade(Contagem entity) {
		if (entity.getId() == null) {
			entity.setEstado(ContagemEstado.E);
			entity.setUltimaVersao(true);
			entity.setCriado(LocalDate.now());
			if (entity.getVersao() == null) {
				entity.setVersao(1);
			}
		}
	}
	
	public void setVersionar() {
		super.setValidator((entidade) -> {
			super.setValidator(null);
			if (!entidade.getEstado().equals(ContagemEstado.E)) {
				throw new Exception("Esta contagem já está versionada"); 
			}
		});
		super.setBeforeSaveCallback((entidade) -> {
			super.setBeforeSaveCallback(null);
			entidade.setEstado(ContagemEstado.V);
			entidade.setModificado(LocalDate.now());
			return null;
		});
	}

	public void setCriarNovoEsbocoIncrementoVersao() throws Exception {
		super.setValidator((entidade) -> {
			super.setValidator(null);
			if (entidade.getEstado() != ContagemEstado.V) {
				throw new Exception("Esta contagem não está versionada para criar um novo esboço."); 
			}
			if (!entidade.getUltimaVersao()) {
				throw new Exception("Esta contagem não é a última versão, só é possível criar esboços a partir da última versão."); 
			}
		});
		super.setBeforeSaveCallback((entidade) -> {
			super.setBeforeSaveCallback(null);
			trataEsbocoIncrementoVersao(entidade);
			return null;
		});
	}
	
	private void trataEsbocoIncrementoVersao(Contagem origem) throws Exception {
		origem.setUltimaVersao(false);
		origem.setModificado(LocalDate.now());
		
		super.setAfterSaveCallback((entidade) -> {
			super.setAfterSaveCallback(null);
			Contagem nova = origem.criarEsbocoVersionado();
			entidade = this.save(nova);
			return entidade;
		});
	}

	public ContagemDto compararVersoes(Long id, Long idVersaoAnterior) throws Exception {
		Optional<Contagem> atual = findById(id);
		Optional<Contagem> anterior = findById(idVersaoAnterior);
		
		if (atual.isEmpty() || anterior.isEmpty()) {
			throw new Exception("Foi informado algum identificador inválido para comparação.");
		} else if (!verificaSeAnteriorEhAnterior(atual.get().getEntidadeOrigem(), anterior.get())) {
			throw new Exception("A contagem selecionada como anterior não é anterior a contagem atual.");
		}
		
		return mapper.convertCompararVersoes(atual.get(), anterior.get());
	}

	@Override
	public AbstractMapperBase<ContagemDto, Contagem> getMapper() {
		return mapper;
	}
}
