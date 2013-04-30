package ch.spacebase.openclassic.api.block.physics;

import ch.spacebase.openclassic.api.Position;
import ch.spacebase.openclassic.api.block.Block;
import ch.spacebase.openclassic.api.block.BlockType;
import ch.spacebase.openclassic.api.block.VanillaBlock;
import ch.spacebase.openclassic.api.block.physics.BlockPhysics;
import ch.spacebase.openclassic.api.level.Level;

/**
 * Physics used in liquids to make it flow.
 */
public class LiquidPhysics implements BlockPhysics {

	private byte id;
	
	public LiquidPhysics(byte id) {
		this.id = id;
	}
	
	@Override
	public void update(Block block) {
		boolean moving = false;
		int y = block.getPosition().getBlockY();
		
		while (true) {
			y--;
			if (block.getLevel().getBlockTypeAt(block.getPosition().getBlockX(), y, block.getPosition().getBlockZ()) != VanillaBlock.AIR || !this.canMove(block.getLevel(), block.getPosition().getBlockX(), y, block.getPosition().getBlockZ())) {
				break;
			}

			if (block.getLevel().setBlockIdAt(block.getPosition().getBlockX(), y, block.getPosition().getBlockZ(), this.id)) {
				moving = true;
			} else {
				break;
			}
			
			if(this.id == VanillaBlock.LAVA.getId()) {
				break;
			}
		}

		y++;
		if (this.id == VanillaBlock.WATER.getId() || !moving) {
			int x = block.getPosition().getBlockX();
			int yy = block.getPosition().getBlockY();
			int z = block.getPosition().getBlockZ();
			moving = moving | this.canFlow(block.getLevel(), x - 1, yy, z) | this.canFlow(block.getLevel(), x + 1, yy, z) | this.canFlow(block.getLevel(), x, yy, z - 1) | this.canFlow(block.getLevel(), x, yy, z + 1);
		}

		if (moving) {
			block.getLevel().delayTick(new Position(block.getLevel(), block.getPosition().getBlockX(), y, block.getPosition().getBlockZ()), this.id);
		}
	}
	
	private boolean canMove(Level level, int x, int y, int z) {
		if (this.id == VanillaBlock.WATER.getId()) {
			for (int bx = x - 2; bx <= x + 2; bx++) {
				for (int by = y - 2; by <= y + 2; by++) {
					for (int bz = z - 2; bz <= z + 2; bz++) {
						BlockType block = level.getBlockTypeAt(bx, by, bz);
						if (block != null && block == VanillaBlock.SPONGE) {
							return false;
						}
					}
				}
			}
		}

		return true;
	}

	private boolean canFlow(Level level, int x, int y, int z) {
		if (level.getBlockTypeAt(x, y, z) == VanillaBlock.AIR) {
			if (!this.canMove(level, x, y, z)) {
				return false;
			}

			if (level.setBlockIdAt(x, y, z, this.id)) {
				level.delayTick(new Position(level, x, y, z), this.id);
			}
		}

		return false;
	}

	@Override
	public void onPlace(Block block) {
		block.getLevel().delayTick(block.getPosition(), this.id);
	}

	@Override
	public void onBreak(Block block) {
	}

	@Override
	public void onNeighborChange(Block block, Block neighbor) {
		if (neighbor.getType() != VanillaBlock.AIR) {
			if (this.id == VanillaBlock.WATER.getId() && neighbor.getType() == VanillaBlock.LAVA || neighbor.getType() == VanillaBlock.WATER && this.id == VanillaBlock.LAVA.getId()) {
				block.setType(VanillaBlock.STONE);
				return;
			}
		}

		block.getLevel().delayTick(block.getPosition(), block.getTypeId());
	}

}
