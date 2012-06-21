package com.dimexer.controller;

import java.util.ArrayList;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import com.dimexer.ejb.UserBean;
import com.dimexer.model.Customer;
import com.dimexer.model.Product;

@ManagedBean
@RequestScoped
public class LoginController {
	
	private String email;
	
	private String password;

	@EJB
	private UserBean userBean;
	
	@ManagedProperty(value="#{userController}")
	private UserController userController;
	
	public String login(){
		Customer cust = userBean.login(email, password);
		if(cust != null){
			userController.setCustomer(cust);
			return "pretty:index";
		}
		else{
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Incorrect username and/or password!"));
			return null;
		}
	}
	
	public String logout(){
		userController.getCart().setProducts(new ArrayList<Product>());
		userController.getCart().setSize(0);
		userController.setCustomer(null);
		
		return "pretty:index";
	}
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public UserController getUserController() {
		return userController;
	}

	public void setUserController(UserController userController) {
		this.userController = userController;
	}

	public UserBean getUserBean() {
		return userBean;
	}

	public void setUserBean(UserBean userBean) {
		this.userBean = userBean;
	}

}
