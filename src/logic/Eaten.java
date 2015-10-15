package logic;

import java.util.ArrayList;

import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;


public class Eaten extends FlowPane {

	
	private ArrayList<Piece> white;
	private ArrayList<Piece> black;
	
	public Eaten(ArrayList<Piece> white, ArrayList<Piece> black){
		this.black = black;
		this.white = white;
		
	}
		
	public void draw(){
		for (Piece piece : black) {
			if(piece.eaten){
				this.getChildren().add(new ImageView(piece.img));
			}
		}
		for (Piece piece : white) {
			if(piece.eaten)
				this.getChildren().add(new ImageView(piece.img));
		}
			
	}
}