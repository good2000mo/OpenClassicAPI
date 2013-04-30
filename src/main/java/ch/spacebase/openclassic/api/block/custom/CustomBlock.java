package ch.spacebase.openclassic.api.block.custom;

import ch.spacebase.openclassic.api.block.BlockType;
import ch.spacebase.openclassic.api.block.StepSound;
import ch.spacebase.openclassic.api.block.VanillaBlock;
import ch.spacebase.openclassic.api.block.model.CubeModel;
import ch.spacebase.openclassic.api.block.model.Model;
import ch.spacebase.openclassic.api.block.physics.BlockPhysics;

/**
 * Represents a custom block.
 */
public class CustomBlock implements BlockType {
	
	private byte id;
	private boolean opaque;
	private boolean selectable;
	private StepSound sound;
	private boolean liquid;
	private Model model;
	
	private BlockPhysics physics;
	private int delay;
	
	private VanillaBlock fallback = VanillaBlock.STONE;
	private boolean solid = true;
	
	public CustomBlock(byte id, StepSound sound) {
		this(id, sound, true);
	}
	
	public CustomBlock(byte id, StepSound sound, Model model) {
		this(id, sound, model, true);
	}
	
	public CustomBlock(byte id, StepSound sound, boolean opaque) {
		this(id, sound, opaque, false);
	}
	
	public CustomBlock(byte id, StepSound sound, Model model, boolean opaque) {
		this(id, sound, model, opaque, false);
	}
	
	public CustomBlock(byte id, StepSound sound, boolean opaque, boolean liquid) {
		this(id, sound, opaque, liquid, true);
	}
	
	public CustomBlock(byte id, StepSound sound, Model model, boolean opaque, boolean liquid) {
		this(id, sound, model, opaque, liquid, true);
	}
	
	public CustomBlock(byte id, StepSound sound, boolean opaque, boolean liquid, boolean selectable) {
		this(id, sound, new CubeModel(TERRAIN, 1), opaque, liquid, selectable);
	}
	
	public CustomBlock(byte id, StepSound sound, Model model, boolean opaque, boolean liquid, boolean selectable) {
		this.id = id;
		this.sound = sound;
		this.model = model;
		this.opaque = opaque;
		this.liquid = liquid;
		this.selectable = selectable;
		
		if(!this.opaque) {
			this.fallback = VanillaBlock.GLASS;
		}
	}
	
	public byte getId() {
		return this.id;
	}
	
	public boolean isOpaque() {
		return this.opaque;
	}
	
	public boolean isSelectable() {
		return this.selectable;
	}
	
	public boolean isLiquid() {
		return this.liquid;
	}
	
	public StepSound getStepSound() {
		return this.sound;
	}
	
	public BlockPhysics getPhysics() {
		return this.physics;
	}
	
	public void setPhysics(BlockPhysics physics) {
		this.physics = physics;
	}
	
	public int getTickDelay() {
		return this.delay;
	}
	
	public void setTickDelay(int delay) {
		this.delay = delay;
	}
	
	public Model getModel() {
		return this.model;
	}
	
	/**
	 * Gets the block to be sent to clients if the client isn't custom.
	 * @return The block's fallback.
	 */
	public VanillaBlock getFallback() {
		return this.fallback;
	}
	
	/**
	 * Sets the block to be sent to clients if the client isn't custom.
	 * @param fallback The block's new fallback.
	 */
	public void setFallback(VanillaBlock fallback) {
		this.fallback = fallback;
	}

	public boolean isSolid() {
		return this.solid;
	}
	
	public void setSolid(boolean solid) {
		this.solid = solid;
	}

	public void setModel(Model model) {
		this.model = model;
	}
	
}
