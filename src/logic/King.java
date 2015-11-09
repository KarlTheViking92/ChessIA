package logic;


import java.util.ArrayList;
import javafx.scene.image.Image;

public class King extends Piece {

	public King(Image img, int col, Position pos) {
		super("king", img, col, 100, pos);
	}

	public ArrayList<Position> permittedMoves(Position[][] pos) {
		permpos.clear();
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
			permpos.add(new Position(actualPos.X+1, actualPos.Y+1));
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
