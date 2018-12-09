package wall.chinese.checkers.clientside.board;

import java.awt.Color;

public enum CogTypes {
	EAX(Color.RED), EBX(Color.GREEN), ECX(Color.BLUE), EDX(Color.MAGENTA),
	EEX(Color.CYAN), EFX(Color.YELLOW), EBP(Color.BLACK);

	private Color color;

	private CogTypes(Color color) {
		this.color = color;
	}

	public Color getColor() {
		return color;
	}
}