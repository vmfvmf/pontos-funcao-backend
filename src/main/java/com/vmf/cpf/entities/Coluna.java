package com.vmf.cpf.entities;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.vmf.cpf.dto.ColunaDto;
import com.vmf.cpf.interfaces.IHaveCriadoModificadoId;
import com.vmf.cpf.interfaces.IHaveEntidadeOrigem;


@Entity
@Table(name = "coluna")
public class Coluna extends AbstractBase implements IHaveCriadoModificadoId, IHaveEntidadeOrigem<Coluna> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8933834758570620231L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "nome", nullable = false)
	private String nome;
	
	@ManyToOne
	@JoinColumn(name="tabela_id")
	private Tabela tabela;
	
	@OneToOne
	@JoinColumn(name="coluna_origem_id")
	private Coluna entidadeOrigem;
	
	@Column
	private LocalDate criado;
	
	@Column
	private LocalDate modificado;
		
	public void setNome(String nome) {
		this.nome = nome;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public Tabela getTabela() {
		return tabela;
	}

	public void setTabela(Tabela tabela) {
		this.tabela = tabela;
	}

	public Coluna getEntidadeOrigem() {
		return entidadeOrigem;
	}

	public void setEntidadeOrigem(Coluna colunaOrigem) {
		this.entidadeOrigem = colunaOrigem;
	}

	public LocalDate getCriado() {
		return criado;
	}

	public void setCriado(LocalDate criado) {
		this.criado = criado;
	}

	public LocalDate getModificado() {
		return modificado;
	}

	public void setModificado(LocalDate modificado) {
		this.modificado = modificado;
	}

	public Boolean equals (Coluna other) {
		Boolean equals = false;
		
		if (other == null) {
			return false;
		}
		
		equals = getId().equals(other.getId());
		equals = Objects.equals(getEntidadeOrigem(), other.getEntidadeOrigem()) || equals;
		equals = getNome().equals(other.getNome()) || equals;
		equals = getTabela().equals(other.getTabela()) || equals;
		
		return equals;
	}
	
	@Override
	public Coluna clone() {
		Coluna nova = new Coluna();
		nova.setCriado(LocalDate.now());
		nova.setNome(getNome());
		nova.setEntidadeOrigem(this);		
		
		return nova;
	}
	
	public Coluna findOrigemDaSelecionada(List<Coluna> compararOrigem) {
		if (compararOrigem.contains(this)) {
			return this;
		}
		if (this.getEntidadeOrigem() == null) {
			return null;
		}
		return this.getEntidadeOrigem().findOrigemDaSelecionada(compararOrigem);
	}
	
	public Coluna findOrigemDaSelecionada(Coluna compararOrigem) {
		if (compararOrigem.equals(this)) {
			return this;
		}
		if (this.getEntidadeOrigem() == null) {
			return null;
		}
		return this.getEntidadeOrigem().findOrigemDaSelecionada(compararOrigem);
	}

	public Boolean checkIsOrigemDaLista(List<ColunaDto> colunas) {
		for(int i = 0; i < colunas.size(); i++) {
			ColunaDto col = colunas.get(i);
			if (getId() == col.getEntidadeOrigem().getId()) {
				colunas.remove(col);
				return true;
			} else if (col.getEntidadeOrigem() != null) {
				if (checkIsOrigem(col.getEntidadeOrigem())) {
					colunas.remove(col);
					return true;
				}
			}
		};
		return false;
	}
	
	public Boolean checkIsOrigem(Coluna atual) {
		if (atual == null) {
			return false;
		} else if (atual.getId() == this.getId()) {
			return true;
		}
		return checkIsOrigem(atual.getEntidadeOrigem());
	}
	
}
