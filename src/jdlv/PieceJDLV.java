package jdlv;

public class PieceJDLV {
private int id;
private String name;
private int value;
private int colour;
private int eaten;
private int promoved;
private int moved;

public PieceJDLV(){	
}

public PieceJDLV(int id, String name, int value, int colour,int eaten, int promoved, int moved){
	this.setId(id);
	this.setName(name);
	this.setValue(value);
	this.setColour(colour);
	this.setEaten(eaten);
	this.setPromoved(promoved);
	this.setMoved(moved);
}

public int getId() {
	return id;
}

public void setId(int id) {
	this.id = id;
}

public String getName() {
	return name;
}

public void setName(String name) {
	this.name = name;
}

public int getValue() {
	return value;
}

public void setValue(int value) {
	this.value = value;
}

public int getColour() {
	return colour;
}

public void setColour(int colour) {
	this.colour = colour;
}

public int getPromoved() {
	return promoved;
}

public void setPromoved(int promoved) {
	this.promoved = promoved;
}

public int getEaten() {
	return eaten;
}

public void setEaten(int eaten) {
	this.eaten = eaten;
}

public int getMoved() {
	return moved;
}

public void setMoved(int moved) {
	this.moved = moved;
}


}
