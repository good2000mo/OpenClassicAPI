package ch.spacebase.openclassic.api.event.plugin;

import ch.spacebase.openclassic.api.event.Event;
import ch.spacebase.openclassic.api.plugin.Plugin;

/**
 * Represents an event involving a plugin.
 */
public abstract class PluginEvent extends Event {

	private Plugin plugin;
	
	public PluginEvent(EventType type, Plugin plugin) {
		super(type);
		this.plugin = plugin;
	}
	
	/**
	 * Gets the plugin involved in this event.
	 * @return The plugin in the event.
	 */
	public Plugin getPlugin() {
		return this.plugin;
	}
	
}
