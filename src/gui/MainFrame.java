package gui;

import com.sun.glass.events.MouseEvent;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.beans.InvalidationListener;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Screen;
import javafx.stage.Stage;
import logic.ChessManager;

public class MainFrame extends Application {
	
	static ChessManager manager;
	ChessBoardGui gui;

	@Override
	public void start(Stage arg0) throws Exception {
		final PromotionPanel promotion = new PromotionPanel();
		final EatenPieces eaten = new EatenPieces();
		manager = new ChessManager();
		gui = new ChessBoardGui( manager , eaten  );
		
		final FlowPane flow = new FlowPane(85.00, 800.00);
		//flow.setAlignment(Pos.CENTER);
		
		
		final Label turn = new Label(Integer.toString(manager.turn));

		flow.getChildren().add(turn);
		Button btn = new Button("Prova");
		btn.setOnMouseClicked(new EventHandler<Event>() {

			@Override
			public void handle(Event arg0) {
				Stage newStage = new Stage();
				newStage.initModality(Modality.APPLICATION_MODAL);
				newStage.setTitle("Pop up window");
				newStage.setScene(promotion);
				newStage.showAndWait();
			}
		});
		
		flow.getChildren().add(btn);
		
		arg0.setTitle("Scacchi AI");
		//BorderPane main = new BorderPane();
		
		GridPane grid = new GridPane();
		GridPane grid2 = new GridPane();
		Label labbel = new Label("PEZZI MANGIATI");
		GridPane.setHalignment(labbel, HPos.CENTER);

		grid.add(flow, 0, 0);
		grid.add(gui, 1, 0);
		
		grid2.add(labbel, 0, 0);
		grid2.add(eaten , 0 , 1);
		
		grid.add(grid2, 2, 0);
		
	    grid.setAlignment(Pos.CENTER);
	    grid.setHgap(2);
	    grid.setVgap(2);
	    grid.setPadding(new Insets(25));
	    grid.setGridLinesVisible(false);
	    

	    Rectangle2D screen = Screen.getPrimary().getVisualBounds();
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
