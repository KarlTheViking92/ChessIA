package gui;

import java.util.ArrayList;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import logic.Piece;
import logic.Position;

public class PieceGui extends Rectangle {
	
	protected Piece piece;
	
	public PieceGui(Piece p) {	
		this.piece = p;
		this.setHeight(70);
		this.setWidth(70);
		this.setFill( new ImagePattern(p.getImage()));
		this.setX(piece.getPosition().X);
		this.setY(piece.getPosition().Y);
		
	}
	
	public Piece getLogicPiece(){
		return piece;
	}
	
	public void setPiece(Piece p){
		this.piece = p;
		this.setFill( new ImagePattern(p.getImage()));
		this.updatePos();
	}
	
	public ArrayList<Position> calculate( Position pos[][]){
		return piece.permittedMoves(pos);
	}
	
	public void changePos(Rectangle r){
		piece.setPosition(new Position((int)r.getX(),(int) r.getY()));
	}
	public void updatePos(){
		if(this.getX() != piece.getPosition().X && this.getY() != piece.getPosition().Y){
			setX(piece.getPosition().X); setY(piece.getPosition().Y);
		}
	}

}
