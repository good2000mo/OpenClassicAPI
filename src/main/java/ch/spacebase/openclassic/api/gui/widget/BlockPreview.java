package ch.spacebase.openclassic.api.gui.widget;

import ch.spacebase.openclassic.api.block.BlockType;
import ch.spacebase.openclassic.api.gui.Screen;
import ch.spacebase.openclassic.api.render.RenderHelper;

/**
 * A preview of a block, like in the inventory quickbar.
 */
public class BlockPreview extends Widget {

	private BlockType type;
	private float scale;
	
	public BlockPreview(int id, int x, int y, Screen parent, BlockType type) {
		this(id, x, y, parent, type, 0);
	}
	
	public BlockPreview(int id, int x, int y, Screen parent, BlockType type, float scale) {
		super(id, x, y, 0, 0, parent);
		this.type = type;
		this.scale = scale;
	}
	
	/**
	 * Gets the block being previewed.
	 * @return The block being previewed.
	 */
	public BlockType getBlock() {
		return this.type;
	}
	
	/**
	 * Sets the block being previewed.
	 * @param block The new block being previewed.
	 */
	public void setBlock(BlockType block) {
		this.type = block;
	}

	@Override
	public void render() {
		RenderHelper.getHelper().drawRotatedBlock(x, y, this.type, this.scale);
	}

}
