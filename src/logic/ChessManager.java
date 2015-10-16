package logic;

import java.util.ArrayList;

public class ChessManager {

	private Chessboard chessboard;
	private Eaten eatens;
	
	public ChessManager() {
		this.chessboard = new Chessboard();
	}
	
	public boolean move(Piece p,Position newPos){
		ArrayList<Position> position = p.permittedMoves(chessboard.getChessboard());
		for (Position pos : position) {
			if(pos.equals(newPos)){
				p.actualPos.occupied = -1;
				p.setPosition(newPos);
				return true;
			}
		}
		return false;
	}
	
	public void eat(Piece p1, Piece p2){
		if(p1.colour != p2.colour && !(p2 instanceof King)){
			boolean hasEaten = false;
			if(move(p1,p2.actualPos)){
				p2.eaten = true;
				hasEaten = true;
			}
			if((p1 instanceof Pawn) && (p2 instanceof Pawn)){
				int s = 1;
				if(p1.colour == 1)
					s = -1;
				if(move(p1,new Position(p2.actualPos.X+s, p2.actualPos.Y))){
					p2.eaten = true;
				}
			}
		}
	}
	
	public void promove(Piece p){
		
	}
	
	
	
}
