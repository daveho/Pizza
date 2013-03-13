package edu.ycp.cs320.pizza.client;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.LayoutPanel;
import com.google.gwt.user.client.ui.InlineLabel;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.user.client.ui.ListBox;

import edu.ycp.cs320.pizza.shared.IPublisher;
import edu.ycp.cs320.pizza.shared.ISubscriber;
import edu.ycp.cs320.pizza.shared.Pizza;
import edu.ycp.cs320.pizza.shared.Size;
import edu.ycp.cs320.pizza.shared.Topping;

import com.google.gwt.user.client.ui.Button;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.ChangeEvent;

public class PizzaView extends Composite implements ISubscriber {
	private Pizza model;
	private InlineLabel sizeLabel;
	private InlineLabel toppingsLabel;
	private ListBox sizeComboBox;
	private ListBox selectedToppingsList;
	private ListBox availToppingsList;
	private Button addToppingButton;
	
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
		sizeComboBox.addChangeHandler(new ChangeHandler() {
			public void onChange(ChangeEvent event) {
				handleSizeChange();
			}
		});
		layoutPanel.add(sizeComboBox);
		layoutPanel.setWidgetLeftWidth(sizeComboBox, 114.0, Unit.PX, 173.0, Unit.PX);
		layoutPanel.setWidgetTopHeight(sizeComboBox, 49.0, Unit.PX, 26.0, Unit.PX);
		
		selectedToppingsList = new ListBox();
		layoutPanel.add(selectedToppingsList);
		layoutPanel.setWidgetLeftWidth(selectedToppingsList, 114.0, Unit.PX, 102.0, Unit.PX);
		layoutPanel.setWidgetTopHeight(selectedToppingsList, 96.0, Unit.PX, 177.0, Unit.PX);
		selectedToppingsList.setVisibleItemCount(5);
		
		availToppingsList = new ListBox();
		layoutPanel.add(availToppingsList);
		layoutPanel.setWidgetLeftWidth(availToppingsList, 325.0, Unit.PX, 110.0, Unit.PX);
		layoutPanel.setWidgetTopHeight(availToppingsList, 96.0, Unit.PX, 177.0, Unit.PX);
		availToppingsList.setVisibleItemCount(5);
		
		addToppingButton = new Button("New button");
		addToppingButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				handleAddTopping();
			}
		});
		addToppingButton.setText("<< Add");
		layoutPanel.add(addToppingButton);
		layoutPanel.setWidgetLeftWidth(addToppingButton, 222.0, Unit.PX, 97.0, Unit.PX);
		layoutPanel.setWidgetTopHeight(addToppingButton, 117.0, Unit.PX, 30.0, Unit.PX);
		
		Button removeToppingButton = new Button("New button");
		removeToppingButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				handleRemoveTopping();
			}
		});
		removeToppingButton.setText("Remove >>");
		layoutPanel.add(removeToppingButton);
		layoutPanel.setWidgetLeftWidth(removeToppingButton, 222.0, Unit.PX, 97.0, Unit.PX);
		layoutPanel.setWidgetTopHeight(removeToppingButton, 188.0, Unit.PX, 30.0, Unit.PX);
	}

	protected void handleSizeChange() {
		int selIndex = sizeComboBox.getSelectedIndex();
		if (selIndex >= 0) {
			Size size = Size.valueOf(sizeComboBox.getItemText(selIndex));
			model.setSize(size);
		}
	}

	protected void handleAddTopping() {
		int index = availToppingsList.getSelectedIndex();
		if (index >= 0) {
			String s = availToppingsList.getItemText(index);
			Topping t = Topping.valueOf(s);
			model.addTopping(t);
		}
	}
	
	protected void handleRemoveTopping() {
		int index = selectedToppingsList.getSelectedIndex();
		if (index >= 0) {
			String s = selectedToppingsList.getItemText(index);
			Topping t = Topping.valueOf(s);
			model.removeTopping(t);
		}
	}

	public void setModel(Pizza model) {
		this.model = model;
		this.model.subscribe(Pizza.Events.CHANGE_SIZE, this);
		this.model.subscribe(Pizza.Events.ADD_TOPPING, this);
		this.model.subscribe(Pizza.Events.REMOVE_TOPPING, this);
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
		
		// Clear toppings list boxes
		selectedToppingsList.clear();
		availToppingsList.clear();
		
		for (Topping t : Topping.values()) {
			if (model.getToppingList().contains(t)) {
				selectedToppingsList.addItem(t.toString());
			} else {
				availToppingsList.addItem(t.toString());
			}
		}
	}
	
	@Override
	public void eventOccurred(Object key, IPublisher publisher, Object hint) {
		update();
	}
}
