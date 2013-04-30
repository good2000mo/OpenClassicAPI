package ch.spacebase.openclassic.api.event.player;

import ch.spacebase.openclassic.api.Position;
import ch.spacebase.openclassic.api.event.Cancellable;
import ch.spacebase.openclassic.api.player.Player;

/**
 * Called when a player respawns with the Load Location key.
 */
public class PlayerRespawnEvent extends PlayerEvent implements Cancellable {
    
	private boolean cancelled = false;
	private Position pos;
	
    public PlayerRespawnEvent(Player player, Position pos) {
    	super(EventType.PLAYER_RESPAWN, player);
    	this.pos = pos;
    }
    
    /**
     * Gets the location the player is respawning at.
     * @return The respawn location.
     */
    public Position getPosition() {
    	return this.pos;
    }
    
    /**
     * Sets the location the player is respawning at.
     * @param pos The new respawn location.
     */
    public void setPosition(Position pos) {
    	this.pos = pos;
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
