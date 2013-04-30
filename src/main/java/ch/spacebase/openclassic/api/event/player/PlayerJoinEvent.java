package ch.spacebase.openclassic.api.event.player;

import ch.spacebase.openclassic.api.player.Player;

/**
 * Called when a player joins the server.
 */
public class PlayerJoinEvent extends PlayerEvent {

    private String message;
    
    public PlayerJoinEvent(Player player, String message) {
    	super(EventType.PLAYER_JOIN, player);
    	this.message = message;
    }
    
    /**
     * Gets the login message.
     * @return The login message.
     */
    public String getMessage() {
    	return this.message;
    }
    
    /**
     * Sets the login message.
     * @param message The new login message.
     */
    public void setMessage(String message) {
    	this.message = message;
    }
	
}
