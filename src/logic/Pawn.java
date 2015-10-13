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
		
		
		return null;
	}
	
	public static void main(String[] args) {
		Piece p = new Pawn(null, 1, new Position(0, 0), null);
		
		p.permpos.add(new Position(1, 1));
		
		for (Position pos : p.permpos) {
			System.out.println(pos.X);
		}
		System.out.println("finito");
	}
}
