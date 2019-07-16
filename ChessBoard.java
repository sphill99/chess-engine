package components;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;
import java.awt.image.BufferedImage;

@SuppressWarnings("serial") 
public class ChessBoard extends JComponent {
	int[][] board = new int[8][8];
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
		board[0][0] = 4;
		board[0][1] = 2;
		board[0][2] = 3;
		board[0][3] = 6;
		board[0][4] = 5;
		board[0][7] = 4;
		board[0][6] = 2;
		board[0][5] = 3;
		
		//Set up white back row
		board[7][0] = 4;
		board[7][1] = 2;
		board[7][2] = 3;
		board[7][3] = 6;
		board[7][4] = 5;
		board[7][7] = 4;
		board[7][6] = 2;
		board[7][5] = 3;
		
		//Set up pawns
		for (int i = 0; i < 8; i++) {
			board[1][i] = 1;
			board[6][i] = 1;
		}
		repaint();
	}
	
	
}
