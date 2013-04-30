package ch.spacebase.openclassic.api.gui.widget;

import ch.spacebase.openclassic.api.gui.Screen;
import ch.spacebase.openclassic.api.render.RenderHelper;

/**
 * The default background of the client implementation.
 */
public class DefaultBackground extends Widget {

	public DefaultBackground(int id, Screen parent) {
		super(id, 0, 0, 0, 0, parent);
	}

	@Override
	public void render() {
		RenderHelper.getHelper().drawDefaultBG();
	}

}
