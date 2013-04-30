package ch.spacebase.openclassic.api.network.msg;

/**
 * Sent when a player is spawned.
 */
public class PlayerSpawnMessage extends Message {
	
	private byte playerId;
	private String name;
	private double x;
	private double y;
	private double z;
	private byte yaw;
	private byte pitch;
	
	public PlayerSpawnMessage(byte playerId, String name, double x, double y, double z, byte yaw, byte pitch) {
		this.playerId = playerId;
		this.name = name;
		this.x = x;
		this.y = y;
		this.z = z;
		this.yaw = yaw;
		this.pitch = pitch;
	}
	
	/**
	 * Gets the player ID of the spawned player.
	 * @return The player's ID.
	 */
	public byte getPlayerId() {
		return this.playerId;
	}
	
	/**
	 * Gets the name of the spawned player.
	 * @return The player's name.
	 */
	public String getName() {
		return this.name;
	}
	
	/**
	 * Gets the X of the spawned player.
	 * @return The player's X.
	 */
	public double getX() {
		return this.x;
	}
	
	/**
	 * Gets the Y of the spawned player.
	 * @return The player's Y.
	 */
	public double getY() {
		return this.y;
	}
	
	/**
	 * Gets the Z of the spawned player.
	 * @return The player's Z.
	 */
	public double getZ() {
		return this.z;
	}
	
	/**
	 * Gets the yaw of the spawned player.
	 * @return The player's yaw.
	 */
	public byte getYaw() {
		return this.yaw;
	}
	
	/**
	 * Gets the pitch of the spawned player.
	 * @return The player's pitch.
	 */
	public byte getPitch() {
		return this.pitch;
	}
	
	@Override
	public String toString() {
		return "PlayerSpawnMessage{playerid=" + playerId + ",name=" + name + ",x=" + x + ",y=" + y + ",z=" + z + ",yaw=" + yaw + ",pitch=" + pitch + "}";
	}

	@Override
	public Object[] getParams() {
		return new Object[] { this.playerId, this.name, this.x, this.y, this.z, this.yaw, this.pitch };
	}
	
	@Override
	public byte getOpcode() {
		return 7;
	}
	
}
