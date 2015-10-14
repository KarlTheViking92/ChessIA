package logic;

import java.awt.Image;
import java.util.ArrayList;

public class Rook extends Piece {

	public Rook(Image img, int col, Position pos) {
		super("rook", img, col, 5, pos);
	}

	public ArrayList<Position> permittedMoves(Position[][] pos) {
		int j = 0;
		//1 direzione
		while(isPermitted(actualPos.X+j, actualPos.Y, pos) && pos[actualPos.X+j][actualPos.Y].occupied == -1){
			if(isPermitted(actualPos.X+j, actualPos.Y, pos)){
				permpos.add(new Position(actualPos.X+j,actualPos.Y));
				j++;
			}
		}
		//2 direzione
		while(isPermitted(actualPos.X, actualPos.Y+j, pos) && pos[actualPos.X][actualPos.Y+j].occupied == -1){
			j = 0;
			if(isPermitted(actualPos.X, actualPos.Y+j, pos)){
				permpos.add(new Position(actualPos.X,actualPos.Y+j));
				j++;
			}
		}
		
		//3 direzione
		while(isPermitted(actualPos.X-j, actualPos.Y, pos) && pos[actualPos.X-j][actualPos.Y].occupied == -1){
			j = 0;
			if(isPermitted(actualPos.X-j, actualPos.Y, pos)){
				permpos.add(new Position(actualPos.X-j,actualPos.Y));
				j++;
			}
		}
		//4 direzione
		while(isPermitted(actualPos.X, actualPos.Y-j, pos) && pos[actualPos.X][actualPos.Y-j].occupied == -1){
			j = 0;
			if(isPermitted(actualPos.X, actualPos.Y-j, pos)){
				permpos.add(new Position(actualPos.X, actualPos.Y-j));
				j++;
			}
		}
		return permpos;
	}

}
