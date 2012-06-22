package com.dimexer.controller;

import java.util.Map;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.NavigationHandler;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import com.dimexer.ejb.CartBean;
import com.dimexer.ejb.ProductBean;
import com.dimexer.model.Product;

@ManagedBean
@RequestScoped
public class ProductController {

	private Product currentProduct;
	private String buyId;

	@EJB
	private ProductBean productBean;

	@ManagedProperty(value = "#{userController}")
	private UserController userController;

	@PostConstruct
	public void initialize() {
		FacesContext fc = FacesContext.getCurrentInstance();
		HttpServletRequest req = (HttpServletRequest) fc.getExternalContext().getRequest();
		String idString = req.getParameter("pid");
		NavigationHandler nh = fc.getApplication().getNavigationHandler();
		Integer id = null;

		try {
			id = Integer.parseInt(idString);
		} catch (Exception ex) {
			nh.handleNavigation(fc, null, "pretty:notfound");
		}

		if (id != null) {
			this.currentProduct = productBean.loadProductById(id);
			if(this.currentProduct == null){
				nh.handleNavigation(fc, null, "pretty:notfound");
			}
		}

	}

	public Object addToCart() {
		Map<String, String> params = FacesContext.getCurrentInstance()
				.getExternalContext().getRequestParameterMap();
		String id = params.get("productId");
		buyId = id;
		if (id != null) {
			CartBean custCart = userController.getCart();
			custCart.getProducts().add(
					productBean.loadProductById(Integer.valueOf(id)));
			custCart.setSize(custCart.getSize() + 1);
		}

		return "pretty:productr";

	}

	public Product getCurrentProduct() {
		return currentProduct;
	}

	public void setCurrentProduct(Product currentProduct) {
		this.currentProduct = currentProduct;
	}

	public ProductBean getProductBean() {
		return productBean;
	}

	public void setProductBean(ProductBean productBean) {
		this.productBean = productBean;
	}

	public UserController getUserController() {
		return userController;
	}

	public void setUserController(UserController userController) {
		this.userController = userController;
	}

	public String getBuyId() {
		return buyId;
	}

	public void setBuyId(String buyId) {
		this.buyId = buyId;
	}

}
