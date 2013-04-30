package ch.spacebase.openclassic.api.block.physics;

import java.util.Random;

import ch.spacebase.openclassic.api.block.Block;
import ch.spacebase.openclassic.api.block.BlockType;
import ch.spacebase.openclassic.api.block.VanillaBlock;
import ch.spacebase.openclassic.api.block.physics.BlockPhysics;

/**
 * Physics used in saplings to grow trees.
 */
public class SaplingPhysics implements BlockPhysics {

	private static final Random rand = new Random();
	
	@Override
	public void update(Block block) {
		BlockType type = block.getLevel().getBlockTypeAt(block.getPosition().getBlockX(), block.getPosition().getBlockY() - 1, block.getPosition().getBlockZ());
		if (block.getLevel().isLit(block.getPosition().getBlockX(), block.getPosition().getBlockY(), block.getPosition().getBlockZ()) && (type == VanillaBlock.DIRT || type == VanillaBlock.GRASS)) {
			if (rand.nextInt(5) == 0) {
				block.setType(VanillaBlock.AIR, false);
				if (!block.getLevel().growTree(block.getPosition().getBlockX(), block.getPosition().getBlockY(), block.getPosition().getBlockZ())) {
					block.setType(VanillaBlock.SAPLING, false);
				}
			}
		} else {
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
