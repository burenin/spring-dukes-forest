package com.forest.payment.services;

import com.forest.event.OrderEvent;

public interface IPaymentService {
	
	boolean processPayment(OrderEvent<?> order) ;

}
