package com.dimexer.ejb;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.dimexer.model.Product;

@Stateless
public class ProductBean {

	@PersistenceContext
	private EntityManager em;

	public Product loadProductById(Integer id){
		Product res = em.find(Product.class, id);
		return res;
	}
	
	public EntityManager getEm() {
		return em;
	}

	public void setEm(EntityManager em) {
		this.em = em;
	}
	
	
}
