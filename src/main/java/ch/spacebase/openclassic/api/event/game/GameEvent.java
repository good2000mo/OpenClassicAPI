package ch.spacebase.openclassic.api.event.game;

import ch.spacebase.openclassic.api.event.Event;

public abstract class GameEvent extends Event {

	public GameEvent(EventType type) {
		super(type);
	}

}
