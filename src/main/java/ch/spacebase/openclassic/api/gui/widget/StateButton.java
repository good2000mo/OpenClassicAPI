package ch.spacebase.openclassic.api.gui.widget;

import ch.spacebase.openclassic.api.gui.Screen;
import ch.spacebase.openclassic.api.gui.widget.Button;

/**
 * Represents a button with a state. The text in the button displays as so: "Text: State"
 */
public final class StateButton extends Button {

	private String state;
	
	public StateButton(int id, int x, int y, Screen parent, String text) {
		this(id, x, y, 200, 20, parent, text);
	}
	
	public StateButton(int id, int x, int y, int width, int height, Screen parent, String text) {
		super(id, x, y, width, height, parent, text);
	}
	
	/**
	 * Gets the state of the button.
	 * @return The button's state.
	 */
	public String getState() {
		return this.state;
	}
	
	/**
	 * Sets the state of this button.
	 * @param state The new state of this button.
	 */
	public void setState(String state) {
		this.state = state;
	}
	
	@Override
	public void render() {
		String text = this.getText();
		this.setText(text + ": " + state);
		super.render();
		this.setText(text);
	}
	
}
