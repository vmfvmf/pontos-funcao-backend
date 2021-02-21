package com.example.demo.model;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.example.demo.enums.TipoTransacaoTDEnum;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@Table
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@MappedSuperclass
public abstract class TransacaoTD extends Base {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2848952794406058442L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column
	private TipoTransacaoTDEnum tipoTransacaoTd;
	
	@JsonIgnoreProperties("transacao")
	@ManyToOne
	@JoinColumn(name="transacao_id", nullable=false)
	private ContagemItem transacao;
	
	@JsonIgnoreProperties("coluna")
	@OneToOne
	@JoinColumn(name="coluna_id")
	private Coluna coluna;
	
	@OneToOne
	@JoinColumn(name="mensagem_tela_id", nullable=false)
	private MensagemTela mensagemTela;
	
	
	public TipoTransacaoTDEnum getTipoTransacaoTd() {
		return tipoTransacaoTd;
	}

	public void setTipoTransacaoTd(TipoTransacaoTDEnum tipoTransacaoTd) {
		this.tipoTransacaoTd = tipoTransacaoTd;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public ContagemItem getTransacao() {
		return transacao;
	}

	public void setTransacao(ContagemItem transacao) {
		this.transacao = transacao;
	}

	public Coluna getColuna() {
		return coluna;
	}

	public void setColuna(Coluna coluna) {
		this.coluna = coluna;
	}
	
}
