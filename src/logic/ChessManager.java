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
	
	public int next(Piece p , Position next, String promotion , boolean virtual) throws RuntimeException{
		
		Gesture g = new Gesture(p, p.actualPos, next, p.colour);
		int turnTmp = checkMove(p, next, promotion, false);
		
		if(turnTmp != turn){
			history.add(g);
			turn = turnTmp;
		}
		
		return turnTmp;
		
	}
	
	
	public boolean move(Piece p,Position newPos){
			
		p.actualPos.occupied = -1;
		p.setPosition(newPos);
		return true;

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
	/*	for (Piece black : getChessBoard().getBlack()) {
			System.out.println(black.promoved);
		}
		for (Piece white : getChessBoard().getWhite()) {
			System.out.println(white.promoved);
		} */
		
		
	}
	
	public boolean checkMate(ArrayList<Piece> enemy , ArrayList<Piece> myPlayer){

		boolean check = true;
		
		for (Piece piece : enemy) {
			if(!piece.eaten){
				ArrayList<Position> permitted = piece.permittedMoves(chessboard.getChessboardPosition());
					
				for (int i = 0; i < permitted.size() && check; i++) {
	
					int ble = checkMove(piece, permitted.get(i) , "promozione", true);
					if(ble != piece.colour){
						check = false;
					}
				}
				if(!check) break;

			}	
		}
		return check;
	}
	
	public void update() throws RuntimeException{
		System.out.println("entro in update");
		System.out.println("turno del : " + turn);
		ArrayList<Piece> enemyPlayer;
		ArrayList<Piece> myPlayer;
		String message;
		if(turn == 1){
			enemyPlayer = chessboard.getWhite();
			myPlayer = chessboard.getBlack();
			message = "Nero";
		}
		else{
			enemyPlayer = chessboard.getBlack();
			myPlayer = chessboard.getWhite();
			message = "Bianco";
		}

		for (Piece piece : enemyPlayer) {
			if(piece instanceof King ){
				if( !isPermitKing( (King) piece, myPlayer )){
					boolean scaccomatto = checkMate(enemyPlayer, myPlayer);
					if(!scaccomatto){
						throw new RuntimeException("Scacco Al Re");
					} else
						throw new RuntimeException("Scacco Matto");
				
				}else
					System.out.println("apposto niente scacco");
				
			}
		}
	}
	
	public boolean isPermitKing(King k, ArrayList<Piece> enemy) throws RuntimeException{
		for (Piece piece : enemy) {
			if(!piece.eaten){
				ArrayList<Position> permitted = piece.permittedMoves(chessboard.getChessboardPosition());
				for (Position position : permitted) {
					if(position.equals(k.actualPos)){
						//throw new RuntimeException("Re Sotto Scacco");
						return false;
					}
				}
			}
		}
		
		return true;
	}

	public int checkMove(Piece p, Position n , String promotion , boolean virtual){
		
//		System.out.println("voglio andare in posizione "+ n.X + " " + n.Y);
		ArrayList<Position> position = p.permittedMoves(chessboard.getChessboardPosition());
		ArrayList<Piece> myPlayer;
		ArrayList<Piece> enemy;
		Position next = chessboard.getChessboardPosition()[n.X][n.Y];
		int turnTmp = turn;
		Position initial = p.getPosition();
		
		int tmpOccupied = next.occupied;
		
		System.out.println("il turno è : " + turnTmp);
		if(turnTmp == 0){
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
				System.out.println("pezzo in posizione : " + p.getPosition().X + "  " + p.getPosition().Y);
				//p.setPosition(next);	
				move(p, next);
				turnTmp = enemyKing.colour;
				boolean hasEaten = false;
				
				try{
					
					if(!isPermitKing(myKing, enemy)){
						System.out.println("eppure entro");
						if(virtual){
							turnTmp = p.colour;
							move(p, initial);
							next.occupied =  tmpOccupied ;
						}
						else{
							System.out.println("myking : " + myKing.colour);
							throw new RuntimeException("Scacco Al Re");
						
						}
							
					}
				for (Piece enemyPiece : enemy) {
										
					if (next.equals(enemyPiece.getPosition()) && !enemyPiece.eaten && !virtual) {
						System.out.println("mangio il pezzo : " + enemyPiece.name + "  " + enemyPiece.getPosition().X + " " + p.getPosition().Y);
						enemyPiece.eaten = true;
						hasEaten = true;
					}	
				}
										
				if(p instanceof Pawn && !virtual)
					enPassant(p, next, initial, hasEaten, virtual);
				
				if(p instanceof Pawn && (p.getPosition().X == 0 || p.getPosition().X == 7) && promotion != null ){
					promove(p, promotion);
				}
				
				
				}catch(RuntimeException ru){
					if(ru.getLocalizedMessage() == "Mossa Non Valida" || ru.getLocalizedMessage() == "Scacco Al Re" ){
						
						System.out.println("ecceccion");
						
						turnTmp = p.colour;
						move(p, initial);
						next.occupied =  tmpOccupied ;
						//ru.printStackTrace();
						throw ru;
					}
				}
			}//fine if
		}
		
		if(virtual){
			System.out.println("ripristino la posizione iniziale del pezzo in " + p.getPosition().X + "  " + p.getPosition().Y);
			System.out.println(p.getColour() + " " + p.name);
			move(p, initial);
			next.occupied = tmpOccupied;
		}
		
/*		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				System.out.print(chessboard.getChessboardPosition()[i][j].occupied + "   ");
			}
			System.out.println();
		}  */ 
		
		
		return turnTmp;
		
	}
	
	private King getKing(ArrayList<Piece> list){
		for (Piece piece : list) {
			if (piece instanceof King) {
				return (King) piece;
			}
		}
		return null;
	}
	
	private void enPassant(Piece p1, Position next, Position prev, boolean hasEaten, boolean virtual) throws RuntimeException{
		int s; 
		if(p1.colour == 1)
			s = -1;
		else
			s = 1;
		if (p1 instanceof Pawn && !hasEaten && ( ( next.X == prev.X+s && next.Y == prev.Y+s ) || ( next.X == prev.X+s && next.Y == prev.Y-s ) ) ) {
			System.out.println("history " + history.size());
			if (!history.isEmpty()) {
				
				Gesture g = history.get(history.size() - 1);
				
				
				Position p2Init = g.getStartingPosition();

				if( !(p2Init.X-s == next.X  &&  p2Init.Y == next.Y))
					throw new RuntimeException("Mossa Non Valida");
				else
					{g.getPiece().eaten = true;	System.out.println("appo enpassant giusto");	}	
			}
		}
	}
	
	
	
}
