package logic;

public class Gesture {
	
	private Piece piece;
	private Position startingPosition;
	private Position finalPosition;
	private int turn;
	
	
	public Gesture(Piece p, Position sP, Position fP, int t) {
		this.piece = p;
		this.startingPosition = sP;
		this.finalPosition = fP;
		this.turn = t;
	}


	public Position getStartingPosition() {
		return startingPosition;
	}


	public Position getFinalPosition() {
		return finalPosition;
	}


	public int getTurn() {
		return turn;
	}
	public Piece getPiece(){
		return this.piece;
	}

}
