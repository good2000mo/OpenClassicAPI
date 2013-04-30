package ch.spacebase.openclassic.api.block;

import ch.spacebase.openclassic.api.block.model.Model;
import ch.spacebase.openclassic.api.block.model.Texture;
import ch.spacebase.openclassic.api.block.physics.BlockPhysics;

/**
 * Represents a block type.
 */
public interface BlockType {
	
	public static final Texture TERRAIN = new Texture("/terrain.png", true, 256, 256, 16);
	
	/**
	 * Gets the block's id.
	 * @return The block's id.
	 */
	public byte getId();
	
	/**
	 * Returns true if the block is opaque.
	 * @return True if the block is opaque.
	 */
	public boolean isOpaque();
	
	/**
	 * Returns true if the block is selectable in the block menu.
	 * @return True if the block is selectable.
	 */
	public boolean isSelectable();
	
	/**
	 * Gets the block's step sound.
	 * @return The block's step sound.
	 */
	public StepSound getStepSound();
	
	/**
	 * Gets the block's physics if applicable.
	 * @return The block's physics.
	 */
	public BlockPhysics getPhysics();
	
	/**
	 * Sets the block's physics.
	 * @param phys The physics to set.
	 */
	public void setPhysics(BlockPhysics phys);
	
	/**
	 * Gets whether the block is a liquid.
	 * @return True if the block is a liquid.
	 */
	public boolean isLiquid();
	
	/**
	 * Gets the tick delay of this block.
	 * @return The block's tick delay.
	 */
	public int getTickDelay();
	
	/**
	 * Sets the tick delay of this block.
	 * @return tickDelay The block's new tick delay.
	 */
	public void setTickDelay(int tickDelay);
	
	/**
	 * Gets the model of this block.
	 * @return The block's model.
	 */
	public Model getModel();
	
	/**
	 * Returns true if the block is fully solid.
	 * @return True if the block is solid.
	 */
	public boolean isSolid();
	
}
