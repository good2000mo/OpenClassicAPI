package ch.spacebase.openclassic.api.config;

import java.util.ArrayList;
import java.util.List;

import ch.spacebase.openclassic.api.math.MathHelper;

/**
 * Represents a node at a configuration path.
 */
@SuppressWarnings("unchecked")
public class ConfigurationNode {

	private final String path;
	protected Configuration config;
	private Object value;
	private Object def;

	public ConfigurationNode(String path, Object def) {
		this.path = path;
		this.def = def;
		this.value = def;
	}

	/**
	 * Gets the path of this node.
	 * @return The path of this node.
	 */
	public String getPath() {
		return path;
	}

	/**
	 * Gets the value of this node.
	 * @return The node's value.
	 */
	public Object getValue() {
		return this.getValue(true);
	}
	
	/**
	 * Gets the value of this node, falling back to the node's default if needed.
	 * @param useDefIfNeeded Whether to use the default value if needed.
	 * @return The node's value.
	 */
	public Object getValue(boolean useDefIfNeeded) {
		if (value != null) {
			return value;
		}

		if(useDefIfNeeded) this.setValue(def, true);
		return def;
	}

	/**
	 * Sets the node's value.
	 * @param value The value to set.
	 * @param toConfig Whether to apply it to the parent configuration.
	 */
	public void setValue(Object value, boolean toConfig) {
		this.value = value;
		if (this.config != null && toConfig) {
			this.config.addNode(this);
		}
	}

	/**
	 * Sets the node's default value.
	 * @param def Default value to set.
	 */
	public void setDefaultValue(Object def) {
		this.def = def;
		
		if (this.value == null) {
			this.value = def;
		}
	}

	/**
	 * Sets the configuration this node belongs to.
	 * @param config Configuration this node belongs to.
	 */
	public void setConfiguration(Configuration config) {
		config.addNode(this);
	}

	/**
	 * Gets this node's value as a string.
	 * @return This node's string value.
	 */
	public String getString() {
		return this.getString(null);
	}

	/**
	 * Gets this node's value as a string.
	 * @param def A default value for if the value is null.
	 * @return This node's string value.
	 */
	public String getString(String def) {

		if (this.value == null) {
			this.setValue(def, true);
		} else {
			return value.toString();
		}
		return def;
	}

	/**
	 * Gets this node's value as an integer.
	 * @return This node's integer value.
	 */
	public int getInteger() {
		return this.getInteger(0);
	}

	/**
	 * Gets this node's value as an integer.
	 * @param def Default if the value isn't set.
	 * @return This node's integer value.
	 */
	public int getInteger(int def) {
		Integer i = MathHelper.castInt(value);
		if (i != null) {
			return i;
		}

		if(this.value == null) {
			this.setValue(def, true);
		}
		return def;
	}

	/**
	 * Gets this node's value as a double.
	 * @return This node's double value.
	 */
	public double getDouble() {
		return this.getDouble(0);
	}

	/**
	 * Gets this node's value as a double.
	 * @param def Default if the value isn't set.
	 * @return This node's double value.
	 */
	public double getDouble(double def) {
		Double d = MathHelper.castDouble(value);
		if (d != null) {
			return d;
		}

		if (this.value == null) {
			this.setValue(def, true);
		}
		return def;
	}
	
	/**
	 * Gets this node's value as a float.
	 * @return This node's float value.
	 */
	public float getFloat() {
		return this.getFloat(0);
	}

	/**
	 * Gets this node's value as a float.
	 * @param def Default if the value isn't set.
	 * @return This node's float value.
	 */
	public float getFloat(float def) {
		Float f = MathHelper.castFloat(value);
		if (f != null) {
			return f;
		}

		if (this.value == null) {
			this.setValue(def, true);
		}
		return def;
	}

	/**
	 * Gets this node's value as a boolean.
	 * @return This node's boolean value.
	 */
	public boolean getBoolean() {
		return this.getBoolean(false);
	}

	/**
	 * Gets this node's value as a boolean.
	 * @param def Default if the value isn't set.
	 * @return This node's boolean value.
	 */
	public boolean getBoolean(boolean def) {
		Boolean b = MathHelper.castBoolean(value);
		if (b != null) {
			return b;
		}

		if (this.value == null) {
			this.setValue(def, true);
		}
		return def;
	}

	/**
	 * Gets this node's value as a List.
	 * @return This node's List value.
	 */
	public List<Object> getList() {
		return this.getList(null);
	}

	/**
	 * Gets this node's value as a List.
	 * @param def Default if the value isn't set.
	 * @return This node's List value.
	 */
	public List<Object> getList(List<Object> def) {
		if (value != null && value instanceof List) {
			return (List<Object>) value;
		}

		if (this.value == null) {
			this.setValue(def, true);
		}
		return def;
	}

	/**
	 * Gets this node's value as a List<String>.
	 * @return This node's List<String> value.
	 */
	public List<String> getStringList() {
		return this.getStringList(null);
	}

	/**
	 * Gets this node's value as a List<String>.
	 * @param def Default if the value isn't set.
	 * @return This node's List<String> value.
	 */
	public List<String> getStringList(List<String> def) {
		List<Object> raw = this.getList();
		if (raw != null) {
			List<String> list = new ArrayList<String>();
			for (Object obj : raw) {
				list.add(obj.toString());
			}

			return list;
		}

		if(this.value == null) {
			this.setValue(def, true);
		}
		
		return def;
	}

	/**
	 * Gets this node's value as a List<Integer>.
	 * @return This node's List<Integer> value.
	 */
	public List<Integer> getIntegerList() {
		return this.getIntegerList(null);
	}

	/**
	 * Gets this node's value as a List<Integer>.
	 * @param def Default if the value isn't set.
	 * @return This node's List<Integer> value.
	 */
	public List<Integer> getIntegerList(List<Integer> def) {
		List<Object> raw = this.getList();
		if (raw != null) {
			List<Integer> list = new ArrayList<Integer>();
			for (Object o : raw) {
				Integer i = MathHelper.castInt(o);
				if (i != null) {
					list.add(i);
				}
			}

			return list;
		}
		
		if(this.value == null) {
			this.setValue(def, true);
		}
		
		return def;
	}

	/**
	 * Gets this node's value as a List<Double>.
	 * @return This node's List<Double> value.
	 */
	public List<Double> getDoubleList() {
		return this.getDoubleList(null);
	}

	/**
	 * Gets this node's value as a List<Double>.
	 * @param def Default if the value isn't set.
	 * @return This node's List<Double> value.
	 */
	public List<Double> getDoubleList(List<Double> def) {
		List<Object> raw = this.getList();
		if (raw != null) {
			List<Double> list = new ArrayList<Double>();
			for (Object o : raw) {
				Double i = MathHelper.castDouble(o);
				if (i != null) {
					list.add(i);
				}
			}

			return list;
		}
		
		if(this.value == null) {
			this.setValue(def, true);
		}
		
		return def;
	}

	/**
	 * Gets this node's value as a List<Float>.
	 * @return This node's List<Float> value.
	 */
	public List<Float> getFloatList() {
		return this.getFloatList(null);
	}

	/**
	 * Gets this node's value as a List<Float>.
	 * @param def Default if the value isn't set.
	 * @return This node's List<Float> value.
	 */
	public List<Float> getFloatList(List<Float> def) {
		List<Object> raw = this.getList();
		if (raw != null) {
			List<Float> list = new ArrayList<Float>();
			for (Object o : raw) {
				Float f = MathHelper.castFloat(o);
				if (f != null) {
					list.add(f);
				}
			}

			return list;
		}
		
		if(this.value == null) {
			this.setValue(def, true);
		}
		
		return def;
	}
	
	/**
	 * Gets this node's value as a List<Boolean>.
	 * @return This node's List<Boolean> value.
	 */
	public List<Boolean> getBooleanList() {
		return this.getBooleanList(null);
	}

	/**
	 * Gets this node's value as a List<Boolean>.
	 * @param def Default if the value isn't set.
	 * @return This node's List<Boolean> value.
	 */
	public List<Boolean> getBooleanList(List<Boolean> def) {
		List<Object> raw = this.getList();
		if (raw != null) {
			List<Boolean> list = new ArrayList<Boolean>();
			for (Object o : raw) {
				Boolean b = MathHelper.castBoolean(o);
				if (b != null) {
					list.add(b);
				}
			}

			return list;
		}
		
		if(this.value == null) {
			this.setValue(def, true);
		}
		
		return def;
	}

}
