package ch.spacebase.openclassic.api.event.entity;

import ch.spacebase.openclassic.api.block.Block;
import ch.spacebase.openclassic.api.entity.BlockEntity;
import ch.spacebase.openclassic.api.event.Cancellable;

/**
 * Called when an entity's block is set at a location.
 */
public class EntityBlockSetEvent extends EntityEvent implements Cancellable {

	private boolean cancelled = false;
	private Block block;
	
	public EntityBlockSetEvent(BlockEntity entity, Block block) {
		super(EventType.ENTITY_BLOCK_SET, entity);
		this.block = block;
	}
	
	/**
	 * Gets the block being set.
	 * @return The block being set.
	 */
	public Block getBlock() {
		return this.block;
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
