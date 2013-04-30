package ch.spacebase.openclassic.api.network.msg.custom.block;

import ch.spacebase.openclassic.api.block.custom.CustomBlock;
import ch.spacebase.openclassic.api.network.msg.Message;

/**
 * Contains a custom block.
 */
public class CustomBlockMessage extends Message {

	private CustomBlock block;
	
	public CustomBlockMessage(CustomBlock block) {
		this.block = block;
	}
	
	/**
	 * Gets the block contained in the message.
	 * @return The contained block.
	 */
	public CustomBlock getBlock() {
		return this.block;
	}
	
	@Override
	public String toString() {
		return "CustomBlockMessage{blockid=" + block.getId() + "}";
	}

	@Override
	public Object[] getParams() {
		return new Object[] { this.block.getId(), this.block.isOpaque() ? (byte) 1 : (byte) 0, this.block.isSelectable() ? (byte) 1 : (byte) 0, this.block.getStepSound().name(), this.block.isLiquid() ? (byte) 1 : (byte) 0, this.block.getTickDelay(), this.block.getFallback().getId(), this.block.isSolid() ? (byte) 1 : (byte) 0 };
	}
	
	@Override
	public byte getOpcode() {
		return 17;
	}

}
