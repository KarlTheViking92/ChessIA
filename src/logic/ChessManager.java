package logic;

import java.util.ArrayList;

public class ChessManager {

	private Chessboard chessboard;
	private ArrayList<Gesture> history;
	
	public ChessManager() {
		this.chessboard = new Chessboard();
		this.history = new ArrayList<Gesture>();
	}
	public Chessboard getChessBoard(){
		return chessboard;
	}
	
	public boolean move(Piece p,Position newPos){
		ArrayList<Position> position = p.permittedMoves(chessboard.getChessboardPosition());
		for (Position pos : position) {
			if(pos.equals(newPos)){
				history.add(new Gesture(p, p.actualPos, newPos, p.colour));
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
				
				if ( !hasEaten && (p1.getPosition().X == p2.getPosition().X && p1.getPosition().Y == p2.getPosition().Y - s) || (p1.getPosition().X == p2.getPosition().X && p1.getPosition().Y == p2.getPosition().Y + s)) {
				
					if(!history.isEmpty()){
						Gesture last = history.get(history.size()-1);
						Position tmp = last.getStartingPosition();
						if ( !p1.getPosition().equals(new Position(tmp.X - s, tmp.Y)) ){
							throw new RuntimeException("Mossa Non Valida");
						}
						else{
							move(p1, new Position(p2.getPosition().X - s, p2.getPosition().Y));
							p2.eaten = true;
						}
						
					}
				}
				
			/*	if(move(p1,new Position(p2.actualPos.X+s, p2.actualPos.Y))){
					p2.eaten = true;
				}*/
			}
		}
	}
	
	public void promove(Piece p){
		
	}
	
	
	
}
