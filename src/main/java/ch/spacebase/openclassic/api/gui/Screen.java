package ch.spacebase.openclassic.api.gui;

import java.util.ArrayList;
import java.util.List;

import ch.spacebase.openclassic.api.gui.widget.Widget;

/**
 * Represents a screen.
 */
public abstract class Screen {
	
	private List<Widget> widgets = new ArrayList<Widget>();
	
	/**
	 * Gets the width of the screen.
	 * @return The screen's width.
	 */
	public abstract int getWidth();
	
	/**
	 * Gets the height of the screen.
	 * @return The screen's height.
	 */
	public abstract int getHeight();
	
	/**
	 * Attaches a widget to this GuiScreen.
	 * @param widget Widget to attach.
	 */
	public void attachWidget(Widget widget) {
		this.widgets.add(widget);
		widget.setParent(this);
		widget.onAttached(this);
	}
	
	/**
	 * Removes a widget from this GuiScreen.
	 * @param id ID of the widget.
	 */
	public void removeWidget(int id) {
		for(Widget widget : this.widgets) {
			if(widget.getId() == id) {
				this.removeWidget(widget);
			}
		}
	}
	
	/**
	 * Removes a widget from this GuiScreen.
	 * @param widget Widget to remove.
	 */
	public void removeWidget(Widget widget) {
		this.widgets.remove(widget);
		widget.setParent(null);
		widget.onRemoved(this);
	}
	
	/**
	 * Clears the widget list.
	 */
	public void clearWidgets() {
		for(Widget widget : this.widgets) {
			widget.setParent(null);
			widget.onRemoved(this);
		}
		
		this.widgets.clear();
	}
	
	/**
	 * Gets the widget with the given ID.
	 * @param id ID to look for.
	 * @return The widget.
	 */
	public Widget getWidget(int id) {
		for(Widget widget : this.widgets) {
			if(widget.getId() == id) return widget;
		}
		
		return null;
	}
	
	/**
	 * Gets the widget with the given ID and type.
	 * @param id ID to look for.
	 * @param type Type of widget to look for.
	 * @return The widget.
	 */
	@SuppressWarnings("unchecked")
	public <T extends Widget> T getWidget(int id, Class<T> type) {
		for(Widget widget : this.widgets) {
			if(widget.getId() == id && type.isInstance(widget)) return (T) widget;
		}
		
		return null;
	}
	
	/**
	 * Gets a list of all widgets attached to this GuiScreen.
	 * @return All the attached widgets.
	 */
	public List<Widget> getWidgets() {
		return new ArrayList<Widget>(this.widgets);
	}
	
	/**
	 * Called when a tick update occurs.
	 */
	public void update() {
		for(Widget widget : this.getWidgets()) {
			widget.update();
		}
	}
	
	/**
	 * Renders the Screen.
	 */
	public void render() {
		for (Widget widget : this.getWidgets()) {
			if (widget.isVisible()) {
				widget.render();
			}
		}
	}
	
}
