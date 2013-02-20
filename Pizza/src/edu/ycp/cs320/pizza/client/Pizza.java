package edu.ycp.cs320.pizza.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootLayoutPanel;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class Pizza implements EntryPoint {
	/**
	 * This is the entry point method.
	 */
	public void onModuleLoad() {
		FlowPanel panel = new FlowPanel();
		
		Label helloWorld = new Label("Hello, world!");
		
		panel.add(helloWorld);
		
		RootLayoutPanel.get().add(panel);
		RootLayoutPanel.get().setWidgetTopBottom(panel, 10.0, Unit.PX, 10.0, Unit.PX);
		RootLayoutPanel.get().setWidgetLeftRight(panel, 10.0, Unit.PX, 10.0, Unit.PX);
	}
}
