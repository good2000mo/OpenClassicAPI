package ch.spacebase.openclassic.api.network.msg;

/**
 * Represents a network message.
 */
public abstract class Message {

	@Override
	public abstract String toString();
	
	/**
	 * Gets the param values for client sending.
	 * @return The message's param values.
	 */
	public abstract Object[] getParams();
	
	/**
	 * Gets the message's opcode.
	 * @return The message's opcode.
	 */
	public abstract byte getOpcode();
	
}
