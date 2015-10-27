package gui;

import java.util.ArrayList;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import logic.Piece;
import logic.Position;

public class PieceGui extends Rectangle {
	
	Piece piece;
	
	public PieceGui(Piece p) {	
		this.piece = p;
		this.setHeight(70);
		this.setWidth(70);
		this.setFill( new ImagePattern(p.getImage()));
		this.setX(piece.getPosition().X);
		this.setY(piece.getPosition().Y);
		
		this.setOnMouseReleased(new EventHandler<Event>() {

			@Override
			public void handle(Event arg0) {
				System.out.println(this.getClass());
				
				
			}
		});
	}
	
	
	public ArrayList<Position> calculate( Position pos[][]){
		return piece.permittedMoves(pos);
	}
	

}
