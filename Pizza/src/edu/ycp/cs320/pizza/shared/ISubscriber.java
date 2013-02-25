package edu.ycp.cs320.pizza.shared;

/**
 * Interface implemented by classes that are event subscribers.
 */
public interface ISubscriber {
	/**
	 * Called by a Publisher when an event occurs.
	 * 
	 * @param key        key indicating the type of the event
	 * @param publisher  the publisher
	 * @param hint       additional information about the event
	 */
	public void eventOccurred(Object key, IPublisher publisher, Object hint);
}
