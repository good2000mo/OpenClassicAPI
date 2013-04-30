package ch.spacebase.openclassic.api.block.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a texture.
 */
public class Texture {

	private String texture;
	private boolean jar;
	
	private int width;
	private int height;
	private int subWidth;
	private int subHeight;
	private List<SubTexture> subTextures;
	
	public Texture(String texture, boolean jar, int width, int height) {
		this(texture, jar, width, height, width, height);
	}
	
	public Texture(String texture, boolean jar, int width, int height, int subSize) {
		this(texture, jar, width, height, subSize, subSize);
	}
	
	public Texture(String texture, boolean jar, int width, int height, int subWidth, int subHeight) {
		this.texture = texture;
		this.jar = jar;
		this.width = width;
		this.height = height;
		this.subWidth = subWidth;
		this.subHeight = subHeight;
		this.subTextures = new ArrayList<SubTexture>((width / subWidth) * (height / subHeight));
		
		int count = 0;
		for (int y = 0; y < height / subHeight; y++) {
			for (int x = 0; x < width / subHeight; x++) {
				this.subTextures.add(count, new SubTexture(this, count, x * subWidth, y * subHeight, subWidth, subHeight));
				count++;
			}
		}
	}
	
	/**
	 * Gets the SubTexture with the given ID.
	 * @param id ID of the SubTexture.
	 * @return The SubTexture with the given ID.
	 */
	public SubTexture getSubTexture(int id) {
		return this.subTextures.get(id);
	}
	
	/**
	 * Gets the path of this texture's file.
	 * @return The path of this texture's file.
	 */
	public String getTexture() {
		return this.texture;
	}
	
	/**
	 * Sets the texture file of this texture.
	 * @param path Path of the texture file.
	 */
	public void setTexture(String path) {
		this.texture = path;
	}
	
	/**
	 * Returns true if this texture is in the minecraft client jar file.
	 * @return True if the texture is in the minecraft jar.
	 */
	public boolean isInJar() {
		return this.jar;
	}

	/**
	 * Gets the width of each SubTexture belonging to this texture.
	 * @return Each SubTexture's width.
	 */
	public int getSubTextureWidth() {
		return this.subWidth;
	}
	
	/**
	 * Gets the height of each SubTexture belonging to this texture.
	 * @return Each SubTexture's height.
	 */
	public int getSubTextureHeight() {
		return this.subHeight;
	}

	/**
	 * Gets the width of this texture.
	 * @return The texture's width.
	 */
	public int getWidth() {
		return this.width;
	}

	/**
	 * Gets the height of this texture.
	 * @return The texture's height.
	 */
	public int getHeight() {
		return this.height;
	}

	/**
	 * Gets the number of subtextures in this texture.
	 * @return The number of subtextures in this texture.
	 */
	public int getSubTextures() {
		return this.subTextures.size();
	}
	
}
