package ch.spacebase.openclassic.api.event.player;

import ch.spacebase.openclassic.api.player.Player;

/**
 * Called when the state of a key is changed on the client. (custom client only)
 */
public class PlayerKeyChangeEvent extends PlayerEvent {

	private int key;
	private boolean pressed;
	
	public PlayerKeyChangeEvent(Player player, int key, boolean pressed) {
		super(EventType.PLAYER_KEY_CHANGE, player);
		this.key = key;
		this.pressed = pressed;
	}
	
	/**
	 * Gets the key involved in the event.
	 * @return The key involved in the event.
	 */
	public int getKey() {
		return this.key;
	}
	
	/**
	 * Returns true if the key is being pressed.
	 * @return True if the key is being pressed.
	 */
	public boolean isPressed() {
		return this.pressed;
	}

}
