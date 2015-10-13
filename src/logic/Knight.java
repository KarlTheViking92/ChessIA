package logic;

import java.awt.Image;
import java.util.ArrayList;

public class Knight extends Piece {

	public Knight(Image img, int col, Position pos) {
		super("knight", img, col, 3, pos);
		
	}

	public ArrayList<Position> permittedMoves(Position[][] pos) {
		
		if (isPermitted(actualPos.X+2, actualPos.Y+1, pos)) {
			permpos.add(new Position(actualPos.X+2, actualPos.Y+1));
		}
		
		if (isPermitted(actualPos.X+1, actualPos.Y+2, pos)) {
			permpos.add(new Position(actualPos.X+1, actualPos.Y+2));
		}
		
		if (isPermitted(actualPos.X-1, actualPos.Y+2, pos)) {
			permpos.add(new Position(actualPos.X-1, actualPos.Y+2));
		}
		
		if (isPermitted(actualPos.X-2, actualPos.Y+1, pos)) {
			permpos.add(new Position(actualPos.X-2, actualPos.Y+1));
		}
		
		if (isPermitted(actualPos.X-2, actualPos.Y-1, pos)) {
			permpos.add(new Position(actualPos.X-2, actualPos.Y-1));
		}
		
		if (isPermitted(actualPos.X-1, actualPos.Y-2, pos)) {
			permpos.add(new Position(actualPos.X-1, actualPos.Y-2));
		}
		
		if (isPermitted(actualPos.X+1, actualPos.Y-2, pos)) {
			permpos.add(new Position(actualPos.X+1, actualPos.Y-2));
		}
		
		if (isPermitted(actualPos.X+2, actualPos.Y-1, pos)) {
			permpos.add(new Position(actualPos.X+2, actualPos.Y-1));
		}
		return permpos;
	}

}
