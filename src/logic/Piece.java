package logic;

import java.awt.Image;
import java.util.ArrayList;

public abstract class Piece {

	protected boolean moved;
	protected boolean eaten;
	protected boolean promoved;
	
	protected Position actualPos;
	protected Image img;
	protected String name;
	protected int colour; // -1 vuota 0 nero 1 bianco
	protected int value;
	
	public ArrayList<Position> permpos;

	public Piece(String name, Image img, int col, int val, Position pos ) {
		this.name = name;
		this.img = img;
		this.colour = col;
		this.value = val;
		this.moved = false;
		this.eaten = false;
		this.promoved = false;
		this.permpos = new ArrayList<>();
		this.setPosition(pos);
	}
	
	public Position getPosition() {
		return actualPos;
	}
	
	public void setPosition(Position p){
		this.actualPos.X = p.X;
		this.actualPos.Y = p.Y;
		this.actualPos.occupied = colour;
	}
	
	public boolean isPermitted(int x, int y, Position pos[][]){
		
		if(x < 0 || x > 7)
			return false;
		if(y < 0 || y > 7)
			return false;
		if (pos[x][y].occupied == this.actualPos.occupied)
			return false;
		
		return true;
	}
	
	public abstract ArrayList<Position> permittedMoves(Position pos[][]);

}
