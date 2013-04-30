package ch.spacebase.openclassic.api.data;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import ch.spacebase.openclassic.api.OpenClassic;
import ch.spacebase.opennbt.stream.NBTInputStream;
import ch.spacebase.opennbt.stream.NBTOutputStream;
import ch.spacebase.opennbt.tag.*;
import ch.spacebase.opennbt.tag.custom.*;

/**
 * Represents an NBT data file.
 */
public class NBTData {

	private CompoundTag data;
	
	public NBTData(String name) {
		this.data = new CompoundTag(name);
	}
	
	/**
	 * Loads NBT data from the given file.
	 * @param file File to load from.
	 */
	public void load(String file) {
		File f = new File(file);
		if(!f.exists()) {
			if(!f.getParentFile().exists()) {
				f.getParentFile().mkdirs();
			}
			
			try {
				f.createNewFile();
			} catch (IOException e) {
				OpenClassic.getLogger().severe("Failed to create new file for NBTData " + this.data.getName() + "!");
				e.printStackTrace();
				return;
			}
			
			return;
		}
		
		NBTInputStream in = null;
		
		try {
			in = new NBTInputStream(new FileInputStream(f));
			CompoundTag tag = (CompoundTag) in.readTag();
			this.data.clear();
			
			for(String name : tag.keySet()) {
				this.data.put(name, tag.get(name));
			}
		} catch (EOFException e) {
			this.data.clear();
			return;
		} catch (IOException e) {
			OpenClassic.getLogger().severe("Failed to open stream for NBTData " + this.data.getName() + "!");
			e.printStackTrace();
			return;
		} finally {
			if(in != null) {
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	/**
	 * Saves the NBT data to the given file.
	 * @param file File to save to.
	 */
	public void save(String file) {
		File f = new File(file);
		if(!f.exists()) {
			if(!f.getParentFile().exists()) {
				f.getParentFile().mkdirs();
			}
			
			try {
				f.createNewFile();
			} catch (IOException e) {
				OpenClassic.getLogger().severe("Failed to create new file for NBTData " + this.data.getName() + "!");
				e.printStackTrace();
				return;
			}
		}
		
		NBTOutputStream out = null;
		
		try {
			out = new NBTOutputStream(new FileOutputStream(f));
			out.writeTag(this.data);
		} catch (IOException e) {
			OpenClassic.getLogger().severe("Failed to open stream for NBTData " + this.data.getName() + "!");
			e.printStackTrace();
			return;
		} finally {
			if(out != null) {
				try {
					out.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	/**
	 * Puts a tag with the given value and the given name.
	 * @param name Name to put.
	 * @param b Value to put.
	 * @return The resulting Tag.
	 */
	public Tag put(String name, byte b) {
		return this.data.put(name, new ByteTag(name, b));
	}
	
	/**
	 * Puts a tag with the given value and the given name.
	 * @param name Name to put.
	 * @param b Value to put.
	 * @return The resulting Tag.
	 */
	public Tag put(String name, byte b[]) {
		return this.data.put(name, new ByteArrayTag(name, b));
	}
	
	/**
	 * Puts a CompoundTag.
	 * @param compound Tag to put.
	 * @return The resulting Tag.
	 */
	public Tag put(CompoundTag compound) {
		return this.data.put(compound.getName(), compound);
	}
	
	/**
	 * Puts a tag with the given value and the given name.
	 * @param name Name to put.
	 * @param d Value to put.
	 * @return The resulting Tag.
	 */
	public Tag put(String name, double d) {
		return this.data.put(name, new DoubleTag(name, d));
	}
	
	/**
	 * Puts a tag with the given value and the given name.
	 * @param name Name to put.
	 * @param d Value to put.
	 * @return The resulting Tag.
	 */
	public Tag put(String name, double d[]) {
		return this.data.put(name, new DoubleArrayTag(name, d));
	}
	
	/**
	 * Puts an EndTag.
	 * @param tag Tag to put.
	 * @return The resulting Tag.
	 */
	public Tag put(EndTag tag) {
		return this.data.put(tag.getName(), tag);
	}
	
	/**
	 * Puts a tag with the given value and the given name.
	 * @param name Name to put.
	 * @param f Value to put.
	 * @return The resulting Tag.
	 */
	public Tag put(String name, float f) {
		return this.data.put(name, new FloatTag(name, f));
	}
	
	/**
	 * Puts a tag with the given value and the given name.
	 * @param name Name to put.
	 * @param f Value to put.
	 * @return The resulting Tag.
	 */
	public Tag put(String name, float f[]) {
		return this.data.put(name, new FloatArrayTag(name, f));
	}
	
	/**
	 * Puts a tag with the given value and the given name.
	 * @param name Name to put.
	 * @param i Value to put.
	 * @return The resulting Tag.
	 */
	public Tag put(String name, int i) {
		return this.data.put(name, new IntTag(name, i));
	}
	
	/**
	 * Puts a tag with the given value and the given name.
	 * @param name Name to put.
	 * @param i Value to put.
	 * @return The resulting Tag.
	 */
	public Tag put(String name, int i[]) {
		return this.data.put(name, new IntArrayTag(name, i));
	}
	
	/**
	 * Puts a ListTag with the given value, type, and name.
	 * @param name Name to put.
	 * @param clazz Type of the list.
	 * @param l List to put.
	 * @return The resulting Tag.
	 */
	public <T extends Tag> Tag put(String name, Class<T> clazz, List<T> l) {
		return this.data.put(name, new ListTag<T>(name, clazz, l));
	}
	
	/**
	 * Puts a tag with the given value and the given name.
	 * @param name Name to put.
	 * @param l Value to put.
	 * @return The resulting Tag.
	 */
	public Tag put(String name, long l) {
		return this.data.put(name, new LongTag(name, l));
	}
	
	/**
	 * Puts a tag with the given value and the given name.
	 * @param name Name to put.
	 * @param l Value to put.
	 * @return The resulting Tag.
	 */
	public Tag put(String name, long l[]) {
		return this.data.put(name, new LongArrayTag(name, l));
	}
	
	/**
	 * Puts a tag with the given value and the given name.
	 * @param name Name to put.
	 * @param s Value to put.
	 * @return The resulting Tag.
	 */
	public Tag put(String name, short s) {
		return this.data.put(name, new ShortTag(name, s));
	}
	
	/**
	 * Puts a tag with the given value and the given name.
	 * @param name Name to put.
	 * @param s Value to put.
	 * @return The resulting Tag.
	 */
	public Tag put(String name, short s[]) {
		return this.data.put(name, new ShortArrayTag(name, s));
	}
	
	/**
	 * Puts a tag with the given value and the given name.
	 * @param name Name to put.
	 * @param s Value to put.
	 * @return The resulting Tag.
	 */
	public Tag put(String name, String s) {
		return this.data.put(name, new StringTag(name, s));
	}
	
	/**
	 * Puts a tag with the given value and the given name.
	 * @param name Name to put.
	 * @param s Value to put.
	 * @return The resulting Tag.
	 */
	public Tag put(String name, String s[]) {
		return this.data.put(name, new StringArrayTag(name, s));
	}
	
	/**
	 * Puts a tag with the given value and the given name.
	 * @param name Name to put.
	 * @param o Value to put.
	 * @return The resulting Tag.
	 */
	public Tag put(String name, Object o) {
		return this.data.put(name, new ObjectTag(name, o));
	}
	
	/**
	 * Puts a tag with the given value and the given name.
	 * @param name Name to put.
	 * @param o Value to put.
	 * @return The resulting Tag.
	 */
	public Tag put(String name, Object o[]) {
		return this.data.put(name, new ObjectArrayTag(name, o));
	}
	
	/**
	 * Gets the Tag with the given name.
	 * @param name Name of the tag.
	 * @return The resulting Tag.
	 */
	public Tag get(String name) {
		return this.data.get(name);
	}
	
	/**
	 * Removes the Tag with the given name from the data.
	 * @param name Name of the tag.
	 * @return The removed tag.
	 */
	public Tag remove(String name) {
		return this.data.remove(name);
	}
	
	/**
	 * Gets the keys of the data.
	 * @return The data's keys.
	 */
	public Set<String> keySet() {
		return this.data.keySet();
	}
	
	/**
	 * Gets the Tags of the data.
	 * @return The data's Tags.
	 */
	public Collection<Tag> values() {
		return this.data.values();
	}
	
	/**
	 * Gets the size of the data.
	 * @return The data's size.
	 */
	public int size() {
		return this.data.size();
	}
	
	/**
	 * Clears the data.
	 */
	public void clear() {
		this.data.clear();
	}
	
}
