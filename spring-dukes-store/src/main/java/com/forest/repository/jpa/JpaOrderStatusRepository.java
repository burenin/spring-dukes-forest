package com.forest.repository.jpa;

import org.springframework.stereotype.Repository;

import com.forest.entity.Customer;
import com.forest.entity.OrderStatus;
import com.forest.repository.IOrderStatusRepository;

@Repository
public class JpaOrderStatusRepository extends AbstractRepository<OrderStatus> implements IOrderStatusRepository {

	public JpaOrderStatusRepository(){
		super(OrderStatus.class);
	}
	
}
