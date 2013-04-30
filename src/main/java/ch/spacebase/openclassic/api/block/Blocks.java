package ch.spacebase.openclassic.api.block;

import java.util.Arrays;
import java.util.List;

import ch.spacebase.openclassic.api.event.EventFactory;
import ch.spacebase.openclassic.api.event.block.BlockRegisterEvent;
import ch.spacebase.openclassic.api.event.block.BlockUnregisterEvent;

/**
 * The block registry.
 */
public class Blocks {
	
	private static final BlockType registry[] = new BlockType[256];
	
	static {
		// make sure enum is loaded.
		@SuppressWarnings("unused")
		VanillaBlock v = VanillaBlock.STONE;
	}
	
	/**
	 * Gets the block with the given ID.
	 * @param id ID of the block.
	 * @return The block with the given ID.
	 */
	public static BlockType fromId(int id) {
		return registry[id];
	}
	
	/**
	 * Registers a block.
	 * @param block Block to register.
	 */
	public static void register(BlockType block) {
		EventFactory.callEvent(new BlockRegisterEvent(block));
		registry[block.getId()] = block;
	}
	
	/**
	 * Unregisters a block.
	 * @param id ID of the Block to unregister.
	 */
	public static void unregister(int id) {
		EventFactory.callEvent(new BlockUnregisterEvent(fromId(id)));
		registry[id] = null;
	}
	
	/**
	 * Gets a list of all the blocks.
	 * @return A list of blocks.
	 */
	public static List<BlockType> getBlocks() {
		return Arrays.asList(registry);
	}
	
}
