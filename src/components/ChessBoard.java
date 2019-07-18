package components;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;
import java.awt.image.BufferedImage;

@SuppressWarnings("serial") 
public class ChessBoard extends JComponent {
	Cell[][] board = new Cell[8][8];
	private JPanel gameBoard;

	
	public ChessBoard() {
		setUpGame();
	}
	
	/*
	 * Pawn = 1
	 * Knight = 2
	 * Bishop = 3
	 * Rook = 4
	 * Queen = 5
	 * King = 6
	 * Empty = 0
	*/
	public void setUpGame() {
       
		gameBoard = new JPanel(new GridLayout(0, 9));
		gameBoard.setBorder(new LineBorder(Color.DARK_GRAY));
		this.add(gameBoard);
		//Set up black back row
		Cell[0][0] = new Cell()
		repaint();
	}
	
	public Piece[] createPieces() {
		Piece[] pieces = new Piece[32];
		for (int i = )
	}
	
	
}
