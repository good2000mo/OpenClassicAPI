package ch.spacebase.openclassic.api.event.level;

import ch.spacebase.openclassic.api.event.Cancellable;
import ch.spacebase.openclassic.api.level.Level;

/**
 * Called when a level is unloaded.
 */
public class LevelUnloadEvent extends LevelEvent implements Cancellable {

	private boolean cancelled = false;
	
	public LevelUnloadEvent(Level level) {
		super(EventType.LEVEL_UNLOAD, level);
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
