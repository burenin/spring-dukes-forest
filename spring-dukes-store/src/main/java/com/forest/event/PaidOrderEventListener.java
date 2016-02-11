package com.forest.event;

import java.util.logging.Level;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import com.forest.entity.CustomerOrder;
import com.forest.service.IOrderService;
import com.forest.service.IOrderStatusService;

@Component
public class PaidOrderEventListener {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(PaidOrderEventListener.class);
	
	@Autowired
	private IOrderService orderService;
	
	@EventListener
    public void handlePaidOrderEvent(OrderEvent<PaidOrder> event) {
		LOGGER.info("{} Event being processed by DeliveryHandler", Thread.currentThread().getName());
		try {           
			LOGGER.info(String.format("Order #%s has been paid in the amount of %s. Order is now ready for delivery!", event.getOrderID(), event.getAmount()));
			
			orderService.setOrderStatus(event.getOrderID(), IOrderStatusService.Status.READY_TO_SHIP.getStatus());
			
			CustomerOrder order = orderService.findById(event.getOrderID());
            if (order != null) {
//                orderPublisher.sendMessage(order);
               
            } else {
                throw new Exception("The order does not exist");
            }
        } catch (Exception jex) {
        	LOGGER.error("Unable to handle paid order", jex);
        }
	}

}
