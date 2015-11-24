package gui;

import java.util.ArrayList;
import java.util.List;

import com.sun.corba.se.impl.protocol.giopmsgheaders.Message;

import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseDragEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import logic.ChessManager;
import logic.Pawn;
import logic.Piece;
import logic.Position;

public class ChessBoardGui extends GridPane {

	List<Rectangle> colored;
	private Position DragPos;
	public ChessManager manager;
	private ArrayList<Piece> eaten;
	private PieceGui enPassant;
	private PromotionPanel promotion;
	private boolean scacco = false;
	
	public ChessBoardGui(final ChessManager manager , final EatenPieces eaten , PromotionPanel prom) {
		
		this.promotion = prom;
		this.eaten = eaten.getList();
		this.manager = manager;
		this.colored = new ArrayList<>();
	    this.setAlignment(Pos.CENTER);
	    this.setHgap(2);
	    this.setVgap(2);
	    this.setPadding(new Insets(25));
	    this.setGridLinesVisible(false);
	    
	    for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
								
				final Rectangle rect = new Rectangle(i, j, 100, 100);

				final CustomStackPane myStack = new CustomStackPane(rect);
					
				if(((j % 2) == 1 && (i%2) == 0) || ((j % 2) == 0 && (i%2) == 1))
					rect.setFill(Color.web("#4c4c4c"));
				else	
					rect.setFill(Color.web("#ffdab9"));	

				Position piecePos = this.manager.getChessBoard().getChessboardPosition()[i][j];
				
				if(piecePos.occupied != -1){
					
					if(piecePos.occupied == 0){
						for (Piece blackPiece : manager.getChessBoard().getBlack()) {
							if(blackPiece.getPosition().equals(piecePos) && !blackPiece.eaten){
								myStack.addPiece(new PieceGui(blackPiece));
								//rect.setFill(new ImagePattern(blackPiece.getImage()));
								break;
							}
						}
					}
					else{
						for (Piece whitePiece : manager.getChessBoard().getWhite()) {
							if(whitePiece.getPosition().equals(piecePos) && !whitePiece.eaten){
								myStack.addPiece(new PieceGui(whitePiece));
								//rect.setFill(new ImagePattern(whitePiece.getImage()));
								break;
							}
						}
						
					}
					
				}
				
				myStack.setOnMousePressed(new EventHandler<Event>() {

					@Override
					public void handle(Event arg0) {
						
						for (Node child : myStack.getChildren()) {
							if(child instanceof PieceGui && manager.turn == ((PieceGui) child).getLogicPiece().getColour()){
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
									r.setFill(Color.web("#4c4c4c"));		
								else	
									r.setFill(Color.web("#ffdab9"));
							}
							colored.clear();
						}
				});
				
				this.add(myStack, j, i);	
			}
		}

	    setDragAndDrop();
	    
	}
	public void repaint(){
//		System.out.println("repaint");
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				
				Position piecePos = this.manager.getChessBoard().getChessboardPosition()[i][j];
				CustomStackPane tmp = ((CustomStackPane) getStackByRowColumnIndex(i, j));
				if(tmp.getChildren().size() == 2)
					tmp.getChildren().remove(1);
				
				for (Rectangle r : colored) {
					if((( r.getY() % 2) == 1 && (r.getX()%2) == 0) || ((r.getY() % 2) == 0 && (r.getX()%2) == 1))
						r.setFill(Color.web("#4c4c4c"));		
					else
						r.setFill(Color.web("#ffdab9"));
				}
				colored.clear();
				
				if(piecePos.occupied != -1){
					
					if(piecePos.occupied == 0){
						for (Piece blackPiece : manager.getChessBoard().getBlack()) {
							if(blackPiece.eaten && !eaten.contains(blackPiece))
								eaten.add(blackPiece);
							if(blackPiece.getPosition().equals(piecePos) && !blackPiece.eaten){
								tmp.addPiece(new PieceGui(blackPiece));
								break;
							}
						}
					}
					else{
						for (Piece whitePiece : manager.getChessBoard().getWhite()) {
							if(whitePiece.eaten && !eaten.contains(whitePiece))
								eaten.add(whitePiece);
							if(whitePiece.getPosition().equals(piecePos) && !whitePiece.eaten ){
								tmp.addPiece(new PieceGui(whitePiece));
								break;
							}
						}
					}
				}
				
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
						
					}
					startFullDrag();
					arg0.consume();
				}
			});
			
			
			tmp.setOnMouseDragReleased(new EventHandler<MouseDragEvent>() {

				@Override
				public void handle(MouseDragEvent arg0) {
					int turno = manager.turn;
					int mossa = -1;
//					System.out.println("tocca a : " +turno);
//					System.out.println(" mammeta  "+ arg0.getTarget().getClass());
					Rectangle r = (Rectangle) arg0.getTarget();
					String prom = "promozione";
					CustomStackPane p = (CustomStackPane) arg0.getSource();
					PieceGui toMove = getPieceByRowColumnIndex(DragPos.X, DragPos.Y);
//					System.out.println("scacco : " + scacco);
					try{
					
					if(arg0.getTarget() instanceof PieceGui){
						PieceGui tmp = (PieceGui) arg0.getTarget();
						
//						System.out.println("suarta "+tmp.getLogicPiece().getPosition().X + "  " + tmp.getLogicPiece().getPosition().Y);
						
						if(!scacco && (tmp.getLogicPiece().getPosition().X == 0 || tmp.getLogicPiece().getPosition().X == 7 ) && toMove.getLogicPiece() instanceof Pawn ){
							promotion = new PromotionPanel(toMove);
							prom = promotion.response;
						}
						
						mossa = manager.next(toMove.getLogicPiece(), tmp.getLogicPiece().getPosition(), prom, false );
						
						if(mossa != turno ){
							System.out.println("mangio in gui");
						}
					}
					else{
//						System.out.println(" r " + r.getX() + " " + r.getY());
						if(!scacco && (r.getX() == 0 || r.getX() == 7 ) && toMove.getLogicPiece() instanceof Pawn ){
//							System.out.println("mammeta");
							promotion = new PromotionPanel(toMove);
							prom = promotion.response;
						}
						System.out.println("prom : "+ prom);
						mossa = manager.next(toMove.getLogicPiece(), manager.getChessBoard().getChessboardPosition()[(int) r.getX()][(int) r.getY()], prom, false);
					}		

						scacco = false;
						manager.update();
					}catch(RuntimeException ru){

						if (ru.getLocalizedMessage() == "Scacco Matto") {
							System.out.println("Scacco Matto, BRAVOH");
						}
						if(ru.getLocalizedMessage() == "Mossa Non Valida"){
							Alert alert = new Alert(AlertType.INFORMATION);
							alert.setTitle("Errore");
							alert.setHeaderText(null);
							alert.setContentText("La Mossa Effettuata Non È Valida");

							alert.showAndWait();
						}
						if(ru.getLocalizedMessage() == "Scacco Al Re"){
							System.out.println("entro nell eccezione dello scacco");
							Alert alert = new Alert(AlertType.INFORMATION);
							alert.setTitle("Errore");
							alert.setHeaderText(null);
							alert.setContentText("Scacco al re " + manager.turn );
							//alert.setContentText("La Mossa Lascia Il Re Sotto Scacco");
							alert.showAndWait();
							scacco = true;
							
						}if(ru instanceof NullPointerException){
							System.out.println("nullpointer");
//							ru.printStackTrace();
						}
							
					}
					repaint();
				}
				
			});
			
		}
	}
	
	
	

}
