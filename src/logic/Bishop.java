package logic;


import java.util.ArrayList;

import javafx.scene.image.Image;

public class Bishop extends Piece {

	public Bishop(Image img, int col, Position pos) {
		super("bishop", img, col, 3, pos);
	}

	public ArrayList<Position> permittedMoves(Position[][] pos) {
		permpos.clear();
		//1 direzione positiva 
		int j = 1;
		while(isPermitted(actualPos.X+j, actualPos.Y+j, pos) && pos[actualPos.X+j][actualPos.Y+j].occupied == -1){
			if(isPermitted(actualPos.X+j, actualPos.Y+j, pos))
				permpos.add(new Position(actualPos.X+j, actualPos.Y+j));
			
			j++;
		}
		if(isPermitted(actualPos.X+j, actualPos.Y+j, pos) && pos[actualPos.X+j][actualPos.Y+j].occupied != this.colour)
			permpos.add(new Position(actualPos.X+j, actualPos.Y+j));
				
		
			
			//2 direzione 
		j = 1;
			while(isPermitted(actualPos.X-j, actualPos.Y-j, pos) && pos[actualPos.X-j][actualPos.Y-j].occupied == -1){
				if(isPermitted(actualPos.X-j, actualPos.Y-j, pos))
					permpos.add(new Position(actualPos.X-j, actualPos.Y-j));
					
				j++;
			}
			if(isPermitted(actualPos.X-j, actualPos.Y-j, pos) && pos[actualPos.X-j][actualPos.Y-j].occupied != this.colour)
				permpos.add(new Position(actualPos.X-j, actualPos.Y-j));
			
			//3 direzione 
			j = 1;
				while(isPermitted(actualPos.X+j, actualPos.Y-j, pos) && pos[actualPos.X+j][actualPos.Y-j].occupied == -1){
					if(isPermitted(actualPos.X+j, actualPos.Y-j, pos))
						permpos.add(new Position(actualPos.X+j, actualPos.Y-j));
						
					j++;
				}
				if(isPermitted(actualPos.X+j, actualPos.Y-j, pos) && pos[actualPos.X+j][actualPos.Y-j].occupied != this.colour)
					permpos.add(new Position(actualPos.X+j, actualPos.Y-j));
			//4 direzione
				j = 1;
				while(isPermitted(actualPos.X-j, actualPos.Y+j, pos) && pos[actualPos.X-j][actualPos.Y+j].occupied == -1){
					if(isPermitted(actualPos.X-j, actualPos.Y+j, pos))
						permpos.add(new Position(actualPos.X-j, actualPos.Y+j));
						
					j++;
				}
				if(isPermitted(actualPos.X-j, actualPos.Y+j, pos) && pos[actualPos.X-j][actualPos.Y+j].occupied != this.colour)
					permpos.add(new Position(actualPos.X-j, actualPos.Y+j));
					return permpos;
	}

}
