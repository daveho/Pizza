package edu.ycp.cs320.pizza.shared;

import java.math.BigDecimal;

public class PizzaPriceController {
	public BigDecimal calcPrice(Pizza pizza) {
		BigDecimal total = new BigDecimal(0);
		
		switch (pizza.getSize()) {
		case SMALL:
			total = total.add(new BigDecimal(5));
			break;
		case MEDIUM:
			total = total.add(new BigDecimal(6));
			break;
		case LARGE:
			total = total.add(new BigDecimal(7));
			break;
		}
		
		for (Topping t : pizza.getToppingList()) {
			total = total.add(new BigDecimal("0.50"));
		}
		
		return total;
	}
}
