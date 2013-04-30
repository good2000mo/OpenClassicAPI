package ch.spacebase.openclassic.api.event.block;

import ch.spacebase.openclassic.api.block.Block;
import ch.spacebase.openclassic.api.event.Cancellable;

/**
 * Called when physics is updated for a block.
 */
public class BlockPhysicsEvent extends BlockEvent implements Cancellable {

	private boolean cancelled = false;
	
	public BlockPhysicsEvent(Block block) {
		super(EventType.BLOCK_PHYSICS, block);
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
