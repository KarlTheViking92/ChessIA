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
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DataFormat;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseDragEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import logic.ChessManager;
import logic.Chessboard;
import logic.Pawn;
import logic.Piece;
import logic.Position;

public class ChessBoardGui extends GridPane {

	List<Rectangle> colored;
	boolean drag;
	private Position DragPos;
	ChessManager manager;
	
	public ChessBoardGui(final ChessManager manager) {
		
		this.manager = manager;
		this.colored = new ArrayList<>();
	    this.setAlignment(Pos.CENTER);
	    this.setHgap(2);
	    this.setVgap(2);
	    this.setPadding(new Insets(25));
	    this.setGridLinesVisible(false);
	    this.drag = false;
	    
	    for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				
				//final StackPane stack = new StackPane();
				
				final Rectangle rect = new Rectangle(i, j, 100, 100);

				final CustomStackPane myStack = new CustomStackPane(rect);
					
				if(((j % 2) == 1 && (i%2) == 0) || ((j % 2) == 0 && (i%2) == 1))
					rect.setFill(Color.web("#ffdab9"));		
				else
					rect.setFill(Color.web("#4c4c4c"));
				
			/*	stack.setOnMouseEntered(new EventHandler<Event>() {

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
				
				
				stack.getChildren().add(rect);*/
				Position piecePos = this.manager.getChessBoard().getChessboardPosition()[i][j];
				
				if(piecePos.occupied != -1){
					
					if(piecePos.occupied == 0){
						for (Piece blackPiece : manager.getChessBoard().getBlack()) {
							if(blackPiece.getPosition().equals(piecePos)){
								//stack.getChildren().add(new PieceGui(blackPiece));
								myStack.addPiece(new PieceGui(blackPiece));
								break;
							}
						}
					}
					else{
						for (Piece whitePiece : manager.getChessBoard().getWhite()) {
							if(whitePiece.getPosition().equals(piecePos)){
								//stack.getChildren().add(new PieceGui(whitePiece));
								myStack.addPiece(new PieceGui(whitePiece));
								break;
							}
						}
						
					}
					
				}
				
				myStack.setOnMousePressed(new EventHandler<Event>() {

					@Override
					public void handle(Event arg0) {
						for (Node child : myStack.getChildren()) {
							if(child instanceof PieceGui){
								for (Position pos : ((PieceGui) child).calculate(manager.getChessBoard().getChessboardPosition())) {
									Rectangle n = getNodeByRowColumnIndex(pos.X, pos.Y);
									n.setFill(Color.web("#0000FF"));
									colored.add(n);
								}
								
							}
						}
						
					}
				});
				

				
				
			/*	stack.setOnDragDetected(new EventHandler<Event>() {

					@Override
					public void handle(Event arg0) {
						
						Dragboard db = ((Node) arg0.getTarget()).startDragAndDrop(TransferMode.ANY);
						System.out.println("drag detected event");
						ClipboardContent content = new ClipboardContent();
						
						System.out.println(arg0.getSource().getClass());
						
					//	content.put(new DataFormat("Piece"), arg0.getTarget());
						db.setContent(content);
					}
				});
				
				
				stack.setOnDragDone(new EventHandler<Event>() {

					@Override
					public void handle(Event arg0) {
						System.out.println("drag done");
						
					}
				});*/
				

				myStack.setOnMouseReleased(new EventHandler<Event>() {

					public void handle(Event arg0) {
							for (Rectangle r : colored) {
								if((( r.getY() % 2) == 1 && (r.getX()%2) == 0) || ((r.getY() % 2) == 0 && (r.getX()%2) == 1))
									r.setFill(Color.web("#ffdab9"));		
								else
									r.setFill(Color.web("#4c4c4c"));
							}
							colored.clear();
							
							
							
							//System.out.println(arg0.getTarget().getClass());
							//System.out.println(getNodeByRowColumnIndex(row, column));
						}
				});
				
				this.add(myStack, j, i);
				
				
			}
		}
	    setDragAndDrop();
	    
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
	
	
	public PieceGui getPieceByRowColumnIndex(final int row,final int column) {
        Node result = null;
        ObservableList<Node> childrens = this.getChildren();
        for(Node node : childrens) {
            if(this.getRowIndex(node) == row && this.getColumnIndex(node) == column) {
                result = node;
                break;
            }
        }
        
        for (Node node : ((StackPane) result).getChildren()) {
			if(node instanceof PieceGui)
				 return (PieceGui) node;
		}
        return null;
    }
	
	private void setDragAndDrop(){
		List<Node> children = this.getChildrenUnmodifiable();
		System.out.println("size: " + children.size());
		for (Node node : children) {
			
			StackPane tmp = (StackPane) node;
			
			tmp.setOnDragDetected(new EventHandler<Event>() {

				@Override
				public void handle(Event arg0) {
					
					//Dragboard db = ((Node) arg0.getTarget()).startDragAndDrop(TransferMode.ANY);
					System.out.println("drag detected event");
					System.out.println(arg0.getTarget().getClass());
					drag = true;
					DragPos = ((PieceGui)arg0.getTarget() ).getLogicPiece().getPosition();
					startFullDrag();
					
					arg0.consume();
				//	content.put(new DataFormat("Piece"), arg0.getTarget());
				}
			});
			
			
			tmp.setOnMouseDragReleased(new EventHandler<MouseDragEvent>() {

				@Override
				public void handle(MouseDragEvent arg0) {
					System.out.println("drag ended");
					Rectangle r = (Rectangle) arg0.getTarget();
					CustomStackPane p = (CustomStackPane) arg0.getSource();
					PieceGui toMove = getPieceByRowColumnIndex(DragPos.X, DragPos.Y);
					if(manager.move(toMove.getLogicPiece(), manager.getChessBoard().getChessboardPosition()[(int) r.getX()][(int) r.getY()])){
						toMove.updatePos();
						p.addPiece(toMove);
						
					}
					//p.addPiece(getPieceByRowColumnIndex( (int)r.getX(),(int) r.getY()));
					
					for(int i = 0 ; i < 8 ; i++){
						for (int j = 0; j < 8; j++) {
							System.out.print(manager.getChessBoard().getChessboardPosition()[i][j].occupied + " ");
						}
						System.out.println();
					}
				}
			});
			
			/*if(tmp.getChildren().size() == 2){
				System.out.println(tmp.getChildren().get(1).getClass());
				Rectangle rect = (Rectangle) tmp.getChildren().get(0);
				System.out.println(rect.getX() + "  " + rect.getY());
				Piece p = ((PieceGui) tmp.getChildren().get(1)).getLogicPiece();
				System.out.println(p.getClass());
			}*/
			
		}
	}
	
	

}
