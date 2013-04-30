package ch.spacebase.openclassic.api.event.level;

import ch.spacebase.openclassic.api.level.Level;

/**
 * Called when a level is loaded.
 */
public class LevelLoadEvent extends LevelEvent {

	public LevelLoadEvent(Level level) {
		super(EventType.LEVEL_LOAD, level);
	}

}
