package com.dimexer.controller;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
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
		this.availableShops = shopBean.extractAvailableShops();

		HttpServletRequest req = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext()
				.getRequest();
		String idString = req.getParameter("sid");
		Integer id = null;
		
		try{
			id = Integer.parseInt(idString);
		}
		catch(Exception ex){
			System.out.println("Bad id passed. Can't instantiate shop");
		}
		
		if (id != null) {
				this.currentShop = shopBean.loadShopById(id);
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
