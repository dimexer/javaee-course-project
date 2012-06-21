package com.dimexer.util;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class CalcResponse {
	private double orderPrice;

	public double getOrderPrice() {
		return orderPrice;
	}

	public void setOrderPrice(double orderPrice) {
		this.orderPrice = orderPrice;
	}

}
