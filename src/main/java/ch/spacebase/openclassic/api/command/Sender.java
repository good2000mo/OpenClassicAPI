package ch.spacebase.openclassic.api.command;

/**
 * Represents a command sender.
 */
public interface Sender {

	/**
	 * Sends a message to the command sender.
	 * @param message Message to send.
	 */
	public void sendMessage(String message);
	
	/**
	 * Gets the name of the sender.
	 * @return The sender's name.
	 */
	public String getName();
	
	/**
	 * Gets the sender's display name.
	 * @return The sender's display name.
	 */
	public String getDisplayName();
	
	/**
	 * Checks whether the sender has the given permssion.
	 * @param permission Permission to check for.
	 * @return True if the sender has the permission.
	 */
	public boolean hasPermission(String permission);
	
	/**
	 * Gets the command prefix for this sender.
	 * @return The command prefix.
	 */
	public String getCommandPrefix();
	
	/**
	 * Gets the sender's language.
	 * @return The sender's language.
	 */
	public String getLanguage();
	
}
