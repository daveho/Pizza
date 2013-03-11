package edu.ycp.cs320.pizza.shared;

import java.math.BigDecimal;

public class Order implements ISubscriber {
	private Pizza pizza;
	private String user;
	private BigDecimal price;
	
	public Order() {
		
	}
	
	public void setPizza(Pizza pizza) {
		this.pizza = pizza;
		pizza.subscribe(Pizza.Events.CHANGE_SIZE, this);
		pizza.subscribe(Pizza.Events.ADD_TOPPING, this);
		pizza.subscribe(Pizza.Events.REMOVE_TOPPING, this);
	}
	
	public Pizza getPizza() {
		return pizza;
	}
	
	public void setUser(String user) {
		this.user = user;
	}
	
	public String getUser() {
		return user;
	}
	
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	
	public BigDecimal getPrice() {
		return price;
	}
	
	@Override
	public void eventOccurred(Object key, IPublisher publisher, Object hint) {
		// The pizza changed!
		PizzaPriceController controller = new PizzaPriceController();
		price = controller.calcPrice(pizza);
	}
}
