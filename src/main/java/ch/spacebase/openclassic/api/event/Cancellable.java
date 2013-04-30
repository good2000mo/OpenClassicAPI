package ch.spacebase.openclassic.api.event;

/**
 * Represents a cancellable event.
 */
public interface Cancellable {

	/**
	 * Gets whether an event is cancelled.
	 * @return True if the event is cancelled.
	 */
	public boolean isCancelled();
	
	/**
	 * Sets whether the event is cancelled.
	 * @param cancel Whether the event is cancelled.
	 */
	public void setCancelled(boolean cancel);
	
}
