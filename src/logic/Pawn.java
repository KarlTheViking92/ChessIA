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
		
		if(colour==1) {s=1;t=1;u=4;}
		else {s=-1;t=6;u=3;}
		
		if(isPermitted(actualPos.X , actualPos.Y + s, pos) && pos[actualPos.X][actualPos.Y + s ].occupied == -1)
			permpos.add(new Position(actualPos.X, actualPos.Y + s));
		
		if(isPermitted(actualPos.X + s , actualPos.Y + s, pos) && pos[actualPos.X + s][actualPos.Y + s ].occupied != -1)
			permpos.add(new Position(actualPos.X + s, actualPos.Y + s));
		
		if(isPermitted(actualPos.X - s , actualPos.Y + s, pos) && pos[actualPos.X - s ][actualPos.Y + s ].occupied != -1)
			permpos.add(new Position(actualPos.X - s, actualPos.Y - s));
		
		if(actualPos.Y == t && isPermitted(actualPos.X , actualPos.Y + (s*2), pos) && pos[actualPos.X][actualPos.Y + s ].occupied == -1)
			permpos.add(new Position(actualPos.X, actualPos.Y + (s*2)));
		
		if (actualPos.Y == u) {
			isEnPassant(s, pos);
			
			isEnPassant(-s, pos);
		}
		
		
		return permpos;
	}
	
	private void isEnPassant(int z, Position pos[][]){
		if (isPermitted(actualPos.X + z, actualPos.Y + s, pos)) {
			for (int i = 0; i < 8; i++) {
				if (!enemyPieces.get(i).eaten && !enemyPieces.get(i).promoved && enemyPieces.get(i).actualPos == pos[actualPos.X + z][actualPos.Y]) {
					permpos.add(new Position(actualPos.X + z, actualPos.Y + s));
					break;
				}
			}
			
		}
		
	}
	

}
