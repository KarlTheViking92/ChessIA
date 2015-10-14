package logic;

import java.awt.Image;
import java.util.ArrayList;

public class Bishop extends Piece {

	public Bishop(Image img, int col, int val, Position pos) {
		super("bishop", img, col, 3, pos);
	}

	public ArrayList<Position> permittedMoves(Position[][] pos) {
		//1 direzione positiva 
		int j = 0;
		while(isPermitted(actualPos.X+j, actualPos.Y+j, pos) && pos[actualPos.X+j][actualPos.Y+j].occupied == -1){
			if(isPermitted(actualPos.X+j, actualPos.Y+j, pos)){
				permpos.add(new Position(actualPos.X+j, actualPos.Y+j));
			}
		}
			
			//2 direzione 
			while(isPermitted(actualPos.X+j, actualPos.Y+j, pos) && pos[actualPos.X+j][actualPos.Y+j].occupied == -1){
				j = 0;
				if(isPermitted(actualPos.X-j, actualPos.Y-j, pos)){
					permpos.add(new Position(actualPos.X-j, actualPos.Y-j));
					j++;
				}
			}
			//3 direzione 
				while(isPermitted(actualPos.X+j, actualPos.Y+j, pos) && pos[actualPos.X+j][actualPos.Y+j].occupied == -1){
					j = 0;
					if(isPermitted(actualPos.X+j, actualPos.Y-j, pos)){
						permpos.add(new Position(actualPos.X+j, actualPos.Y-j));
						j++;
					}
				}
			//4 direzione
				while(isPermitted(actualPos.X+j, actualPos.Y+j, pos) && pos[actualPos.X+j][actualPos.Y+j].occupied == -1){
					j = 0;
					if(isPermitted(actualPos.X-j, actualPos.Y+j, pos)){
						permpos.add(new Position(actualPos.X-j, actualPos.Y+j));
						j++;
					}
				}
				return permpos;
	}

}
