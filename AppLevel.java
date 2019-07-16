package components;


import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class AppLevel {
	private static void createAndShowGUI() {
		ChessBoard game = new ChessBoard();
		JFrame chess = new JFrame("Chess Game");
		chess.getContentPane().add(game);
		
		chess.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
        JLabel emptyLabel = new JLabel("");
        emptyLabel.setPreferredSize(new Dimension(700, 700));
        chess.getContentPane().add(emptyLabel, BorderLayout.CENTER);

        //Display the window.
        chess.pack();
        chess.setVisible(true);
    }

    public static void main(String[] args) {
        //Schedule a job for the event-dispatching thread:
        //creating and showing this application's GUI.
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }
}
