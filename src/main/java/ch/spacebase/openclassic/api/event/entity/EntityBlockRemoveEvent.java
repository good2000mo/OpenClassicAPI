package ch.spacebase.openclassic.api.event.entity;

import ch.spacebase.openclassic.api.block.Block;
import ch.spacebase.openclassic.api.entity.BlockEntity;
import ch.spacebase.openclassic.api.entity.BlockEntity.BlockRemoveCause;
import ch.spacebase.openclassic.api.event.Cancellable;

/**
 * Called when an entity's block is removed.
 */
public class EntityBlockRemoveEvent extends EntityEvent implements Cancellable {
	
	private boolean cancelled = false;
	private BlockRemoveCause cause;
	private Block block;
	
	public EntityBlockRemoveEvent(BlockEntity entity, BlockRemoveCause cause, Block block) {
		super(EventType.ENTITY_BLOCK_REMOVE, entity);
		this.cause = cause;
		this.block = block;
	}
	
	/**
	 * Gets the cause of the block being removed.
	 * @return The cause of the removal.
	 */
	public BlockRemoveCause getCause() {
		return this.cause;
	}
	
	/**
	 * Gets the block being removed.
	 * @return The block being removed.
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
