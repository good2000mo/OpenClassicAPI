package ch.spacebase.openclassic.api.event.level;

import ch.spacebase.openclassic.api.level.Level;

/**
 * Called when a level is created.
 */
public class LevelCreateEvent extends LevelEvent {

	public LevelCreateEvent(Level level) {
		super(EventType.LEVEL_CREATE, level);
	}

}
