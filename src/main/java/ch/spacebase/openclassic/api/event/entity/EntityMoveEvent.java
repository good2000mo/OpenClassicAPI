package ch.spacebase.openclassic.api.event.entity;

import ch.spacebase.openclassic.api.Position;
import ch.spacebase.openclassic.api.entity.BlockEntity;
import ch.spacebase.openclassic.api.event.Cancellable;

/**
 * Called when an entity moves.
 */
public class EntityMoveEvent extends EntityEvent implements Cancellable {
    
	private boolean cancelled = false;
	private Position from;
	private Position to;
    
    public EntityMoveEvent(BlockEntity entity, Position from, Position to) {
    	super(EventType.ENTITY_MOVE, entity);
    	this.from = from;
    	this.to = to;
    }
    
    /**
     * Gets the location this move is from.
     * @return The location the move is from.
     */
    public Position getFrom() {
    	return this.from;
    }
    
    /**
     * Sets the location this move is from.
     * @param from The location the move is from.
     */
    public void setFrom(Position from) {
    	this.from = from;
    }
    
    /**
     * Gets the location this move is to.
     * @return The location the move is to.
     */
    public Position getTo() {
    	return this.to;
    }
    
    /**
     * Sets the location this move is to.
     * @param to The location the move is to.
     */
    public void setTo(Position to) {
    	this.to = to;
    }
    
	@Override
	public boolean isCancelled() {
		return this.cancelled;
	}

	@Override
	public void setCancelled(boolean cancel) {
		this.cancelled = cancel;
	}
	
}
