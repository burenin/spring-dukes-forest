package com.forest.payment.services;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.forest.event.OrderEvent;

@Path("/pay")
@Component
public class PaymentService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(PaymentService.class);

	@POST
    @Consumes("application/xml")
	public Response processPayment(OrderEvent<?> order) {
		LOGGER.info("Amount: "+order.getAmount());
		if (order.getAmount() < 1000) {
			return Response.ok().build();
        } 
		return Response.status(401).build();
	}
	
	@GET
    @Produces("text/html")
    public String getHtml() {
        return "PaymentService";
    }
}