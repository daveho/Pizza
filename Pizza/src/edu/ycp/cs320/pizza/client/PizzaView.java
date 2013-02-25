package edu.ycp.cs320.pizza.client;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.LayoutPanel;
import com.google.gwt.user.client.ui.InlineLabel;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.user.client.ui.ListBox;

import edu.ycp.cs320.pizza.shared.Pizza;
import edu.ycp.cs320.pizza.shared.Size;
import edu.ycp.cs320.pizza.shared.Topping;

import com.google.gwt.user.client.ui.Button;

public class PizzaView extends Composite {
	private Pizza model;
	private InlineLabel sizeLabel;
	private InlineLabel toppingsLabel;
	private ListBox sizeComboBox;
	private ListBox availToppingsList;
	private ListBox selectedToppingsList;
	
	public PizzaView() {
		
		LayoutPanel layoutPanel = new LayoutPanel();
		initWidget(layoutPanel);
		
		sizeLabel = new InlineLabel("Size:");
		layoutPanel.add(sizeLabel);
		layoutPanel.setWidgetLeftWidth(sizeLabel, 18.0, Unit.PX, 90.0, Unit.PX);
		layoutPanel.setWidgetTopHeight(sizeLabel, 49.0, Unit.PX, 18.0, Unit.PX);
		
		toppingsLabel = new InlineLabel("Toppings:");
		layoutPanel.add(toppingsLabel);
		layoutPanel.setWidgetLeftWidth(toppingsLabel, 18.0, Unit.PX, 90.0, Unit.PX);
		layoutPanel.setWidgetTopHeight(toppingsLabel, 96.0, Unit.PX, 18.0, Unit.PX);
		
		sizeComboBox = new ListBox();
		layoutPanel.add(sizeComboBox);
		layoutPanel.setWidgetLeftWidth(sizeComboBox, 139.0, Unit.PX, 173.0, Unit.PX);
		layoutPanel.setWidgetTopHeight(sizeComboBox, 49.0, Unit.PX, 26.0, Unit.PX);
		
		selectedToppingsList = new ListBox();
		layoutPanel.add(selectedToppingsList);
		layoutPanel.setWidgetLeftWidth(selectedToppingsList, 139.0, Unit.PX, 61.0, Unit.PX);
		layoutPanel.setWidgetTopHeight(selectedToppingsList, 120.0, Unit.PX, 85.0, Unit.PX);
		selectedToppingsList.setVisibleItemCount(5);
		
		availToppingsList = new ListBox();
		layoutPanel.add(availToppingsList);
		layoutPanel.setWidgetLeftWidth(availToppingsList, 366.0, Unit.PX, 61.0, Unit.PX);
		layoutPanel.setWidgetTopHeight(availToppingsList, 120.0, Unit.PX, 85.0, Unit.PX);
		availToppingsList.setVisibleItemCount(5);
		
		Button addToppingButton = new Button("New button");
		addToppingButton.setText("<< Add");
		layoutPanel.add(addToppingButton);
		layoutPanel.setWidgetLeftWidth(addToppingButton, 206.0, Unit.PX, 81.0, Unit.PX);
		layoutPanel.setWidgetTopHeight(addToppingButton, 119.0, Unit.PX, 27.0, Unit.PX);
		
		Button removeToppingButton = new Button("New button");
		removeToppingButton.setText("Remove >>");
		layoutPanel.add(removeToppingButton);
		layoutPanel.setWidgetLeftWidth(removeToppingButton, 206.0, Unit.PX, 81.0, Unit.PX);
		layoutPanel.setWidgetTopHeight(removeToppingButton, 165.0, Unit.PX, 27.0, Unit.PX);
	}
	
	public void setModel(Pizza model) {
		this.model = model;
	}
	
	public void update() {
		// If necessary, add possible sizes to combo box
		if (sizeComboBox.getItemCount() == 0) {
			Size[] sizes = Size.values();
			for (Size s : sizes) {
				sizeComboBox.addItem(s.toString());
			}
		}
		
		// Set pizza's current size in the combo box
		Size pizzaSize = model.getSize();
		sizeComboBox.setSelectedIndex(pizzaSize.ordinal());
		
		// Clear list boxes
		selectedToppingsList.clear();
		availToppingsList.clear();
		
		for (Topping topping : Topping.values()) {
			if (model.getToppingList().contains(topping)) {
				selectedToppingsList.addItem(topping.toString());
			} else {
				availToppingsList.addItem(topping.toString());
			}
		}
	}
}
