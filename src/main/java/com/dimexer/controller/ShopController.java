package com.dimexer.controller;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.NavigationHandler;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import com.dimexer.ejb.ShopBean;
import com.dimexer.model.Shop;

import java.util.List;

@ManagedBean
@RequestScoped
public class ShopController {

	private List<Shop> availableShops;
	private Shop currentShop;

	@EJB
	private ShopBean shopBean;

	@PostConstruct
	public void initialize() {
		FacesContext fc = FacesContext.getCurrentInstance();
		this.availableShops = shopBean.extractAvailableShops();

		HttpServletRequest req = (HttpServletRequest) fc.getExternalContext()
				.getRequest();
		String idString = req.getParameter("sid");
		NavigationHandler nh = fc.getApplication().getNavigationHandler();
		Integer id = null;
		if (idString != null) {
			try {
				id = Integer.parseInt(idString);
			} catch (Exception ex) {
				nh.handleNavigation(fc, null, "pretty:notfound");
			}
		}

		if (id != null) {
			this.currentShop = shopBean.loadShopById(id);
			if (this.currentShop == null) {
				nh.handleNavigation(fc, null, "pretty:notfound");
			}
		}
	}

	public Shop getCurrentShop() {
		return currentShop;
	}

	public void setCurrentShop(Shop currentShop) {
		this.currentShop = currentShop;
	}

	public List<Shop> getAvailableShops() {
		return this.availableShops;
	}

}
