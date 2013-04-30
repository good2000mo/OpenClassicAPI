package ch.spacebase.openclassic.api.block.physics;

import java.util.Random;

import ch.spacebase.openclassic.api.block.Block;
import ch.spacebase.openclassic.api.block.VanillaBlock;
import ch.spacebase.openclassic.api.block.physics.BlockPhysics;

/**
 * Physics used for grass.
 */
public class GrassPhysics implements BlockPhysics {

	private static final Random rand = new Random();
	
	@Override
	public void update(Block block) {
		if (rand.nextInt(4) == 0) {
			if (!block.getLevel().isLit(block.getPosition().getBlockX(), block.getPosition().getBlockY(), block.getPosition().getBlockZ())) {
				block.setType(VanillaBlock.DIRT);
			} else {
				for (int count = 0; count < 4; ++count) {
					int x = block.getPosition().getBlockX() + rand.nextInt(3) - 1;
					int y = block.getPosition().getBlockY() + rand.nextInt(5) - 3;
					int z = block.getPosition().getBlockZ() + rand.nextInt(3) - 1;
					
					if (block.getLevel().getBlockTypeAt(x, y, z) == VanillaBlock.DIRT && block.getLevel().isLit(x, y, z)) {
						block.getLevel().setBlockAt(x, y, z, VanillaBlock.GRASS);
					}
				}
			}
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
