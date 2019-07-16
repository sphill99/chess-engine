package components;

import java.awt.Color;
import java.awt.Graphics;

public class Cell implements Comparable<Cell> {
	private int x; 
	private int y;
	private boolean isSelected;
	private final int size = 70;
	private boolean ship;
	
	public Cell(int x, int y) {
		this.x = x;
		this.y = y;
		isSelected = false;
	}
	
	public void changeSelection() {
		isSelected = !isSelected;
	}
	
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public void drawCell(Graphics gc) {
		Color c;
		if (x % 2 == y % 2) {
			c = new Color(245, 242, 208);
		} else {
			c = new Color(45, 62, 102);
		}
		gc.setColor(c);
		gc.fillRect(x, y, size, size);
		if (isSelected) {
			Color col = new Color(255, 255, 0, 100);
			gc.setColor(col);
			gc.fillRect(x, y, size, size);
		}
		gc.setColor(Color.BLACK);
		gc.drawRect(x, y, size, size);
		if (isShip()) {
			gc.fillOval(x, y, size, size);
		}
	}

	

	public boolean isShip() {
		return ship;
	}

	public void setShip(boolean ship) {
		this.ship = ship;
	}

	@Override
	public int compareTo(Cell o) {
		if (x > o.getX()) {
			return 1;
		} else if (x < o.getX()) {
			return -1;
		} else if (y > o.getY()) {
			return 1;
		} else if (y < o.getY()) {
			return -1;
		}
		return 0;
	}
	
	public boolean equalsTo(Cell o) {
		return (x == o.getX() && y == o.getY());
	}
}

