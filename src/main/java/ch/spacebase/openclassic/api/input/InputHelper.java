package ch.spacebase.openclassic.api.input;

/**
 * A collection of input helper methods.
 */
public abstract class InputHelper {

	private static InputHelper helper;
	
	/**
	 * Gets the current InputHelper instance.
	 * @return The current instance.
	 */
	public static InputHelper getHelper() {
		return helper;
	}
	
	/**
	 * Sets the current InputHelper instance.
	 * @param helper The new instance.
	 */
	public static void setHelper(InputHelper helper) {
		if(InputHelper.helper != null || helper == null) return;
		InputHelper.helper = helper;
	}
	
	/**
	 * Returns true if the given mouse button is down.
	 * @param button Button to check.
	 * @return True if the button is down.
	 */
	public abstract boolean isMouseButtonDown(int button);
	
	/**
	 * Returns true if the given key is down.
	 * @param key Key to check.
	 * @return True if the key is down.
	 */
	public abstract boolean isKeyDown(int key);

	/**
	 * Gets the X of the mouse.
	 * @return The X of the mouse.
	 */
	public abstract int getMouseX();

	/**
	 * Gets the Y of the mouse.
	 * @return The Y of the mouse.
	 */
	public abstract int getMouseY();

	/**
	 * Returns mouse focus to the MainScreen.
	 */
	public abstract void grabMouse();

	/**
	 * Sets whether keyboard repeat events are enabled.
	 * @param enabled Whether keyboard repeat events are enabled.
	 */
	public abstract void enableRepeatEvents(boolean enabled);
	
}
