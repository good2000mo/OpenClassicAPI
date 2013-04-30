package ch.spacebase.openclassic.api.event;

/**
 * Represents an event.
 */
public abstract class Event {

	private final EventType type;
	
	public Event(EventType type) {
		this.type = type;
	}
	
	/**
	 * Gets the type of event this is.
	 * @return The event's type.
	 */
	public final EventType getType() {
		return this.type;
	}
	
	/**
	 * The possible types of events.
	 */
	public enum EventType {
		/** Called when a block is broken. */
		BLOCK_BREAK,
		/** Called when a block is placed. */
		BLOCK_PLACE,
		/** Called when a block physics update occurs. */
		BLOCK_PHYSICS,
		/** Called when a block is registered. */
		BLOCK_REGISTER,
		/** Called when a block is unregistered. */
		BLOCK_UNREGISTER,
		/** Called when an entity's block is removed. */
		ENTITY_BLOCK_REMOVE,
		/** Called when an entity's block is set at a location. */
		ENTITY_BLOCK_SET,
		/** Called when an entity dies. */
		ENTITY_DEATH,
		/** Called when an entity moves. */
		ENTITY_MOVE,
		/** Called when an event is dispatched. */
		EVENT_DISPATCH,
		/** Called when a level is created. */
		LEVEL_CREATE,
		/** Called when a level is loaded. */
		LEVEL_LOAD,
		/** Called when a level is saved. */
		LEVEL_SAVE,
		/** Called when a level is unloaded. */
		LEVEL_UNLOAD,
		/** Called when a level's spawn is changed. */
		SPAWN_CHANGE,
		/** Called when a player chats. */
		PLAYER_CHAT,
		/** Called when a player connects. */
		PLAYER_CONNECT,
		/** Called when a player joins the game. */
		PLAYER_JOIN,
		/** Called when a player is kicked. */
		PLAYER_KICK,
		/** Called when a player logs in. */
		PLAYER_LOGIN,
		/** Called when a player moves. */
		PLAYER_MOVE,
		/** Called when a player leaves the game. */
		PLAYER_QUIT,
		/** Called when a player respawns with the Load Location key. */
		PLAYER_RESPAWN,
		/** Called when a player teleports. */
		PLAYER_TELEPORT,
		/** Called when a plugin is enabled. */
		PLUGIN_ENABLE,
		/** Called when a plugin is disabled. */
		PLUGIN_DISABLE,
		/** Called when a command is not found. */
		COMMAND_NOT_FOUND,
		/** Called when a command is executed. */
		PRE_COMMAND,
		/** Called when the state of a key is changed on the client. (custom client only) */
		PLAYER_KEY_CHANGE,
		/** Called when a custom message is recieved. */
		CUSTOM_MESSAGE;
	}
	
}
