package logic;

public class Position {

	public int X;
	public int Y;
	public int occupied = -1;
	
	public Position(int x, int y) {
		this.X = x;
		this.Y = y;
	}
	
	@Override
	public boolean equals(Object other) {
		
		Position tmp = (Position) other;
		
		if (this == tmp) return true;
		
		if(this.X == tmp.X && this.Y == tmp.Y ) return true;
		
		return false;
	}
	

	
}
