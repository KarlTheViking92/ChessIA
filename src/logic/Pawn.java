package logic;


import java.util.ArrayList;

import javafx.scene.image.Image;


public class Pawn extends Piece {
	
	private ArrayList<Piece> enemyPieces;
	private int s ,t, u;

	public Pawn(Image img, int col, Position pos, ArrayList<Piece> eP) {
		super("pawn", img, col, 1 , pos);
		
		this.enemyPieces = eP;
	}

	public ArrayList<Position> permittedMoves(Position[][] pos) {
		
		permpos.clear();
		
		if(colour==0) {s=1;t=1;u=4;}
		else {s=-1;t=6;u=3;}
		
		
		if(isPermitted(actualPos.X  + s , actualPos.Y, pos) && pos[actualPos.X + s ][actualPos.Y ].occupied == -1)
			permpos.add(new Position(actualPos.X + s, actualPos.Y ));
			
		
		if(isPermitted(actualPos.X + s , actualPos.Y + s, pos) && pos[actualPos.X + s][actualPos.Y + s ].occupied != -1)
			permpos.add(new Position(actualPos.X + s, actualPos.Y + s));
		
		if(isPermitted(actualPos.X + s , actualPos.Y - s, pos) && pos[actualPos.X + s ][actualPos.Y - s ].occupied != -1)
			permpos.add(new Position(actualPos.X + s, actualPos.Y - s));
		
		if(actualPos.X == t && isPermitted(actualPos.X + s , actualPos.Y , pos) && pos[actualPos.X + s][actualPos.Y].occupied == -1){
			if(isPermitted(actualPos.X + 2*s , actualPos.Y , pos) && pos[actualPos.X + 2*s][actualPos.Y].occupied == -1)
				permpos.add(new Position(actualPos.X +2*s, actualPos.Y ));
		}
		
		if (actualPos.X == u) {
			isEnPassant(s, pos);
			
			isEnPassant(-s, pos);
		}
		
		
		return permpos;
	}
	
	private void isEnPassant(int z, Position pos[][]){
		if (isPermitted(actualPos.X + s, actualPos.Y + z, pos)) {
			for (int i = 0; i < 8; i++) {
				if (!enemyPieces.get(i).eaten && !enemyPieces.get(i).promoved && enemyPieces.get(i).actualPos == pos[actualPos.X][actualPos.Y + z]) {
					permpos.add(new Position(actualPos.X + s, actualPos.Y + z));
					break;
				}
			}
			
		}
		
	}


	public ArrayList<Position> permittedMovesKing (Position pos [] []){
		if(colour == 0)
			s=1;
		else 
			s=-1;
		if(isPermitted(actualPos.X+s, actualPos.Y+s, pos))
			permpos.add(new Position(actualPos.X+s, actualPos.Y+s));
		if(isPermitted(actualPos.X+s, actualPos.Y-s, pos))
			permpos.add(new Position(actualPos.X+s,actualPos.Y-s));
		return permpos;
	}
}