package ch.spacebase.openclassic.api.event.block;

import ch.spacebase.openclassic.api.block.Block;
import ch.spacebase.openclassic.api.event.Event;

/**
 * Represents an event involving a block.
 */
public abstract class BlockEvent extends Event {

	private Block block;
	
	public BlockEvent(EventType type, Block block) {
		super(type);
		this.block = block;
	}
	
	/**
	 * Gets the block involved in this event.
	 * @return The block involved.
	 */
	public Block getBlock() {
		return this.block;
	}
	
}
