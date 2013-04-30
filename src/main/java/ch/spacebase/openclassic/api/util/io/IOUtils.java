package ch.spacebase.openclassic.api.util.io;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

/**
 * A set of utilities for I/O interactions.
 */
public class IOUtils {

	/**
	 * Reads a string from a DataInputStream.
	 * @param in DataInputStream to read from.
	 * @return The string read from the stream.
	 * @throws IOException if an I/O error occurs.
	 */
	public static String readString(DataInputStream in) throws IOException {
		StringBuilder builder = new StringBuilder();
		
		for(int length = in.readShort(); length > 0; length--) {
			builder.append(in.readChar());
		}
		
		return builder.toString();
	}
	
	/**
	 * Writes a string to a DataInputStream.
	 * @param out DataOutputStream to write to.
	 * @param str The string to write.
	 * @throws IOException if an I/O error occurs.
	 */
	public static void writeString(DataOutputStream out, String str) throws IOException {
		out.writeShort(str.length());
		
		for(char letter : str.toCharArray()) {
			out.writeChar(letter);
		}
	}
	
	/**
	 * Default private constructor
	 */
	private IOUtils() {
	}
	
}
