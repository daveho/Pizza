package edu.ycp.cs320.pizza.client;

import com.google.gwt.core.client.GWT;

public class RPC {
	public static final OrderServiceAsync orderService =
			GWT.create(OrderService.class);
}
