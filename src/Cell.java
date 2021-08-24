
public class Cell {
	
	public boolean occupiedSnake;
	public boolean occupiedFood;
	public int row;
	public int col;
	public int xMax;
	public int xMin;
	public int yMax;
	public int yMin;
	
	Cell(int row, int col) {
		occupiedSnake = false;
		occupiedFood = false;
		this.row = row;
		this.col = col;
		xMax = col*30 + 59;
		xMin = col*30 + 30;
		yMax = row*30 + 59;
		yMin = row*30 + 30;
	}
	
}
