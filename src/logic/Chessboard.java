package logic;

import java.util.ArrayList;

public class Chessboard {

	private ArrayList<Piece> white;
	private ArrayList<Piece> black;
	private Position[][] chessboardPosition;
	
	
	public Chessboard() {
		chessboardPosition = new Position[8][8];
		white = new ArrayList<>();
		black = new ArrayList<>();
		
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				chessboardPosition[i][j] = new Position(i, j);
			}
		}
		// creazione Pedoni neri
		for (int i = 0; i < 8; i++) {
			black.add(new Pawn(null, 0, chessboardPosition[1][i], white));
		}
		
//		black.add()
		

	}
	
	
}
