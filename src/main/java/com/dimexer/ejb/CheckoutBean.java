package com.dimexer.ejb;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.core.MediaType;

import com.dimexer.model.Customer;
import com.dimexer.model.Order;
import com.dimexer.model.OrderEntry;
import com.dimexer.model.Product;
import com.dimexer.util.CalcRequest;
import com.dimexer.util.CalcResponse;
import com.dimexer.util.RequestEntry;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;

@Stateless
public class CheckoutBean {

	@PersistenceContext
	private EntityManager em;

	public void createOrder(List<Product> products, Customer cust) {
		Order ord = new Order();
		ord.setCustomer(cust);
		em.persist(ord);
		
		CalcRequest req = new CalcRequest();
		for (Product p : products) {
			OrderEntry oe = new OrderEntry();
			oe.setQuantity(1);
			oe.setProduct(p);
			oe.setOrder(ord);
			em.persist(oe);
			req.add(new RequestEntry(p.getId(), 1, p.getPrice()));
		}

		// web service call
		Client cl = Client.create();
		WebResource wr = cl.resource("http://localhost:8080/web-mall/rest");
		CalcResponse res = wr.path("calc").accept(MediaType.APPLICATION_XML)
				.put(CalcResponse.class, req);
		System.out.println("ORDER PRICE: " + res.getOrderPrice());
		// end of web service call
	}

	public EntityManager getEm() {
		return em;
	}

	public void setEm(EntityManager em) {
		this.em = em;
	}

}
