package ch.spacebase.openclassic.api.block.physics;

import ch.spacebase.openclassic.api.block.Block;
import ch.spacebase.openclassic.api.block.BlockFace;
import ch.spacebase.openclassic.api.block.VanillaBlock;
import ch.spacebase.openclassic.api.block.physics.BlockPhysics;

/**
 * Physics used to make half steps join.
 */
public class HalfStepPhysics implements BlockPhysics {

	@Override
	public void update(Block block) {
	}
	
	@Override
	public void onPlace(Block block) {
		Block relative = block.getRelative(BlockFace.DOWN);
		if(relative != null && relative.getType() == VanillaBlock.SLAB) {
			block.setType(VanillaBlock.AIR);
			relative.setType(VanillaBlock.DOUBLE_SLAB);
		}
	}

	@Override
	public void onBreak(Block block) {
	}

	@Override
	public void onNeighborChange(Block block, Block neighbor) {
	}

}
