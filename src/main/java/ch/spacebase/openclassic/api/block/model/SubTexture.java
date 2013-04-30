package ch.spacebase.openclassic.api.block.model;

/**
 * Represents a part of a bigger texture.
 */
public class SubTexture implements Cloneable {
	
	private Texture parent;
	private int id;
	private float x;
	private float y;
	private float xLength;
	private float yLength;

	public SubTexture(Texture parent, int id, float x, float y, float length) {
		this(parent, id, x, y, length, length);
	}

	public SubTexture(Texture parent, int id, float x, float y, float xLength, float yLength) {
		this.parent = parent;
		this.id = id;
		this.x = x;
		this.y = y;
		this.xLength = xLength;
		this.yLength = yLength;
	}

	/**
	 * Gets the X of the SubTexture's first point.
	 * @return The X of the first point.
	 */
	public float getX1() {
		return this.x;
	}

	/**
	 * Gets the Y of the SubTexture's first point.
	 * @return The Y of the first point.
	 */
	public float getY1() {
		return this.y;
	}

	/**
	 * Gets the X of the SubTexture's second point.
	 * @return The X of the second point.
	 */
	public float getX2() {
		return this.x + this.xLength;
	}

	/**
	 * Gets the Y of the SubTexture's second point.
	 * @return The Y of the second point.
	 */
	public float getY2() {
		return this.y + this.yLength;
	}

	/**
	 * Gets the parent this SubTexture belongs to.
	 * @return The SubTexture's parent.
	 */
	public Texture getParent() {
		return this.parent;
	}

	/**
	 * Gets the texture ID of this SubTexture.
	 * @return The SubTexture's texture ID.
	 */
	public int getId() {
		return this.id;
	}
	
	public SubTexture clone() {
		return new SubTexture(this.parent, this.id, this.x, this.y, this.xLength, this.yLength);
	}
	
	@Override
	public String toString() {
		return "SubTexture{parent=" + this.parent.getTexture() + ",id=" + this.id + ",x=" + this.x + ",y=" + this.y + ",xLength=" + this.xLength + ",yLength=" + this.yLength + "}";
	}
	
}
