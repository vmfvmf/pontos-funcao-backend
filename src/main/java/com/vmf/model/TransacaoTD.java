package com.vmf.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;


@Table(name = "transacao_td")
@Entity
public class TransacaoTD extends Base {

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
}
