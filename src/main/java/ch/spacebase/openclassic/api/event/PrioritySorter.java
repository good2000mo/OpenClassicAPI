package ch.spacebase.openclassic.api.event;

import java.lang.reflect.Method;
import java.util.Comparator;

/**
 * Sorts event methods by priority.
 */
public class PrioritySorter implements Comparator<Method> {

	@Override
	public int compare(Method method, Method other) {
		EventHandler handler = method.getAnnotation(EventHandler.class);
		EventHandler otherHandler = other.getAnnotation(EventHandler.class);
		if(handler.priority() == otherHandler.priority()) {
			return 0;
		}
		
		if(handler.priority().ordinal() > otherHandler.priority().ordinal()) {
			return 1;
		}
		
		return -1;
	}
	
}
