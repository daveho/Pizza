package edu.ycp.cs320.pizza.server;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import edu.ycp.cs320.pizza.client.OrderService;
import edu.ycp.cs320.pizza.client.Pizza;

public class OrderServiceImpl extends RemoteServiceServlet implements OrderService {
	private static final long serialVersionUID = 1L;

	@Override
	public Boolean hello(String message) {
		System.out.println("Hello: " + message);
		return true;
	}
	
	@Override
	public Boolean placeOrder(Pizza pizza, String customerName,
			String customerAddress) {
		System.out.println("Pizza order received!");
		
		// TODO
		
		return true;
	}

}
