package edu.ycp.cs320.pizza.client;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.InlineLabel;
import com.google.gwt.user.client.ui.TextBox;

import edu.ycp.cs320.pizza.shared.IPublisher;
import edu.ycp.cs320.pizza.shared.ISubscriber;
import edu.ycp.cs320.pizza.shared.Order;

public class OrderView extends Composite implements ISubscriber {
	
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
		orderButton.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				handleOrder();
			}
		});
		
		panel.add(userAndOrderButton);
		
		initWidget(panel);
	}

	protected void handleOrder() {
		RPC.orderService.placeOrder(model.getPizza(), "XYZ", "123", new AsyncCallback<Boolean>() {
			@Override
			public void onSuccess(Boolean result) {
				GWT.log("Successfully placed order!");
				// FIXME: should display result in UI
			}
			
			@Override
			public void onFailure(Throwable caught) {
				GWT.log("RPC call failed", caught);
				// FIXME: should display error message in UI
			}
		});
	}

	public void setModel(Order model) {
		this.model = model;
		pizzaView.setModel(model.getPizza());
		model.subscribe(Order.Events.PRICE_CHANGED, this);
	}
	
	@Override
	public void eventOccurred(Object key, IPublisher publisher, Object hint) {
		update();
	}
	
	public void update() {
		priceTextBox.setText(model.getPrice().toString());
		pizzaView.update();
	}
}
