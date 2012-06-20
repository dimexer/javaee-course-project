package com.dimexer.ejb;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.dimexer.model.Customer;

@Stateless
public class UserBean {

	@PersistenceContext
	private EntityManager em;

	public Customer login(String email, String password) {
		if (email == null || password == null)
			return null;

		// Customer usr = em.find(Customer.class, null, params);
		Query query = em
				.createQuery("SELECT c FROM Customer c where email=:email and password=:password");
		query.setParameter("email", email);
		query.setParameter("password", password);
		List<Customer> res = query.getResultList();

		Customer usr = null;
		if (res.size() > 0)
			usr = res.get(0);

		return usr;

	}
	
	public Customer createCustomer(String email, String firstName, String lastName, String password, String address, String phoneNumber){
		Customer cust = new Customer();
		cust.setEmail(email);
		cust.setFirstName(firstName);
		cust.setLastName(lastName);
		cust.setPassword(password);
		cust.setAddress(address);
		cust.setPhoneNumber(phoneNumber);
		
		try{
		em.persist(cust);
		}catch(Exception ex){
			ex.printStackTrace();
			return null;
		}
		return cust;
	}
}
