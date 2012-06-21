package com.dimexer.util;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class RequestEntry {
		private int id;
		private int quantity;
		private double price;

		public RequestEntry(){
			
		}
		
		public RequestEntry(int id, int q, double p){
			this.id=id;
			this.quantity=q;
			this.price=p;
		}
		
		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}

		public int getQuantity() {
			return quantity;
		}

		public void setQuantity(int quantity) {
			this.quantity = quantity;
		}

		public double getPrice() {
			return price;
		}

		public void setPrice(double price) {
			this.price = price;
		}

	}