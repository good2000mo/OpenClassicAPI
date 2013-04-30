package ch.spacebase.openclassic.api.event.game;

import ch.spacebase.openclassic.api.command.Sender;
import ch.spacebase.openclassic.api.event.Cancellable;

/**
 * Called when a command is executed.
 */
public class PreCommandEvent extends GameEvent implements Cancellable {

	private boolean cancelled = false;
	private Sender sender;
	private String command;
	
	public PreCommandEvent(Sender sender, String command) {
		super(EventType.PRE_COMMAND);
		this.sender = sender;
		this.command = command;
	}
	
	/**
	 * Gets the sender sending the command.
	 * @return The command sender.
	 */
	public Sender getSender() {
		return this.sender;
	}
	
	/**
	 * Gets the command being sent.
	 * @return The sent command.
	 */
	public String getCommand() {
		return this.command;
	}
	
	/**
	 * Sets the command being sent.
	 * @param command The new command to send.
	 */
	public void setCommand(String command) {
		this.command = command;
	}
	
	@Override
	public boolean isCancelled() {
		return this.cancelled;
	}

	@Override
	public void setCancelled(boolean cancel) {
		this.cancelled = cancel;
	}

}
