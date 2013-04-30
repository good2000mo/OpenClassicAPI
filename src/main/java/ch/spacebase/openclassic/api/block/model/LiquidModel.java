package ch.spacebase.openclassic.api.block.model;

import java.util.ArrayList;
import java.util.List;

import ch.spacebase.openclassic.api.Client;
import ch.spacebase.openclassic.api.OpenClassic;
import ch.spacebase.openclassic.api.block.BlockFace;
import ch.spacebase.openclassic.api.block.BlockType;
import ch.spacebase.openclassic.api.render.RenderHelper;

/**
 * A model used in liquids.
 */
public class LiquidModel extends CubeModel {

	private int quadCount = 0;
	private BoundingBox top = new BoundingBox(0, 0, 0, 1, 0.95f, 1);
	private List<Quad> topQuads = new ArrayList<Quad>();
	
	public LiquidModel(Texture texture, int textureIds[]) {
		super(texture, textureIds);
		
		Quad bottom = new Quad(0, texture.getSubTexture(textureIds[0]));
		bottom.addVertex(0, 0, 0, 0);
		bottom.addVertex(1, 1, 0, 0);
		bottom.addVertex(2, 1, 0, 1);
		bottom.addVertex(3, 0, 0, 1);
		this.addTopQuad(bottom);
		
		Quad top = new Quad(1, texture.getSubTexture(textureIds[1]));
		top.addVertex(0, 0, 0.95f, 0);
		top.addVertex(1, 0, 0.95f, 1);
		top.addVertex(2, 1, 0.95f, 1);
		top.addVertex(3, 1, 0.95f, 0);
		this.addTopQuad(top);

		Quad face1 = new Quad(2, texture.getSubTexture(textureIds[2]));
		face1.addVertex(0, 0, 0, 0);
		face1.addVertex(1, 0, 0.95f, 0);
		face1.addVertex(2, 1, 0.95f, 0);
		face1.addVertex(3, 1, 0, 0);
		this.addTopQuad(face1);

		Quad face2 = new Quad(3, texture.getSubTexture(textureIds[3]));
		face2.addVertex(0, 1, 0, 1);
		face2.addVertex(1, 1, 0.95f, 1);
		face2.addVertex(2, 0, 0.95f, 1);
		face2.addVertex(3, 0, 0, 1);
		this.addTopQuad(face2);

		Quad face3 = new Quad(4, texture.getSubTexture(textureIds[4]));
		face3.addVertex(0, 0, 0, 1);
		face3.addVertex(1, 0, 0.95f, 1);
		face3.addVertex(2, 0, 0.95f, 0);
		face3.addVertex(3, 0, 0, 0);
		this.addTopQuad(face3);
		
		Quad face4 = new Quad(5, texture.getSubTexture(textureIds[5]));
		face4.addVertex(0, 1, 0, 0);
		face4.addVertex(1, 1, 0.95f, 0);
		face4.addVertex(2, 1, 0.95f, 1);
		face4.addVertex(3, 1, 0, 1);
		this.addTopQuad(face4);
	}
	
	public LiquidModel(Texture texture, int textureId) {
		this(texture, new int[] { textureId, textureId, textureId, textureId, textureId, textureId });
	}
	
	public LiquidModel(String texture, int textureSize) {
		this(new Texture(texture, false, textureSize, textureSize, textureSize), 0);
	}

	@Override
	public void addQuad(Quad quad) {
		quad.id = this.quadCount;
		super.addQuad(quad);
		this.quadCount++;
		
		/* Quad q = new Quad(this.quadCount, quad.getTexture());
		q.addVertex(0, quad.getVertex(3));
		q.addVertex(1, quad.getVertex(2));
		q.addVertex(2, quad.getVertex(1));
		q.addVertex(3, quad.getVertex(0));
		super.addQuad(q);
		this.quadCount++; */
	}
	
	public void addTopQuad(Quad quad) {
		quad.id = this.quadCount;
		this.topQuads.add(quad.getId(), quad);
		quad.setParent(this);
		this.quadCount++;
		
		/* Quad q = new Quad(this.quadCount, quad.getTexture());
		q.addVertex(0, quad.getVertex(2));
		q.addVertex(1, quad.getVertex(3));
		q.addVertex(2, quad.getVertex(0));
		q.addVertex(3, quad.getVertex(1));
		this.topQuads.add(quad.getId(), quad);
		quad.setParent(this);
		this.quadCount++; */
	}
	
	@Override
	public BoundingBox getSelectionBox(int x, int y, int z) {
		if(OpenClassic.getGame() instanceof Client && OpenClassic.getClient().getLevel() != null && OpenClassic.getClient().getLevel().getBlockTypeAt(x, y + 1, z) != null && !OpenClassic.getClient().getLevel().getBlockTypeAt(x, y + 1, z).isLiquid()) {
			return this.top;
		}
		
		return super.getSelectionBox(x, y, z);
	}
	
	@Override
	public boolean render(float x, float y, float z, float brightness) {
		BlockType block = OpenClassic.getClient().getLevel().getBlockTypeAt((int) x, (int) y, (int) z);
		if(block == null) return false;
		boolean result = false;
		
		List<Quad> quads = this.getQuads();
		if(OpenClassic.getClient().getLevel().getBlockTypeAt((int) x, (int) y + 1, (int) z) != null && !OpenClassic.getClient().getLevel().getBlockTypeAt((int) x, (int) y + 1, (int) z).isLiquid()) {
			quads = this.topQuads;
		}
		
		RenderHelper.getHelper().setCulling(false);
		int count = 0;
		for(Quad quad : quads) {
			BlockFace face = quadToFace(this, count);
			if (RenderHelper.getHelper().canRenderSide(block, (int) x, (int) y, (int) z, face)) {
				float mod = 0;
				switch(face) {
					case DOWN:
						mod = 0.5F;
						break;
					case UP:
						mod = 1;
						break;
					case WEST:
					case EAST:
						mod = 0.8F;
						break;
					case SOUTH:
					case NORTH:
						mod = 0.6F;
						break;
				}
				
				quad.render(x, y, z, RenderHelper.getHelper().getBrightness(block, (int) x + face.getModX(), (int) y + face.getModY(), (int) z + face.getModZ()) * mod);
				result = true;
			}
			
			count++;
		}
		
		RenderHelper.getHelper().setCulling(true);
		return result;
	}

}
