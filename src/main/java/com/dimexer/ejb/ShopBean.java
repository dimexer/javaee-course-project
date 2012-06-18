package com.dimexer.ejb;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.dimexer.model.Shop;

@Stateless
public class ShopBean {

	@PersistenceContext
	private EntityManager em;
	
	public List<Shop> extractAvailableShops(){
		Query q = em.createQuery("SELECT s FROM Shop s");
		List<Shop> res = q.getResultList();
		return res;
	}
	
	public Shop loadShopById(Integer id){
		Shop res = em.find(Shop.class, id);
		return res;
	}
}
