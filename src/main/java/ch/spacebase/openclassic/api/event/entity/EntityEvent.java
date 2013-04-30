package ch.spacebase.openclassic.api.event.entity;

import ch.spacebase.openclassic.api.entity.BlockEntity;
import ch.spacebase.openclassic.api.event.Event;

/**
 * Represents an event involving an entity.
 */
public abstract class EntityEvent extends Event {

	private BlockEntity entity;
	
	public EntityEvent(EventType type, BlockEntity entity) {
		super(type);
		this.entity = entity;
	}
	
	/**
	 * Gets the entity involved in this event.
	 * @return The involved entity.
	 */
	public BlockEntity getEntity() {
		return this.entity;
	}

}
