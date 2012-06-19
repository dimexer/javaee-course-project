package com.dimexer.ejb;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.dimexer.model.Category;

@Stateless
public class CategoryBean {

	@PersistenceContext
	private EntityManager em;

	public Category loadCategoryById(Integer id) {
		Category res = em.find(Category.class, id);
		return res;
	}

	public EntityManager getEm() {
		return em;
	}

	public void setEm(EntityManager em) {
		this.em = em;
	}
}
