package gui;

import java.util.ArrayList;

import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import logic.Piece;

public class EatenPieces extends FlowPane {

	ArrayList<Piece> eaten;
	
	public EatenPieces() {
		// TODO Auto-generated constructor stub
	}
	
	public void addEaten(Piece p){
		if(p.eaten)
			eaten.add(p);
	}
	
	public void draw(){
		for (Piece piece : eaten) {
			this.getChildren().add(new ImageView(piece.getImage()));
		}
	}
	
}
