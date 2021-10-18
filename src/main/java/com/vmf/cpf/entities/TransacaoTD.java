package com.vmf.cpf.entities;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.vmf.cpf.dto.TransacaoTDDto;
import com.vmf.cpf.interfaces.IHaveCriadoModificadoId;


@Table(name = "transacao_td")
@Entity
public class TransacaoTD extends AbstractBase implements IHaveCriadoModificadoId {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2848952794406058442L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
		
	@ManyToOne
	@JoinColumn(name="contagem_item_id")
	private ContagemItemTransacao itemTransacao;
	
	@OneToOne
	@JoinColumn(name="coluna_id")
	private Coluna coluna;
	
	@OneToOne
	@JoinColumn(name="transacao_td_origem_id")
	private TransacaoTD entidadeOrigem;
	
	@Column
	private LocalDate criado;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public ContagemItemTransacao getItemTransacao() {
		return itemTransacao;
	}

	public void setItemTransacao(ContagemItemTransacao itemTransacao) {
		this.itemTransacao = itemTransacao;
	}

	public Coluna getColuna() {
		return coluna;
	}

	public void setColuna(Coluna coluna) {
		this.coluna = coluna;
	}

	public TransacaoTD getEntidadeOrigem() {
		return entidadeOrigem;
	}

	public void setEntidadeOrigem(TransacaoTD entidadeOrigem) {
		this.entidadeOrigem = entidadeOrigem;
	}

	public LocalDate getCriado() {
		return criado;
	}

	public void setCriado(LocalDate criado) {
		this.criado = criado;
	}

	@Override
	public void setModificado(LocalDate date) {
	}
	
	@Override
	public TransacaoTD clone() {
		TransacaoTD nova = new TransacaoTD();
		nova.setCriado(LocalDate.now());
		nova.setEntidadeOrigem(this);
		
		return nova;
	}

	public Boolean checkIsOrigemDaLista(TransacaoTD origemSelecionada, List<TransacaoTDDto> tds) {
		for(int i = 0; i < tds.size(); i++) {
			TransacaoTDDto td = tds.get(i);
			if (origemSelecionada.getId() == td.getEntidadeOrigem().getId()) {
				tds.remove(td);
				return true;
			} else if (td.getEntidadeOrigem() != null) {
				if (checkIsOrigem(origemSelecionada, td.getEntidadeOrigem())) {
					tds.remove(td);
					return true;
				}
			}
		};
		return false;
	}
	
	public Boolean checkIsOrigem(TransacaoTD origemSelecionada, TransacaoTD atual) {
		if (atual == null) {
			return false;
		} else if (atual.getId() == origemSelecionada.getId()) {
			return true;
		}
		return checkIsOrigem(origemSelecionada, atual.getEntidadeOrigem());
	}
	
	public TransacaoTD findOrigemDaSelecionada(
			List<TransacaoTD> compararOrigem) {
		if (compararOrigem.contains(this)) {
			return this;
		}
		if (this.getEntidadeOrigem() == null) {
			return null;
		}
		return this.getEntidadeOrigem().findOrigemDaSelecionada(compararOrigem);
	}
}
