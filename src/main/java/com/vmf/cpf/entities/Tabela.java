package com.vmf.cpf.entities;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.vmf.cpf.dto.TabelaDto;
import com.vmf.cpf.interfaces.IHaveCriadoModificadoId;
import com.vmf.cpf.interfaces.IHaveEntidadeOrigem;


@Entity
@Table
public class Tabela extends AbstractBase implements IHaveCriadoModificadoId, IHaveEntidadeOrigem<Tabela> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8933834758570620231L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "nome", nullable = false)
	private String nome;
	
	@OneToMany(mappedBy = "tabela", cascade = {CascadeType.ALL}, orphanRemoval = true)
	private List<Coluna> colunas = new ArrayList<>();
	
	@ManyToOne
	@JoinColumn(name="contagem_item_id")
	private ContagemItemArquivoReferenciado arquivoReferenciado;
	
	@OneToOne
	@JoinColumn(name="tabela_origem_id")
	private Tabela entidadeOrigem;
	
	@Column
	private LocalDate criado;
	
	@Column
	private LocalDate modificado;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<Coluna> getColunas() {
		return colunas;
	}

	public void setColunas(List<Coluna> colunas) {
		this.colunas = colunas;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public ContagemItemArquivoReferenciado getArquivoReferenciado() {
		return arquivoReferenciado;
	}

	public void setArquivoReferenciado(ContagemItemArquivoReferenciado arquivoReferenciado) {
		this.arquivoReferenciado = arquivoReferenciado;
	}

	public Tabela getEntidadeOrigem() {
		return entidadeOrigem;
	}

	public void setEntidadeOrigem(Tabela tabelaOrigem) {
		this.entidadeOrigem = tabelaOrigem;
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

	@Override
	public Tabela clone() {
		Tabela nova = new Tabela();
		nova.setCriado(LocalDate.now());
		nova.setNome(getNome());
		nova.setEntidadeOrigem(this);		
		
		for(Coluna coluna : getColunas()) {
			Coluna novaColuna = coluna.clone();
			nova.getColunas().add(novaColuna);
		}
		
		return nova;
	}

	public Boolean checkIsOrigemDaLista(List<TabelaDto> tds) {
		for(int i = 0; i < tds.size(); i++) {
			TabelaDto td = tds.get(i);
			if (getId() == td.getEntidadeOrigem().getId()) {
				tds.remove(td);
				return true;
			} else if (td.getEntidadeOrigem() != null) {
				if (td.getEntidadeOrigem().checkIsOrigem(this)) {
					tds.remove(td);
					return true;
				}
			}
		};
		return false;
	}
	
	public Boolean checkIsOrigem(Tabela origemSelecionada) {
		if (this.getId() == origemSelecionada.getId()) {
			return true;
		} else if (this.getEntidadeOrigem() == null) {
			return false;
		} 
		return this.getEntidadeOrigem().checkIsOrigem(origemSelecionada);
	}
	
	public Tabela findOrigemDaSelecionada(
			List<Tabela> compararOrigem) {
		if (compararOrigem.contains(this)) {
			return this;
		}
		if (this.getEntidadeOrigem() == null) {
			return null;
		}
		return this.getEntidadeOrigem().findOrigemDaSelecionada(compararOrigem);
	}
}
