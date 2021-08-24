import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.Timer;


public class Snake extends JFrame implements ActionListener, KeyListener {
	
	SnakeView view;
	boolean changeLeft;
	int countLeft;
	boolean changeRight;
	int countRight;
	boolean changeUp;
	int countUp;
	boolean changeDown;
	int countDown;
	boolean needFood;
	int foodRow;
	int foodCol;
	
	Snake() {
		view = new SnakeView();
		this.add(view);
		changeLeft = false;
		countLeft = 0;
		changeRight = false;
		countRight = 0;
		changeUp = false;
		countUp= 0;
		changeDown = false;
		countDown = 0;
		needFood = true;
		foodRow = -1;
		foodCol = -1;
		view.repaint();
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.pack();
		this.setLocationRelativeTo(null);
		view.addKeyListener(this);
		Timer timer = new Timer(200, this);
		timer.start();
		this.setVisible(true);
	}
	
	
	public static void main(String[] args) {
		new Snake();
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		int code = e.getKeyCode();
		switch (code) {
		case KeyEvent.VK_LEFT:
			if (view.snake.get(0).direction != SnakeModel.Orientation.RIGHT) {
				changeLeft = true;
			}
			break;
		case KeyEvent.VK_RIGHT:
			if (view.snake.get(0).direction != SnakeModel.Orientation.LEFT) {
				changeRight = true;
			}
			break;
		case KeyEvent.VK_UP:
			if (view.snake.get(0).direction != SnakeModel.Orientation.DOWN) {
				changeUp = true;
			}
			break;
		case KeyEvent.VK_DOWN:
			if (view.snake.get(0).direction != SnakeModel.Orientation.UP) {
				changeDown = true;
			}
			break;	
		}
		if (view.gameOver && code == KeyEvent.VK_SPACE) {
			view.reset();
			needFood = true;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (changeLeft && countLeft < view.snake.size()) {
			view.snake.get(countLeft).direction = SnakeModel.Orientation.LEFT;
			countLeft++;
		} else {
			changeLeft = false;
			countLeft = 0;
		}
		if (changeDown && countDown < view.snake.size()) {
			view.snake.get(countDown).direction = SnakeModel.Orientation.DOWN;
			countDown++;
		} else {
			changeDown = false;
			countDown = 0;
		}
		if (changeUp && countUp < view.snake.size()) {
			view.snake.get(countUp).direction = SnakeModel.Orientation.UP;
			countUp++;
		} else {
			changeUp = false;
			countUp = 0;
		}
		if (changeRight && countRight < view.snake.size()) {
			view.snake.get(countRight).direction = SnakeModel.Orientation.RIGHT;
			countRight++;
		} else {
			changeRight = false;
			countRight = 0;
		}
		if (!view.gameOver) {
			for (SnakeModel s : view.snake) {
				s.update();
			}
			if (view.snake.get(0).row == foodRow && view.snake.get(0).col == foodCol) {
				view.board[foodRow][foodCol].occupiedFood = false;
				needFood = true;
				view.score += view.snake.size()*10;
			}
			if (view.checkIntersect()) {
				view.gameOver = true;
			}
		} else {
			view.gameOver = true;
		}
		while (needFood) {
			view.addSnake();
			foodRow = (int) (Math.random()*15);
			foodCol = (int) (Math.random()*15);
			boolean isValid = true;
			for (SnakeModel s : view.snake) {
				if (s.row == foodRow && s.col == foodCol) {
					isValid = false;
					break;
				}
			}
			if (isValid) {
				needFood = false;
				view.board[foodRow][foodCol].occupiedFood = true;
			}
			
		}
		view.repaint();
	}
}
