package com.vmf.cpf.entities;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.hibernate.annotations.DiscriminatorOptions;
import org.springframework.data.util.Pair;

import com.vmf.cpf.enums.ComplexidadeItemEnum;
import com.vmf.cpf.enums.ContagemItemFuncaoEnum;
import com.vmf.cpf.interfaces.IHaveEntidadeOrigem;

@Entity
@DiscriminatorValue("A")
@DiscriminatorOptions(force = true)
public class ContagemItemArquivoReferenciado extends AbstractContagemItem<ContagemItemArquivoReferenciado> 
implements IHaveEntidadeOrigem<ContagemItemArquivoReferenciado> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@OneToMany(cascade = {CascadeType.ALL}, orphanRemoval = true)
	@JoinColumn(name = "contagem_item_id")
	private List<Tabela> tabelas = new ArrayList<>();
	
	@OneToOne
	@JoinColumn(name="contagem_item_origem_id")
	private ContagemItemArquivoReferenciado entidadeOrigem;

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
		super.clone(novo, this);
		
		novo.setEntidadeOrigem(this);
				
		getTabelas().forEach(tabela -> {
			Tabela novaTabela = tabela.clone();
			novo.getTabelas().add(novaTabela);
		});
		
		return novo;
	}

	public ContagemItemArquivoReferenciado getEntidadeOrigem() {
		return this.entidadeOrigem;
	}
	
	public void setEntidadeOrigem(ContagemItemArquivoReferenciado entidadeOrigem) {
		this.entidadeOrigem = entidadeOrigem;
	}
	
	public void calcularPontosFuncao() {
	    setTr(getTabelas().size());
	    setTd(getTabelas().stream().reduce(0, 
	    	(total, tab) -> total + tab.getColunas().stream()
	    	.reduce(0, (total2, col) -> 1 + total2, Integer::sum), Integer::sum));
	    analisarComplexidade();
	    this.calcularPF();
	}
	
	private void analisarComplexidade() {
		Boolean complexidadeBaixa = (this.getTd() < 50 && this.getTr() == 1) ||
			      (this.getTd() < 20 && this.getTr() <= 5),
			    complexidadeMedia = (this.getTd() > 50 && this.getTr() == 1) ||
			  	      (this.getTd() >= 20 && this.getTd() <= 50 && this.getTr() <= 5);
	    
		if (complexidadeBaixa) {
	      this.setComplexidade(ComplexidadeItemEnum.BAIXA);
	    } else if (complexidadeMedia) {
	    	this.setComplexidade(ComplexidadeItemEnum.MEDIA);
	    } else {
	    	this.setComplexidade(ComplexidadeItemEnum.ALTA);
	    }
	  }

	  private void calcularPF() {
		  Map<Pair<ContagemItemFuncaoEnum, ComplexidadeItemEnum>, Integer> mapa = 
				  new HashMap<Pair<ContagemItemFuncaoEnum,ComplexidadeItemEnum>, Integer>();
		  
		  mapa.put(Pair.of(ContagemItemFuncaoEnum.ALI, ComplexidadeItemEnum.BAIXA), 7);
		  mapa.put(Pair.of(ContagemItemFuncaoEnum.ALI, ComplexidadeItemEnum.MEDIA), 10);
		  mapa.put(Pair.of(ContagemItemFuncaoEnum.ALI, ComplexidadeItemEnum.BAIXA), 15);
		  
		  mapa.put(Pair.of(ContagemItemFuncaoEnum.AIE, ComplexidadeItemEnum.BAIXA),5);
		  mapa.put(Pair.of(ContagemItemFuncaoEnum.AIE, ComplexidadeItemEnum.MEDIA), 7);
		  mapa.put(Pair.of(ContagemItemFuncaoEnum.AIE, ComplexidadeItemEnum.BAIXA), 10);

	      setPontosFuncao(mapa.get(Pair.of(getFuncao(), getComplexidade())));
	  }
}
