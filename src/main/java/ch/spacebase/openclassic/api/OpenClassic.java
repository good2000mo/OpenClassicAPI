package ch.spacebase.openclassic.api;

import java.util.logging.Logger;

/**
 * The central class of OpenClassic.
 */
public class OpenClassic {

	private static final Logger logger = Logger.getLogger("OpenClassic");
	private static Client client;
	private static Server server;
	
	/**
	 * Gets the current game instance.
	 * @return The game instance.
	 */
	public static Game getGame() {
		if(Thread.currentThread().getName().contains("Client")) {
			return client;
		} else {
			return server;
		}
	}
	
	/**
	 * Gets the current server instance.
	 * @return The server instance (null if the server instance doesn't exist).
	 */
	public static Server getServer() {
		return server;
	}
	
	/**
	 * Gets the current client instance.
	 * @return The client instance (null if the client instance doesn't exist).
	 */
	public static Client getClient() {
		return client;
	}
	
	/**
	 * Sets the current client instance.
	 * @param client The client instance.
	 */
	public static void setClient(Client client) {
		if(OpenClassic.client != null && client != null) return;
		OpenClassic.client = client;
	}
	
	/**
	 * Sets the current server instance.
	 * @param server The server instance.
	 */
	public static void setServer(Server server) {
		if(OpenClassic.server != null && server != null) return;
		OpenClassic.server = server;
	}
	
	/**
	 * Returns true if the game is running.
	 * @return True if the game is running.
	 */
	public static boolean isRunning() {
		if(Thread.currentThread().getName().contains("Client")) {
			if(client == null) return false;
			return client.isRunning();
		} else {
			if(server == null) return false;
			return server.isRunning();
		}
	}
	
	/**
	 * Gets OpenClassic's logger.
	 * @return The logger.
	 */
	public static Logger getLogger() {
		return logger;
	}
	
	private OpenClassic() {
	}
	
}
