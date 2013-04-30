package ch.spacebase.openclassic.api.network.msg.custom;

import java.util.Arrays;

import ch.spacebase.openclassic.api.network.msg.Message;

/**
 * A message sent by a plugin containing custom data.
 */
public class CustomMessage extends Message {

	private String id;
	private byte data[];
	
	public CustomMessage(String id, byte data[]) {
		this.id = id;
		this.data = data;
	}
	
	/**
	 * Gets the ID of this message.
	 * @return The message's ID.
	 */
	public String getId() {
		return this.id;
	}

	
	/**
	 * Gets the data sent in this message.
	 * @return The message's data.
	 */
	public byte[] getData() {
		return this.data;
	}

	@Override
	public Object[] getParams() {
		return new Object[] { this.id, this.data, this.data.length };
	}
	
	@Override
	public String toString() {
		return "CustomMessage{id=" + id + ",data=" + Arrays.toString(data) + "}";
	}
	
	@Override
	public byte getOpcode() {
		return 26;
	}
	
}
