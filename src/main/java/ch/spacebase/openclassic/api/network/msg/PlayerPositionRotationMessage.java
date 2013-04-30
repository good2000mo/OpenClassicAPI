package ch.spacebase.openclassic.api.network.msg;

/**
 * Sent to update a player's position and rotation.
 */
public class PlayerPositionRotationMessage extends Message {
	
	private byte playerId;
	private double xChange;
	private double yChange;
	private double zChange;
	private byte yaw;
	private byte pitch;
	
	public PlayerPositionRotationMessage(byte playerId, double xChange, double yChange, double zChange, byte yaw, byte pitch) {
		this.playerId = playerId;
		this.xChange = xChange;
		this.yChange = yChange;
		this.zChange = zChange;
		this.yaw = yaw;
		this.pitch = pitch;
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
	
	/**
	 * Gets the new yaw of the player.
	 * @return The player's new yaw.
	 */
	public byte getYaw() {
		return this.yaw;
	}
	
	/**
	 * Gets the new pitch of the player.
	 * @return The player's new pitch.
	 */
	public byte getPitch() {
		return this.pitch;
	}
	
	@Override
	public String toString() {
		return "PlayerPositionRotationMessage{playerid=" + playerId + ",xchange=" + xChange + ",ychange=" + yChange + ",zchange=" + zChange + ",yaw=" + yaw + ",pitch=" + pitch + "}";
	}

	@Override
	public Object[] getParams() {
		return new Object[] { this.playerId, this.xChange, this.yChange, this.zChange, this.yaw, this.pitch };
	}
	
	@Override
	public byte getOpcode() {
		return 9;
	}
	
}
