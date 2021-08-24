import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

public class SnakeView extends JPanel {
	
	final int PANEL_HEIGHT = 500;
	final int PANEL_WIDTH = 500;
	GridLayout layout;
	Cell[][] board;
	List<SnakeModel> snake;
	boolean gameOver;
	int score;
	
	SnakeView() {
		snake = new ArrayList<SnakeModel>();
		snake.add(new SnakeModel(true));
		layout = new GridLayout(15,15,0,0);
		gameOver = false;
		score = 0;
		this.setLayout(layout);
		board = new Cell[15][15];
		this.setPreferredSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));
		createGrid();
		setFocusable(true);
	}
	

	public void createGrid() {
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[i].length; j++) {
				board[i][j] = new Cell(i, j);
				if (i == board.length/2 && j == board[i].length/2) {
					board[i][j].occupiedSnake = true;
				}
			}
		}
		this.repaint();
	}
	
	public boolean checkIntersect() {
		SnakeModel head = snake.get(0);
		if (head.row > 14 || head.row < 0 || head.col > 14 || head.col < 0) {
			return true;
		}
		for (int i = 1; i < snake.size(); i++) {
			if (snake.get(i).row == head.row && snake.get(i).col == head.col) {
				return true;
			}
		}
		return false;
	}
	
	public void reset() {
		snake.clear();
		snake.add(new SnakeModel(true));
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[i].length; j++) {
				board[i][j].occupiedFood = false;
			}
		}
		score = 0;
		gameOver = false;
	}
	
	public void addSnake() {
		SnakeModel addSnake = new SnakeModel(false);
		SnakeModel lastSnake = snake.get(snake.size() - 1);
		switch (lastSnake.direction) {
		case UP:
			addSnake.row = lastSnake.row + 1;
			addSnake.col = lastSnake.col;
			break;
		case RIGHT:
			addSnake.row = lastSnake.row;
			addSnake.col = lastSnake.col - 1;
			break;
		case LEFT:
			addSnake.row = lastSnake.row;
			addSnake.col = lastSnake.col + 1;
			break;
		case DOWN:
			addSnake.row = lastSnake.row - 1;
			addSnake.col = lastSnake.col;
			break;
		}
		addSnake.direction = lastSnake.direction;
		snake.add(addSnake);
	}

	@Override
	public void paint(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		g2.setColor(Color.WHITE);
		g2.fillRect(0, 0, PANEL_WIDTH, PANEL_HEIGHT);
		g2.setColor(Color.BLACK);
		g2.drawLine(30, 30, 30, 479);
		g2.drawLine(30, 30, 479, 30);
		g.setColor(Color.BLACK);
		g2.drawString("Score " + score, 400, 20);
		for (int i = 0; i < board.length; i++) {
			g2.drawLine(board[0][i].xMax, 479, board[0][i].xMax, 30);
			g2.drawLine(479, board[i][0].yMax, 30, board[i][0].yMax);
		}
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[i].length; j++) {
				for (SnakeModel s : snake) {
					if (s.row == i && s.col == j) {
						g2.setColor(Color.GREEN);
						g.fillRect(board[i][j].xMin, board[i][j].yMin, 29, 29);
					}
				}
				if (board[i][j].occupiedFood) {
					g2.setColor(Color.RED);
					g.fillRect(board[i][j].xMin, board[i][j].yMin, 29, 29);
				}
			}
		}
		if (gameOver) {
			g.setColor(Color.BLACK);
			g2.drawString("Game Over. Press Space to Play Again.", 150, 20);
		}
	}
}
