package ch.spacebase.openclassic.api.config;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;


import org.yaml.snakeyaml.DumperOptions;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.SafeConstructor;

import ch.spacebase.openclassic.api.OpenClassic;
import ch.spacebase.openclassic.api.math.MathHelper;

/**
 * Represents a YAML configuration.
 */
@SuppressWarnings("unchecked")
public class Configuration {

	protected Map<String, Object> data;
	protected Set<ConfigurationNode> nodes;
	
	private File configFile;
	private InputStream in;
	private Yaml yaml;

	public Configuration(InputStream in) {
		this.data = new HashMap<String, Object>();
		this.nodes = new HashSet<ConfigurationNode>();
		this.in = in;
		
		DumperOptions options = new DumperOptions();

		options.setIndent(4);
		options.setDefaultFlowStyle(DumperOptions.FlowStyle.BLOCK);

		this.yaml = new Yaml(new SafeConstructor(), new EmptyNullRepresenter(), options);
	}
	
	public Configuration(File file) {
		this.data = new HashMap<String, Object>();
		this.nodes = new HashSet<ConfigurationNode>();
		this.configFile = file;
		
		DumperOptions options = new DumperOptions();

		options.setIndent(4);
		options.setDefaultFlowStyle(DumperOptions.FlowStyle.BLOCK);

		this.yaml = new Yaml(new SafeConstructor(), new EmptyNullRepresenter(), options);
	}

	/**
	 * Loads the configuration.
	 */
	public void load() {
		InputStream in = null;
		if(this.in != null) {
			in = this.in;
		} else {		
			try {
				if (!this.configFile.exists()) {
					if(this.configFile.getParentFile() != null) this.configFile.getParentFile().mkdirs();
					this.configFile.createNewFile();
				}
	
				in = new FileInputStream(this.configFile);
			} catch (IOException e) {
				OpenClassic.getLogger().severe("Failed to load config file " + this.configFile.getName() + "!");
				e.printStackTrace();
				return;
			}
		}
		
		this.data = (Map<String, Object>) this.yaml.load(in);
		if(this.data == null) this.data = new HashMap<String, Object>();
	}

	/**
	 * Saves the configuration.
	 */
	public void save() {
		if(this.configFile != null) {
			try {
				if (!this.configFile.exists()) {
					if(this.configFile.getParentFile() != null) this.configFile.getParentFile().mkdirs();
					this.configFile.createNewFile();
				}
	
				FileOutputStream out = new FileOutputStream(configFile);
				OutputStreamWriter writer = new OutputStreamWriter(out, "UTF-8");
	
				for (ConfigurationNode node : nodes) {
					node.setConfiguration(this);
				}
				
				this.yaml.dump(data, writer);
			} catch (IOException e) {
				OpenClassic.getLogger().severe("Failed to save config file " + this.configFile.getName() + "!");
				e.printStackTrace();
			}
		}
	}

	/**
	 * Gets the value at the given path.
	 * @param path Path of the value.
	 * @return The value.
	 */
	public Object getValue(String path) {
		if(path.equals("")) return new HashMap<String, Object>(this.data);
		
		if (!path.contains(".")) {
			Object value = this.data.get(path);
			return value;
		}

		String[] parts = path.split("\\.");
		Map<String, Object> node = this.data;

		for (int index = 0; index < parts.length; index++) {
			Object obj = node.get(parts[index]);

			if (obj == null) {
				return null;
			}

			if (index == parts.length - 1) {
				return obj;
			}

			try {
				node = (Map<String, Object>) obj;
			} catch (ClassCastException e) {
				return null;
			}
		}

		return null;
	}

	/**
	 * Sets the value of the given path.
	 * @param path Path to set the value of.
	 * @param value Value to set.
	 */
	public void setValue(String path, Object value) {
		if (!path.contains(".")) {
			data.put(path, value);
			return;
		}

		String[] parts = path.split("\\.");
		Map<String, Object> node = data;

		for (int index = 0; index < parts.length; index++) {
			Object obj = node.get(parts[index]);

			if (index == parts.length - 1) {
				node.put(parts[index], value);
				return;
			}

			if (obj == null || !(obj instanceof Map)) {
				obj = new HashMap<String, Object>();
				node.put(parts[index], obj);
			}

			node = (Map<String, Object>) obj;
		}
	}
	
	/**
	 * Applies the given default to the path if the path's value is null.
	 * @param path Path to apply the default to.
	 * @param def Default to apply.
	 */
	public void applyDefault(String path, Object def) {
		if(this.getValue(path) == null) this.setValue(path, def);
	}
	
	/**
	 * Removes the node at the given path.
	 * @param path Path to remove from.
	 */
	public void remove(String path) {
		this.removeNode(path);
		
		if (!path.contains(".")) {
			data.remove(path);
			return;
		}

		String[] parts = path.split("\\.");
		Map<String, Object> node = data;

		for (int index = 0; index < parts.length; index++) {
			Object obj = node.get(parts[index]);

			if (index == parts.length - 1) {
				node.remove(parts[index]);
				return;
			}

			if (obj == null || !(obj instanceof Map)) {
				return;
			}

			node = (Map<String, Object>) obj;
		}
	}

	/**
	 * Gets all the nodes belonging to this configuration.
	 * @return All the configuration's nodes.
	 */
	public Set<ConfigurationNode> getNodes() {
		return new HashSet<ConfigurationNode>(nodes);
	}

	/**
	 * Removes the node at the given path.
	 * @param path Path to remove from.
	 */
	public void removeNode(String path) {
		synchronized(nodes) {
			for (ConfigurationNode node : nodes) {
				if (node.getPath().equalsIgnoreCase(path)) {
					nodes.remove(node);
				}
			}
		}
	}

	/**
	 * Adds a node.
	 * @param node Node to add.
	 */
	public void addNode(ConfigurationNode node) {
		Object value = this.getValue(node.getPath());
		if (value == null) {
			this.setValue(node.getPath(), node.getValue(false));
		} else {
			node.setValue(value, false);
		}

		nodes.add(node);
		node.config = this;
	}

	/**
	 * Adds a node with the given path and value.
	 * @param path Path of the node.
	 * @param value Value of the node.
	 * @return The newly added node.
	 */
	public ConfigurationNode addNode(String path, Object value) {
		ConfigurationNode node = new ConfigurationNode(path, value);
		node.setConfiguration(this);
		return node;
	}

	/**
	 * Adds multiple nodes.
	 * @param nodes Nodes to add.
	 */
	public void addNodes(ConfigurationNode... nodes) {
		for (ConfigurationNode node : nodes) {
			node.setConfiguration(this);
		}
	}

	/**
	 * Gets the node at the given path.
	 * @param path Path of the node.
	 * @return The node.
	 */
	public ConfigurationNode getNode(String path) {
		return this.getNode(path, null);
	}
	
	/**
	 * Gets a node at the given path, assigning the given default if it isn't found.
	 * @param path Path of the node.
	 * @param def The default to use if it isn't found.
	 * @return The node.
	 */
	public ConfigurationNode getNode(String path, Object def) {
		ConfigurationNode node = new ConfigurationNode(path, def);
		Object value = this.getValue(node.getPath());
		if (value == null) {
			if(def == null) {
				return null;
			}
			
			value = def;
		}

		node.setValue(value, true);
		node.setConfiguration(this);
		return node;
	}

	/**
	 * Gets the string value of the given path.
	 * @param path Path of the value.
	 * @return The path's string value.
	 */
	public String getString(String path) {
		return this.getString(path, "");
	}

	/**
	 * Gets the string value of the given path.
	 * @param path Path of the value.
	 * @param def Default for if the value isn't assigned.
	 * @return The path's string value.
	 */
	public String getString(String path, String def) {
		Object value = this.getValue(path);

		if (value instanceof String) {
			return (String) value;
		}

		if (value == null) {
			this.setValue(path, def);
			save();
		}

		return def;
	}

	/**
	 * Gets the integer value of the given path.
	 * @param path Path of the value.
	 * @return The path's integer value.
	 */
	public int getInteger(String path) {
		return this.getInteger(path, 0);
	}

	/**
	 * Gets the integer value of the given path.
	 * @param path Path of the value.
	 * @param def Default for if the value isn't assigned.
	 * @return The path's integer value.
	 */
	public int getInteger(String path, int def) {
		Object value = this.getValue(path);
		Integer intValue = MathHelper.castInt(this.getValue(path));

		if (intValue != null) {
			return intValue;
		}

		if (value == null) {
			this.setValue(path, def);
			save();
		}

		return def;
	}

	/**
	 * Gets the double value of the given path.
	 * @param path Path of the value.
	 * @return The path's double value.
	 */
	public double getDouble(String path) {
		return this.getDouble(path, 0);
	}

	/**
	 * Gets the double value of the given path.
	 * @param path Path of the value.
	 * @param def Default for if the value isn't assigned.
	 * @return The path's double value.
	 */
	public double getDouble(String path, double def) {
		Object value = this.getValue(path);
		Double doubleValue = MathHelper.castDouble(value);

		if (doubleValue != null) {
			return doubleValue;
		}

		if (value == null) {
			this.setValue(path, def);
			save();
		}

		return def;
	}

	/**
	 * Gets the float value of the given path.
	 * @param path Path of the value.
	 * @return The path's float value.
	 */
	public float getFloat(String path) {
		return this.getFloat(path, 0);
	}

	/**
	 * Gets the float value of the given path.
	 * @param path Path of the value.
	 * @param def Default for if the value isn't assigned.
	 * @return The path's float value.
	 */
	public float getFloat(String path, float def) {
		Object value = this.getValue(path);
		Float floatValue = MathHelper.castFloat(value);

		if (floatValue != null) {
			return floatValue;
		}

		if (value == null) {
			this.setValue(path, def);
			save();
		}

		return def;
	}

	/**
	 * Gets the boolean value of the given path.
	 * @param path Path of the value.
	 * @return The path's boolean value.
	 */
	public boolean getBoolean(String path) {
		return this.getBoolean(path, false);
	}

	/**
	 * Gets the boolean value of the given path.
	 * @param path Path of the value.
	 * @param def Default for if the value isn't assigned.
	 * @return The path's boolean value.
	 */
	public boolean getBoolean(String path, boolean def) {
		Object value = this.getValue(path);
		Boolean booleanValue = MathHelper.castBoolean(value);

		if (booleanValue != null) {
			return booleanValue;
		}

		if (value == null) {
			this.setValue(path, def);
			save();
		}

		return def;
	}

	/**
	 * Gets the List value of the given path.
	 * @param path Path of the value.
	 * @return The path's List value.
	 */
	public List<Object> getList(String path) {
		return this.getList(path, null);
	}

	/**
	 * Gets the List value of the given path.
	 * @param path Path of the value.
	 * @param def Default for if the value isn't assigned.
	 * @return The path's List value.
	 */
	public List<Object> getList(String path, List<Object> def) {
		Object value = this.getValue(path);

		if (value != null && value instanceof List) {
			return (List<Object>) value;
		}

		if (value == null) {
			this.setValue(path, def);
			save();
		}

		return def;
	}

	public List<String> getStringList(String path) {
		return this.getStringList(path, null);
	}

	/**
	 * Gets the List<String> value of the given path.
	 * @param path Path of the value.
	 * @param def Default for if the value isn't assigned.
	 * @return The path's List<String> value.
	 */
	public List<String> getStringList(String path, List<String> def) {
		List<Object> raw = this.getList(path);

		if (raw != null) {
			List<String> list = new ArrayList<String>();

			for (Object obj : raw) {
				list.add(obj.toString());
			}

			return list;
		}
		
		this.setValue(path, def);
		save();
		
		return def;
	}

	/**
	 * Gets the List<Integer> value of the given path.
	 * @param path Path of the value.
	 * @return The path's List<Integer> value.
	 */
	public List<Integer> getIntegerList(String path) {
		return this.getIntegerList(path, null);
	}

	/**
	 * Gets the List<Integer> value of the given path.
	 * @param path Path of the value.
	 * @param def Default for if the value isn't assigned.
	 * @return The path's List<Integer> value.
	 */
	public List<Integer> getIntegerList(String path, List<Integer> def) {
		List<Object> raw = this.getList(path);

		if (raw != null) {
			List<Integer> list = new ArrayList<Integer>();

			for (Object obj : raw) {
				Integer integerValue = MathHelper.castInt(obj);

				list.add(integerValue);
			}

			return list;
		}
		
		this.setValue(path, def);
		save();

		return def;
	}

	/**
	 * Gets the List<Double> value of the given path.
	 * @param path Path of the value.
	 * @return The path's List<Double> value.
	 */
	public List<Double> getDoubleList(String path) {
		return this.getDoubleList(path, null);
	}

	/**
	 * Gets the List<Double> value of the given path.
	 * @param path Path of the value.
	 * @param def Default for if the value isn't assigned.
	 * @return The path's List<Double> value.
	 */
	public List<Double> getDoubleList(String path, List<Double> def) {
		List<Object> raw = this.getList(path);

		if (raw != null) {
			List<Double> list = new ArrayList<Double>();

			for (Object obj : raw) {
				Double doubleValue = MathHelper.castDouble(obj);

				list.add(doubleValue);
			}

			return list;
		}
		
		this.setValue(path, def);
		save();

		return def;
	}

	/**
	 * Gets the List<Float> value of the given path.
	 * @param path Path of the value.
	 * @return The path's List<Float> value.
	 */
	public List<Float> getFloatList(String path) {
		return this.getFloatList(path, null);
	}

	/**
	 * Gets the List<Float> value of the given path.
	 * @param path Path of the value.
	 * @param def Default for if the value isn't assigned.
	 * @return The path's List<Float> value.
	 */
	public List<Float> getFloatList(String path, List<Float> def) {
		List<Object> raw = this.getList(path);

		if (raw != null) {
			List<Float> list = new ArrayList<Float>();

			for (Object obj : raw) {
				Float floatValue = MathHelper.castFloat(obj);

				list.add(floatValue);
			}

			return list;
		}
		
		this.setValue(path, def);
		save();

		return def;
	}

	/**
	 * Gets the List<Boolean> value of the given path.
	 * @param path Path of the value.
	 * @return The path's List<Boolean> value.
	 */
	public List<Boolean> getBooleanList(String path) {
		return this.getBooleanList(path, null);
	}

	/**
	 * Gets the List<Boolean> value of the given path.
	 * @param path Path of the value.
	 * @param def Default for if the value isn't assigned.
	 * @return The path's List<Boolean> value.
	 */
	public List<Boolean> getBooleanList(String path, List<Boolean> def) {
		List<Object> raw = this.getList(path);

		if (raw != null) {
			List<Boolean> list = new ArrayList<Boolean>();

			for (Object obj : raw) {
				Boolean booleanValue = MathHelper.castBoolean(obj);

				list.add(booleanValue);
			}

			return list;
		}
		
		this.setValue(path, def);
		save();

		return def;
	}

	/**
	 * Gets all the keys at the given path.
	 * @param path Path to look at.
	 * @return Keys at the path.
	 */
	public Set<String> getKeys(String path) {
		Set<String> keys = new HashSet<String>();
		String[] sections = path.split("\\.");
		Map<String, Object> section = this.data;

		for (int index = 0; index < sections.length && section != null; index++) {
			String sec = sections[index];

			try {
				section = (Map<String, Object>) section.get(sec);
			} catch (Exception e) {
				OpenClassic.getLogger().warning("[Configuration] Invalid path!");
				e.printStackTrace();
			}
		}

		if (section != null) {
			keys = section.keySet();
		}

		return keys;
	}
	
	/**
	 * Gets all of the configuration's data.
	 * @return The configuration's data.
	 */
	public Map<String, Object> getData() {
		return new HashMap<String, Object>(this.data);
	}
	
	/**
	 * Returns true if this configuration contains the given key.
	 * @return True if the configuration contains the given key.
	 */
	public boolean contains(String key) {
		return this.getValue(key) != null;
	}

}
