package com.dimexer.ejb;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.dimexer.model.Customer;
import com.dimexer.model.Order;
import com.dimexer.model.OrderEntry;
import com.dimexer.model.Product;

@Stateless
public class CheckoutBean {
	
	@PersistenceContext
	private EntityManager em;
	
	public void createOrder(List<Product> products, Customer cust){
		Order ord = new Order();
		ord.setCustomer(cust);
		em.persist(ord);
		for(Product p : products){
			OrderEntry oe = new OrderEntry();
			oe.setQuantity(1);
			oe.setProduct(p);
			oe.setOrder(ord);
			em.persist(oe);
		}
	}

	public EntityManager getEm() {
		return em;
	}

	public void setEm(EntityManager em) {
		this.em = em;
	}
	

}
