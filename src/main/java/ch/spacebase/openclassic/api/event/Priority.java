package ch.spacebase.openclassic.api.event;

/**
 * Represents how early or late an event handler should be called.
 */
public enum Priority {
	/** Call the handler first. */
	FIRST,
	/** Call the handler early. */
	EARLY,
	/** Call the handler at the usual time. */
	NORMAL,
	/** Call the handler later than normal. */
	LATE,
	/** Call the handler the latest */
	LAST;
}
