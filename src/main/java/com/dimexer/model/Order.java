package com.dimexer.model;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="`order`")
public class Order {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@OneToMany (mappedBy="order", fetch=FetchType.EAGER)
	private Set<OrderEntry> entries;
	
	@ManyToOne
	private Customer customer;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Object[] getEntries() {
		return entries.toArray();
	}

	public void setEntries(Set<OrderEntry> entries) {
		this.entries = entries;
	}
}
