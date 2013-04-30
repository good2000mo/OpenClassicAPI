package ch.spacebase.openclassic.api.command;

import ch.spacebase.openclassic.api.OpenClassic;

/**
 * Represents the console when it sends a command.
 */
public class Console implements Sender {

	@Override
	public void sendMessage(String message) {
		OpenClassic.getLogger().info(message);
	}

	@Override
	public String getName() {
		return "Server";
	}
	
	@Override
	public String getDisplayName() {
		return "Server";
	}
	
	@Override
	public boolean hasPermission(String permission) {
		return true;
	}
	
	@Override
	public String getCommandPrefix() {
		return "";
	}

	@Override
	public String getLanguage() {
		return OpenClassic.getGame().getLanguage();
	}
	
}
