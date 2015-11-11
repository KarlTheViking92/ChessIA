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
	ArrayList<Piece> eaten;
	
	public ChessBoardGui(final ChessManager manager , final EatenPieces eaten) {
		
		this.eaten = eaten.getList();
		this.manager = manager;
		this.colored = new ArrayList<>();
		//this.eaten = new ArrayList<>();
	    this.setAlignment(Pos.CENTER);
	    this.setHgap(2);
	    this.setVgap(2);
	    this.setPadding(new Insets(25));
	    this.setGridLinesVisible(false);
	    this.drag = false;
	    
	    for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
								
				final Rectangle rect = new Rectangle(i, j, 100, 100);

				final CustomStackPane myStack = new CustomStackPane(rect);
					
				if(((j % 2) == 1 && (i%2) == 0) || ((j % 2) == 0 && (i%2) == 1))
					rect.setFill(Color.web("#ffdab9"));		
				else
					rect.setFill(Color.web("#4c4c4c"));
				

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
//								System.out.println(((PieceGui) child).getLogicPiece().getClass());
								for (Position pos : ((PieceGui) child).calculate(manager.getChessBoard().getChessboardPosition())) {
									Rectangle n = getNodeByRowColumnIndex(pos.X, pos.Y);
//									System.out.println(pos.X + " " + pos.Y);
									n.setFill(Color.web("#0000FF"));
									colored.add(n);
								}
								
							}
						}
						
					}
				});				

				myStack.setOnMouseReleased(new EventHandler<Event>() {

					public void handle(Event arg0) {
							for (Rectangle r : colored) {
								if((( r.getY() % 2) == 1 && (r.getX()%2) == 0) || ((r.getY() % 2) == 0 && (r.getX()%2) == 1))
									r.setFill(Color.web("#ffdab9"));		
								else
									r.setFill(Color.web("#4c4c4c"));
							}
							colored.clear();
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
		//System.out.println("size: " + children.size());
		for (Node node : children) {
			
			StackPane tmp = (StackPane) node;
			
			tmp.setOnDragDetected(new EventHandler<Event>() {

				@Override
				public void handle(Event arg0) {
					
					drag = true;
					if(arg0.getTarget() instanceof PieceGui)
						DragPos = ((PieceGui)arg0.getTarget() ).getLogicPiece().getPosition();
					startFullDrag();
					
					arg0.consume();
				}
			});
			
			
			tmp.setOnMouseDragReleased(new EventHandler<MouseDragEvent>() {

				@Override
				public void handle(MouseDragEvent arg0) {
					
					Rectangle r = (Rectangle) arg0.getTarget();
					CustomStackPane p = (CustomStackPane) arg0.getSource();
					PieceGui toMove = getPieceByRowColumnIndex(DragPos.X, DragPos.Y);
					System.out.println("customStack ha size "+ p.getChildren().size());
					//System.out.println("la pos è occupata da: "+manager.getChessBoard().getChessboardPosition()[(int) r.getX()][(int) r.getY()].occupied);
					
					if(toMove != null && manager.move(toMove.getLogicPiece(), manager.getChessBoard().getChessboardPosition()[(int) r.getX()][(int) r.getY()])){
						toMove.updatePos();
						p.addPiece(toMove);
						
						/*if(toMove.getLogicPiece() instanceof Pawn){
							PieceGui enPassant = null;
							if(checkPos(toMove.getLogicPiece().getPosition().X, toMove.getLogicPiece().getPosition().Y-1) && getPieceByRowColumnIndex(toMove.getLogicPiece().getPosition().X, toMove.getLogicPiece().getPosition().Y-1).getLogicPiece() instanceof Pawn)
								enPassant = getPieceByRowColumnIndex(toMove.getLogicPiece().getPosition().X, toMove.getLogicPiece().getPosition().Y-1);
							else if(checkPos(toMove.getLogicPiece().getPosition().X, toMove.getLogicPiece().getPosition().Y+1) && getPieceByRowColumnIndex(toMove.getLogicPiece().getPosition().X, toMove.getLogicPiece().getPosition().Y+1).getLogicPiece() instanceof Pawn)
								enPassant = getPieceByRowColumnIndex(toMove.getLogicPiece().getPosition().X, toMove.getLogicPiece().getPosition().Y+1);
							
							if(toMove != null && enPassant != null && manager.eat(toMove.getLogicPiece(), enPassant.getLogicPiece())){
								System.out.println(enPassant.getClass());
								
								eaten.add(enPassant.getLogicPiece());
								p.getChildren().remove(enPassant);
								p.addPiece(toMove);
							}
						}*/
					}
					else if(p.getChildren().size() == 2){
						PieceGui toEat = (PieceGui) p.getChildren().get(1);
						
						if(toMove != null && manager.eat(toMove.getLogicPiece(), toEat.getLogicPiece())){
							
							eaten.add(toEat.getLogicPiece());
							p.getChildren().remove(toEat);
							p.addPiece(toMove);
						}
					}
					
					
				/*	for(int i = 0 ; i < 8 ; i++){
						for (int j = 0; j < 8; j++) {
							System.out.print(manager.getChessBoard().getChessboardPosition()[i][j].occupied + " ");
						}
						System.out.println();
					}*/
				}
			});
			
		}
	}
	
	boolean checkPos(int x, int y){
		if(x > 7 || x < 0)
			return false;
		if(y > 7 || y < 0)
			return false;
		
		return true;
	}

}
