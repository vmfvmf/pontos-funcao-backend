package com.vmf.entities;

import java.time.LocalDate;

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
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.DiscriminatorOptions;

import com.vmf.enums.ComplexidadeItemEnum;
import com.vmf.enums.ContagemItemFuncaoEnum;
import com.vmf.interfaces.IHaveCriadoModificadoId;
import com.vmf.interfaces.IHaveEntidadeOrigem;

@Entity
@Table(name = "contagem_item")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipo")
@DiscriminatorOptions(force = true)
public abstract class AbstractContagemItem extends AbstractBase implements IHaveCriadoModificadoId, IHaveEntidadeOrigem<AbstractContagemItem> {
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
	
	@OneToOne
	@JoinColumn(name="contagem_item_origem_id")
	private AbstractContagemItem contagemItemOrigem;
	
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

	public AbstractContagemItem getEntidadeOrigem() {
		return contagemItemOrigem;
	}

	public void setContagemItemOrigem(AbstractContagemItem contagemItemOrigem) {
		this.contagemItemOrigem = contagemItemOrigem;
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
	
	public static void clone(AbstractContagemItem nova, AbstractContagemItem origem) {
		nova.setComplexidade(origem.getComplexidade());
		nova.setCriado(LocalDate.now());
		nova.setFuncao(origem.getFuncao());
		nova.setNome(origem.getNome());
		nova.setPf(origem.getPf());
		nova.setTd(origem.getTd());
		nova.setTr(origem.getTr());
		nova.setContagemItemOrigem(calculateContagemOrigem(origem));
	}
	
	protected static AbstractContagemItem calculateContagemOrigem(AbstractContagemItem origem) {
		return  origem.getEntidadeOrigem() != null && origem.equals(origem.getEntidadeOrigem())
				?  origem.getEntidadeOrigem() : origem;
	}
}
