package logic;

import java.util.ArrayList;

import javafx.scene.image.Image;

public class ChessManager {

	public Chessboard chessboard;
	private ArrayList<Gesture> history;
	public int turn;
	
	int t = 0;
	
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
				
		Gesture g = new Gesture(p, p.actualPos, newPos, p.colour);
		history.add(g);
	/*	System.out.println(g.getTurn() + " " + g.getPiece().name);
		System.out.println("Posizione iniziale   " + g.getStartingPosition().X + " " + g.getStartingPosition().Y);
		System.out.println("Posizione finale   " + g.getFinalPosition().X + " " + g.getFinalPosition().Y);*/
		p.actualPos.occupied = -1;
		p.setPosition(newPos);
		return true;
		
		}
	else{
		return false;
	}
	}
	
	public boolean eat(Piece p1, Piece p2) throws RuntimeException{
		
		System.out.println("entro in eat e il turno è "+ turn);
		boolean hasEaten = false;
		
		if(turn == p1.colour){
			if(p1.colour != p2.colour && !(p2 instanceof King)){
				
				if((p1 instanceof Pawn) && (p2 instanceof Pawn)){
					//hasEaten = enPassant(p1, p2, hasEaten);
				}
				
				/*else if(move(p1,p2.actualPos) && !hasEaten){
					p2.eaten = true;
					hasEaten = true;
				}*/
			}
			System.out.println("return "+hasEaten);
			return hasEaten;
		}
		else{
			return false;
		}
	}
	
	
	public void promove(Piece p, String s){
		Piece tmp = null;
		if(p.colour == 1){
			
						
			if(p instanceof Pawn && !p.promoved && p.actualPos.X==0){
				
				switch(s){
				case "Queen" :
					tmp = new Queen(new Image("file:data/WhiteQueen.png"), 1, p.actualPos);
					break;
				case "Rook" :
					tmp = new Rook(new Image("file:data/WhiteRook.png"),1,p.actualPos);
					break;
				case "Knight" : 
					tmp = new Knight(new Image("file:data/WhiteKnight.png"),1,p.actualPos);
					break;
				case "Bishop" :
					tmp = new Bishop(new Image("file:data/WhiteBishop.png"), 1, p.actualPos);
					break;
				}
			}
			tmp.promoved = true;
			chessboard.getWhite().remove(p);
			chessboard.getWhite().add(tmp);
		}
		
		if(p.colour == 0){
			
			if(p instanceof Pawn && !p.promoved && p.actualPos.X==7){
				
				switch(s){
				case "Queen" :
					tmp = new Queen(new Image("file:data/BlackQueen.png"), 0, p.actualPos);
					break;
				case "Rook" :
					tmp = new Rook(new Image("file:data/BlackRook.png"), 0,p.actualPos);
					break;
				case "Knight" : 
					tmp = new Knight(new Image("file:data/BlackKnight.png"), 0,p.actualPos);
					break;
				case "Bishop" :
					tmp = new Bishop(new Image("file:data/BlackBishop.png"), 0, p.actualPos);
					break;
				}
			}
			tmp.promoved = true;
			chessboard.getBlack().remove(p);
			chessboard.getBlack().add(tmp);
		}
		for (Piece black : getChessBoard().getBlack()) {
			System.out.println(black.promoved);
		}
		for (Piece white : getChessBoard().getWhite()) {
			System.out.println(white.promoved);
		}
		
		
	}
	
	public boolean checkMate(ArrayList<Piece> enemy ){

		ArrayList<Piece> toCheck = enemy;
		boolean check = true;
//		if(turn == 1)
//			toCheck = chessboard.getBlack();
//		else
//			toCheck = chessboard.getWhite();
		
		for (Piece piece : toCheck) {
			ArrayList<Position> permitted = piece.permittedMoves(chessboard.getChessboardPosition());
			for (Position position : permitted) {
//				if(checkMove(piece, position))
//					check = false;
			}
		}	
		return check;
	}
	
	public void update() throws RuntimeException{
		System.out.println("entro in update");
		ArrayList<Piece> enemyPlayer;
		ArrayList<Piece> myPlayer;
		String message;
		System.out.println("turno del : " + turn);
		if(turn == 0){
			enemyPlayer = chessboard.getWhite();
			myPlayer = chessboard.getBlack();
			message = "Nero";
		}
		else{
			enemyPlayer = chessboard.getBlack();
			myPlayer = chessboard.getWhite();
			message = "Bianco";
		}

		for (Piece piece : myPlayer) {
			if(piece instanceof King && !isPermitKing((King) piece, enemyPlayer)){
				System.out.println("sei tu?");
				throw new RuntimeException("Scacco al Re");
			}
		}
		
	/*	if(checkMate(enemyPlayer)){
			System.out.println("Scacco matto");
			throw new RuntimeException("Scacco Matto");
		} */
	}
	
	public boolean isPermitKing(King k, ArrayList<Piece> enemy) throws RuntimeException{
		for (Piece piece : enemy) {
				ArrayList<Position> permitted = piece.permittedMoves(chessboard.getChessboardPosition());
				for (Position position : permitted) {
					if(position.equals(k.actualPos)){
						//throw new RuntimeException("Re Sotto Scacco");
						return false;
					}
			}
		}
		
		return true;
	}

	public int checkMove(Piece p, Position next , String promotion) throws RuntimeException{
		
		System.out.println("voglio andare in posizione "+ next.X + " " + next.Y);
		ArrayList<Position> position = p.permittedMoves(chessboard.getChessboardPosition());
		ArrayList<Piece> myPlayer;
		ArrayList<Piece> enemy;
		System.out.println("il turno è : " + turn);
		if(turn == 0){
			myPlayer = this.getChessBoard().getBlack();
			enemy = this.getChessBoard().getWhite();
		}else{
			myPlayer = this.getChessBoard().getWhite();
			enemy = this.getChessBoard().getBlack();
		}
		King enemyKing = getKing(enemy);
		King myKing = getKing(myPlayer);
		
		
		for (Position pos : position) {
			
			if(pos.equals(next) && !enemyKing.getPosition().equals(next)){			
				
				int tmpOccupied = next.occupied;
				Position initial = p.getPosition();
				
				
				//p.setPosition(next);
				move(p, next);
				turn = enemyKing.colour;
				
				boolean hasEaten = false;
				try{
					if(!isPermitKing(myKing, enemy))
						throw new RuntimeException("Re Sotto Scacco");
				for (Piece enemyPiece : enemy) {
										
					if (next.equals(enemyPiece.getPosition()) && !enemyPiece.eaten) {
						System.out.println("HO TROVATO IL PEZZO DA MANGIARE");
						System.out.println("mangio in logic");
						enemyPiece.eaten = true;
						hasEaten = true;
					}	
				}
						

				
				if(p instanceof Pawn)
					enPassant(p, next, initial, hasEaten);
				
				if(p instanceof Pawn && (p.getPosition().X == 0 || p.getPosition().X == 7) ){
					promove(p, promotion);
				}
				
				for (int i = 0; i < 8; i++) {
					for (int j = 0; j < 8; j++) {
						System.out.print(chessboard.getChessboardPosition()[i][j].occupied + "   ");
					}
					System.out.println();
				}
				
				
				}catch(RuntimeException ru){
					if(ru.getLocalizedMessage() == "Mossa Non Valida" || ru.getLocalizedMessage() == "Re Sotto Scacco" ){
						System.out.println("ecceccion");
						turn = p.colour;
						move(p, initial);
						next.occupied = tmpOccupied;
						throw ru;
					}
				}
			}//fine if
		}
		return turn;
		
	}
	
	private King getKing(ArrayList<Piece> list){
		for (Piece piece : list) {
			if (piece instanceof King) {
				return (King) piece;
			}
		}
		return null;
	}
	
	private void enPassant(Piece p1, Position next, Position prev, boolean hasEaten) throws RuntimeException{
		int s; 
		if(p1.colour == 1)
			s = -1;
		else
			s = 1;
		if (p1 instanceof Pawn && !hasEaten && ( ( next.X == prev.X+s && next.Y == prev.Y+s ) || ( next.X == prev.X+s && next.Y == prev.Y-s ) ) ) {
			if (!history.isEmpty()) {
				
				Gesture g = history.get(history.size() - 2);
				
				Position p2Init = g.getStartingPosition();

				if( !(p2Init.X-s == next.X  &&  p2Init.Y == next.Y) )
					throw new RuntimeException("Mossa Non Valida");
				else
					{g.getPiece().eaten = true;	System.out.println("appo enpassant giusto");	}	
			}
		}
	}
	
	
	
}
