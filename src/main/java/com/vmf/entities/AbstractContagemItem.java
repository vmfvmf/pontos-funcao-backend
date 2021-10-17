package com.vmf.entities;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.DiscriminatorOptions;

import com.vmf.enums.ComplexidadeItemEnum;
import com.vmf.enums.ContagemItemFuncaoEnum;
import com.vmf.interfaces.IHaveCriadoModificadoId;

@Entity
@Table(name = "contagem_item")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipo")
@DiscriminatorOptions(force = true)
public abstract class AbstractContagemItem<T> extends AbstractBase implements IHaveCriadoModificadoId {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2621619970365647172L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "nome")
	private String nome;
			
	@Column(name = "td")
	private Integer td;
	
	@Column(name = "tr")
	private Integer tr;	
	
	@Column(name = "complexidade")
	@Enumerated(EnumType.STRING)
	private ComplexidadeItemEnum complexidade;
	
	@ManyToOne
	@JoinColumn(name="contagem_id")
	private Contagem contagem;
	
	@Column(name = "pf")
	private Integer pf;
	
	@Column(name="funcao")
	@Enumerated(EnumType.STRING)
	private ContagemItemFuncaoEnum funcao;
	
	@Column
	private LocalDate criado;
	
	@Column
	private LocalDate modificado;
	
	private transient Boolean compararVersao = false;
				
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}	
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Integer getTd() {
		return td;
	}

	public void setTd(Integer td) {
		this.td = td;
	}
	
	public Integer getTr() {
		return tr;
	}

	public void setTr(Integer tr) {
		this.tr = tr;
	}

	public ComplexidadeItemEnum getComplexidade() {
		return complexidade;
	}

	public void setComplexidade(ComplexidadeItemEnum complexidade) {
		this.complexidade = complexidade;
	}

	public Contagem getContagem() {
		return contagem;
	}

	public void setContagem(Contagem contagem) {
		this.contagem = contagem;
	}

	public Integer getPf() {
		return pf;
	}

	public void setPf(Integer pf) {
		this.pf = pf;
	}

	public ContagemItemFuncaoEnum getFuncao() {
		return funcao;
	}

	public void setFuncao(ContagemItemFuncaoEnum funcao) {
		this.funcao = funcao;
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
	
	public Boolean getCompararVersao() {
		return compararVersao;
	}

	public void setCompararVersao(Boolean compararVersao) {
		this.compararVersao = compararVersao;
	}

	public void clone(AbstractContagemItem<T> nova, AbstractContagemItem<T> origem) {
		nova.setComplexidade(origem.getComplexidade());
		nova.setCriado(LocalDate.now());
		nova.setFuncao(origem.getFuncao());
		nova.setNome(origem.getNome());
		nova.setPf(origem.getPf());
		nova.setTd(origem.getTd());
		nova.setTr(origem.getTr());
	}

	public abstract T findOrigemDaSelecionada(List<T> compararOrigem);
}
