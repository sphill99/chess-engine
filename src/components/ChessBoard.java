package components;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.*;

import components.Piece.Type;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

@SuppressWarnings("serial") 
public class ChessBoard extends JComponent {
	Cell[][] board = new Cell[8][8];
	int[][] colorBoard = new int[8][8];
	boolean whiteTurn = true;
	private ChessBoard cb;
	private boolean pieceSelected;
	private ArrayList<Cell> listOfMoves;
	private Cell selected;
	
	public ChessBoard() {
		cb = this;
		pieceSelected = false;
		listOfMoves = new ArrayList<Cell>();
		selected = null;
		setUpGame();
		
		addMouseListener( new MouseListener() {

					@Override
					public void mouseClicked(MouseEvent e) {
						int x = e.getX();
		                int y = e.getY();
		                if (!pieceSelected) {
		                	Cell c = cb.getCell(y, x);
	                		if (c != null && c.getPiece() != null && c.getPiece().isWhite() == whiteTurn) {
	                			pieceSelected = true;
		                		ArrayList<Cell> moves =  c.getMoves(cb, whiteTurn);
		                		listOfMoves = moves;
		                		selected = c;
		                		for (int i = 0; i < moves.size(); i++) {
		                			Cell c1 = moves.get(i);
		                			c1.makeSelection();
		                			System.out.println(c1.getX() + ", " + c1.getY());
		                		}
		                	}
		                } else {
		                	Cell c = cb.getCell(y, x);
		                	if (listOfMoves.contains(c)) {
		                		Piece p = c.setPiece(selected.getPiece());
		                		selected.setPiece(null);
		                		whiteTurn = !whiteTurn;
		                		colorBoard[c.getX()][c.getY()] = whiteTurn ? 1 : 2;
		                		colorBoard[selected.getX()][selected.getY()] = 0;
		                		
		                		selected = null;
			                	pieceSelected = false;
			                	clearCells();
			                	listOfMoves = new ArrayList<>();
		                	} else if (c.getPiece() != null && c.getPiece().isWhite() == selected.getPiece().isWhite()) {
		                		pieceSelected = true;
		                		ArrayList<Cell> moves =  c.getMoves(cb, whiteTurn);
		                		listOfMoves = moves;
		                		selected = c;
		                		clearCells();
		                		for (int i = 0; i < moves.size(); i++) {
		                			Cell c1 = moves.get(i);
		                			c1.makeSelection();
		                			System.out.println(c1.getX() + ", " + c1.getY());
		                		}
		                	} else {
		                		selected = null;
			                	pieceSelected = false;
			                	clearCells();
			                	listOfMoves = new ArrayList<>();
		                	}
		                }
		                
		                repaint();
		             //   System.out.println("THE MOUSE WAS PRESSED");
						
					}

					@Override
					public void mousePressed(MouseEvent e) {
						// TODO Auto-generated method stub
						
					}

					@Override
					public void mouseReleased(MouseEvent e) {
						// TODO Auto-generated method stub
						
					}

					@Override
					public void mouseEntered(MouseEvent e) {
						// TODO Auto-generated method stub
						
					}

					@Override
					public void mouseExited(MouseEvent e) {
						// TODO Auto-generated method stub
						
					}
		        });
	}
	
	public Cell getCell(int x, int y) {
		int size = Cell.getSize();
		x = x - 100;
		y = y - 100;
		
		if (x < 0 || y < 0 || x > 8 * size || y > 8 * size) {
			System.out.println("NULL RETURN");
			return null;
		}
		
		x = x / 70;
		y = y / 70;
		
		System.out.println("X Coord: " + x + ", Y Coord: " + y);
		return board[x][y];
	}
	
	public void setUpGame() {
		for (int i = 0; i < 2; i++) {
			for (int j = 0; j < 8; j++) {
				colorBoard[i][j] = 1;
			}
		}
		
		for (int i = 6; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				colorBoard[i][j] = 2;
			}
		}
		
		for (int i = 0; i < 64; i++) {
			int y = i % 8;
			int x = i / 8;
			Piece[] pieces = createPieces();
			Cell c = null;
			if (i == 0 || i == 7) {
				c = new Cell(x, y, pieces[26]);
			} else if (i == 1 || i == 6) {
				c = new Cell(x, y, pieces[22]);
			} else if (i == 2 || i == 5) {
				c = new Cell(x, y, pieces[18]);
			} else if (i == 3) {
				c = new Cell(x, y, pieces[29]);
			} else if (i == 4) {
				c = new Cell(x, y, pieces[31]);
			} else if (i == 56 || i == 63) {
				c = new Cell(x, y, pieces[24]);
			} else if (i == 57 || i == 62) {
				c = new Cell(x, y, pieces[20]);
			} else if (i == 58 || i == 61) {
				c = new Cell(x, y, pieces[16]);
			} else if (i == 59) {
				c = new Cell(x, y, pieces[28]);
			} else if (i == 60) {
				c = new Cell(x, y, pieces[30]);
			} else if (i >= 8 && i < 16) {
				c = new Cell(x, y, pieces[8]);
			} else if (i <= 55 && i >= 48) {
				c = new Cell(x, y, pieces[1]);
			} else {
				c = new Cell(x, y, null);
			}
			board[x][y] = c;
		}
		repaint();
	}
	
	public void clearCells() {
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				board[i][j].clearSelection();
			}
		}
	}
	
	public int[][] getColorBoard() {
		return colorBoard;
	}
	
	public Cell[][] getBoard() {
		return board;
	}
	
	public Piece[] createPieces() {
		Piece[] pieces = new Piece[32];
		for (int i = 0; i < 32; i++) {
			Piece p = null;
			if (i < 8) {
				p = new Piece(true, Type.PAWN);
			} else if (i < 16) {
				p = new Piece(false, Type.PAWN);
			} else if (i < 18) {
				p = new Piece(true, Type.BISHOP);
			} else if (i < 20) {
				p = new Piece(false, Type.BISHOP);
			} else if (i < 22) {
				p = new Piece(true, Type.KNIGHT);
			} else if (i < 24) {
				p = new Piece(false, Type.KNIGHT);
			} else if (i < 26) {
				p = new Piece(true, Type.ROOK);
			} else if (i < 28) {
				p = new Piece(false, Type.ROOK);
			} else if (i == 28) {
				p = new Piece(true, Type.QUEEN);
			} else if (i == 29) {
				p = new Piece(false, Type.QUEEN);
			} else if (i == 30) {
				p = new Piece(true, Type.KING);
			} else {
				p = new Piece(false, Type.KING);
			}
			pieces[i] = p;
		}
		return pieces;
	}
	
	@Override
	public void paintComponent(Graphics gc) {
		Graphics2D g2 = (Graphics2D) gc;
		g2.setStroke(new BasicStroke(10));
		gc.setColor(Color.LIGHT_GRAY);
		g2.fillRect(0, 0, 750, 750);
		g2.setColor(Color.BLACK);
		g2.drawRect(100, 100, 8 * 70, 8 * 70);
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				Cell c = board[i][j];
				try {
					c.drawCell(g2);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	@Override
	public Dimension getPreferredSize() {
		return new Dimension(750, 750);
	}
}
