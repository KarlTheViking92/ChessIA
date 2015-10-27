package gui;

import java.util.ArrayList;
import java.util.List;

import com.sun.glass.events.MouseEvent;

import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import logic.Chessboard;
import logic.Piece;
import logic.Position;

public class ChessBoardGui extends GridPane {

	List<Rectangle> colored;
	
	public ChessBoardGui(final Chessboard game) {
		
		this.colored = new ArrayList<>();
	    this.setAlignment(Pos.CENTER);
	    this.setHgap(2);
	    this.setVgap(2);
	    this.setPadding(new Insets(25));
	    this.setGridLinesVisible(false);
	    
	    for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				
				final StackPane stack = new StackPane();
				
				final Shape rect = new Rectangle(j, i, 100, 100);

					
				if(((j % 2) == 1 && (i%2) == 0) || ((j % 2) == 0 && (i%2) == 1))
					rect.setFill(Color.web("#ffdab9"));		
				else
					rect.setFill(Color.web("#4c4c4c"));
				
				stack.setOnMouseEntered(new EventHandler<Event>() {

					@Override
					public void handle(Event arg0) {
						rect.setFill(Color.web("#fd482f"));
						
					}
				});
				
				stack.setOnMouseExited(new EventHandler<Event>() {

					public void handle(Event arg0) {
						
						if((( ((Rectangle)rect).getY() % 2) == 1 && (((Rectangle)rect).getX()%2) == 0) || ((((Rectangle)rect).getY() % 2) == 0 && (((Rectangle)rect).getX()%2) == 1))
							rect.setFill(Color.web("#ffdab9"));		
						else
							rect.setFill(Color.web("#4c4c4c"));
					}
				});
				
				
				stack.getChildren().add(rect);
				Position piecePos = game.getChessboardPosition()[i][j];
				
				if(piecePos.occupied != -1){
					
					if(piecePos.occupied == 0){
						for (Piece blackPiece : game.getBlack()) {
							if(blackPiece.getPosition().equals(piecePos)){
								stack.getChildren().add(new PieceGui(blackPiece));
								break;
							}
						}
					}
					else{
						for (Piece whitePiece : game.getWhite()) {
							if(whitePiece.getPosition().equals(piecePos)){
								stack.getChildren().add(new PieceGui(whitePiece));
								break;
							}
						}
						
					}
					
				}
				
				stack.setOnMousePressed(new EventHandler<Event>() {

					@Override
					public void handle(Event arg0) {
						for (Node child : stack.getChildren()) {
							if(child instanceof PieceGui){
								for (Position pos : ((PieceGui) child).calculate(game.getChessboardPosition())) {
									Rectangle n = getNodeByRowColumnIndex(pos.X, pos.Y);
									n.setFill(Color.web("#0000FF"));
									colored.add(n);
								}
								
							}
						}
						
					}
				});
				
				stack.setOnMouseReleased(new EventHandler<Event>() {

					public void handle(Event arg0) {
							for (Rectangle r : colored) {
								if((( r.getY() % 2) == 1 && (r.getX()%2) == 0) || ((r.getY() % 2) == 0 && (r.getX()%2) == 1))
									r.setFill(Color.web("#ffdab9"));		
								else
									r.setFill(Color.web("#4c4c4c"));
							}
							colored.clear();
							
							//System.out.println(getNodeByRowColumnIndex(row, column));
						}
				});
				
				stack.setOnMouseDragged(new EventHandler<Event>() {

					@Override
					public void handle(Event arg0) {
						/*System.out.println( ((Rectangle)arg0).getX()    );
						System.out.println( ((Rectangle)arg0).getY()    );*/
					}
				});
				
				
				this.add(stack, j, i);
				
				
			}
		}
	    
	}
	
	public Rectangle getNodeByRowColumnIndex(final int row,final int column) {
        Node result = null;
        ObservableList<Node> childrens = this.getChildren();
        for(Node node : childrens) {
            if(this.getRowIndex(node) == row && this.getColumnIndex(node) == column) {
                result = node;
                break;
            }
        }
        
        for (Node node : ((StackPane) result).getChildren()) {
			if(node instanceof Rectangle)
				 return (Rectangle) node;
		}
        return null;
    }
	
	

}
