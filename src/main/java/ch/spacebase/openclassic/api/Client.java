package ch.spacebase.openclassic.api;

import ch.spacebase.openclassic.api.gui.GuiScreen;
import ch.spacebase.openclassic.api.gui.MainScreen;
import ch.spacebase.openclassic.api.level.Level;
import ch.spacebase.openclassic.api.player.Player;

/**
 * Represents the OpenClassic Client.
 */
public interface Client extends Game {

	/**
	 * Gets the client's player.
	 * @return The client's player.
	 */
	public Player getPlayer();
	
	/**
	 * Gets the client's level.
	 * @return The client's level.
	 */
	public Level getLevel();
	
	/**
	 * Loads and enters the given level.
	 * @return name The level to load.
	 */
	public Level openLevel(String name);
	
	/**
	 * Saves the current level.
	 */
	public void saveLevel();

	/**
	 * Sets the current visible GuiScreen.
	 * @param screen Screen to set visible.
	 */
	public void setCurrentScreen(GuiScreen screen);
	
	/**
	 * Gets the current visible GuiScreen.
	 * @return Currently visible screen.
	 */
	public GuiScreen getCurrentScreen();

	/**
	 * Returns true if the client is in a game session.
	 * @return True if the client is ingame.
	 */
	public boolean isInGame();

	/**
	 * Gets the main screen of the client.
	 * @return The client's main screen. (null if not in a game session)
	 */
	public MainScreen getMainScreen();

	/**
	 * Exits the current level and returns to the main menu.
	 */
	public void exitLevel();
	
	/**
	 * Exits the current level and returns to the main menu.
	 * @param save Whether to save before exiting.
	 */
	public void exitLevel(boolean save);
	
	/**
	 * Returns true if the client is connected to an OpenClassic server.
	 * @return True if the server is an OpenClassic server.
	 */
	public boolean isConnectedToOpenClassic();
	
	/**
	 * Gets the version of the OpenClassic server this client is connected to, if applicable.
	 * @return The server's version
	 */
	public String getServerVersion();
	
	/**
	 * Gets the client's progress bar display.
	 * @return The client's progress bar display.
	 */
	public ProgressBar getProgressBar();
	
}
