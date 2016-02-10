package com.forest.payment.services;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.spring.scope.RequestContextFilter;

public class PaymentApplication extends ResourceConfig {
	/**
     * Register JAX-RS application components.
     */
    public PaymentApplication() {
    	register(RequestContextFilter.class);
    	register(PaymentService.class);
    }

}
