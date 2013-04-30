package ch.spacebase.openclassic.api.network.msg.custom.block;

import ch.spacebase.openclassic.api.block.model.Quad;
import ch.spacebase.openclassic.api.network.msg.Message;

/**
 * Contains a model's quad.
 */
public class QuadMessage extends Message {

	private byte block;
	private Quad quad;
	
	public QuadMessage(byte block, Quad quad) {
		this.block = block;
		this.quad = quad;
	}
	
	/**
	 * Gets the block the quad belongs to.
	 * @return The block the quad belongs to.
	 */
	public byte getBlock() {
		return this.block;
	}
	
	/**
	 * Gets the quad contained in this message.
	 * @return The contained quad.
	 */
	public Quad getQuad() {
		return this.quad;
	}
	
	@Override
	public String toString() {
		return "QuadMessage{block=" + block + "}";
	}

	@Override
	public Object[] getParams() {
		return new Object[] { this.block, this.quad.getId(), this.quad.getVertex(0).getX(), this.quad.getVertex(0).getY(), this.quad.getVertex(0).getZ(), this.quad.getVertex(1).getX(), this.quad.getVertex(1).getY(), this.quad.getVertex(1).getZ(), this.quad.getVertex(2).getX(), this.quad.getVertex(2).getY(), this.quad.getVertex(2).getZ(), this.quad.getVertex(3).getX(), this.quad.getVertex(3).getY(), this.quad.getVertex(3).getZ(), this.quad.getTexture().getParent().getTexture(), this.quad.getTexture().getParent().isInJar() ? (byte) 1 : (byte) 0, this.quad.getTexture().getParent().getWidth(), this.quad.getTexture().getParent().getHeight(), this.quad.getTexture().getParent().getSubTextureWidth(), this.quad.getTexture().getParent().getSubTextureHeight(), this.quad.getTexture().getId() };
	}
	
	@Override
	public byte getOpcode() {
		return 19;
	}

}
