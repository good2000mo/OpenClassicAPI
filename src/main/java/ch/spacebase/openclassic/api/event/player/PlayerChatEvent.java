package ch.spacebase.openclassic.api.event.player;

import ch.spacebase.openclassic.api.Color;
import ch.spacebase.openclassic.api.event.Cancellable;
import ch.spacebase.openclassic.api.player.Player;

/**
 * Called when a player chats.
 */
public class PlayerChatEvent extends PlayerEvent implements Cancellable {

	private boolean cancelled = false;
    private String message;
    private String format = "%1$s" + Color.WHITE + ": %2$s";
    
    public PlayerChatEvent(Player player, String message) {
    	this(EventType.PLAYER_CHAT, player, message);
    }
    
    public PlayerChatEvent(EventType type, Player player, String message) {
    	super(type, player);
    	this.message = message;
    }
    
    /**
     * Gets the message being sent.
     * @return The sent message.
     */
    public String getMessage() {
    	return this.message;
    }
    
    /**
     * Sets the message being sent.
     * @param message Message to send.
     */
    public void setMessage(String message) {
    	this.message = message;
    }
    
    /**
     * Gets the format of the chat output.
     * @return The chat format.
     */
    public String getFormat() {
    	return this.format;
    }
    
    /**
     * Sets the format of the chat output.
     * @param format The new chat format.
     */
    public void setFormat(String format) {
    	this.format = format;
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
