package app.controller.helper;

import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

public class GridPaneHelper {

	public static void loadGridPane(GridPane pane, BorderPane borderPane, double prefWidth, double prefHeight) {
		Pane paneContainer = new Pane();
    	paneContainer.setPrefWidth(prefWidth);
    	paneContainer.setPrefHeight(prefHeight);
    	paneContainer.getChildren().add(borderPane);
    	pane.add(paneContainer, 0, 0);
	}
	
	public static void reloadGridPane(GridPane pane, BorderPane borderPane, double prefWidth, double prefHeight) {
		pane.getChildren().clear();
		loadGridPane(pane, borderPane, prefWidth, prefHeight);
	}

}
