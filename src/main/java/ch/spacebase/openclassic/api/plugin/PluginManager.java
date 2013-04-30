package ch.spacebase.openclassic.api.plugin;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

import org.yaml.snakeyaml.Yaml;

import ch.spacebase.openclassic.api.Client;
import ch.spacebase.openclassic.api.Color;
import ch.spacebase.openclassic.api.OpenClassic;
import ch.spacebase.openclassic.api.Server;
import ch.spacebase.openclassic.api.event.EventFactory;
import ch.spacebase.openclassic.api.event.Listener;
import ch.spacebase.openclassic.api.event.plugin.PluginDisableEvent;
import ch.spacebase.openclassic.api.event.plugin.PluginEnableEvent;
import ch.spacebase.openclassic.api.util.io.JarFilter;

/**
 * Manages the server's plugins.
 */
public class PluginManager {
	
	private List<Plugin> plugins = new ArrayList<Plugin>();
	private Map<Listener, Plugin> listeners = new HashMap<Listener, Plugin>();

	/**
	 * Loads plugins with the specified load order step.
	 * @param prder Load order to load for.
	 */
	public void loadPlugins(LoadOrder order) {
		File plugins = new File(OpenClassic.getGame().getDirectory(), "plugins");
		if(!plugins.exists()) plugins.mkdirs();
		
		File jars[] = plugins.listFiles(new JarFilter());
		
		for(File file : jars) {
			PluginDescription description = this.getDescription(file);
			if(description == null) continue;
			
			if(description.getLoadOrder() == order) {
				this.loadPlugin(file, description);
			}
		}
		
		if(order == LoadOrder.POSTWORLD) OpenClassic.getLogger().info(this.plugins.size() + " plugin(s) loaded!");
	}
	
	/**
	 * Loads a plugin from the given file if the file is a plugin.
	 * @param file File to load from.
	 */
	public void loadPlugin(File file) {
		PluginDescription description = this.getDescription(file);
		if(description == null) return;
		
		this.loadPlugin(file, description);
	}
	
	/**
	 * Loads a plugin from the given file using the given description.
	 * @param file File to load from.
	 * @param description PluginDescription to use.
	 */
	public void loadPlugin(File file, PluginDescription description) {
		URL url = null;
		
		try {
			url = file.toURI().toURL();
		} catch (MalformedURLException e) {
			OpenClassic.getLogger().severe("Malformed plugin jar URL!");
			e.printStackTrace();
			return;
		}
		
		try {
			OpenClassic.getLogger().info(Color.GREEN + "Loading " + description.getFullName() + "...");
			
			URLClassLoader loader = new URLClassLoader(new URL[] { url }, Thread.currentThread().getContextClassLoader());
			
	        Class<?> jarClass = Class.forName(description.getMainClass(), true, loader);
	        Class<? extends Plugin> plugin = jarClass.asSubclass(Plugin.class);
	
	        Constructor<? extends Plugin> constructor = plugin.getConstructor();
	
	        if(ClientPlugin.class.isAssignableFrom(plugin) && !(OpenClassic.getGame() instanceof Client)) {
	        	OpenClassic.getLogger().info(Color.RED + "Plugin " + description.getFullName() + " is client-only. Skipping...");
	        	return;
	        }
	        
	        if(ServerPlugin.class.isAssignableFrom(plugin) && !(OpenClassic.getGame() instanceof Server)) {
	        	OpenClassic.getLogger().info(Color.RED + "Plugin " + description.getFullName() + " is server-only. Skipping...");
	        	return;
	        }
	        
	        Plugin p = constructor.newInstance();
	        p.init(description);
	        p.onLoad();
	        
	        boolean matched = true;
	        for(String dependency : p.getDescription().getDependencies()) {
	        	if(!this.isPluginEnabled(dependency)) matched = false;
	        }
	        
	        if(matched) {
	        	this.enablePlugin(p);
	        }
	        
	        this.plugins.add(p);
		} catch(Exception e) {
			OpenClassic.getLogger().severe("Failed to load plugin from file " + file.getName() + "!");
			e.printStackTrace();
		}
	}
	
	/**
	 * Enables the plugin and any plugins that now have their dependencies met.
	 * @param plugin Plugin to enable.
	 */
	public void enablePlugin(Plugin plugin) {
		if(plugin.isEnabled()) return;
		
		OpenClassic.getLogger().info(Color.GREEN + "Enabling " + plugin.getDescription().getFullName() + "...");
		
		plugin.setEnabled(true);
		plugin.onEnable();
		
		OpenClassic.getLogger().info(plugin.getDescription().getFullName() + " has been enabled!");
		EventFactory.callEvent(new PluginEnableEvent(plugin));
		
		for(Plugin p : this.plugins) {
			if(!p.isEnabled()) {
				List<String> deps = Arrays.asList(p.getDescription().getDependencies());
				if(deps.contains(plugin.getDescription().getName())) {
					boolean matched = true;
					
					for(String dep : deps) {
						if(!this.isPluginEnabled(dep)) matched = false;
					}
					
					if(matched) this.enablePlugin(p);
				}
			}
		}
	}
	
	/**
	 * Disables all plugins.
	 */
	public void disablePlugins() {
		for(Plugin plugin : this.plugins) {
			this.disablePlugin(plugin);
		}
	}
	
	/**
	 * Disables the plugin and any plugins that require it.
	 * @param plugin Plugin to disable.
	 */
	public void disablePlugin(Plugin plugin) {
		if(!plugin.isEnabled()) return;
		
		OpenClassic.getLogger().info(Color.GREEN + "Disabling " + plugin.getDescription().getFullName() + "...");
		plugin.setEnabled(false);
		plugin.onDisable();
		for(Listener listener : this.listeners.keySet()) {
			if(this.listeners.get(listener).getDescription().getName().equals(plugin.getDescription().getName())) {
				this.listeners.remove(listener);
			}
		}
		
		OpenClassic.getGame().unregisterCommands(plugin);
		OpenClassic.getGame().unregisterExecutors(plugin);
		
		OpenClassic.getLogger().info(plugin.getDescription().getFullName() + " has been disabled!");
		EventFactory.callEvent(new PluginDisableEvent(plugin));
		
		for(Plugin p : this.plugins) {
			if(p.isEnabled()) {
				List<String> deps = Arrays.asList(p.getDescription().getDependencies());
				if(deps.contains(plugin.getDescription().getName())) {		
					this.disablePlugin(p);
				}
			}
		}
	}
	
	/**
	 * Removes the plugin instance from the plugin list.
	 * @param plugin Plugin to remove.
	 */
	public void removePlugin(Plugin plugin) {
		this.plugins.remove(plugin);
	}
	
	/**
	 * Clears the plugin list.
	 */
	public void clearPlugins() {
		this.plugins.clear();
	}
	
	/**
	 * Gets the plugin with the given name.
	 * @param name Name of the plugin.
	 * @return The plugin.
	 */
	public Plugin getPlugin(String name) {
		for(Plugin plugin : this.plugins) {
			if(plugin.getDescription().getName().equalsIgnoreCase(name)) return plugin;
		}
		
		return null;
	}
	
	/**
	 * Gets the plugin which the listener belongs to, if it is registered.
	 * @param listen Listener that belongs to the plugin.
	 * @return The plugin it belongs to.
	 */
	public Plugin getPlugin(Listener listen) {
		return this.listeners.get(listen);
	}
	
	/**
	 * Gets a list of all the loaded (not necessarily enabled) plugins.
	 * @return A list of loaded plugins.
	 */
	public List<Plugin> getPlugins() {
		return new ArrayList<Plugin>(this.plugins);
	}
	
	/**
	 * Returns true if the plugin is enabled.
	 * @param name Plugin to check for.
	 * @return True if the plugin is enabled.
	 */
	public boolean isPluginEnabled(String name) {
		return this.getPlugin(name) != null && this.getPlugin(name).isEnabled();
	}
	
	/**
	 * Loads the PluginDescription from the given file (must be a plugin jar)
	 * @param file File to load the description from.
	 * @return The loaded plugin description.
	 */
	@SuppressWarnings("unchecked")
	public PluginDescription getDescription(File file) {
		if(file == null) return null;
		
        JarFile jar = null;

        try {
            jar = new JarFile(file);
            JarEntry entry = jar.getJarEntry("plugin.yml");

            if (entry == null) {
                OpenClassic.getLogger().severe("Plugin in file " + file.getName() + " does not contain a plugin.yml!");
                return null;
            }

            Map<String, Object> yml = (Map<String, Object>) (new Yaml()).load(jar.getInputStream(entry));
            
            return new PluginDescription(String.valueOf(yml.get("name")), String.valueOf(yml.get("version")), String.valueOf(yml.get("main-class")), String.valueOf(yml.get("depends")), String.valueOf(yml.get("order")));
        } catch (Exception e) {
            OpenClassic.getLogger().severe("Failed to load plugin description!");
            e.printStackTrace();
            return null;
        } finally {
            if (jar != null) {
                try {
                    jar.close();
                } catch (IOException e) {
                    OpenClassic.getLogger().severe("Failed to close jar after getting plugin description!");
                    e.printStackTrace();
                }
            }
        }
	}
	
	/**
	 * Gets a list of all registered listeners.
	 * @return A list of all listeners.
	 */
	public Collection<Listener> getListeners() {
		return this.listeners.keySet();
	}
	
	/**
	 * Gets a list of all listeners registered to the given plugin.
	 * @param plugin Plugin to get listeners for.
	 * @return Listeners registered to the plugin.
	 */
	public List<Listener> getListeners(String plugin) {
		List<Listener> result = new ArrayList<Listener>();
		
		for(Listener listener : this.listeners.keySet()) {
			if(this.listeners.get(listener).getDescription().getName().equals(plugin)) {
				result.add(listener);
			}
		}
		
		return result;
	}
	
	/**
	 * Registers a listener to the plugin.
	 * @param listener Listener to register.
	 * @param plugin Plugin the listener belongs to.
	 */
	public void registerListener(Listener listener, Plugin plugin) {
		this.listeners.put(listener, plugin);
	}
	
	/**
	 * Represents when in the loading process a plugin should be loaded.
	 */
	public enum LoadOrder {
		/** Load before worlds are loaded */
		PREWORLD,
		/** Load after worlds are loaded */
		POSTWORLD;
	}
	
}
