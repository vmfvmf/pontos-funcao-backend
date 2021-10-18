package com.vmf.cpf.entities;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.vmf.cpf.enums.ContagemEscopoEnum;
import com.vmf.cpf.enums.ContagemEstado;
import com.vmf.cpf.interfaces.IHaveCriadoModificadoId;
import com.vmf.cpf.interfaces.IHaveEntidadeOrigem;

@Entity
@Table
public class Contagem extends AbstractBase implements IHaveEntidadeOrigem<Contagem>, IHaveCriadoModificadoId {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2825945051996560941L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name="sistema_id", nullable=false)
	private Sistema sistema;
	
	@Column(name = "contador", nullable = false)
	private String contador;
	
	@Column(name = "data_contagem", nullable = false)
	private LocalDate dataContagem;
	
	@Column(name = "total_pontos_funcao")
	private Integer totalPontosFuncao;
	
	@Column(name = "escopo", nullable = false)
	@Enumerated(EnumType.STRING)
	private ContagemEscopoEnum escopo;
	
	@ManyToOne
	@JoinColumn(name="projeto_id")
	private Projeto projeto;

	@ManyToOne
	@JoinColumn(name="sprint_id")
	private Sprint sprint;
	
	@Column
	@Enumerated(EnumType.STRING)
	private ContagemEstado estado;
	
	@OneToMany(cascade = {CascadeType.ALL}, orphanRemoval = true)
	@JoinColumn(name = "contagem_id")
	private List<ContagemItemTransacao> transacoes = new ArrayList<>();
	
	@OneToMany(cascade = {CascadeType.ALL}, orphanRemoval = true)
	@JoinColumn(name = "contagem_id")
	private List<ContagemItemArquivoReferenciado> arquivosReferenciados = new ArrayList<>();
	
	@OneToOne
	@JoinColumn(name="contagem_origem_id")
	private Contagem contagemOrigem;
	
	@Column 
	private LocalDate criado;
	
	@Column 
	private LocalDate modificado;
		
	@Column
	private Integer versao;
	
	@Column(name="ultima_versao")
	private Boolean ultimaVersao;
	
	public transient Boolean clonada = false;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getTotalPontosFuncao() {
		return totalPontosFuncao;
	}

	public void setTotalPontosFuncao(Integer totalPontosFuncao) {
		this.totalPontosFuncao = totalPontosFuncao;
	}

	public Sprint getSprint() {
		return sprint;
	}

	public void setSprint(Sprint sprint) {
		this.sprint = sprint;
	}
		
	public Projeto getProjeto() {
		return projeto;
	}

	public void setProjeto(Projeto projeto) {
		this.projeto = projeto;
	}

	public Sistema getSistema() {
		return sistema;
	}

	public void setSistema(Sistema sistema) {
		this.sistema = sistema;
	}

	public String getContador() {
		return contador;
	}

	public void setContador(String contador) {
		this.contador = contador;
	}

	public LocalDate getDataContagem() {
		return dataContagem;
	}

	public void setDataContagem(LocalDate dataContagem) {
		this.dataContagem = dataContagem;
	}

	public ContagemEscopoEnum getEscopo() {
		return escopo;
	}

	public void setEscopo(ContagemEscopoEnum escopo) {
		this.escopo = escopo;
	}

	public ContagemEstado getEstado() {
		return estado;
	}

	public void setEstado(ContagemEstado estado) {
		this.estado = estado;
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

	public Integer getVersao() {
		return versao;
	}

	public void setVersao(Integer versao) {
		this.versao = versao;
	}

	public List<ContagemItemTransacao> getTransacoes() {
		return transacoes;
	}

	public void setTransacoes(List<ContagemItemTransacao> transacoes) {
		this.transacoes = transacoes;
	}

	public List<ContagemItemArquivoReferenciado> getArquivosReferenciados() {
		return arquivosReferenciados;
	}

	public void setArquivosReferenciados(List<ContagemItemArquivoReferenciado> arquivosReferenciados) {
		this.arquivosReferenciados = arquivosReferenciados;
	}

	public Contagem getEntidadeOrigem() {
		return contagemOrigem;
	}

	public void setEntidadeOrigem(Contagem contagemOrigem) {
		this.contagemOrigem = contagemOrigem;
	}

	public Boolean getUltimaVersao() {
		return ultimaVersao;
	}

	public void setUltimaVersao(Boolean ultimaVersao) {
		this.ultimaVersao = ultimaVersao;
	}
	
	public Boolean equals(Contagem other) {
		Boolean equals = false;
		
		// VERIFICA SE THIS TEM UM ARQUIVO QUE OTHER NÃO POSSUÍ
		for (ContagemItemArquivoReferenciado arquivoReferenciado : this.getArquivosReferenciados()) {
			equals = !other.getArquivosReferenciados().stream()
					.anyMatch(arq -> arq.getId() == arquivoReferenciado.getId() && arquivoReferenciado.equals(arq)) || equals;
		}
		
		// VERIFICA SE OTHER TEM UM ARQUIVO QUE THIS NÃO POSSUÍ
		for (ContagemItemArquivoReferenciado arquivoReferenciado : other.getArquivosReferenciados()) {
			equals = !this.getArquivosReferenciados().stream()
					.anyMatch(arq -> arq.getId() == arquivoReferenciado.getId() && arquivoReferenciado.equals(arq)) || equals;
		}
		
		// VERIFICA SE THIS TEM UMA TRANSAÇÃO QUE OTHER NÃO POSSUÍ
		for (ContagemItemTransacao transacao : this.getTransacoes()) {
			equals = !other.getTransacoes().stream()
					.anyMatch(trans -> trans.getId() == transacao.getId() && transacao.equals(trans)) || equals;
		}
		
		// VERIFICA SE OTHER TEM UMA TRANSAÇÃO QUE THIS NÃO POSSUÍ
		for (ContagemItemTransacao transacao : other.getTransacoes()) {
			equals = !this.getTransacoes().stream()
					.anyMatch(trans -> trans.getId() == transacao.getId() && transacao.equals(trans)) || equals;
		}
		
		equals = !this.getUltimaVersao().equals(other.getUltimaVersao()) || equals;
		equals = !this.getContador().equals(other.getContador()) || equals;
		equals = !this.getDataContagem().equals(other.getDataContagem()) || equals;
		equals = !this.getDataContagem().equals(other.getDataContagem()) || equals;
		equals = !this.getEstado().equals(other.getEstado()) || equals;
		
		equals = Objects.equals(other.getProjeto(), this.getProjeto()) || equals;
		equals = Objects.equals(other.getSistema(), this.getSistema()) || equals;
		equals = Objects.equals(other.getSprint(), this.getSprint()) || equals;
		
		return equals;
	}
	
	public Contagem criarEsbocoVersionado() {
		Contagem contagem = new Contagem();
		contagem.setContador(this.getContador());
		contagem.setEntidadeOrigem(this);
		contagem.setDataContagem(LocalDate.now());
		contagem.setEscopo(this.getEscopo());
		contagem.setEstado(ContagemEstado.E);
		contagem.setProjeto(getProjeto());
		contagem.setSistema(getSistema());
		contagem.setSprint(getSprint());
		contagem.setTotalPontosFuncao(getTotalPontosFuncao());
		contagem.setUltimaVersao(true);
		contagem.setVersao(getVersao() + 1);
		contagem.clonada = true;
		
		for(ContagemItemArquivoReferenciado arquivo : getArquivosReferenciados()) {
			ContagemItemArquivoReferenciado novoArquivo = arquivo.clone();
			contagem.getArquivosReferenciados().add(novoArquivo);
		}
		
		for(ContagemItemTransacao transacao : getTransacoes()) {
			ContagemItemTransacao novaTransacao = transacao.clone();
			contagem.getTransacoes().add(novaTransacao);
		}
		
		// REGISTRA A COLUNA DOS TDS DAS TRANSACOES 
		List<Coluna> colunas = new ArrayList<>();
		contagem.getArquivosReferenciados().forEach(arq -> arq.getTabelas()
				.forEach(tab -> tab.getColunas().forEach(col -> colunas.add(col))));
		
		contagem.getTransacoes().forEach(trans -> trans.getTransacaoTDs().forEach(td -> td.setColuna(
				colunas.stream().filter(col -> col.getEntidadeOrigem().equals(td.getEntidadeOrigem().getColuna()))
				.findFirst().get()
		)));
		
		return contagem;
	}
}
