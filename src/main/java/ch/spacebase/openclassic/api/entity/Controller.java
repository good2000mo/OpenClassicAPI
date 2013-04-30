package ch.spacebase.openclassic.api.entity;

import ch.spacebase.openclassic.api.block.Block;
import ch.spacebase.openclassic.api.block.BlockType;
import ch.spacebase.openclassic.api.entity.BlockEntity.BlockRemoveCause;

/**
 * A Controller that handles actions on a BlockEntity.
 */
public abstract class Controller {

	private BlockEntity parent;
	
	public BlockEntity getParent() {
		return this.parent;
	}
	
	protected final void attach(BlockEntity entity) {
		if(entity == null) return;
		this.parent = entity;
		this.onAttached(this.parent);
	}
	
	protected final void detach() {
		if(this.parent == null) return;
		BlockEntity entity = this.parent;
		
		this.parent = null;
		this.onDetached(entity);
	}
	
	/**
	 * Called when the controller is attached to an entity.
	 * @param entity Entity being attached to.
	 */
	public abstract void onAttached(BlockEntity entity);
	
	/**
	 * Called when the controller is detached from an entity.
	 * @param entity Entity being detached from.
	 */
	public abstract void onDetached(BlockEntity entity);
	
	/**
	 * Called when the entity's block is removed.
	 * @param cause Cause of the block's removal.
	 * @param block Block that was removed.
	 * @return Whether to allow the removal.
	 */
	public boolean onBlockRemoval(BlockRemoveCause cause, Block block) {
		if(cause == BlockRemoveCause.PLAYER) {
			return false;
		}
		
		return true;
	}
	
	/**
	 * Called when the entity's block is set at a location.
	 * @param block Block being set.
	 * @return Whether to allow the set.
	 */
	public boolean onBlockSet(Block block) {	
		return true;
	}
	
	/**
	 * Called when the server ticks.
	 */
	public abstract void tick();
	
	/**
	 * Gets the entity's BlockType.
	 * @return The entity's BlockType.
	 */
	public abstract BlockType getBlock();
	
	/**
	 * Called when the entity dies.
	 */
	public abstract void onDeath();
	
}
