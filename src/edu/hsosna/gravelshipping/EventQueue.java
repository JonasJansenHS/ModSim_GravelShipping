package edu.hsosna.gravelshipping;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class EventQueue extends ArrayList<Event> {

	private EventQueue() {
	}

	private static class SingletonHelper {
		private static final EventQueue INSTANCE = new EventQueue();
	}

	public static EventQueue getInstance() {
		return SingletonHelper.INSTANCE;
	}

	public ArrayList<Event> peak(Integer timestep, EventType eventType, SimObject attachedObject, SimObject receiverObject,
			Class<? extends SimObject> receiverClass) {
		
		ArrayList<Event> matchingEvents = new ArrayList<Event>();
		
		for (Event ev : this) {
			if((timestep == null || timestep >= ev.getTimestep()) 
					&& (eventType == null || eventType.equals(ev.getEventType()))
					&& (attachedObject == null || attachedObject.equals(ev.getAttachedObject()))
					&& (receiverObject == null || receiverObject.equals(ev.getReceiverObject()))
					&& (receiverClass == null || receiverClass.equals(ev.getReceiverClass()))) 
			{
				matchingEvents.add(ev);
			}
		}
		
		if(matchingEvents.isEmpty())
			return null;
		
		return matchingEvents;
	}
	
	public Event peakFutureEvents(int timestep){
		for(Event ev: this) {
			if(ev.getTimestep() > timestep) {
				return ev;
			}
		}
		return null;
	}

	public void addEvent(Event event) {
		SingletonHelper.INSTANCE.add(event);
		Collections.sort(this);
	}
}