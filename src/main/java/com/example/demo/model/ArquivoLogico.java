package com.example.demo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


@Entity
@Table
public class ArquivoLogico extends ItemContagem{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1822603001509468294L;
	
	
	@Column(name = "tr")
	private Integer TR;
	
	@Column(name = "tr")
	private List<List<String>> TR;

	public Integer getTR() {
		return TR;
	}

	public void setTR(Integer tR) {
		TR = tR;
	}
}
