package com.forest.payment.services;

import java.io.Serializable;
import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import com.forest.event.OrderEvent;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class XmlOrderEvent implements Serializable {
		
		
	private static final long serialVersionUID = -1608173866614184367L;
	
		private double amount;
	    private Date dateCreated;
	    private int customerID;
	    private int orderID;
	    private int statusID;
	    
	    public XmlOrderEvent() {
		}
	    
	    
	    public XmlOrderEvent(OrderEvent<?> orderEvent){
	    	this.amount = orderEvent.getAmount();
	    	this.dateCreated = orderEvent.getDateCreated();
	    	this.customerID = orderEvent.getCustomerID();
	    	this.orderID = orderEvent.getOrderID();
	    	this.statusID = orderEvent.getStatusID();
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
