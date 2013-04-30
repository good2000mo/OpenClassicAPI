package ch.spacebase.openclassic.api.network.msg;

/**
 * Sent/Recieved during the identification process.
 */
public class IdentificationMessage extends Message {
	
	private byte protocolVersion;
	private String usernameOrServerName;
	private String verificationKeyOrMotd;
	private byte op;
	
	public IdentificationMessage(byte protocolVersion, String usernameOrServerName, String verificationKeyOrMotd, byte op) {
		this.protocolVersion = protocolVersion;
		this.usernameOrServerName = usernameOrServerName;
		this.verificationKeyOrMotd = verificationKeyOrMotd;
		this.op = op;
	}
	
	/**
	 * Gets the sent/recieved protocol version.
	 * @return The protocol version.
	 */
	public byte getProtocolVersion() {
		return this.protocolVersion;
	}
	
	/**
	 * Gets the player's name if the message is client->server or the server name if it is server->client.
	 * @return The username or server name.
	 */
	public String getUsernameOrServerName() {
		return this.usernameOrServerName;
	}
	
	/**
	 * Gets the verification key if the message is client->server or the motd if it is server->client.
	 * @return The verification key or motd.
	 */
	public String getVerificationKeyOrMotd() {
		return this.verificationKeyOrMotd;
	}
	
	/**
	 * Gets whether this player is an OP if the message is server->client or whether the client is custom if it is client->server.
	 * @return Whether the player is an OP or is custom.
	 */
	public byte getOpOrCustomClient() {
		return this.op;
	}
	
	@Override
	public String toString() {
		return "IdentificationMessage{protocol=" + protocolVersion + ",usernameorservername=" + usernameOrServerName + ",verificationkeyormotd=" + verificationKeyOrMotd + ",op=" + op + "}";
	}

	@Override
	public Object[] getParams() {
		return new Object[] { this.usernameOrServerName, this.verificationKeyOrMotd, this.protocolVersion, this.op };
	}
	
	@Override
	public byte getOpcode() {
		return 0;
	}
	
}
