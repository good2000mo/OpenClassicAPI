package ch.spacebase.openclassic.api.event.player;

import ch.spacebase.openclassic.api.player.Player;

/**
 * Called when a player leaves the server.
 */
public class PlayerQuitEvent extends PlayerEvent {

    private String message;
    
    public PlayerQuitEvent(Player player, String message) {
    	super(EventType.PLAYER_QUIT, player);
    	this.message = message;
    }
    
    /**
     * Gets the logout message.
     * @return The logout message.
     */
    public String getMessage() {
    	return this.message;
    }
    
    /**
     * Sets the logout message.
     * @param message The new logout message.
     */
    public void setMessage(String message) {
    	this.message = message;
    }
	
}
