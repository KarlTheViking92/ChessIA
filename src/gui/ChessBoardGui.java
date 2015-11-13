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
//	boolean drag;
	private Position DragPos;
	ChessManager manager;
	ArrayList<Piece> eaten;
	PieceGui enPassant;
	
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
//	    this.drag = false;
	    
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
								for (Position pos : ((PieceGui) child).calculate(manager.getChessBoard().getChessboardPosition())) {
									Rectangle n = getNodeByRowColumnIndex(pos.X, pos.Y);
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
	
	public StackPane getStackByRowColumnIndex(final int row,final int column) {
        Node result = null;
        ObservableList<Node> childrens = this.getChildren();
        for(Node node : childrens) {
            if(this.getRowIndex(node) == row && this.getColumnIndex(node) == column) {
                result = node;
                break;
            }
        }
        
        return (StackPane) result;
    }
	
	private void setDragAndDrop(){
		List<Node> children = this.getChildrenUnmodifiable();
		for (Node node : children) {
			
			StackPane tmp = (StackPane) node;
			
			tmp.setOnDragDetected(new EventHandler<Event>() {

				@Override
				public void handle(Event arg0) {
					
//					drag = true;
					if(arg0.getTarget() instanceof PieceGui && ((PieceGui)arg0.getTarget() ).getLogicPiece().getPosition().occupied == manager.turn){
						DragPos = ((PieceGui)arg0.getTarget() ).getLogicPiece().getPosition();
						PieceGui tmp = (PieceGui)arg0.getTarget();
						
						if(tmp.getLogicPiece() instanceof Pawn){
							if(checkPos(tmp.getLogicPiece().getPosition().X, tmp.getLogicPiece().getPosition().Y-1)){
								PieceGui p = getPieceByRowColumnIndex(tmp.getLogicPiece().getPosition().X, tmp.getLogicPiece().getPosition().Y-1);
								if( p != null &&  p.getLogicPiece().getPosition().occupied != manager.turn){
									enPassant = p;
									System.out.println("inizializzo enpassant");
								}
							}
							if(checkPos(tmp.getLogicPiece().getPosition().X, tmp.getLogicPiece().getPosition().Y+1) ){
								PieceGui p = getPieceByRowColumnIndex(tmp.getLogicPiece().getPosition().X, tmp.getLogicPiece().getPosition().Y+1);
									if(p != null && p.getLogicPiece().getPosition().occupied != manager.turn){
										enPassant = p;
										System.out.println("enpassant inizializato con pos "+ enPassant.getLogicPiece().getPosition().X + " "+enPassant.getLogicPiece().getPosition().Y);
									}
							}
						}
						
						startFullDrag();
					}
					
					arg0.consume();
				}
			});
			
			
			tmp.setOnMouseDragReleased(new EventHandler<MouseDragEvent>() {

				@Override
				public void handle(MouseDragEvent arg0) {
					System.out.println("tocca a : " +manager.turn);
					Rectangle r = (Rectangle) arg0.getTarget();
					CustomStackPane p = (CustomStackPane) arg0.getSource();
					PieceGui toMove = getPieceByRowColumnIndex(DragPos.X, DragPos.Y);
					
					if(toMove != null && p.getChildren().size() == 1){
						if(manager.move(toMove.getLogicPiece(), manager.getChessBoard().getChessboardPosition()[(int) r.getX()][(int) r.getY()])){
							toMove.updatePos();
							p.addPiece(toMove);
							changeTurn();
						}
						
						if(toMove != null && toMove.getLogicPiece() instanceof Pawn ){
							if(enPassant != null && manager.eat(toMove.getLogicPiece(), enPassant.getLogicPiece())){
								eaten.add(enPassant.getLogicPiece());
								CustomStackPane st = (CustomStackPane) getStackByRowColumnIndex(enPassant.getLogicPiece().getPosition().X, enPassant.getLogicPiece().getPosition().Y);
								st.getChildren().remove(enPassant);
								p.addPiece(toMove);
								changeTurn();
							}
						}
						

					}
					else if(p.getChildren().size() == 2){
						PieceGui toEat = (PieceGui) p.getChildren().get(1);
						System.out.println("entro per mangiare");
						if(toMove != null && manager.eat(toMove.getLogicPiece(), toEat.getLogicPiece())){
							
							eaten.add(toEat.getLogicPiece());
							p.getChildren().remove(toEat);
							p.addPiece(toMove);
							System.out.println("ti mangio, ti mangio");
							changeTurn();
						}
					}
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
	
	void changeTurn(){
		if(manager.turn == 1)
			manager.turn = 0;
		else
			manager.turn = 1;
	}

}
