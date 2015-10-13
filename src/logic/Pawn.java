package logic;

import java.awt.Image;

public class Pawn extends Piece {
	
	private Piece enemyPieces[];
	private int i, s ,t, u;

	public Pawn(Image img, int col, Position pos, Piece eP[]) {
		super("pawn", img, col, 1 , pos);
		
		this.enemyPieces = eP;
	}

	public Position[] permittedMoves(Position[][] pos) {
		
		i=0;
		if(colour==1) {s=1;t=1;u=4;}
		else {s=-1;t=6;u=3;}
		
		if(isPermitted(actualPos.X , actualPos.Y + s, pos) && pos[actualPos.X][actualPos.Y + s ].occupied != -1)
			permpos.add(new Position(actualPos.X, actualPos.Y));
		
		return null;
	}
	

}
