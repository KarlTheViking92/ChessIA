package logic;

import java.util.ArrayList;

import javafx.scene.image.Image;

public class Knight extends Piece {

	public Knight(Image img, int col, Position pos) {
		super("knight", img, col, 3, pos);
		
	}

	public ArrayList<Position> permittedMoves(Position[][] pos) {
		permpos.clear();
		
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
