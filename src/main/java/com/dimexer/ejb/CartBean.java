package com.dimexer.ejb;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.PostActivate;
import javax.ejb.Stateless;

import com.dimexer.model.Product;

@Stateless
public class CartBean {

	int size;
	private List<Product> products;

	public CartBean() {
		products = new ArrayList<Product>();
		size = 0;
	}

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

}
