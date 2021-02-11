package com.example.demo.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "sprint")
public class Sprint extends Base {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6696627300095246781L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(name = "numero", nullable = false)
	private Integer numero;
	
	@Column(name = "data_inicio")
	private Date dataInicio;
	
	@Column(name = "data_fim")
	private Date dataFim;
	
	@Column(name = "dias_uteis")
	private Integer diasUteis;
	
	@ManyToOne()
	@JoinColumn(name="ded_id", nullable=false)
	@JsonBackReference
	private Ded ded;

	@JsonManagedReference(value="sprint")
	@OneToMany(mappedBy = "sprint")
	private List<Contagem> contagens;
	
	
	public Ded getDed() {
		return ded;
	}

	public void setDed(Ded ded) {
		this.ded = ded;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Integer getNumero() {
		return numero;
	}

	public void setNumero(Integer numero) {
		this.numero = numero;
	}

	public Date getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(Date dataInicio) {
		this.dataInicio = dataInicio;
	}

	public Date getDataFim() {
		return dataFim;
	}

	public void setDataFim(Date dataFim) {
		this.dataFim = dataFim;
	}

	public Integer getDiasUteis() {
		return diasUteis;
	}

	public void setDiasUteis(Integer diasUteis) {
		this.diasUteis = diasUteis;
	}

	public List<Contagem> getContagens() {
		return contagens;
	}

	public void setContagens(List<Contagem> contagens) {
		this.contagens = contagens;
	}
}
