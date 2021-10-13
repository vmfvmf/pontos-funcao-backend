package com.example.demo.service;

import org.springframework.stereotype.Service;

import com.example.demo.enums.ContagemEstado;
import com.example.demo.model.Contagem;

@Service("contagemService")
public class ContagemService extends AbstractService<Contagem> {

	
	@Override
	public void prepareToSave(Contagem entity) {
		Integer somador = entity.getArquivosReferenciados().stream().reduce(0, (subTotal, element) -> subTotal + element.getPf(),
				Integer::sum)
				+ entity.getTransacoes().stream().reduce(0, (subTotal, element) -> subTotal + element.getPf(), Integer::sum);
		entity.setTotalPontosFuncao(somador);
		entity.getArquivosReferenciados().forEach(arq -> {
			arq.setContagem(entity);
			arq.getTabelas().forEach(t -> {
				t.setArquivoReferenciado(arq);
				t.getColunas().forEach(c -> c.setTabela(t));
			});
		});
		entity.getTransacoes().forEach(t -> {
			t.setContagem(entity);
			t.getGrupo().setContagem(entity);
			t.getTransacaoTDs().forEach(td -> td.setItemTransacao(t));
			if (t.isAcao() == null) {
				t.setAcao(false);
			}
			if (t.isMensagem() == null) {
				t.setMensagem(false);
			}
		});
	}
	
	public void setVersionar() {
		super.setValidator((entidade) -> {
			if (!entidade.getEstado().equals(ContagemEstado.E) ||
					entidade.getVersao() != null) {
				throw new Exception("Esta contagem já está versionada"); 
			}
		});
		super.setCustomCallback((entidade) -> {
			entidade.setEstado(ContagemEstado.V);
			entidade.setVersao(1);
		});
	}

}
