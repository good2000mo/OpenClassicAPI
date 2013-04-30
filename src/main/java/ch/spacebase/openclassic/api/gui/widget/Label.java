package ch.spacebase.openclassic.api.gui.widget;

import ch.spacebase.openclassic.api.gui.Screen;
import ch.spacebase.openclassic.api.render.RenderHelper;

/**
 * A text label.
 */
public class Label extends Widget {

	private String text = "";
	
	public Label(int id, int x, int y, Screen parent, String text) {
		super(id, x, y, (int) RenderHelper.getHelper().getStringWidth(text), 8, parent);
		this.text = text;
	}
	
	/**
	 * Gets the text in this label.
	 * @return The text in this label.
	 */
	public String getText() {
		return this.text;
	}
	
	/**
	 * Sets the text in this label.
	 * @param text The new text in this label.
	 */
	public void setText(String text) {
		this.text = text;
		this.width = (int) RenderHelper.getHelper().getStringWidth(text);
	}

	@Override
	public void render() {
		RenderHelper.getHelper().renderText(this.text, this.x, this.y, false);
	}

}
