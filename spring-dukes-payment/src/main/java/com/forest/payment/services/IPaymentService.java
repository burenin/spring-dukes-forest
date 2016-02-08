package com.forest.payment.services;

public interface IPaymentService {
	
	boolean processPayment(OrderEvent order) ;

}
