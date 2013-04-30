package ch.spacebase.openclassic.api.event;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
/**
 * An annotation representing an event handling method.
 */
public @interface EventHandler {
	/**
	 * Gets the priority of the event handler.
	 * @return The handler's priority.
	 */
	Priority priority() default Priority.NORMAL;
}
