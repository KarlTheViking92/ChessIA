package jdlv;

public class PermittedMoveJDLV {
private int id;
private int x;
private int y;
public PermittedMoveJDLV() {
}
public PermittedMoveJDLV(int id,int x,int y){
	this.setId(id);
	this.setX(x);
	this.setY(y);
}
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public int getX() {
	return x;
}
public void setX(int x) {
	this.x = x;
}
public int getY() {
	return y;
}
public void setY(int y) {
	this.y = y;
}
}
