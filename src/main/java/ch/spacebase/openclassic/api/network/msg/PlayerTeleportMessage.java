package ch.spacebase.openclassic.api.network.msg;

/**
 * Sent/Recieved when a player is moved.
 */
public class PlayerTeleportMessage extends Message {
	
	private byte playerId;
	private double x;
	private double y;
	private double z;
	private byte yaw;
	private byte pitch;
	
	public PlayerTeleportMessage(byte playerId, double x, double y, double z, byte yaw, byte pitch) {
		this.playerId = playerId;
		this.x = x;
		this.y = y;
		this.z = z;
		this.yaw = yaw;
		this.pitch = pitch;
	}
	
	/**
	 * Gets the ID of the moved player.
	 * @return The player's ID.
	 */
	public byte getPlayerId() {
		return this.playerId;
	}
	
	/**
	 * Gets the player's new X.
	 * @return The new X.
	 */
	public double getX() {
		return this.x;
	}
	
	/**
	 * Gets the player's new Y.
	 * @return The new Y.
	 */
	public double getY() {
		return this.y;
	}
	
	/**
	 * Gets the player's new Z.
	 * @return The new Z.
	 */
	public double getZ() {
		return this.z;
	}
	
	/**
	 * Gets the player's new yaw.
	 * @return The new yaw.
	 */
	public byte getYaw() {
		return this.yaw;
	}
	
	/**
	 * Gets the player's new pitch.
	 * @return The new pitch.
	 */
	public byte getPitch() {
		return this.pitch;
	}
	
	@Override
	public String toString() {
		return "PlayerTeleportMessage{playerid=" + playerId + ",x=" + x + ",y=" + y + ",z=" + z + ",yaw=" + yaw + ",pitch=" + pitch + "}";
	}
	
	@Override
	public Object[] getParams() {
		return new Object[] { this.playerId, this.x, this.y, this.z, this.yaw, this.pitch };
	}
	
	@Override
	public byte getOpcode() {
		return 8;
	}
	
}
