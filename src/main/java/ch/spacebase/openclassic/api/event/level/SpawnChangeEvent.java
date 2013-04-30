package ch.spacebase.openclassic.api.event.level;

import ch.spacebase.openclassic.api.Position;
import ch.spacebase.openclassic.api.level.Level;

/**
 * Called when a level's spawn is changed.
 */
public class SpawnChangeEvent extends LevelEvent {

	private Position old;
	
	public SpawnChangeEvent(Level level, Position old) {
		super(EventType.SPAWN_CHANGE, level);
		this.old = old;
	}
	
	/**
	 * Gets the level's old spawn.
	 * @return The level's old spawn.
	 */
	public Position getOldSpawn() {
		return this.old;
	}

}
