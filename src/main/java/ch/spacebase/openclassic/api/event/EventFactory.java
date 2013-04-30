package ch.spacebase.openclassic.api.event;

import java.lang.reflect.Method;

import ch.spacebase.openclassic.api.OpenClassic;
import ch.spacebase.openclassic.api.event.game.EventDispatchEvent;
import ch.spacebase.openclassic.api.util.EventUtil;

/**
 * A factory for building and calling events.
 */
public class EventFactory {

	/**
	 * Calls the given event.
	 * @param event Event to call.
	 */
	public static <T extends Event> T callEvent(T event) {
		if(OpenClassic.getGame() == null || OpenClassic.getGame().getPluginManager() == null) return event;
		for(Priority pri : Priority.values()) {
			callEventInternal(new EventDispatchEvent(event, pri), pri);
			callEventInternal(event, pri);
		}
		
		return event;
	}
	
	public static void callEventInternal(Event event, Priority pri) {
		for(Listener listen : OpenClassic.getGame().getPluginManager().getListeners()) {
			if(!OpenClassic.getGame().getPluginManager().getPlugin(listen).isEnabled()) continue;
			
			Method methods[] = EventUtil.getMethodsFor(listen, event.getClass(), pri);
			if(methods != null && methods.length > 0) {
				for(Method method : methods) {
					try {
						method.invoke(listen, event);
					} catch(Exception e) {
						OpenClassic.getLogger().severe("Failed to call event " + event.getType().name() + "!");
						e.printStackTrace();
					}
				}
			}
		}
	}
	
}
