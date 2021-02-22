package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import com.example.demo.model.ContagemItem;

public interface ContagemItemService {
	public List<ContagemItem> findAll(ContagemItem filtro);
	public ContagemItem save(ContagemItem d);
	public void deleteById(long id);
	public Optional<ContagemItem> findById(long id);
	public void apagaTdsByContagemItem(long item_id);
}
