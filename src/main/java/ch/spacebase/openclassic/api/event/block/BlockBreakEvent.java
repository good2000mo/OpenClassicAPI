package ch.spacebase.openclassic.api.event.block;

import ch.spacebase.openclassic.api.block.Block;
import ch.spacebase.openclassic.api.block.BlockType;
import ch.spacebase.openclassic.api.event.Cancellable;
import ch.spacebase.openclassic.api.player.Player;

/**
 * Called when a block is broken.
 */
public class BlockBreakEvent extends BlockEvent implements Cancellable {

	private BlockType held;
	private Player player;
	private boolean cancelled = false;
	
	public BlockBreakEvent(Block block, Player player, BlockType held) {
		super(EventType.BLOCK_BREAK, block);
		this.player = player;
		this.held = held;
	}
	
	/**
	 * Gets the player breaking the block.
	 * @return The player breaking the block.
	 */
	public Player getPlayer() {
		return this.player;
	}
	
	/**
	 * Gets the block held by the player breaking the block.
	 * @return The block held by the player.
	 */
	public BlockType getHeldBlock() {
		return this.held;
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
