package com.forest.event;

import java.io.UnsupportedEncodingException;

import javax.xml.bind.DatatypeConverter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import com.forest.controller.OrderController;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientHandlerException;
import com.sun.jersey.api.client.ClientRequest;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.filter.ClientFilter;

@Component
public class OrderEventListener  {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(OrderEventListener.class);
	
	
	@EventListener
    public void handleNewOrderEvent(OrderEvent<NewOrder> event) {
		
		LOGGER.info("{} Event being processed by OrderEventListener", Thread.currentThread().getName());
		
		
    }
	
	private boolean processPayment(OrderEvent order) {

        boolean success = false;
        
        Client client = Client.create();
        client.addFilter();
        
        Client client = ClientBuilder.newClient();
        client.register(new AuthClientRequestFilter("jack@example.com", "1234"));
        Response resp = client.target(ENDPOINT)
                .request(MediaType.APPLICATION_XML)
                .post(Entity.entity(order, MediaType.APPLICATION_XML), Response.class);
        int status = resp.getStatus();
        if (status == 200) {
            success = true;
        }
        logger.log(Level.INFO, "[PaymentHandler] Response status {0}", status);
        client.close();
        return success;
    }
    
    /* Client filter for basic HTTP auth */
    class AuthClientRequestFilter extends ClientFilter {
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
		@Override
		public ClientResponse handle(ClientRequest cr) throws ClientHandlerException {
			try {
				cr.getHeaders().add(
                        "Authorization",
                        "BASIC " + DatatypeConverter.printBase64Binary(
                                   (user+":"+password).getBytes("UTF-8"))
                );
            } catch (UnsupportedEncodingException ex) { }
		}
    }
}
