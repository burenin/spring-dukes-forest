package com.forest.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.forest.entity.CustomerOrder;
import com.forest.entity.OrderStatus;
import com.forest.repository.IOrderRepository;

@Service
public class OrderServiceImpl extends AbstractService<CustomerOrder> implements IOrderService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(OrderServiceImpl.class);

	@Autowired
	private IOrderRepository repository;
	
	@Autowired
	private IOrderStatusService orderStatusService;
	

	@Override
	public IOrderRepository getRepository() {
		return repository;
	}


	@Override
	public List<CustomerOrder> getMyOrders(Integer id) {
		return repository.getOrderByCustomerId(id);
	}

	@Transactional
	@Override
	public void setOrderStatus(int orderId, int newStatus) {
		LOGGER.info(String.format("Order id:%s - Status:%s", orderId, newStatus));

        try {
        	CustomerOrder order = repository.findById(orderId);

            if (order != null) {
            	LOGGER.info(String.format("Updating order %s status to %s", order.getId(), newStatus));

                OrderStatus oStatus = orderStatusService.findById(newStatus);
                order.setOrderStatus(oStatus);

                order = repository.save(order);

                LOGGER.info("Order Updated!");
            }

        } catch (Exception ex) {

        	LOGGER.error("Unable to set order status" , ex);
        }
		
	}
}
