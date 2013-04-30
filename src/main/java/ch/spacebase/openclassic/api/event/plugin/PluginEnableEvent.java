package ch.spacebase.openclassic.api.event.plugin;

import ch.spacebase.openclassic.api.plugin.Plugin;

/**
 * Called when a plugin is enabled.
 */
public class PluginEnableEvent extends PluginEvent {

	public PluginEnableEvent(Plugin plugin) {
		super(EventType.PLUGIN_ENABLE, plugin);
	}
	
}
