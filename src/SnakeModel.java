import java.awt.Color;
import java.awt.Graphics;

public class SnakeModel {
	
	public int row;
	public int col;
	public boolean isHead;
	public enum Orientation {
		UP,
		RIGHT,
		DOWN,
		LEFT,
	}
	Orientation direction;
	
	public SnakeModel(boolean isHead) {
		if (isHead) {
			this.row = 7;
			this.col = 7;
			direction = Orientation.UP;
		}
		this.isHead = isHead;
	}
	
	public void update() {
		switch (direction) {
		case UP:
			row--;
			break;
		case RIGHT:
			col++;
			break;
		case LEFT:
			col--;
			break;
		case DOWN:
			row++;
			break;
		}
	}
	
}
