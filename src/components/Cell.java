package components;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import components.Piece.Type;

public class Cell implements Comparable<Cell> {
	private int x; 
	private int y;
	private boolean isSelected;
	private final static int size = 70;
	private Piece p;
	
	public Cell(int x, int y, Piece P) {
		this.x = x;
		this.y = y;
		isSelected = false;
		p = P;
	}
	
	public static int getSize() {
		return size;
	}
	
	public void makeSelection() {
		isSelected = true;
	}
	
	public void clearSelection() {
		isSelected = false;
	}
	
	public boolean getSelection() {
		return isSelected;
	}
	
	public boolean hasPiece() {
		return p != null;
	}
	
	public Piece getPiece() {
		return p;
	}
	
	public ArrayList<Cell> getMoves(ChessBoard cb, boolean white) {
		ArrayList<Cell> moves = new ArrayList<Cell>();
		if (p == null) return new ArrayList<>();
		if (p.isWhite() != white) return new ArrayList<>();   
		int[][] board = cb.getColorBoard();
		Cell[][] cellBoard = cb.getBoard();
		int type = p.isWhite() ? 2 : 1;
		int x = getX();
		int y = getY();
		if (p.getPiece() == Type.PAWN) {
			int mult = white ? -1 : 1;
			int newX = x + 1 * mult;
			if (x == 0 || x == 7) return null;
			
			if (board[newX][y] == 0) {
				moves.add(cellBoard[newX][y]);
			}
			
			if (y + 1 < 8 && board[newX][y + 1] != type && board[newX][y + 1] != 0) {
				moves.add(cellBoard[newX][y + 1]);
			}
			
			if (y - 1 >= 0 && board[newX][y - 1] != type && board[newX][y - 1] != 0) {
				moves.add(cellBoard[newX][y - 1]);
			}
			
			if (white && x == 6 && board[5][y] == 0 && board[4][y] == 0) {
				moves.add(cellBoard[4][y]);
			}
			
			if (!white && x == 1 && board[2][y] == 0 && board[3][y] == 0) {
				moves.add(cellBoard[3][y]);
			}
		}
		
		if (p.getPiece() == Type.BISHOP || p.getPiece() == Type.QUEEN) {
			int[] directions = new int[8];
			for (int i = 0; i < 8; i++) {
				if (i == 0 || i == 2) {
					directions[i] = x - 1;
				} else if (i == 1 || i == 5){
					directions[i] = y - 1;
				} else if (i == 4 || i == 6) {
					directions[i] = x + 1;
				} else {
					directions[i] = y + 1;
				}
			}
			
			while(directions[0] >= 0 && directions[1] >= 0) {
				if (board[directions[0]][directions[1]] == 0) {
					moves.add(cellBoard[directions[0]][directions[1]]);
				} else if (board[directions[0]][directions[1]] != type) {
					moves.add(cellBoard[directions[0]][directions[1]]);
					break;
				} else {
					break;
				}
				directions[0]--;
				directions[1]--;
			}
			
			while(directions[2] >= 0 && directions[3] <= 7) {
				if (board[directions[2]][directions[3]] == 0) {
					moves.add(cellBoard[directions[2]][directions[3]]);
				} else if (board[directions[2]][directions[3]] != type) {
					moves.add(cellBoard[directions[2]][directions[3]]);
					break;
				} else {
					break;
				}
				directions[2]--;
				directions[3]++;
			}
			
			while(directions[4] <= 7 && directions[5] >= 0) {
				if (board[directions[4]][directions[5]] == 0) {
					moves.add(cellBoard[directions[4]][directions[5]]);
				} else if (board[directions[4]][directions[5]] != type) {
					moves.add(cellBoard[directions[4]][directions[5]]);
					break;
				} else {
					break;
				}
				directions[4]++;
				directions[5]--;
			}
			
			while(directions[6] <= 7 && directions[7] <= 7) {
				if (board[directions[6]][directions[7]] == 0) {
					moves.add(cellBoard[directions[6]][directions[7]]);
				} else if (board[directions[6]][directions[7]] != type) {
					moves.add(cellBoard[directions[6]][directions[7]]);
					break;
				} else {
					break;
				}
				directions[6]++;
				directions[7]++;
			}
		}
		
		if (p.getPiece() == Type.ROOK || p.getPiece() == Type.QUEEN) {
			int[] directions = new int[4];
			for (int i = 0; i < 4; i++) {
				if (i == 0) {
					directions[i] = x - 1;
				} else if (i == 1) {
					directions[i] = y - 1;
				} else if (i == 2) {
					directions[i] = x + 1;
				} else {
					directions[i] = y + 1;
				}
			}
			
			while(directions[0] >= 0) {
				if (board[directions[0]][y] == 0) {
					moves.add(cellBoard[directions[0]][y]);
				} else if (board[directions[0]][y] != type) {
					moves.add(cellBoard[directions[0]][y]);
					break;
				} else {
					break;
				}
				directions[0]--;
			}
			
			while(directions[1] >= 0) {
				if (board[x][directions[1]] == 0) {
					moves.add(cellBoard[x][directions[1]]);
				} else if (board[x][directions[1]] != type) {
					moves.add(cellBoard[x][directions[1]]);
					break;
				} else {
					break;
				}
				directions[1]--;
			}
			
			while(directions[2] <= 7) {
				if (board[directions[2]][y] == 0) {
					moves.add(cellBoard[directions[2]][y]);
				} else if (board[directions[2]][y] != type) {
					moves.add(cellBoard[directions[2]][y]);
					break;
				} else {
					break;
				}
				directions[2]++;
			}
			
			while(directions[3] <= 7) {
				if (board[x][directions[3]] == 0) {
					moves.add(cellBoard[x][directions[3]]);
				} else if (board[x][directions[3]] != type) {
					moves.add(cellBoard[x][directions[3]]);
					break;
				} else {
					break;
				}
				directions[3]++;
			}
		}
		
		if (p.getPiece() == Type.KNIGHT) {
			int xStart = Math.max(0, x - 2);
			int xEnd = Math.min(7, x + 2);
			int yStart = Math.max(0, y - 2);
			int yEnd = Math.min(7, y + 2);
			
			for (int i = xStart; i <= xEnd; i++) {
				for (int j = yStart; j <= yEnd; j++) {
					if (Math.abs(x - i) == Math.abs(y - j) || i == x || j == y) {
						continue;
					}
					if (board[i][j] == 0 || board[i][j] + type == 3) {
						moves.add(cellBoard[i][j]);
					}
				}
			}
		}
		
		if (p.getPiece() == Type.KING) {
			int xStart = Math.max(0, x - 1);
			int xEnd = Math.min(7, x + 1);
			int yStart = Math.max(0, y - 1);
			int yEnd = Math.min(7, y + 1);
			
			for (int i = xStart; i <= xEnd; i++) {
				for (int j = yStart; j < yEnd; j++) {
					if (i == x && j == y) {
						continue;
					}
					if (board[i][j] != type) {
						moves.add(cellBoard[i][j]);
					}
				}
			}
		}
		
		return moves;
	}
	
	public Piece setPiece(Piece pi) {
		Piece temp = p;
		p = pi;
		return temp;
	}
	
	public int getX() {
		return x;
	}
	
	public int getAdjustedX() {
		return 100 + size * y;
	}
	
	public int getAdjustedY() {
		return 100 + size * x;
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
	
	public void drawCell(Graphics gc) throws IOException {
		Color c;
		if (x % 2 == y % 2) {
			c = new Color(243, 220, 187);
		} else {
			c = new Color(45, 62, 102);
		}
		gc.setColor(c);
		gc.fillRect(getAdjustedX(), getAdjustedY(), size, size);
		if (p != null) {
			gc.drawImage(getImage(p), getAdjustedX() + 5, getAdjustedY() + 5, 60, 60, null);
		}
		if (isSelected) {
			gc.setColor(new Color(0, 0, 0, 70));
			gc.fillOval(getAdjustedX() + 25, getAdjustedY() + 25, 15, 15);
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

