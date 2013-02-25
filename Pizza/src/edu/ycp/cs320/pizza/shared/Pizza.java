package edu.ycp.cs320.pizza.shared;

import java.util.ArrayList;
import java.util.List;

public class Pizza extends Publisher {
	public enum Events {
		ADD_TOPPING,
		REMOVE_TOPPING,
		CHANGE_SIZE,
	}
	
	private Size size;
	private List<Topping> toppingList;
	
	public Pizza() {
		this.size = Size.MEDIUM;
		this.toppingList = new ArrayList<Topping>();
	}
	
	public void setSize(Size size) {
		this.size = size;
		notifySubscribers(Events.CHANGE_SIZE, size);
	}
	
	public Size getSize() {
		return size;
	}
	
	public void addTopping(Topping topping) {
		if (!toppingList.contains(topping)) {
			toppingList.add(topping);
			notifySubscribers(Events.ADD_TOPPING, topping);
		}
	}
	
	public void removeTopping(Topping topping) {
		if (toppingList.remove(topping)) {
			notifySubscribers(Events.REMOVE_TOPPING, topping);
		}
	}
	
	public List<Topping> getToppingList() {
		return toppingList;
	}
}
