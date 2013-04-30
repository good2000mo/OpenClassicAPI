package ch.spacebase.openclassic.api.block;

import ch.spacebase.openclassic.api.block.model.CubeModel;
import ch.spacebase.openclassic.api.block.model.CuboidModel;
import ch.spacebase.openclassic.api.block.model.EmptyModel;
import ch.spacebase.openclassic.api.block.model.Model;
import ch.spacebase.openclassic.api.block.model.PlantModel;
import ch.spacebase.openclassic.api.block.model.LiquidModel;
import ch.spacebase.openclassic.api.block.physics.BlockPhysics;

/**
 * Represents a vanilla block type.
 */
public enum VanillaBlock implements BlockType {

	AIR((byte) 0, StepSound.NONE, new EmptyModel(), false),
	STONE((byte) 1, StepSound.STONE, 1),
	GRASS((byte) 2, StepSound.GRASS, new CubeModel(TERRAIN, new int[] { 2, 0, 3, 3, 3, 3 })),
	DIRT((byte) 3, StepSound.GRAVEL, 2),
	COBBLESTONE((byte) 4, StepSound.STONE, 16),
	WOOD((byte) 5, StepSound.WOOD, 4),
	SAPLING((byte) 6, StepSound.GRASS, new PlantModel(TERRAIN, 15), false),
	BEDROCK((byte) 7, StepSound.STONE, 17),
	WATER((byte) 8, StepSound.NONE, new LiquidModel(TERRAIN, 14), 5, true, true),
	STATIONARY_WATER((byte) 9, StepSound.NONE, new LiquidModel(TERRAIN, 14), true, true),
	LAVA((byte) 10, StepSound.NONE, new LiquidModel(TERRAIN, 30), 20, true, true),
	STATIONARY_LAVA((byte) 11, StepSound.NONE, new LiquidModel(TERRAIN, 30), true, true),
	SAND((byte) 12, StepSound.SAND, 18),
	GRAVEL((byte) 13, StepSound.GRAVEL, 19),
	GOLD_ORE((byte) 14, StepSound.STONE, 32),
	IRON_ORE((byte) 15, StepSound.STONE, 33),
	COAL_ORE((byte) 16, StepSound.STONE, 34),
	LOG((byte) 17, StepSound.WOOD, new CubeModel(TERRAIN, new int[] { 21, 21, 20, 20, 20, 20 })),
	LEAVES((byte) 18, StepSound.GRASS, 22, false),
	SPONGE((byte) 19, StepSound.GRASS, 48),
	GLASS((byte) 20, StepSound.METAL, 49, false),
	RED_CLOTH((byte) 21, StepSound.CLOTH, 64),
	ORANGE_CLOTH((byte) 22, StepSound.CLOTH, 65),
	YELLOW_CLOTH((byte) 23, StepSound.CLOTH, 66),
	LIME_CLOTH((byte) 24, StepSound.CLOTH, 67),
	GREEN_CLOTH((byte) 25, StepSound.CLOTH, 68),
	AQUA_GREEN_CLOTH((byte) 26, StepSound.CLOTH, 69),
	CYAN_CLOTH((byte) 27, StepSound.CLOTH, 70),
	BLUE_CLOTH((byte) 28, StepSound.CLOTH, 71),
	PURPLE_CLOTH((byte) 29, StepSound.CLOTH, 72),
	INDIGO_CLOTH((byte) 30, StepSound.CLOTH, 73),
	VIOLET_CLOTH((byte) 31, StepSound.CLOTH, 74),
	MAGENTA_CLOTH((byte) 32, StepSound.CLOTH, 75),
	PINK_CLOTH((byte) 33, StepSound.CLOTH, 76),
	BLACK_CLOTH((byte) 34, StepSound.CLOTH, 77),
	GRAY_CLOTH((byte) 35, StepSound.CLOTH, 78),
	WHITE_CLOTH((byte) 36, StepSound.CLOTH, 79),
	DANDELION((byte) 37, StepSound.GRASS, false, new PlantModel(TERRAIN, 13)),
	ROSE((byte) 38, StepSound.GRASS, false, new PlantModel(TERRAIN, 12)),
	BROWN_MUSHROOM((byte) 39, StepSound.GRASS, false, new PlantModel(TERRAIN, 29)),
	RED_MUSHROOM((byte) 40, StepSound.GRASS, false, new PlantModel(TERRAIN, 28)),
	GOLD_BLOCK((byte) 41, StepSound.METAL, new CubeModel(TERRAIN, new int[] { 56, 24, 40, 40, 40, 40 })),
	IRON_BLOCK((byte) 42, StepSound.METAL, new CubeModel(TERRAIN, new int[] { 55, 23, 39, 39, 39, 39 })),
	DOUBLE_SLAB((byte) 43, StepSound.STONE, new CubeModel(TERRAIN, new int[] { 6, 6, 5, 5, 5, 5 })),
	SLAB((byte) 44, StepSound.STONE, new CuboidModel(TERRAIN, new int[] { 6, 6, 5, 5, 5, 5 }, 0, 0, 0, 1, 0.5F, 1)),
	BRICK_BLOCK((byte) 45, StepSound.STONE, 7),
	TNT((byte) 46, StepSound.GRASS, new CubeModel(TERRAIN, new int[] { 10, 9, 8, 8, 8, 8 })),
	BOOKSHELF((byte) 47, StepSound.WOOD, new CubeModel(TERRAIN, new int[] { 4, 4, 35, 35, 35, 35 })),
	MOSSY_COBBLESTONE((byte) 48, StepSound.STONE, 36),
	OBSIDIAN((byte) 49, StepSound.STONE, 37);
	
	private byte id;
	private BlockPhysics phys;
	private boolean liquid;
	private int tickDelay;
	private boolean opaque;
	private StepSound sound;
	private Model model;
	
	private VanillaBlock(byte id, StepSound sound, int texture) {
		this(id, sound, texture, 0, true, false);
	}
	
	private VanillaBlock(byte id, StepSound sound, Model model) {
		this(id, sound, model, 0, true, false);
	}
	
	private VanillaBlock(byte id, StepSound sound, int texture, boolean opaque) {
		this(id, sound, texture, 0, opaque, false);
	}
	
	private VanillaBlock(byte id, StepSound sound, Model model, boolean opaque) {
		this(id, sound, model, 0, opaque, false);
	}
	
	private VanillaBlock(byte id, StepSound sound, boolean opaque, Model model) {
		this(id, sound, model, 0, opaque, false);
	}
	
	private VanillaBlock(byte id, StepSound sound, Model model, boolean opaque, boolean liquid) {
		this(id, sound, model, 0, opaque, liquid);
	}
	
	private VanillaBlock(byte id, StepSound sound, int texture, int tickDelay, boolean opaque, boolean liquid) {
		this(id, sound, new CubeModel(TERRAIN, texture), tickDelay, opaque, liquid);
	}
	
	private VanillaBlock(byte id, StepSound sound, Model model, int tickDelay, boolean opaque, boolean liquid) {
		this.id = id;
		this.sound = sound;
		this.model = model;
		this.tickDelay = tickDelay;
		this.liquid = liquid;
		this.opaque = opaque;
		
		if(this.liquid || id == 0) {
			this.model.setCollisionBox(null);
		}
		
		Blocks.register(this);
	}
	
	public byte getId() {
		return this.id;
	}
	
	public BlockPhysics getPhysics() {
		return this.phys;
	}
	
	public void setPhysics(BlockPhysics phys) {
		this.phys = phys;
	}
	
	public boolean isOpaque() {
		return this.opaque;
	}

	public boolean isSelectable() {
		return !this.liquid && this != GRASS && this != AIR && this != BEDROCK && this != DOUBLE_SLAB;
	}

	public StepSound getStepSound() {
		return this.sound;
	}
	
	public boolean isLiquid() {
		return this.liquid;
	}
	
	public int getTickDelay() {
		return this.tickDelay;
	}
	
	public void setTickDelay(int tickDelay) {
		this.tickDelay = tickDelay;
	}

	public Model getModel() {
		return this.model;
	}
	
	public boolean isSolid() {
		return this != AIR && this != SAPLING && this != WATER && this != STATIONARY_WATER && this != LAVA && this != STATIONARY_LAVA && this != LEAVES && this != GLASS && this != DANDELION && this != ROSE && this != BROWN_MUSHROOM && this != RED_MUSHROOM && this != SLAB;
	}
	
}
