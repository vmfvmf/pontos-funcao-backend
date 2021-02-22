package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceUnit;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.model.ContagemItem;
import com.example.demo.repository.ContagemItemRepository;

@Service("contagemItemService")
public class ContagemItemServiceImpl implements ContagemItemService{
	
	@PersistenceUnit
	private EntityManagerFactory entityManagerFactory;

	@Autowired
	private ContagemItemRepository contagemItemRepository;
		
	@Override
	public List<ContagemItem> findAll(ContagemItem filtro) {
		ExampleMatcher mat = ExampleMatcher.matching().withIgnoreNullValues().withIgnorePaths("id");
		Example<ContagemItem> ex = Example.of(filtro, mat);
		return contagemItemRepository.findAll(ex);
	}

	@Override
	public ContagemItem save(ContagemItem item) {
		return contagemItemRepository.save(item);
	}
	
	@Override
	public void deleteById(long id) {
		contagemItemRepository.deleteById(id);
	}

	@Override
	public Optional<ContagemItem> findById(long id) {
		return contagemItemRepository.findById(id);
	}

	@Override
	@Transactional
	public void apagaTdsByContagemItem(long item_id) {
		EntityManager em = entityManagerFactory.createEntityManager();
		EntityTransaction tx = null;
//		Session session = null;
		try{
//		       session = em.unwrap(Session.class);
		       tx = em.getTransaction();
		       tx.begin();
		       em.createQuery("delete from TransacaoTD td where td.contagemItem.id = :id")
				.setParameter("id", item_id)
				  .executeUpdate();
		       tx.commit();
		}catch(Exception e) { }		
	}
}
