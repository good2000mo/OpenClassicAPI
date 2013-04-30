package ch.spacebase.openclassic.api.event.block;

import ch.spacebase.openclassic.api.block.BlockType;
import ch.spacebase.openclassic.api.event.Event;

/**
 * Called when a block is unregistered.
 */
public class BlockUnregisterEvent extends Event {

	private BlockType type;
	
	public BlockUnregisterEvent(BlockType type) {
		super(EventType.BLOCK_UNREGISTER);
		this.type = type;
	}
	
	/**
	 * Gets the unregistered BlockType.
	 * @return The unregistered BlockType.
	 */
	public BlockType getBlock() {
		return this.type;
	}

}
