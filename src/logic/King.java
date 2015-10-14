package logic;

import java.awt.Image;
import java.util.ArrayList;

public class King extends Piece {

	public King(Image img, int col, int val, Position pos) {
		super("king", img, col, 100, pos);
	}

	public ArrayList<Position> permittedMoves(Position[][] pos) {

		//1 mossa
		if(isPermitted(actualPos.X+1, actualPos.Y, pos)){
			permpos.add(new Position(actualPos.X+1, actualPos.Y));
			}
		//2 mossa
		if(isPermitted(actualPos.X-1, actualPos.Y,pos)){
			permpos.add(new Position(actualPos.X-1, actualPos.Y));
		}
		//3 mossa
		if(isPermitted(actualPos.X, actualPos.Y+1, pos)){
			permpos.add(new Position(actualPos.X, actualPos.Y+1));
		}
		//4 mossa
		if(isPermitted(actualPos.X, actualPos.Y-1, pos)){
			permpos.add(new Position(actualPos.X, actualPos.Y-1));
		}
		//5 mossa
		if(isPermitted(actualPos.X+1, actualPos.Y+1, pos)){
			permpos.add(new Position(actualPos.X+1, actualPos.Y+11));
		}
		//6 mossa
		if(isPermitted(actualPos.X-1, actualPos.Y-1, pos)){
			permpos.add(new Position(actualPos.X-1, actualPos.Y-1));
		}
		//7 mossa
		if(isPermitted(actualPos.X+1, actualPos.Y-1, pos)){
			permpos.add(new Position(actualPos.X+1, actualPos.Y-1));
		}
		//8 mossa
		if(isPermitted(actualPos.X-1, actualPos.Y+1, pos)){
			permpos.add(new Position(actualPos.X-1, actualPos.Y+1));
		}
		return permpos;
	}

}
