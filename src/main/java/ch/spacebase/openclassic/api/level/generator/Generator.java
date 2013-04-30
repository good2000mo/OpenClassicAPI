package ch.spacebase.openclassic.api.level.generator;

import java.util.Random;

import ch.spacebase.openclassic.api.Position;
import ch.spacebase.openclassic.api.block.VanillaBlock;
import ch.spacebase.openclassic.api.level.Level;

/**
 * Represents a map generator.
 */
public abstract class Generator {

	protected static Random rand = new Random();
	
	/**
	 * Generates the level.
	 * @param level Level to generate.
	 * @param blocks Generated block IDs.
	 */
	public abstract void generate(Level level, byte blocks[]);
	
	/**
	 * Finds a spawn for the level.
	 * @param level The level to find a spawn for.
	 * @return The spawn found for the level.
	 */
	public Position findSpawn(Level level) {
		int x = level.getWidth() / 2;
		int y = level.getWaterLevel() + 2;
		int z = level.getDepth() / 2;
		
		while(level.getBlockTypeAt(x, y, z) != VanillaBlock.AIR || level.getBlockTypeAt(x, y - 1, z) != VanillaBlock.AIR || level.getBlockTypeAt(x, y - 2, z) == VanillaBlock.AIR) {
			if(y >= level.getHeight() - 1) {
				x = rand.nextInt(level.getWidth());
				y = level.getWaterLevel() + 2;
				z = rand.nextInt(level.getDepth());
				
				continue;
			}
			
			y++;
		}
		
		return new Position(level, x, y + 0.5f, z);	
	}
	
	public static int coordsToBlockIndex(Level level, int x, int y, int z) {
		if (x < 0 || y < 0 || z < 0 || x > level.getWidth() || y > level.getHeight() || z > level.getDepth())
			return -1;

		return x + (z * level.getWidth()) + (y * level.getWidth() * level.getDepth());
	}
	
}
