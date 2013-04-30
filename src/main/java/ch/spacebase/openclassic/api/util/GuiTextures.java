package ch.spacebase.openclassic.api.util;

import ch.spacebase.openclassic.api.block.model.SubTexture;
import ch.spacebase.openclassic.api.block.model.Texture;

public class GuiTextures {

	public static final Texture GUI = new Texture("/gui/gui.png", true, 256, 256, 16);
	public static final Texture ICONS = new Texture("/gui/icons.png", true, 256, 256, 16);
	public static final SubTexture CROSSHAIR = new SubTexture(ICONS, 0, 0, 0, 16, 16);
	public static final SubTexture QUICK_BAR = new SubTexture(GUI, 0, 0, 0, 182, 22);
	public static final SubTexture SELECTION = new SubTexture(GUI, 0, 0, 22, 24, 22);
	public static final SubTexture BUTTON = new SubTexture(GUI, 0, 0, 66, 200, 20);
	public static final SubTexture BUTTON_HOVER = new SubTexture(GUI, 0, 0, 86, 200, 20);
	public static final SubTexture BUTTON_INACTIVE = new SubTexture(GUI, 0, 0, 46, 200, 20);
	
}
