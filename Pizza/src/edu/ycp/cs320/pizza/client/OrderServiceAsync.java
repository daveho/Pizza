package edu.ycp.cs320.pizza.client;

import com.google.gwt.user.client.rpc.AsyncCallback;

import edu.ycp.cs320.pizza.shared.Pizza;

public interface OrderServiceAsync {

	void hello(String message, AsyncCallback<Boolean> callback);

	void placeOrder(Pizza pizza, String customerName, String customerAddress,
			AsyncCallback<Boolean> callback);

	void add(Integer a, Integer b, AsyncCallback<Integer> callback);

}
