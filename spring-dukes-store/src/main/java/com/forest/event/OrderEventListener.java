package com.forest.event;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class OrderEventListener  {
	
	
	@EventListener
    public void handleNewOrderEvent(OrderEvent<NewOrder> event) {
    }
}
