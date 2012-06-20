package com.dimexer.controller;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

import com.dimexer.ejb.CheckoutBean;
import com.dimexer.model.Product;

@ManagedBean
@RequestScoped

public class CheckoutController {
	
	@ManagedProperty(value="#{userController}")
	private UserController userController;
	
	@EJB
	private CheckoutBean checkoutBean;
	
	public String checkout(){
		List<Product> order = userController.getCart().getProducts();

		checkoutBean.createOrder(order, userController.getCustomer());
		
		userController.getCart().setProducts(new ArrayList<Product>());
		userController.getCart().setSize(0);
		
		return "pretty:index";
	}

	public UserController getUserController() {
		return userController;
	}

	public void setUserController(UserController userController) {
		this.userController = userController;
	}

	public CheckoutBean getCheckoutBean() {
		return checkoutBean;
	}

	public void setCheckoutBean(CheckoutBean checkoutBean) {
		this.checkoutBean = checkoutBean;
	}
}
