package components;

import java.util.ArrayList;
import java.util.List;

public class Piece {
	private Type t;
	private int val;
	private boolean isWhite;
	
	public Piece(boolean white, Type t) {
		isWhite = white;
		this.t = t;
		this.val = t.getVal();
	}
		
	public boolean isWhite() {
		return isWhite;
	}

	public Type getPiece() {
		return t;
	}


	public void setPiece(Type t) {
		this.t = t;
	}

	public int getVal() {
		return val;
	}

	public enum Type {
		EMPTY(0, ""),
		PAWN(1, "Pawn"), 
		ROOK(5, "Rook"), 
		KNIGHT(3, "Knight"), 
		BISHOP(3, "Bishop"), 
		QUEEN(10, "Queen"), 
		KING(100, "King");
		
		private final int value;
		private final String name;
		
		private Type(int value, String name) {
			this.value = value;
			this.name = name;
		}
		
		public int getVal() {
			return value;
		}
		
		public String getName() {
			return name;
		}
	}
}
