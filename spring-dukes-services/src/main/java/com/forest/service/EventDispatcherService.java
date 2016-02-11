package com.forest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import com.forest.event.OrderEvent;

@Service
public class EventDispatcherService {
	
	
	
	@Autowired
	private ApplicationEventPublisher 			publisher;
	
	public void publish(OrderEvent<?> event){
		publisher.publishEvent(event);
	}
}
