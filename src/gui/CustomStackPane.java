package gui;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import logic.Position;

public class CustomStackPane extends StackPane {

	Rectangle rect;
	PieceGui piece;
	
	
	public CustomStackPane(Rectangle r) {

		this.rect = r;
		this.getChildren().add(rect);
		
		this.setOnMouseEntered(new EventHandler<Event>() {

			@Override
			public void handle(Event arg0) {
				
				rect.setFill(Color.web("#fd482f"));
				
				/*System.out.println("entro in un nodo");
				StackPane tmp = (StackPane) arg0.getTarget();
				
				Rectangle r = (Rectangle) tmp.getChildren().get(0);
				System.out.println(r.getX() + " " + r.getY());*/
			}
		});
		
		this.setOnMouseExited(new EventHandler<Event>() {

			public void handle(Event arg0) {
				
				if((( ((Rectangle)rect).getY() % 2) == 1 && (((Rectangle)rect).getX()%2) == 0) || ((((Rectangle)rect).getY() % 2) == 0 && (((Rectangle)rect).getX()%2) == 1))
					rect.setFill(Color.web("#ffdab9"));		
				else
					rect.setFill(Color.web("#4c4c4c"));
			}
		});
		


	}

	public void addPiece(PieceGui p){
		this.piece = p ;
		if(!this.getChildren().contains(piece))
			this.getChildren().add(piece);
	}
	
}
