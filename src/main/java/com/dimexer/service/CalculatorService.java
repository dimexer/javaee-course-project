package com.dimexer.service;

import javax.ejb.Singleton;
import javax.ws.rs.Consumes;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.xml.bind.JAXBElement;

import com.dimexer.model.Order;
import com.dimexer.model.OrderEntry;
import com.dimexer.util.CalcRequest;
import com.dimexer.util.CalcResponse;
import com.dimexer.util.RequestEntry;

@Singleton
@Path(value = "/calc")
public class CalculatorService {

	@PUT
	@Produces(MediaType.APPLICATION_XML)
	@Consumes(MediaType.APPLICATION_XML)
	public CalcResponse calculateOrderPrice(JAXBElement<CalcRequest> req) {
		CalcRequest products = req.getValue();
		double price = 0;

		for (RequestEntry entry : products.getEntries()) {
			price += entry.getQuantity() * entry.getPrice();
			System.out.printf("%d x %d=%f \n", entry.getQuantity(), entry.getId(), entry.getPrice());
		}

		CalcResponse res = new CalcResponse();
		res.setOrderPrice(price);
		return res;
	}
}
