package wall.chinese.checkers.clientside.board;

import java.awt.Color;

/**
 * The Enum CogTypes.
 */
public enum CogTypes {

	/** The eax. */
	EAX(Color.RED),
	/** The ebx. */
	EBX(Color.GREEN),
	/** The ecx. */
	ECX(Color.BLUE),
	/** The edx. */
	EDX(Color.MAGENTA),

	/** The eex. */
	EEX(Color.CYAN),
	/** The efx. */
	EFX(Color.YELLOW),
	/** The ebp. */
	EBP(Color.BLACK);

	/** The color. */
	private Color color;

	/**
	 * Instantiates a new cog types.
	 *
	 * @param color the color
	 */
	private CogTypes(Color color) {
		this.color = color;
	}

	/**
	 * Gets the color.
	 *
	 * @return the color
	 */
	public Color getColor() {
		return color;
	}
}