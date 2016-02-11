package com.forest.service;

import com.forest.entity.OrderStatus;

public interface IOrderStatusService extends IService<OrderStatus> {
	
	public enum Status {

        PENDING_PAYMENT(2),
        READY_TO_SHIP(3),
        SHIPPED(4),
        CANCELLED_PAYMENT(5),
        CANCELLED_MANUAL(6);
        private int status;

        private Status(int pStatus) {
            status = pStatus;
        }

        public int getStatus() {
            return status;
        }
    }
	
}
