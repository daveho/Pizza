package edu.ycp.cs320.pizza.client;

import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.InlineLabel;
import com.google.gwt.user.client.ui.TextBox;

import edu.ycp.cs320.pizza.shared.Order;

public class OrderView extends Composite {
	
	private Order model;
	
	private PizzaView pizzaView;
	private TextBox userTextBox;
	private Button orderButton;

	private TextBox priceTextBox;
	
	public OrderView() {
		FlowPanel panel = new FlowPanel();
		
		pizzaView  = new PizzaView();
		panel.add(pizzaView);
		
		FlowPanel userAndOrderButton = new FlowPanel();
		
		userAndOrderButton.add(new InlineLabel("User: "));
		userTextBox = new TextBox();
		userTextBox.setSize("160px", "32px");
		userAndOrderButton.add(userTextBox);
		
		userAndOrderButton.add(new InlineLabel("Price: "));
		priceTextBox = new TextBox();
		priceTextBox.setSize("100px", "32px");
		priceTextBox.setEnabled(false);
		userAndOrderButton.add(priceTextBox);
		
		orderButton = new Button("Order!");
		orderButton.setSize("100px", "32px");
		userAndOrderButton.add(orderButton);
		
		panel.add(userAndOrderButton);
		
		initWidget(panel);
	}

	public void setModel(Order model) {
		this.model = model;
		pizzaView.setModel(model.getPizza());
	}
}
