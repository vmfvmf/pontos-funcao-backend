package com.example.demo.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table
@NamedQuery(name = "Sistema.findByContagemId",
			query = "select s from Sistema s join Contagem c on c.sistema= s where c.sistema.id = ?1")
public class Sistema extends Base {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8760239042074141687L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(name = "nome", nullable = false)
	private String nome;
	
	@Column(name = "versao", nullable = false)
	private String versao;
	
	@JsonManagedReference("sistema_contagens")
	@OneToMany(mappedBy = "sistema")
	private List<Contagem> contagens = new ArrayList<Contagem>();
	
	@JsonManagedReference("sistema_tabelas")
	@OneToMany(mappedBy = "sistema")
	private List<Tabela> tabelas = new ArrayList<Tabela>();

	public List<Contagem> getContagens() {
		return contagens;
	}

	public void setContagens(List<Contagem> contagens) {
		this.contagens = contagens;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getVersao() {
		return versao;
	}

	public void setVersao(String versao) {
		this.versao = versao;
	}

	public List<Tabela> getTabelas() {
		return tabelas;
	}

	public void setTabelas(List<Tabela> tabelas) {
		this.tabelas = tabelas;
	}

	
}
