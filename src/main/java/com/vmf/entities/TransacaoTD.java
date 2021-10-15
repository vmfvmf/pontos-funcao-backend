package com.vmf.entities;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.vmf.interfaces.IHaveCriadoModificadoId;


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
	private TransacaoTD transacaoTDOrigem;
	
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

	public TransacaoTD getTransacaoTDOrigem() {
		return transacaoTDOrigem;
	}

	public void setTransacaoTDOrigem(TransacaoTD transacaoTDOrigem) {
		this.transacaoTDOrigem = transacaoTDOrigem;
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
		nova.setTransacaoTDOrigem(calculateTransacaoTDOrigem());
		
		return nova;
	}
	
	private TransacaoTD calculateTransacaoTDOrigem() {
		return  this.getTransacaoTDOrigem() != null && this.equals(this.getTransacaoTDOrigem())
				? this.getTransacaoTDOrigem() : this;
	}
}
