package ch.spacebase.openclassic.api.network.msg.custom;

import ch.spacebase.openclassic.api.network.msg.Message;

/**
 * Contains info about an installed plugin.
 */
public class PluginMessage extends Message {

	private String name;
	private String version;
	
	public PluginMessage(String name, String version) {
		this.name = name;
		this.version = version;
	}
	
	/**
	 * Gets the name of the plugin.
	 * @return The plugin's name.
	 */
	public String getName() {
		return this.name;
	}

	
	/**
	 * Gets the version of the plugin.
	 * @return The plugin's version.
	 */
	public String getVersion() {
		return this.version;
	}

	@Override
	public Object[] getParams() {
		return new Object[] { this.name, this.version };
	}
	
	@Override
	public String toString() {
		return "PluginMessage{name=" + name + ",version=" + version + "}";
	}
	
	@Override
	public byte getOpcode() {
		return 25;
	}
	
}
