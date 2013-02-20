package edu.ycp.cs320.pizza.client;

import com.google.gwt.user.client.rpc.RemoteService;

public interface OrderService extends RemoteService {
	/**
	 * Just for testing.
	 * 
	 * @param message a message to send to the server.
	 * @return true if successful, false otherwise
	 */
	public Boolean hello(String message);
	
	/**
	 * Place an order.
	 * 
	 * @param pizza the pizza to be ordered
	 * @return true if successful, false otherwise
	 */
	public Boolean placeOrder(PizzaApp pizza, String customerName, String customerAddress);
}
