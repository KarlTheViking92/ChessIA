package gui;

import java.util.ArrayList;

import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import logic.Piece;

public class EatenPieces extends FlowPane {

	ArrayList<Piece> eaten;
	
	public EatenPieces() {
		this.eaten = new ArrayList<>();
	}
	
	public void addEaten(Piece p){
		if(p.eaten)
			eaten.add(p);
	}
	
	public void draw(){
		this.getChildren().clear();
		for (Piece piece : eaten) {
			Rectangle r = new Rectangle(70,70);
			r.setFill(new ImagePattern(piece.getImage()));
			this.getChildren().add(r);
		}
	}
	public ArrayList<Piece> getList(){
		return eaten;
	}
	
}
