package ch.spacebase.openclassic.api.block.physics;

import ch.spacebase.openclassic.api.block.Block;
import ch.spacebase.openclassic.api.block.VanillaBlock;
import ch.spacebase.openclassic.api.block.physics.BlockPhysics;

/**
 * Physics used in mushrooms.
 */
public class MushroomPhysics implements BlockPhysics {

	@Override
	public void update(Block block) {
		Block b = block.getLevel().getBlockAt(block.getPosition().getBlockX(), block.getPosition().getBlockY() - 1, block.getPosition().getBlockZ());
		if (block.getLevel().isLit(block.getPosition().getBlockX(), block.getPosition().getBlockY(), block.getPosition().getBlockZ()) || b.getType() != VanillaBlock.STONE && b.getType() != VanillaBlock.GRAVEL && b.getType() != VanillaBlock.COBBLESTONE) {
			block.setType(VanillaBlock.AIR);
		}
	}

	@Override
	public void onPlace(Block block) {
	}

	@Override
	public void onBreak(Block block) {
	}

	@Override
	public void onNeighborChange(Block block, Block neighbor) {
	}

}
