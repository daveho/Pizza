package edu.ycp.cs320.pizza.shared;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Default implementation of {@link IPublisher} interface.
 */
public abstract class Publisher implements IPublisher {
	private static class Registration {
		Object key;
		ISubscriber subscriber;
		
		Registration(Object key, ISubscriber subscriber) {
			this.key = key;
			this.subscriber = subscriber;
		}
	}
	
	private List<Registration> registrationList;
	
	/**
	 * Constructor.
	 */
	protected Publisher() {
		registrationList = new ArrayList<Registration>();
	}
	
	@Override
	public void subscribe(Object key, ISubscriber subscriber) {
		registrationList.add(new Registration(key, subscriber));
	}
	
	@Override
	public void subscribeToAll(Object[] keyList, ISubscriber subscriber) {
		for (Object key : keyList) {
			registrationList.add(new Registration(key, subscriber));
		}
	}
	
	@Override
	public void unsubscribe(Object key, ISubscriber subscriber) {
		for (Iterator<Registration> i = registrationList.iterator(); i.hasNext(); ) {
			Registration reg = i.next();
			if (reg.key.equals(key) && reg.subscriber == subscriber) {
				i.remove();
				return;
			}
		}
	}
	
	@Override
	public void unsubscribeFromAll(ISubscriber subscriber) {
		for (Iterator<Registration> i = registrationList.iterator(); i.hasNext(); ) {
			Registration reg = i.next();
			if (reg.subscriber == subscriber) {
				i.remove();
			}
		}
	}
	
	@Override
	public void notifySubscribers(Object key, Object hint) {
		// protect against concurrent modification exceptions
		ArrayList<Registration> registrationListCopy = new ArrayList<Registration>(registrationList);
		
		// notify all subscribers subscribed for events with this key
		for (Registration reg : registrationListCopy) {
			if (reg.key.equals(key)) {
				reg.subscriber.eventOccurred(key, this, hint);
			}
		}
	}
}
