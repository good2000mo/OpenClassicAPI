package ch.spacebase.openclassic.api.event.level;

import ch.spacebase.openclassic.api.event.Cancellable;
import ch.spacebase.openclassic.api.level.Level;

/**
 * Called when a level is saved.
 */
public class LevelSaveEvent extends LevelEvent implements Cancellable {

	private boolean cancelled = false;
	
	public LevelSaveEvent(Level level) {
		super(EventType.LEVEL_SAVE, level);
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
