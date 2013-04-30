package ch.spacebase.openclassic.api.network.msg.custom;

import ch.spacebase.openclassic.api.network.msg.Message;

/**
 * Contains info about a custom client.
 */
public class GameInfoMessage extends Message {

	private String version;
	private String language;
	
	public GameInfoMessage(String version, String language) {
		this.version = version;
		this.language = language;
	}
	
	/**
	 * Gets the version of the client.
	 * @return The client's version.
	 */
	public String getVersion() {
		return this.version;
	}
	
	/**
	 * Gets the language setting of the client.
	 * @return The client's language setting.
	 */
	public String getLanguage() {
		return this.language;
	}

	@Override
	public Object[] getParams() {
		return new Object[] { this.version };
	}
	
	@Override
	public String toString() {
		return "ClientInfoMessage{version=" + version + ",language=" + language + "}";
	}
	
	@Override
	public byte getOpcode() {
		return 16;
	}
	
}
