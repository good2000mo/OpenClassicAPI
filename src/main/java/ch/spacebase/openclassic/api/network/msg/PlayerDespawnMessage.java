package ch.spacebase.openclassic.api.network.msg;

/**
 * Sent when a player despawns.
 */
public class PlayerDespawnMessage extends Message {
	
	private byte playerId;
	
	public PlayerDespawnMessage(byte playerId) {
		this.playerId = playerId;
	}
	
	/**
	 * Gets the ID of the player despawning.
	 * @return The player's ID.
	 */
	public byte getPlayerId() {
		return this.playerId;
	}
	
	@Override
	public String toString() {
		return "PlayerDespawnMessage{playerid=" + playerId + "}";
	}

	@Override
	public Object[] getParams() {
		return new Object[] { this.playerId };
	}
	
	@Override
	public byte getOpcode() {
		return 12;
	}
	
}
