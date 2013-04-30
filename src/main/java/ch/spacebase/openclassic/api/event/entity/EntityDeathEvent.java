package ch.spacebase.openclassic.api.event.entity;

import ch.spacebase.openclassic.api.entity.BlockEntity;

/**
 * Called when an entity dies.
 */
public class EntityDeathEvent extends EntityEvent {
	
	public EntityDeathEvent(BlockEntity entity) {
		super(EventType.ENTITY_DEATH, entity);
	}
	
}
