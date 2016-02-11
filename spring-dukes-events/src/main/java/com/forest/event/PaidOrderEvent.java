package com.forest.event;

public class PaidOrderEvent extends OrderEvent<PaidOrder>{

	private static final long serialVersionUID = 6729875833564499126L;
	
	public PaidOrderEvent() {
		super("Paid Order Event");
	}

	public PaidOrderEvent(Object source) {
		super(source);
	}

	public PaidOrderEvent(Object source, OrderEvent<?> orderEvent) {
		super(source, orderEvent);
	}
}
