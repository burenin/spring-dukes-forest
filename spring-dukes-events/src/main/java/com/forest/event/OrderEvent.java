package com.forest.event;

import java.io.Serializable;
import java.util.Date;

import org.springframework.context.ApplicationEvent;

public class OrderEvent<T> extends ApplicationEvent
		implements Serializable {
	
	private static final long serialVersionUID = -6732499855148123891L;
	
	private double amount;
    private Date dateCreated;
    private int customerID;
    private int orderID;
    private int statusID;
    
    public OrderEvent(Object source) {
		super(source);
	}

    
    public int getCustomerID() {
        return customerID;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }    
    
    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public int getStatusID() {
        return statusID;
    }

    public void setStatusID(int statusID) {
        this.statusID = statusID;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String toString() {
        return "[OrderEvent] " + getCustomerID();
    }
}
