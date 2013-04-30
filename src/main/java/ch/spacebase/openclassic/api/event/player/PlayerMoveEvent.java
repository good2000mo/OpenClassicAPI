package ch.spacebase.openclassic.api.event.player;

import ch.spacebase.openclassic.api.Position;
import ch.spacebase.openclassic.api.event.Cancellable;
import ch.spacebase.openclassic.api.player.Player;

/**
 * Called when a player moves.
 */
public class PlayerMoveEvent extends PlayerEvent implements Cancellable {
    
	private boolean cancelled = false;
	private Position from;
	private Position to;
	
    public PlayerMoveEvent(Player player, Position from, Position to) {
    	this(EventType.PLAYER_MOVE, player, from, to);
    }
    
    public PlayerMoveEvent(EventType type, Player player, Position from, Position to) {
    	super(type, player);
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
