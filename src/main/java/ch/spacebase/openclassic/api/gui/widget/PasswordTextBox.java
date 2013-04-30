package ch.spacebase.openclassic.api.gui.widget;

import ch.spacebase.openclassic.api.gui.Screen;
import ch.spacebase.openclassic.api.render.RenderHelper;

/**
 * Represents a text box.
 */
public class PasswordTextBox extends TextBox {
	
	public PasswordTextBox(int id, int x, int y, Screen parent) {
		this(id, x, y, parent, 0);
	}
	
	public PasswordTextBox(int id, int x, int y, Screen parent, int max) {
		this(id, x, y, 200, 20, parent, max);
	}
	
	public PasswordTextBox(int id, int x, int y, int width, int height, Screen parent) {
		this(id, x, y, width, height, parent, false);
	}
	
	public PasswordTextBox(int id, int x, int y, int width, int height, Screen parent, int max) {
		this(id, x, y, width, height, parent, max, false);
	}
	
	public PasswordTextBox(int id, int x, int y, Screen parent, boolean chatbox) {
		this(id, x, y, 200, 20, parent, chatbox);
	}
	
	public PasswordTextBox(int id, int x, int y, Screen parent, int max, boolean chatbox) {
		this(id, x, y, 200, 20, parent, chatbox);
	}
	
	public PasswordTextBox(int id, int x, int y, int width, int height, Screen parent, boolean chatbox) {
		this(id, x, y, width, height, parent, 0, chatbox);
	}
	
	public PasswordTextBox(int id, int x, int y, int width, int height, Screen parent, int max, boolean chatbox) {
		super(id, x, y, width, height, parent, max, chatbox);
	}
	
	@Override
	public void render() {
		if(!this.chatbox) RenderHelper.getHelper().drawBox(this.x - 1, this.y - 1, this.x + this.width + 1, this.y + this.height + 1, -6250336);
		RenderHelper.getHelper().drawBox(this.x, this.y, this.x + this.width, this.y + this.height, (!this.chatbox ? -16777216 : Integer.MIN_VALUE));
		String render = this.text.replaceAll("(?s).", "*");
		RenderHelper.getHelper().renderText(render.substring(0, this.cursor) + (this.blink && this.focus ? "|" : "") + render.substring(this.cursor, render.length()), this.x + 4, (this.chatbox ? this.y + 2 : this.y + 6), 14737632, false);
	}

}
