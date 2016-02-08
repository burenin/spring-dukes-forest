package com.forest.payment.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.forest.event.OrderEvent;

public class PaymentServiceImpl implements IPaymentService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(PaymentServiceImpl.class);

	public boolean processPayment(OrderEvent<?> order) {
		LOGGER.info("Amount: "+order.getAmount());
		if (order.getAmount() < 1000) {
            return true;
        } 
		return false;
	}

}
