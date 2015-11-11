package gui;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.beans.InvalidationListener;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Screen;
import javafx.stage.Stage;
import logic.ChessManager;

public class MainFrame extends Application {
	
	static ChessManager manager;
	ChessBoardGui gui;

	@Override
	public void start(Stage arg0) throws Exception {
		final EatenPieces eaten = new EatenPieces();
		manager = new ChessManager();
		gui = new ChessBoardGui( manager , eaten  );
		
		final FlowPane flow = new FlowPane(85.00, 800.00);
		//flow.setAlignment(Pos.CENTER);
		
		
		final Label turn = new Label(Integer.toString(manager.turn));

		flow.getChildren().add(turn);
		
		arg0.setTitle("Scacchi AI");
		//BorderPane main = new BorderPane();
		
		GridPane grid = new GridPane();
		grid.add(flow, 0, 0);
		grid.add(gui, 1, 0);
		grid.add(eaten , 2 , 0);
	    grid.setAlignment(Pos.CENTER);
	    grid.setHgap(2);
	    grid.setVgap(2);
	    grid.setPadding(new Insets(25));
	    grid.setGridLinesVisible(false);
	    
	    /*main.setCenter(gui);
	    main.setLeft(flow);
	    /*main.getLeft().minHeight(85.00);
	    main.getLeft().minWidth(85.00);
	    main.getLeft().maxHeight(85.00);
	    main.getLeft().maxWidth(85.00);*/
	/*    
	    main.setRight(eaten);
	    eaten.setOrientation(Orientation.VERTICAL);
	   /* main.getRight().minHeight(85.00);
	    main.getRight().minWidth(85.00);
	    main.getRight().maxHeight(85.00);
	    main.getRight().maxWidth(85.00);*/
	    
	    //main.getRight().minHeight(100.00);
	   /* main.getCenter().getStyleClass().add("center");
	    main.getCenter().minHeight(800.00);
	    main.getCenter().minWidth(800.00);*/
	    Rectangle2D screen = Screen.getPrimary().getVisualBounds();
	    //System.out.println(screen.getHeight() - 60.00);
	    arg0.setScene(new Scene(grid, screen.getWidth() - 60.00, screen.getHeight() -60.00));
	    
	    new AnimationTimer() {
			
			@Override
			public void handle(long arg0) {
				if(manager.turn == 1)
					turn.setText("È il turno del" + "\n" + "Bianco");
				else
					turn.setText("È il turno del " + "\n" + " Nero");
				
				eaten.draw();
				
			}
		}.start();
	    
	    
	    arg0.show();
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		launch(args);
		

	}

}
