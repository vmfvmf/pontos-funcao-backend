package com.vmf.cpf.entities;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.hibernate.annotations.DiscriminatorOptions;
import org.springframework.data.util.Pair;

import com.vmf.cpf.enums.ComplexidadeItemEnum;
import com.vmf.cpf.enums.ContagemItemFuncaoEnum;
import com.vmf.cpf.interfaces.IHaveEntidadeOrigem;

@Entity
@DiscriminatorValue("T")
@DiscriminatorOptions(force = true)
public class ContagemItemTransacao extends AbstractContagemItem<ContagemItemTransacao>
		implements IHaveEntidadeOrigem<ContagemItemTransacao> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@ManyToOne
	@JoinColumn(name = "grupo_id")
	private Grupo grupo;

	@OneToMany(mappedBy = "itemTransacao", cascade = { CascadeType.ALL }, orphanRemoval = true)
	private List<TransacaoTD> transacaoTDs = new ArrayList<>();

	@Column(name = "transacao_acao")
	private Boolean acao;

	@Column(name = "transacao_mensagem")
	private Boolean mensagem;

	@OneToOne
	@JoinColumn(name = "contagem_item_origem_id")
	private ContagemItemTransacao entidadeOrigem;

	public Grupo getGrupo() {
		return grupo;
	}

	public void setGrupo(Grupo grupo) {
		this.grupo = grupo;
	}

	public List<TransacaoTD> getTransacaoTDs() {
		return transacaoTDs;
	}

	public void setTransacaoTDs(List<TransacaoTD> transacaoTDs) {
		this.transacaoTDs = transacaoTDs;
	}

	public Boolean isAcao() {
		return acao;
	}

	public void setAcao(Boolean acao) {
		this.acao = acao;
	}

	public Boolean isMensagem() {
		return mensagem;
	}

	public void setMensagem(Boolean mensagem) {
		this.mensagem = mensagem;
	}

	@Override
	public ContagemItemTransacao clone() {
		ContagemItemTransacao nova = new ContagemItemTransacao();
		super.clone(nova, this);

		nova.setEntidadeOrigem(this);
		nova.setGrupo(getGrupo());
		nova.setAcao(isAcao());
		nova.setMensagem(isMensagem());

		for (TransacaoTD td : getTransacaoTDs()) {
			TransacaoTD novoTd = td.clone();
			nova.getTransacaoTDs().add(novoTd);
		}
		;

		return nova;
	}

	public ContagemItemTransacao getEntidadeOrigem() {
		return entidadeOrigem;
	}

	public void setEntidadeOrigem(ContagemItemTransacao entidadeOrigem) {
		this.entidadeOrigem = entidadeOrigem;
	}

	public void calcularPontosFuncao() {
		setTd(getTransacaoTDs().stream().reduce(0, (total, td) -> total++, Integer::sum));
		if (isAcao()) {
			setTd(getTd() + 1);
		}
		if (isMensagem()) {
			setTd(getTd() + 1);
		}
		analisarComplexidade();
	}

	private void analisarComplexidade() {
		if (getFuncao() == ContagemItemFuncaoEnum.EE) {
			if ((getTd() <= 15 && getTr() < 2) || (getTd() < 5 && getTr() == 2)) {
				setComplexidade(ComplexidadeItemEnum.BAIXA);
			} else if ((getTd() < 5 && getTr() > 2) || (getTd() <= 15 && getTr() == 2)
					|| (getTd() > 15 && getTr() < 2)) {
				setComplexidade(ComplexidadeItemEnum.MEDIA);
			} else {
				setComplexidade(ComplexidadeItemEnum.ALTA);
			}
		} else if (getFuncao() == ContagemItemFuncaoEnum.CE || getFuncao() == ContagemItemFuncaoEnum.SE) {
			if ((getTd() <= 19 && getTr() < 2) || (getTd() < 6 && getTr() < 3)) {
				setComplexidade(ComplexidadeItemEnum.BAIXA);
			} else if ((getTd() < 6 && getTr() <= 3) || (getTd() <= 19 && getTr() <= 3)
					|| (getTd() > 19 && getTr() < 2)) {
				setComplexidade(ComplexidadeItemEnum.MEDIA);
			} else {
				setComplexidade(ComplexidadeItemEnum.ALTA);
			}
		}

		calcularPF();
	}

	private void calcularPF() {
		Map<Pair<ContagemItemFuncaoEnum, ComplexidadeItemEnum>, Integer> mapa = new HashMap<Pair<ContagemItemFuncaoEnum, ComplexidadeItemEnum>, Integer>();

		mapa.put(Pair.of(ContagemItemFuncaoEnum.EE, ComplexidadeItemEnum.BAIXA), 3);
		mapa.put(Pair.of(ContagemItemFuncaoEnum.EE, ComplexidadeItemEnum.MEDIA), 4);
		mapa.put(Pair.of(ContagemItemFuncaoEnum.EE, ComplexidadeItemEnum.BAIXA), 6);

		mapa.put(Pair.of(ContagemItemFuncaoEnum.CE, ComplexidadeItemEnum.BAIXA), 3);
		mapa.put(Pair.of(ContagemItemFuncaoEnum.CE, ComplexidadeItemEnum.MEDIA), 4);
		mapa.put(Pair.of(ContagemItemFuncaoEnum.CE, ComplexidadeItemEnum.BAIXA), 6);

		mapa.put(Pair.of(ContagemItemFuncaoEnum.SE, ComplexidadeItemEnum.BAIXA), 4);
		mapa.put(Pair.of(ContagemItemFuncaoEnum.SE, ComplexidadeItemEnum.MEDIA), 5);
		mapa.put(Pair.of(ContagemItemFuncaoEnum.SE, ComplexidadeItemEnum.BAIXA), 7);

		setPontosFuncao(mapa.get(Pair.of(getFuncao(), getComplexidade())));
	}
}
