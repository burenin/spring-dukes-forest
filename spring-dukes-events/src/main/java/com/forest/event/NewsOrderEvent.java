package com.forest.event;

public class NewsOrderEvent extends OrderEvent<NewOrder> {

	private static final long serialVersionUID = -5268560410885582285L;
	
	public NewsOrderEvent() {
		super("New Order Event");
	}
	
	public NewsOrderEvent(Object source) {
		super(source);
	}

	public NewsOrderEvent(Object source, OrderEvent<?> orderEvent) {
		super(source, orderEvent);
	}
}
