package gui;

import javafx.application.Application;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Screen;
import javafx.stage.Stage;
import logic.ChessManager;

public class MainFrame extends Application {
	
	static ChessManager manager;
	ChessBoardGui gui;

	@Override
	public void start(Stage arg0) throws Exception {
		manager = new ChessManager();
		gui = new ChessBoardGui( manager );
		
		BorderPane main = new BorderPane();
	    main.setCenter(gui);
	    main.getCenter().getStyleClass().add("center");
	    main.getCenter().minHeight(800.00);
	    main.getCenter().minWidth(800.00);
	    Rectangle2D screen = Screen.getPrimary().getVisualBounds();
	    arg0.setScene(new Scene(main, screen.getWidth() - 60.00, screen.getHeight() -60.00));
	    arg0.show();
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		launch(args);

	}

}
