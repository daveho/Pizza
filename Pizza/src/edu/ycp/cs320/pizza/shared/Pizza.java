package edu.ycp.cs320.pizza.shared;

import java.util.ArrayList;
import java.util.List;

public class Pizza {
	private Size size;
	private List<Topping> toppingList;
	
	public Pizza() {
		this.size = Size.MEDIUM;
		this.toppingList = new ArrayList<Topping>();
	}
	
	public void setSize(Size size) {
		this.size = size;
	}
	
	public Size getSize() {
		return size;
	}
	
	public void addTopping(Topping topping) {
		if (!toppingList.contains(topping)) {
			toppingList.add(topping);
		}
	}
	
	public void removeTopping(Topping topping) {
		toppingList.remove(topping);
	}
	
	public List<Topping> getToppingList() {
		return toppingList;
	}
}
