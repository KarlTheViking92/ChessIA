package logic;


import java.util.ArrayList;
import javafx.scene.image.Image;

public class Queen extends Piece {

	public Queen(Image img, int col, Position pos) {
		super("queen", img, col, 9, pos);
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
		//5 direzione
		while(isPermitted(actualPos.X+j, actualPos.Y+j, pos) && pos[actualPos.X+j][actualPos.Y+j].occupied == -1){
			if(isPermitted(actualPos.X+j, actualPos.Y+j, pos)){
				permpos.add(new Position(actualPos.X+j, actualPos.Y+j));
			}
		}
			
			//6 direzione 
			while(isPermitted(actualPos.X+j, actualPos.Y+j, pos) && pos[actualPos.X+j][actualPos.Y+j].occupied == -1){
				j = 0;
				if(isPermitted(actualPos.X-j, actualPos.Y-j, pos)){
					permpos.add(new Position(actualPos.X-j, actualPos.Y-j));
					j++;
				}
			}
			//7 direzione 
				while(isPermitted(actualPos.X+j, actualPos.Y+j, pos) && pos[actualPos.X+j][actualPos.Y+j].occupied == -1){
					j = 0;
					if(isPermitted(actualPos.X+j, actualPos.Y-j, pos)){
						permpos.add(new Position(actualPos.X+j, actualPos.Y-j));
						j++;
					}
				}
			//8 direzione
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
