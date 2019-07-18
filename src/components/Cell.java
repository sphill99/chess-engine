package components;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Cell implements Comparable<Cell> {
	private int x; 
	private int y;
	private boolean isSelected;
	private final int size = 70;
	private Piece p;
	
	public Cell(int x, int y, Piece P) {
		this.x = x;
		this.y = y;
		isSelected = false;
		p = P;
	}
	
	public void changeSelection() {
		isSelected = !isSelected;
	}
	
	public Piece getPiece() {
		return p;
	}
	
	public Piece setPiece(Piece pi) {
		Piece temp = p;
		temp = pi;
		return temp;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public Image getImage(Piece p) throws IOException {
		BufferedImage i = null;
		Piece.Type t = p.getPiece();
		String s = t.getName();
		if (p.isWhite()) {
			if (s.equals("Pawn")) {
				i = ImageIO.read(new File ("files/whitepawn.png"));
			} else if (s.equals("Bishop")) {
				i = ImageIO.read(new File ("files/whitebishop.png"));
			} else if (s.equals("Rook")) {
				i = ImageIO.read(new File ("files/whiterook.png"));
			} else if (s.equals("Knight")) {
				i = ImageIO.read(new File ("files/whiteknight.png"));
			} else if (s.equals("Queen")) {
				i = ImageIO.read(new File ("files/whitequeen.png"));
			} else if (s.equals("King")) {
				i = ImageIO.read(new File ("files/whiteking.png"));
			}
		} else {
			if (s.equals("Pawn")) {
				i = ImageIO.read(new File ("files/blackpawn.png"));
			} else if (s.equals("Bishop")) {
				i = ImageIO.read(new File ("files/blackbishop.png"));
			} else if (s.equals("Rook")) {
				i = ImageIO.read(new File ("files/blackrook.png"));
			} else if (s.equals("Knight")) {
				i = ImageIO.read(new File ("files/blackknight.png"));
			} else if (s.equals("Queen")) {
				i = ImageIO.read(new File ("files/blackqueen.png"));
			} else if (s.equals("King")) {
				i = ImageIO.read(new File ("files/blackking.png"));
			}
		}
		return i;
	}
	
	public void drawCell(Graphics gc) {
		Color c;
		if (x % 2 == y % 2) {
			c = new Color(243, 220, 187);
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

