package ch.spacebase.openclassic.api.event.player;

import ch.spacebase.openclassic.api.event.Event;
import ch.spacebase.openclassic.api.player.Player;

/**
 * Represents an event involving a player.
 */
public abstract class PlayerEvent extends Event {

	private Player player;
	
	public PlayerEvent(EventType type, Player player) {
		super(type);
		this.player = player;
	}
	
	/**
	 * Gets the player involved in this event.
	 * @return The player involved.
	 */
	public Player getPlayer() {
		return this.player;
	}
	
}
