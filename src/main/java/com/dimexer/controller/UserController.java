package com.dimexer.controller;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import com.dimexer.ejb.CartBean;
import com.dimexer.model.Customer;

@ManagedBean
@SessionScoped
public class UserController {
	
	private Customer customer;
	
	@EJB
	private CartBean cart;
	
	public UserController(){
		System.out.println("Creating user controller");
	}
	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public CartBean getCart() {
		return cart;
	}

	public void setCart(CartBean cart) {
		this.cart = cart;
	}
}
