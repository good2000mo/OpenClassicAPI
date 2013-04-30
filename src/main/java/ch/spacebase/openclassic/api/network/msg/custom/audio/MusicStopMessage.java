package ch.spacebase.openclassic.api.network.msg.custom.audio;

import ch.spacebase.openclassic.api.network.msg.Message;

/**
 * Sent from the server to stop music.
 */
public class MusicStopMessage extends Message {

	private String identifier;
	
	public MusicStopMessage(String identifier) {
		this.identifier = identifier;
	}
	
	/**
	 * Gets the identifier of the music ("all_music" for all music).
	 * @return The music's identifier.
	 */
	public String getIdentifier() {
		return this.identifier;
	}

	@Override
	public String toString() {
		return "MusicStopMessage{identifier=" + identifier + "}";
	}

	@Override
	public Object[] getParams() {
		return new Object[] { this.identifier };
	}

	@Override
	public byte getOpcode() {
		return 24;
	}
	
}
