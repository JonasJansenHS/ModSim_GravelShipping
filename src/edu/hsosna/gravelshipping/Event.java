package edu.hsosna.gravelshipping;

public class Event implements Comparable<Event>{
	
	@Override
	public String toString() {
		return "Event [timestep= " + timestep + ", eventType= " + eventType + ", attachedObject= " + attachedObject
				+ ", receiverObject=" + receiverObject + ", receiverClass= " + receiverClass + "]";
	}

	private Integer timestep;
	private EventType eventType;
	private SimObject attachedObject;
	private SimObject receiverObject;
	private Class<? extends SimObject> receiverClass;
	
	public Event(Integer timestep, EventType eventType, SimObject attachedObject, SimObject receiverObject,
			Class<? extends SimObject> receiverClass){
		
		this.timestep = timestep;
		this.eventType = eventType;
		this.attachedObject = attachedObject;
		this.receiverObject = receiverObject;
		this.receiverClass = receiverClass;
	}

	public int getTimestep() {
		return timestep;
	}

	public EventType getEventType() {
		return eventType;
	}

	public SimObject getAttachedObject() {
		return attachedObject;
	}

	public SimObject getReceiverObject() {
		return receiverObject;
	}

	public Class<? extends SimObject> getReceiverClass() {
		return receiverClass;
	}

	@Override
	public int compareTo(Event o) {
		return timestep.compareTo(o.timestep);
	}

}
