package ch.spacebase.openclassic.api.block.model;

import java.util.Arrays;
import java.util.List;

import ch.spacebase.openclassic.api.render.RenderHelper;

/**
 * A quad face used in models.
 */
public class Quad {

	protected int id;
	private Vertex vertices[] = new Vertex[4];
	private SubTexture texture;
	private Model parent;
	
	public Quad(int id, SubTexture texture) {
		this.texture = texture;
		this.id = id;
	}
	
	public Quad(int id, SubTexture texture, Vertex v1, Vertex v2, Vertex v3, Vertex v4) {
		this(id, texture);
		this.addVertex(0, v1);
		this.addVertex(1, v2);
		this.addVertex(2, v3);
		this.addVertex(3, v4);
	}
	
	/**
	 * Gets the ID of this quad.
	 * @return This quad's ID.
	 */
	public int getId() {
		return this.id;
	}
	
	/**
	 * Adds a vertex to this quad.
	 * @param id ID of the vertex.
	 * @param vertex Vertex to add.
	 */
	public void addVertex(int id, Vertex vertex) {
		if(id > 3 || id < 0) throw new IllegalArgumentException("Quad can only have 4 vertices with IDs 0 - 3!");
		this.vertices[id] = vertex;
	}
	
	/**
	 * Adds a vertex to this quad.
	 * @param id ID of the vertex.
	 * @param x X of the vertex.
	 * @param y Y of the vertex.
	 * @param z Z of the vertex.
	 */
	public void addVertex(int id, float x, float y, float z) {
		this.addVertex(id, new Vertex(x, y, z));
	}
	
	/**
	 * Removes the vertex with the given ID.
	 * @param id ID of the vertex to remove.
	 */
	public void removeVertex(int id) {
		if(id > 3 || id < 0) throw new IllegalArgumentException("Quad can only have 4 vertices with IDs 0 - 3!");
		this.vertices[id] = null;
	}
	
	/**
	 * Gets the vertex with the given ID.
	 * @param id ID to look for.
	 * @return The vertex with the given ID.
	 */
	public Vertex getVertex(int id) {
		if(id > 3 || id < 0) throw new IllegalArgumentException("Quad can only have 4 vertices with IDs 0 - 3!");
		return this.vertices[id];
	}
	
	/**
	 * Gets the quad's vertices.
	 * @return The quad's vertices.
	 */
	public List<Vertex> getVertices() {
		return Arrays.asList(this.vertices);
	}
	
	/**
	 * Gets the quad's texture.
	 * @return The quad's texture.
	 */
	public SubTexture getTexture() {
		return this.texture;
	}
	
	/**
	 * Renders the quad.
	 * @param x X to render at.
	 * @param y Y to render at.
	 * @param z Z to render at.
	 * @param brightness Brightness to render at.
	 */
	public void render(float x, float y, float z, float brightness) {	
		RenderHelper.getHelper().drawQuad(this, x, y, z, brightness);
	}
	
	/**
	 * Renders the quad at the given scale.
	 * @param x X to render at.
	 * @param y Y to render at.
	 * @param z Z to render at.
	 * @param scale Scale to render at.
	 * @param brightness Brightness to render at.
	 */
	public void renderScaled(float x, float y, float z, float scale, float brightness) {
		RenderHelper.getHelper().drawScaledQuad(this, x, y, z, scale, brightness);
	}

	/**
	 * Gets the parent of this quad.
	 * @return The quad's parent.
	 */
	public Model getParent() {
		return this.parent;
	}
	
	protected void setParent(Model parent) {
		this.parent = parent;
	}
	
}
