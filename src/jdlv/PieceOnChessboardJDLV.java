package jdlv;

public class PieceOnChessboardJDLV {
private int id;
private int x; 
private int y;
private int colour;

public PieceOnChessboardJDLV() {
}

public PieceOnChessboardJDLV(int id, int colour, int x, int y){
	this.setId(id);
	this.setX(x);
	this.setY(y);
	this.setColour(colour);
}

public int getY() {
	return y;
}

public void setY(int y) {
	this.y = y;
}

public int getX() {
	return x;
}

public void setX(int x) {
	this.x = x;
}

public int getId() {
	return id;
}

public void setId(int id) {
	this.id = id;
}

public int getColour() {
	return colour;
}

public void setColour(int colour) {
	this.colour = colour;
}
}
