package ch.spacebase.openclassic.api.render;

import java.awt.image.BufferedImage;

import ch.spacebase.openclassic.api.block.BlockFace;
import ch.spacebase.openclassic.api.block.BlockType;
import ch.spacebase.openclassic.api.block.model.Quad;
import ch.spacebase.openclassic.api.block.model.SubTexture;
import ch.spacebase.openclassic.api.block.model.Texture;
import ch.spacebase.openclassic.api.input.InputHelper;

/**
 * A collection of Gui helper methods.
 */
public abstract class RenderHelper {

	protected static RenderHelper helper;
	
	/**
	 * Gets the current GuiHelper instance.
	 * @return The current instance.
	 */
	public static RenderHelper getHelper() {
		return helper;
	}
	
	/**
	 * Sets the current GuiHelper instance.
	 * @param helper New instance.
	 */
	public static void setHelper(RenderHelper helper) {
		if(RenderHelper.helper != null || helper == null) return;
		RenderHelper.helper = helper;
	}
	
	/**
	 * Binds the given texture to the GL buffer.
	 * @param file File to bind.
	 * @param jar Whether the file is in the client jar or not.
	 * @return The binded texture's id.
	 */
	public abstract int bindTexture(String file, boolean jar);
	
	/**
	 * Binds the given texture to the GL buffer.
	 * @param id The texture id to bind.
	 */
	public abstract void bindTexture(int id);
	
	/**
	 * Draws the dirt block background.
	 */
	public abstract void drawDefaultBG();
	
	/**
	 * Renders the given text at the given position with the X as the center.
	 * @param text Text to render
	 * @param x X to render at.
	 * @param y Y to render at.
	 */
	public abstract void renderText(String text, float x, float y);
	
	/**
	 * Renders the given text at the given position.
	 * @param text Text to render
	 * @param x X to render at.
	 * @param y Y to render at.
	 * @param Whether the X is the center or not.
	 */
	public abstract void renderText(String text, float x, float y, boolean xCenter);
	
	/**
	 * Renders the given text at the given position with the X as the center.
	 * @param text Text to render
	 * @param x X to render at.
	 * @param y Y to render at.
	 * @param color Color to render the text.
	 */
	public abstract void renderText(String text, float x, float y, int color);
	
	/**
	 * Renders the given text at the given position.
	 * @param text Text to render
	 * @param x X to render at.
	 * @param y Y to render at.
	 * @param color Color to render the text.
	 * @param xCenter Whether the X is the center or not.
	 */
	public abstract void renderText(String text, float x, float y, int color, boolean xCenter);
	
	/**
	 * Renders the given text at the given position with the X as the center and no shadow.
	 * @param text Text to render
	 * @param x X to render at.
	 * @param y Y to render at.
	 */
	public abstract void renderTextNoShadow(String text, float x, float y);
	
	/**
	 * Renders the given text at the given position with no shadow.
	 * @param text Text to render
	 * @param x X to render at.
	 * @param y Y to render at.
	 * @param Whether the X is the center or not.
	 */
	public abstract void renderTextNoShadow(String text, float x, float y, boolean xCenter);
	
	/**
	 * Renders the given text at the given position with the X as the center and no shadow.
	 * @param text Text to render
	 * @param x X to render at.
	 * @param y Y to render at.
	 * @param color Color to render the text.
	 */
	public abstract void renderTextNoShadow(String text, float x, float y, int color);
	
	/**
	 * Renders the given text at the given position with no shadow.
	 * @param text Text to render
	 * @param x X to render at.
	 * @param y Y to render at.
	 * @param color Color to render the text.
	 * @param xCenter Whether the X is the center or not.
	 */
	public abstract void renderTextNoShadow(String text, float x, float y, int color, boolean xCenter);

	/**
	 * Draws a box with the given corners and color.
	 * @param x1 X of the first corner.
	 * @param y1 Y of the first corner.
	 * @param f X of the second corner.
	 * @param g Y of the second corner.
	 * @param color Color of the box.
	 */
	public abstract void drawBox(float x1, float y1, float x2, float y2, int color);
	
	/**
	 * Colors the specified area, fading into the given color.
	 * @param x1 X of the first corner.
	 * @param y1 Y of the first corner.
	 * @param x2 X of the second corner.
	 * @param y2 Y of the second corner.
	 * @param color Color to draw.
	 */
	public abstract void color(int x1, int y1, int x2, int y2, int color);
	
	/**
	 * Colors the specified area, fading into the given color.
	 * @param x1 X of the first corner.
	 * @param y1 Y of the first corner.
	 * @param x2 X of the second corner.
	 * @param y2 Y of the second corner.
	 * @param color Color to draw.
	 * @param fadeTo Color to fade into.
	 */
	public abstract void color(int x1, int y1, int x2, int y2, int color, int fadeTo);

	/**
	 * Tells OpenGL to draw the specified color.
	 * @param red Red of the color.
	 * @param green Green of the color.
	 * @param blue Blue of the color.
	 * @param alpha Alpha of the color.
	 */
	public abstract void glColor(float red, float green, float blue, float alpha);
	
	/**
	 * Gets the width of the current display.
	 * @return The display's width.
	 */
	public abstract int getDisplayWidth();
	
	/**
	 * Gets the height of the current display.
	 * @return The display's height.
	 */
	public abstract int getDisplayHeight();
	
	/**
	 * Gets the mouse's X relative to rendering coordinates.
	 * @return The mouse's X.
	 */
	public int getRenderMouseX() {
		return InputHelper.getHelper().getMouseX() * (this.getDisplayWidth() * 240 / this.getDisplayHeight()) / this.getDisplayWidth();
	}
	
	/**
	 * Gets the mouse's Y relative to rendering coordinates.
	 * @return The mouse's Y.
	 */
	public int getRenderMouseY() {
		int height = this.getDisplayHeight() * 240 / this.getDisplayHeight();
		return height - InputHelper.getHelper().getMouseY() * height / this.getDisplayHeight() - 1;
	}

	/**
	 * Draws a quad.
	 * @param quad Quad to draw.
	 * @param x X to draw at.
	 * @param y Y to draw at.
	 * @param z Z to draw at.
	 */
	public abstract void drawQuad(Quad quad, float x, float y, float z);
	
	/**
	 * Draws a quad.
	 * @param quad Quad to draw.
	 * @param x X to draw at.
	 * @param y Y to draw at.
	 * @param z Z to draw at.
	 * @param brightness Brightness to draw at.
	 */
	public abstract void drawQuad(Quad quad, float x, float y, float z, float brightness);

	/**
	 * Draws a texture.
	 * @param texture Texture to draw
	 * @param x X to draw at.
	 * @param y Y to draw at.
	 * @param brightness Brightness to draw at.
	 */
	public abstract void drawTexture(Texture texture, float x, float y, float brightness);
	
	/**
	 * Draws a texture.
	 * @param texture Texture to draw
	 * @param x X to draw at.
	 * @param y Y to draw at.
	 * @param z Z to draw at.
	 * @param brightness Brightness to draw at.
	 */
	public abstract void drawTexture(Texture texture, float x, float y, float z, float brightness);
	
	/**
	 * Draws a subtexture.
	 * @param texture SubTexture to draw
	 * @param x X to draw at.
	 * @param y Y to draw at.
	 * @param brightness Brightness to draw at.
	 */
	public abstract void drawSubTex(SubTexture texture, float x, float y, float brightness);
	
	/**
	 * Draws a subtexture.
	 * @param texture SubTexture to draw
	 * @param x X to draw at.
	 * @param y Y to draw at.
	 * @param z Z to draw at.
	 * @param brightness Brightness to draw at.
	 */
	public abstract void drawSubTex(SubTexture texture, float x, float y, float z, float brightness);
	
	/**
	 * Draws a subtexture.
	 * @param texture SubTexture to draw
	 * @param x X to draw at.
	 * @param y Y to draw at.
	 * @param z Z to draw at.
	 * @param scale Scale to draw with.
	 * @param r Red to color with.
	 * @param g Green to color with.
	 * @param b Blue to color with.
	 */
	public abstract void drawSubTex(SubTexture texture, float x, float y, float z, float scale, float r, float g, float b);
	
	/**
	 * Returns true if the side of the block can be rendered.
	 * @param block Block being rendered.
	 * @param z 
	 * @param y 
	 * @param x 
	 * @param face Face being checked.
	 * @return Whether the side can be rendered.
	 */
	public abstract boolean canRenderSide(BlockType block, int x, int y, int z, BlockFace face);
	
	/**
	 * Gets the brightness of the given block.
	 * @param main Type of the block the brightness checks come from.
	 * @param x X of the block to check.
	 * @param y Y of the block to check.
	 * @param z Z of the block to check.
	 * @return The block's brightness.
	 */
	public abstract float getBrightness(BlockType main, int x, int y, int z);
	
	/**
	 * Gets the width of a string.
	 * @return The width of a string.
	 */
	public abstract float getStringWidth(String string);

	/**
	 * Draws a rotated block like in the quickbar.
	 * @param X to draw at.
	 * @param Y to draw at.
	 * @param Block to draw.
	 */
	public abstract void drawRotatedBlock(int x, int y, BlockType block);
	
	/**
	 * Draws a rotated block like in the quickbar.
	 * @param X to draw at.
	 * @param Y to draw at.
	 * @param Block to draw.
	 * @param Scale to draw at.
	 */
	public abstract void drawRotatedBlock(int x, int y, BlockType block, float scale);
	
	/**
	 * Draws the given BufferedImage.
	 * @param image BufferedImage to draw.
	 * @param x X to draw at.
	 * @param y Y to draw at.
	 */
	public abstract void drawImage(BufferedImage image, int x, int y);
	
	/**
	 * Draws the given BufferedImage.
	 * @param image BufferedImage to draw.
	 * @param x X to draw at.
	 * @param y Y to draw at.
	 * @param z Z to draw at.
	 */
	public abstract void drawImage(BufferedImage image, int x, int y, int z);
	
	/**
	 * Draws a quad at the given scale.
	 * @param quad Quad to draw.
	 * @param x X to draw at.
	 * @param y Y to draw at.
	 * @param z Z to draw at.
	 * @param scale Scale to draw at.
	 * @param brightness Brightness to draw at.
	 */
	public abstract void drawScaledQuad(Quad quad, float x, float y, float z, float scale, float brightness);

	/**
	 * Sets whether backface culling is turned on.
	 * @param enabled Whether culling is enabled.
	 */
	public abstract void setCulling(boolean enabled);
	
}
