package ch.spacebase.openclassic.api.network.msg;

/**
 * Sent when a player's position is changed.
 */
public class PlayerPositionMessage extends Message {
	
	private byte playerId;
	private double xChange;
	private double yChange;
	private double zChange;
	
	public PlayerPositionMessage(byte playerId, double xChange, double yChange, double zChange) {
		this.playerId = playerId;
		this.xChange = xChange;
		this.yChange = yChange;
		this.zChange = zChange;
	}
	
	/**
	 * Gets the ID of the affected player.
	 * @return The player's ID.
	 */
	public byte getPlayerId() {
		return this.playerId;
	}
	
	/**
	 * Gets the X change of the player.
	 * @return The X change.
	 */
	public double getXChange() {
		return this.xChange;
	}
	
	/**
	 * Gets the Y change of the player.
	 * @return The Y change.
	 */
	public double getYChange() {
		return this.yChange;
	}
	
	/**
	 * Gets the Z change of the player.
	 * @return The Z change.
	 */
	public double getZChange() {
		return this.zChange;
	}
	
	@Override
	public String toString() {
		return "PlayerPositionMessage{playerid=" + playerId + ",xchange=" + xChange + ",ychange=" + yChange + ",zchange=" + zChange + "}";
	}

	@Override
	public Object[] getParams() {
		return new Object[] { this.playerId, this.xChange, this.yChange, this.zChange };
	}
	
	@Override
	public byte getOpcode() {
		return 10;
	}
	
}
