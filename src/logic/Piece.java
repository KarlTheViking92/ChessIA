package logic;

import java.awt.Image;
import java.util.ArrayList;

public abstract class Piece {

	protected boolean moved;
	protected boolean eaten;
	protected boolean promoved;
	
	protected Position pos;
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
	}
	
	public Position getPosition() {
		return pos;
	}
	
	public void setPosition(Position p){
		this.pos.X = p.X;
		this.pos.Y = p.Y;
		this.pos.occupied = colour;
	}
	
	public boolean isPermitted(int x, int y, Position pos[][]){
		
		if(x < 0 || x > 7)
			return false;
		if(y < 0 || y > 7)
			return false;
		if (pos[x][y].occupied == this.pos.occupied)
			return false;
		
		return true;
	}
	
	public abstract Position[] permittedMoves(Position pos[][]);

}
