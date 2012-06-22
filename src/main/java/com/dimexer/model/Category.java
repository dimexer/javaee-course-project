package com.dimexer.model;

import java.util.List;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

@Entity
public class Category {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@NotNull
	@ManyToOne
	private Shop shop;
	
	@NotNull
	private String name;
	
	@OneToMany(mappedBy="parent", fetch=FetchType.EAGER)
	private List<Category> subcategories;
	
	@ManyToOne
	private Category parent;

	@OneToMany(mappedBy="category", fetch=FetchType.EAGER)
	private Set<Product> products;

	public Shop getShop() {
		return shop;
	}

	public void setShop(Shop shop) {
		this.shop = shop;
	}

	public List<Category> getSubcategories() {
		return subcategories;
	}

	public void setSubcategories(List<Category> subcategories) {
		this.subcategories = subcategories;
	}

	public Category getParent() {
		return parent;
	}

	public void setParent(Category parent) {
		this.parent = parent;
	}

	public Object[] getProducts() {
		return products.toArray();
	}

	public void setProducts(Set<Product> products) {
		this.products = products;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
