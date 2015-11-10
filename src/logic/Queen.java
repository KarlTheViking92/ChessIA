package logic;


import java.util.ArrayList;
import javafx.scene.image.Image;

public class Queen extends Piece {

	public Queen(Image img, int col, Position pos) {
		super("queen", img, col, 9, pos);
	}

	public ArrayList<Position> permittedMoves(Position[][] pos) {
		permpos.clear();
		int j = 1;
		//1 direzione
		while(isPermitted(actualPos.X+j, actualPos.Y, pos) && pos[actualPos.X+j][actualPos.Y].occupied == -1){
			if(isPermitted(actualPos.X+j, actualPos.Y, pos))
				permpos.add(new Position(actualPos.X+j,actualPos.Y));
			j++;
		}
		if(isPermitted(actualPos.X+j, actualPos.Y, pos) && pos[actualPos.X+j][actualPos.Y].occupied != this.colour )
			permpos.add(new Position(actualPos.X+j,actualPos.Y));
		
		//2 direzione
		j = 1;
		while(isPermitted(actualPos.X, actualPos.Y+j, pos) && pos[actualPos.X][actualPos.Y+j].occupied == -1){
			if(isPermitted(actualPos.X, actualPos.Y+j, pos))
				permpos.add(new Position(actualPos.X,actualPos.Y+j));
			j++;
		}
		if(isPermitted(actualPos.X, actualPos.Y+j, pos) && pos[actualPos.X][actualPos.Y+j].occupied != this.colour)
			permpos.add(new Position(actualPos.X,actualPos.Y+j));
		
		//3 direzione
		j = 1;
		while(isPermitted(actualPos.X-j, actualPos.Y, pos) && pos[actualPos.X-j][actualPos.Y].occupied == -1){
			if(isPermitted(actualPos.X-j, actualPos.Y, pos))
				permpos.add(new Position(actualPos.X-j,actualPos.Y));
				
			j++;
		}
		if(isPermitted(actualPos.X-j, actualPos.Y, pos) && pos[actualPos.X-j][actualPos.Y].occupied != this.colour)
			permpos.add(new Position(actualPos.X-j,actualPos.Y));
		
		//4 direzione
		j = 1;
		while(isPermitted(actualPos.X, actualPos.Y-j, pos) && pos[actualPos.X][actualPos.Y-j].occupied == -1){
			if(isPermitted(actualPos.X, actualPos.Y-j, pos))
				permpos.add(new Position(actualPos.X, actualPos.Y-j));
			
			j++;
		}
		if(isPermitted(actualPos.X, actualPos.Y-j, pos) && pos[actualPos.X][actualPos.Y-j].occupied != this.colour)
			permpos.add(new Position(actualPos.X, actualPos.Y-j));
		
		//5 direzione
		j = 1;
		while(isPermitted(actualPos.X+j, actualPos.Y+j, pos) && pos[actualPos.X+j][actualPos.Y+j].occupied == -1){
			if(isPermitted(actualPos.X+j, actualPos.Y+j, pos))
				permpos.add(new Position(actualPos.X+j, actualPos.Y+j));
			
			j++;
		}
		if(isPermitted(actualPos.X+j, actualPos.Y+j, pos) && pos[actualPos.X+j][actualPos.Y+j].occupied != this.colour)
			permpos.add(new Position(actualPos.X+j, actualPos.Y+j));
				
		
			
			//6 direzione 
		j = 1;
			while(isPermitted(actualPos.X-j, actualPos.Y-j, pos) && pos[actualPos.X-j][actualPos.Y-j].occupied == -1){
				if(isPermitted(actualPos.X-j, actualPos.Y-j, pos))
					permpos.add(new Position(actualPos.X-j, actualPos.Y-j));
					
				j++;
			}
			if(isPermitted(actualPos.X-j, actualPos.Y-j, pos) && pos[actualPos.X-j][actualPos.Y-j].occupied != this.colour)
				permpos.add(new Position(actualPos.X-j, actualPos.Y-j));
			
			//7 direzione 
			j = 1;
				while(isPermitted(actualPos.X+j, actualPos.Y-j, pos) && pos[actualPos.X+j][actualPos.Y-j].occupied == -1){
					if(isPermitted(actualPos.X+j, actualPos.Y-j, pos))
						permpos.add(new Position(actualPos.X+j, actualPos.Y-j));
						
					j++;
				}
				if(isPermitted(actualPos.X+j, actualPos.Y-j, pos) && pos[actualPos.X+j][actualPos.Y-j].occupied != this.colour)
					permpos.add(new Position(actualPos.X+j, actualPos.Y-j));
			//8 direzione
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
