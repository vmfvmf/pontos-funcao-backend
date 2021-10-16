package com.vmf.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import org.hibernate.annotations.DiscriminatorOptions;

@Entity
@DiscriminatorValue("A")
@DiscriminatorOptions(force = true)
public class ContagemItemArquivoReferenciado extends AbstractContagemItem {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@OneToMany(cascade = {CascadeType.ALL}, orphanRemoval = true)
	@JoinColumn(name = "contagem_item_id")
	private List<Tabela> tabelas = new ArrayList<>();

	public ContagemItemArquivoReferenciado() {
		super();
		tabelas = new ArrayList<>();
	}
	
	public List<Tabela> getTabelas() {
		return tabelas;
	}

	public void setTabelas(List<Tabela> tabelas) {
		this.tabelas = tabelas;
	}

	@Override
	public ContagemItemArquivoReferenciado clone() {
		ContagemItemArquivoReferenciado novo = new ContagemItemArquivoReferenciado();
		ContagemItemArquivoReferenciado.clone(novo, this);
				
		getTabelas().forEach(tabela -> {
			Tabela novaTabela = tabela.clone();
			novo.getTabelas().add(novaTabela);
		});
		
		return novo;
	}

	public List<Tabela> getObjetos() {
		return getTabelas();
	}
}
