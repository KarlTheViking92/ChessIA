package logic;

import java.util.ArrayList;

import javafx.scene.image.Image;

public class ChessManager {

	private Chessboard chessboard;
	private ArrayList<Gesture> history;
	public int turn;
	
	public ChessManager() {
		this.chessboard = new Chessboard();
		this.history = new ArrayList<Gesture>();
		this.turn = 1; 
	}
	public Chessboard getChessBoard(){
		return chessboard;
	}
	
	public boolean move(Piece p,Position newPos){
		if(turn == p.colour){
			ArrayList<Position> position = p.permittedMoves(chessboard.getChessboardPosition());
			for (Position pos : position) {
				if(pos.equals(newPos)){
					history.add(new Gesture(p, p.actualPos, newPos, p.colour));
					p.actualPos.occupied = -1;
					p.setPosition(newPos);
					
					if(turn == 1)
						turn = 0;
					else
						turn = 1;
					
					return true;
				}
			}
			return false;
		}
		else{
			System.out.println("non è il tuo turno");
			return false;
		}
	}
	
	public void eat(Piece p1, Piece p2){
		if(turn == p1.colour){
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
				}
			}
			turn = p2.colour;
		}
		else{
			System.out.println("non è il tuo turno");
		}
	}
	
	public boolean checkMove(Piece p, Position next){
		
		ArrayList<Position> position = p.permittedMoves(chessboard.getChessboardPosition());
		for (Position pos : position) {
			if(pos.equals(next)){				
				return true;
			}
		}
		return false;
	}
	
	
	public void promove(Piece p, String s){
		if(p.colour == 1){
			if(p instanceof Pawn && !p.promoved && p.actualPos.X==0){
				p.promoved = true;
				switch(s){
				case "Queen" :
					Piece queen = new Queen(new Image("file:data/WhiteQueen.png"), 1, p.actualPos);
					break;
				case "Rook" :
					Piece rook = new Rook(new Image("file:data/WhiteRook.png"),1,p.actualPos);
					break;
				case "Knight" : 
					Piece knight = new Knight(new Image("file:data/WhiteKnight.png"),1,p.actualPos);
					break;
				case "Bishop" :
					Piece bishop = new Bishop(new Image("file:data/WhiteBishop.png"), 1, p.actualPos);
					break;
				}
			}
		}
		
		if(p.colour == 0){
			if(p instanceof Pawn && !p.promoved && p.actualPos.X==7){
				p.promoved = true;
				switch(s){
				case "Queen" :
					Piece queen = new Queen(new Image("file:data/BlackQueen.png"), 0, p.actualPos);
					break;
				case "Rook" :
					Piece rook = new Rook(new Image("file:data/BlackRook.png"), 0,p.actualPos);
					break;
				case "Knight" : 
					Piece knight = new Knight(new Image("file:data/BlackKnight.png"), 0,p.actualPos);
					break;
				case "Bishop" :
					Piece bishop = new Bishop(new Image("file:data/BlackBishop.png"), 0, p.actualPos);
					break;
				}
			}
		}
		
	}
	
	public boolean checkMate(){

		ArrayList<Piece> toCheck;
		boolean check = true;
		if(turn == 1)
			toCheck = chessboard.getBlack();
		else
			toCheck = chessboard.getWhite();
		
		for (Piece piece : toCheck) {
			ArrayList<Position> permitted = piece.permittedMoves(chessboard.getChessboardPosition());
			for (Position position : permitted) {
				if(checkMove(piece, position))
					check = false;
			}
		}	
		return check;
	}
	
	public void update(){
		ArrayList<Piece> enemyPlayer;
		ArrayList<Piece> myPlayer;
		String message;
		if(turn == 0){
			enemyPlayer = chessboard.getBlack();
			myPlayer = chessboard.getWhite();
		}
		else{
			enemyPlayer = chessboard.getWhite();
			myPlayer = chessboard.getBlack();
		}
		if(turn == 0)
			message = "Black";
		else
			message = "White";
		for (Piece piece : enemyPlayer) {
			if(piece instanceof King && !this.isPermitKing((King) piece, enemyPlayer)){
				System.out.println("scacco al Re "+message);
			
				if(this.checkMate()){
					System.out.println("Scacco matto");
				}
			}
		}
	}
	
	public boolean isPermitKing(King k, ArrayList<Piece> enemy){
		for (Piece piece : enemy) {
			if(!piece.eaten && !piece.promoved){
				ArrayList<Position> permitted = piece.permittedMoves(chessboard.getChessboardPosition());
				for (Position position : permitted) {
					if(position.equals(k.actualPos))
						return true;
				}
			}
		}
		
		return false;
	}
	
}
