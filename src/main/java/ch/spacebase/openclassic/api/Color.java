package ch.spacebase.openclassic.api;

/**
 * Represents a chat color.
 */
public enum Color {

	/** Represents the color code prefix. */
	COLOR_CHARACTER("&", 0, 0, 0),
	/** Represents the color black. */
	BLACK(COLOR_CHARACTER + "0", 0, 0, 0),
	/** Represents the color dark blue. */
	DARK_BLUE(COLOR_CHARACTER + "1", 0, 0, 170),
	/** Represents the color dark green. */
	DARK_GREEN(COLOR_CHARACTER + "2", 0, 170, 0),
	/** Represents the color cyan. */
	CYAN(COLOR_CHARACTER + "3", 0, 170, 170),
	/** Represents the color dark red. */
	DARK_RED(COLOR_CHARACTER + "4", 170, 0, 0),
	/** Represents the color purple. */
	PURPLE(COLOR_CHARACTER + "5", 170, 0, 170),
	/** Represents the color gold. */
	GOLD(COLOR_CHARACTER + "6", 255, 170, 0),
	/** Represents the color gray. */
	GRAY(COLOR_CHARACTER + "7", 170, 170, 170),
	/** Represents the color dark gray. */
	DARK_GRAY(COLOR_CHARACTER + "8", 85, 85, 85),
	/** Represents the color blue. */
	BLUE(COLOR_CHARACTER + "9", 85, 85, 255),
	/** Represents the color green. */
	GREEN(COLOR_CHARACTER + "a", 85, 255, 85),
	/** Represents the color aqua. */
	AQUA(COLOR_CHARACTER + "b", 85, 255, 255),
	/** Represents the color red. */
	RED(COLOR_CHARACTER + "c", 255, 85, 85),
	/** Represents the color pink. */
	PINK(COLOR_CHARACTER + "d", 255, 85, 255),
	/** Represents the color yellow. */
	YELLOW(COLOR_CHARACTER + "e", 255, 255, 85),
	/** Represents the color white. */
	WHITE(COLOR_CHARACTER + "f", 255, 255, 255);
	
	private String code;
	private int r;
	private int g;
	private int b;
	
	private Color(String code, int r, int g, int b) {
		this.code = code;
		this.r = r;
		this.g = g;
		this.b = b;
	}
	
	/**
	 * Gets the color code string.
	 * @return The color code.
	 */
	public String getCode() {
		return this.code;
	}
	
	/**
	 * Gets the symbol following the color code prefix.
	 * @return The color code's symbol.
	 */
	public String getSymbol() {
		return this.code.replace(COLOR_CHARACTER.getCode(), "");
	}
	
	/**
	 * Gets the red of this color.
	 * @return This color's red value.
	 */
	public int getRed() {
		return this.r;
	}
	
	/**
	 * Gets the green of this color.
	 * @return This color's green value.
	 */
	public int getGreen() {
		return this.g;
	}
	
	/**
	 * Gets the blue of this color.
	 * @return This color's blue value.
	 */
	public int getBlue() {
		return this.b;
	}
	
	@Override
	public String toString() {
		return this.code;
	}
	
	/**
	 * Strips a message of all color codes.
	 * @param message Message to strip from.
	 * @return Message without color codes.
	 */
	public static String stripColor(String message) {
		StringBuilder builder = new StringBuilder();
		
		for(int index = 0; index < message.length(); index++) {
			char curr = message.charAt(index);
			
			if(curr == COLOR_CHARACTER.getCode().charAt(0)) {
				index++;
				continue;
			}
			
			builder.append(curr);
		}
		
		return builder.toString();
	}

	public static Color getByChar(char c) {
		for(Color color : values()) {
			if(color.getSymbol().length() > 0 && color.getSymbol().charAt(0) == c) return color;
		}
		
		return null;
	}

}
