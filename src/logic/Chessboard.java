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
		
		black.add(new Rook(null, 0, chessboardPosition[0][0]));
		black.add(new Rook(null, 0, chessboardPosition[0][7]));
		black.add(new Knight(null, 0, chessboardPosition[0][1]));
		black.add(new Knight(null, 0, chessboardPosition[0][6]));
		black.add(new Bishop(null, 0, chessboardPosition[0][2]));
		black.add(new Bishop(null, 0, chessboardPosition[0][5]));
		black.add(new Queen(null, 0, chessboardPosition[0][3]));
		black.add(new King(null, 0, chessboardPosition[0][4]));
		
		
		
	}
	
	public static void main(String[] args) {
		Chessboard chess = new Chessboard();
	}
	
}
