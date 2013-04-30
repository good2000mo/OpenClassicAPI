package ch.spacebase.openclassic.api.event.block;

import ch.spacebase.openclassic.api.block.BlockType;
import ch.spacebase.openclassic.api.event.Event;

/**
 * Called when a block is registered.
 */
public class BlockRegisterEvent extends Event {

	private BlockType type;
	
	public BlockRegisterEvent(BlockType type) {
		super(EventType.BLOCK_REGISTER);
		this.type = type;
	}
	
	/**
	 * Gets the registered BlockType.
	 * @return The registered BlockType.
	 */
	public BlockType getBlock() {
		return this.type;
	}

}
