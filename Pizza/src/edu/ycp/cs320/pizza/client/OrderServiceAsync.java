package edu.ycp.cs320.pizza.client;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface OrderServiceAsync {

	void hello(String message, AsyncCallback<Boolean> callback);

	void placeOrder(PizzaApp pizza, String customerName, String customerAddress,
			AsyncCallback<Boolean> callback);

}
