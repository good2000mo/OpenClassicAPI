package ch.spacebase.openclassic.api.event.level;

import ch.spacebase.openclassic.api.event.Event;
import ch.spacebase.openclassic.api.level.Level;

/**
 * Represents an event involving a level.
 */
public abstract class LevelEvent extends Event {

	private Level level;
	
	public LevelEvent(EventType type, Level level) {
		super(type);
		this.level = level;
	}
	
	/**
	 * Gets the level involved in this event.
	 * @return The level involved.
	 */
	public Level getLevel() {
		return this.level;
	}
	
}
