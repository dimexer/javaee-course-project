package com.dimexer.controller;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import com.dimexer.ejb.UserBean;
import com.dimexer.model.Customer;

@ManagedBean
@RequestScoped
public class RegistrationController {
	private String email;
	private String firstName;
	private String lastName;
	private String password;
	private String repeatedPassword;
	private String address;
	private String phoneNumber;

	@EJB
	private UserBean userBean;

	@ManagedProperty(value = "userController")
	private UserController userController;

	public String register() {
		if (!password.equals(repeatedPassword)) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage("Entered passwords do not match"));
			return null;
		}
		Customer cust = userBean.createCustomer(email, firstName, lastName,
				password, address, phoneNumber);
		if (cust == null) {
			FacesContext
					.getCurrentInstance()
					.addMessage(
							null,
							new FacesMessage(
									"Something's wrong with the registration. Maybe user already exists..."));
		return null;
		}
		this.userController.setCustomer(cust);
		return "index";
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRepeatedPassword() {
		return repeatedPassword;
	}

	public void setRepeatedPassword(String repeatedPassword) {
		this.repeatedPassword = repeatedPassword;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public UserBean getUserBean() {
		return userBean;
	}

	public void setUserBean(UserBean userBean) {
		this.userBean = userBean;
	}

	public UserController getUserController() {
		return userController;
	}

	public void setUserController(UserController userController) {
		this.userController = userController;
	}

}
