package ch.spacebase.openclassic.api.gui.widget;

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;

import ch.spacebase.openclassic.api.gui.Screen;
import ch.spacebase.openclassic.api.input.InputHelper;
import ch.spacebase.openclassic.api.input.Keyboard;
import ch.spacebase.openclassic.api.render.RenderHelper;

/**
 * Represents a text box.
 */
public class TextBox extends Widget {

	private static final String ALLOWED = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789 ,.:;-_\'*!\"#@%$/()=+?[]{}<>^";
	
	protected String text = "";
	protected boolean focus;
	protected int cursor = 0;
	protected boolean blink = true;
	protected int blinkDelay = 6;
	protected int max;
	
	protected boolean chatbox;
	
	public TextBox(int id, int x, int y, Screen parent) {
		this(id, x, y, parent, 0);
	}
	
	public TextBox(int id, int x, int y, Screen parent, int max) {
		this(id, x, y, 200, 20, parent, max);
	}
	
	public TextBox(int id, int x, int y, int width, int height, Screen parent) {
		this(id, x, y, width, height, parent, false);
	}
	
	public TextBox(int id, int x, int y, int width, int height, Screen parent, int max) {
		this(id, x, y, width, height, parent, max, false);
	}
	
	public TextBox(int id, int x, int y, Screen parent, boolean chatbox) {
		this(id, x, y, 200, 20, parent, chatbox);
	}
	
	public TextBox(int id, int x, int y, Screen parent, int max, boolean chatbox) {
		this(id, x, y, 200, 20, parent, chatbox);
	}
	
	public TextBox(int id, int x, int y, int width, int height, Screen parent, boolean chatbox) {
		this(id, x, y, width, height, parent, 0, chatbox);
	}
	
	public TextBox(int id, int x, int y, int width, int height, Screen parent, int max, boolean chatbox) {
		super(id, x, y, width, height, parent);
		this.chatbox = chatbox;
		this.max = max;
	}
	
	@Override
	public void onAttached(Screen screen) {
		InputHelper.getHelper().enableRepeatEvents(true);
	}
	
	@Override
	public void onRemoved(Screen screen) {
		InputHelper.getHelper().enableRepeatEvents(false);
	}
	
	/**
	 * Returns true if the text box has the keyboard's focus.
	 * @return True if the text box has focus.
	 */
	public boolean hasFocus() {
		return this.focus;
	}
	
	/**
	 * Sets whether the text box has the keyboard's focus.
	 * @param focus Whether the text box has focus.
	 */
	public void setFocus(boolean focus) {
		this.focus = focus;
	}
	
	/**
	 * Gets the text in this text box.
	 * @return The text box's text.
	 */
	public String getText() {
		return this.text;
	}
	
	/**
	 * Sets the text in this text box.
	 * @param text The text to set.
	 */
	public void setText(String text) {
		this.text = text;
		if(this.text == null) this.text = "";
		
		this.cursor = text.length();
	}
	
	@Override
	public void update() {
		if(!this.focus) {
			if(this.blinkDelay != 10 && !this.blink) {
				this.blink = true;
			}
		} else {
			this.blinkDelay--;
			if(this.blinkDelay <= 0) {
				this.blink = !this.blink;
				this.blinkDelay = 6;
			}
		}
	}
	
	@Override
	public void onKeyPress(char c, int key) {
		if(!this.focus) return;
		
		if (key == Keyboard.KEY_BACK && this.text.length() > 0 && this.cursor > 0) {
			this.text = this.text.substring(0, this.cursor - 1) + this.text.substring(this.cursor);
			this.cursor--;
			for(Widget widget : this.parent.getWidgets()) {
				if(widget instanceof ButtonList && ((ButtonList) widget).getSearchBox().equals(this)) {
					((ButtonList) widget).onType();
				}
			}
		}
		
		if(key == Keyboard.KEY_V && InputHelper.getHelper().isKeyDown(Keyboard.KEY_LCONTROL)) {
			Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
			Transferable transfer = clipboard.getContents(null);
			if(transfer != null) {
				try {
					String old = this.text;
					for(char ch : ((String) transfer.getTransferData(DataFlavor.stringFlavor)).toCharArray()) {
						if (ALLOWED.indexOf(ch) >= 0) {
							this.text = this.text.substring(0, this.cursor) + ch + this.text.substring(this.cursor, this.text.length());
							this.cursor++;
						}
					}
					
					if(!old.equals(this.text)) {
						for(Widget widget : this.parent.getWidgets()) {
							if(widget instanceof ButtonList && ((ButtonList) widget).getSearchBox().equals(this)) {
								((ButtonList) widget).onType();
							}
						}
					}
				} catch (UnsupportedFlavorException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
		if(key == Keyboard.KEY_LEFT && this.cursor > 0) {
			this.cursor--;
		}
		
		if(key == Keyboard.KEY_RIGHT && this.cursor < this.text.length()) {
			this.cursor++;
		}

		if (ALLOWED.indexOf(c) >= 0 && !((this.chatbox && this.text.length() >= 64) || (this.max > 0 && this.text.length() >= this.max))) {
			this.text = this.text.substring(0, this.cursor) + c + this.text.substring(this.cursor, this.text.length());
			this.cursor++;
			for(Widget widget : this.parent.getWidgets()) {
				if(widget instanceof ButtonList && ((ButtonList) widget).getSearchBox().equals(this)) {
					((ButtonList) widget).onType();
				}
			}
		}
	}
	
	@Override
	public void onMouseClick(int x, int y, int button) {
		if(button != 0) return;
		
		if(!this.focus) {
			this.focus = true;
			for(Widget widget : this.parent.getWidgets()) {
				if(widget instanceof TextBox && ((TextBox) widget).hasFocus() && widget.getId() != this.getId()) {
					((TextBox) widget).setFocus(false);
				}
			}
		}
	}
	
	@Override
	public void render() {
		if(!this.chatbox) RenderHelper.getHelper().drawBox(this.x - 1, this.y - 1, this.x + this.width + 1, this.y + this.height + 1, -6250336);
		RenderHelper.getHelper().drawBox(this.x, this.y, this.x + this.width, this.y + this.height, (!this.chatbox ? -16777216 : Integer.MIN_VALUE));
		RenderHelper.getHelper().renderText(this.text.substring(0, this.cursor) + (this.blink && this.focus ? "|" : "") + this.text.substring(this.cursor, this.text.length()), this.x + 4, (this.chatbox ? this.y + 2 : this.y + 6), 14737632, false);
	}

}
