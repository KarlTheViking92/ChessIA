package gui;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.FlowPane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


public class PromotionPanel extends Stage {

	private FlowPane pane;
	private Scene newScene;
	private PieceGui piece;
	public String response;

	public PromotionPanel(PieceGui p) {
		this.piece = p;
		this.pane = new FlowPane();
		this.pane.setAlignment(Pos.CENTER);
		this.initStyle(StageStyle.UTILITY);
		this.newScene = new Scene(pane, 500, 200);
		this.initModality(Modality.APPLICATION_MODAL);
		this.setTitle("Scegli il pezzo da Promuovere");
		this.setScene(newScene);
		if (piece.getLogicPiece().getColour() == 1) 
			createWhiteButtons();
		else
			createBlackButtons();
		
		this.showAndWait();
		
	}
	
	void createWhiteButtons(){
		Rectangle queen = new Rectangle(100, 100);
		queen.setFill(new ImagePattern(new Image("file:data/WhiteQueen.png")));
		queen.setOnMouseClicked(new EventHandler<Event>() {

			@Override
			public void handle(Event arg0) {
				response = "Queen";
				PromotionPanel.this.close();
			}
		});
		
		Rectangle rook = new Rectangle(100, 100);
		rook.setFill(new ImagePattern(new Image("file:data/WhiteRook.png")));
		rook.setOnMouseClicked(new EventHandler<Event>() {

			@Override
			public void handle(Event arg0) {
				response = "Rook";
				PromotionPanel.this.close();
				
			}
		});
		
		Rectangle bishop = new Rectangle(100, 100);
		bishop.setFill(new ImagePattern(new Image("file:data/WhiteBishop.png")));
		bishop.setOnMouseClicked(new EventHandler<Event>() {

			@Override
			public void handle(Event arg0) {
				response = "Bishop";
				PromotionPanel.this.close();
				
			}
		});
		
		Rectangle knight = new Rectangle(100, 100);
		knight.setFill(new ImagePattern(new Image("file:data/WhiteKnight.png")));
		knight.setOnMouseClicked(new EventHandler<Event>() {

			@Override
			public void handle(Event arg0) {
				response = "Knight";
				PromotionPanel.this.close();
				
			}
		});
		
		
		pane.getChildren().add(queen);
		pane.getChildren().add(bishop);
		pane.getChildren().add(rook);
		pane.getChildren().add(knight);
	}
	
	
	void createBlackButtons(){
		Rectangle queen = new Rectangle(100, 100);
		queen.setFill(new ImagePattern(new Image("file:data/BlackQueen.png")));
		queen.setOnMouseClicked(new EventHandler<Event>() {

			@Override
			public void handle(Event arg0) {
				response = "Queen";
				PromotionPanel.this.close();
			}
		});
		
		Rectangle rook = new Rectangle(100, 100);
		rook.setFill(new ImagePattern(new Image("file:data/BlackRook.png")));
		rook.setOnMouseClicked(new EventHandler<Event>() {

			@Override
			public void handle(Event arg0) {
				response = "Rook";
				PromotionPanel.this.close();
				
			}
		});
		
		Rectangle bishop = new Rectangle(100, 100);
		bishop.setFill(new ImagePattern(new Image("file:data/BlackBishop.png")));
		bishop.setOnMouseClicked(new EventHandler<Event>() {

			@Override
			public void handle(Event arg0) {
				response = "Bishop";
				PromotionPanel.this.close();
				
			}
		});
		
		Rectangle knight = new Rectangle(100, 100);
		knight.setFill(new ImagePattern(new Image("file:data/BlackKnight.png")));
		knight.setOnMouseClicked(new EventHandler<Event>() {

			@Override
			public void handle(Event arg0) {
				response = "Knight";
				PromotionPanel.this.close();
				
			}
		});
		
		
		pane.getChildren().add(queen);
		pane.getChildren().add(bishop);
		pane.getChildren().add(rook);
		pane.getChildren().add(knight);
	}

}
