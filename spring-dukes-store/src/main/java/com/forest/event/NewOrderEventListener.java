package com.forest.event;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.ClientRequestContext;
import javax.ws.rs.client.ClientRequestFilter;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.xml.bind.DatatypeConverter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import com.forest.payment.services.XmlOrderEvent;
import com.forest.service.EventDispatcherService;
import com.forest.service.IOrderService;
import com.forest.service.IOrderStatusService;

@Component
public class NewOrderEventListener  {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(NewOrderEventListener.class);
	
	private static final String ENDPOINT = "http://localhost:8080/spring-dukes-payment/payment/pay";
	
	@Autowired
	private IOrderService orderService;
	
	@Autowired
	private EventDispatcherService 	eventDispatcherService;
	
	
	@EventListener
    public void handleNewOrderEvent(OrderEvent<NewOrder> event) {
		
		LOGGER.info("{} Event being processed by OrderEventListener", Thread.currentThread().getName());
		

        if (processPayment(event)) {
        	orderService.setOrderStatus(event.getOrderID(), IOrderStatusService.Status.PENDING_PAYMENT.getStatus());
        	LOGGER.info("Payment Approved");
        	eventDispatcherService.publish(new PaidOrderEvent(this, event));
        } else {
        	orderService.setOrderStatus(event.getOrderID(), IOrderStatusService.Status.CANCELLED_PAYMENT.getStatus());
        	LOGGER.info("Payment Denied");
        }
		
		
    }
	
	private boolean processPayment(OrderEvent<NewOrder> order) {

        boolean success = false;
        XmlOrderEvent xmlOrderEvent = new XmlOrderEvent(order);
        Client client = ClientBuilder.newClient();
//        client.register(new AuthClientRequestFilter("jack@example.com", "1234"));
        Response resp = client.target(ENDPOINT)
                .request(MediaType.APPLICATION_XML)
                .post(Entity.entity(xmlOrderEvent, MediaType.APPLICATION_XML), Response.class);
        int status = resp.getStatus();
        if (status == 200) {
            success = true;
        }
        LOGGER.info("[PaymentHandler] Response status {}", status);
        client.close();
        return success;
    }
    
    /* Client filter for basic HTTP auth */
    class AuthClientRequestFilter implements ClientRequestFilter {
        private final String user;
        private final String password;
        public AuthClientRequestFilter(String user, String password) {
            this.user = user;
            this.password = password;
        }
        @Override
        public void filter(ClientRequestContext requestContext) throws IOException {
            try {
                requestContext.getHeaders().add(
                        "Authorization",
                        "BASIC " + DatatypeConverter.printBase64Binary(
                                   (user+":"+password).getBytes("UTF-8"))
                );
            } catch (UnsupportedEncodingException ex) { }
        }
    }
}
