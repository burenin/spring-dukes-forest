package com.forest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.forest.entity.OrderStatus;
import com.forest.repository.IOrderStatusRepository;

@Service
public class OrderStatusServiceImpl extends AbstractService<OrderStatus> implements IOrderStatusService {

	@Autowired
	private IOrderStatusRepository repository;
	

	@Override
	public IOrderStatusRepository getRepository() {
		return repository;
	}

}
