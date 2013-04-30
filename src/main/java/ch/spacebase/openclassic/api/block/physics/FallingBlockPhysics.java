package ch.spacebase.openclassic.api.block.physics;

import ch.spacebase.openclassic.api.block.Block;
import ch.spacebase.openclassic.api.block.BlockFace;
import ch.spacebase.openclassic.api.block.VanillaBlock;
import ch.spacebase.openclassic.api.block.physics.BlockPhysics;

/**
 * Physics used in falling blocks like sand or gravel.
 */
public class FallingBlockPhysics implements BlockPhysics {

	private byte id;
	
	public FallingBlockPhysics(byte id) {
		this.id = id;
	}
	
	@Override
	public void update(Block block) {
		this.fall(block);
	}

	@Override
	public void onPlace(Block block) {
		this.fall(block);
	}

	@Override
	public void onBreak(Block block) {
	}

	@Override
	public void onNeighborChange(Block block, Block neighbor) {
		this.fall(block);
	}
	
	private void fall(Block block) {
		Block relative = block.getRelative(BlockFace.DOWN);
		if(relative != null && (relative.getType() == VanillaBlock.AIR || relative.getType() == VanillaBlock.WATER || relative.getType() == VanillaBlock.STATIONARY_WATER || relative.getType() == VanillaBlock.LAVA || relative.getType() == VanillaBlock.STATIONARY_LAVA)) {
			block.setType(VanillaBlock.AIR);
			relative.setTypeId(this.id);
			block.getLevel().delayTick(relative.getPosition(), this.id);
		}
	}

}
