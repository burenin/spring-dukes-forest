package com.forest.payment.services;

import javax.ws.rs.ApplicationPath;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.spring.scope.RequestContextFilter;

@ApplicationPath("/payment")
public class PaymentApplication extends ResourceConfig {
	/**
     * Register JAX-RS application components.
     */
    public PaymentApplication() {
    	register(RequestContextFilter.class);
    	register(PaymentService.class);
    }

}
