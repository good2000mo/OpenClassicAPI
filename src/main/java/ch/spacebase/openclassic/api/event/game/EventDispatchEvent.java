package ch.spacebase.openclassic.api.event.game;

import ch.spacebase.openclassic.api.event.Event;
import ch.spacebase.openclassic.api.event.Priority;

public class EventDispatchEvent extends GameEvent {

	private Event event;
	private Priority priority;
	
	public EventDispatchEvent(Event event, Priority priority) {
		super(EventType.EVENT_DISPATCH);
		this.event = event;
		this.priority = priority;
	}
	
	/**
	 * Gets the event being dispatched.
	 * @return The event being dispatched.
	 */
	public Event getEvent() {
		return this.event;
	}
	
	/**
	 * Gets the priority of the event being dispatched.
	 * @return The priority of the event being dispatched.
	 */
	public Priority getPriority() {
		return this.priority;
	}

}
