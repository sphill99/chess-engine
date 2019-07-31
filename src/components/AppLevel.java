package components;


import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class AppLevel implements Runnable{
		public void run() {
	        JFrame frame = new JFrame("Battleship");
	        ChessBoard battleship = new ChessBoard();
	        frame.getContentPane().add(battleship);
	        
	        frame.pack();
	        frame.setResizable(false);
	        frame.setLocationRelativeTo(null);
	        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        frame.setVisible(true);
	        battleship.requestFocusInWindow();
	    }

	    public static void main(String[] args) {
	        SwingUtilities.invokeLater(new AppLevel());
	    }
}
