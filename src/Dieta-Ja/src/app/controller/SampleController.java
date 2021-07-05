package app.controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import app.view.component.MultiSelectionCombo;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

public class SampleController implements Initializable {

    @FXML private GridPane gridPane;

    private Pane paneContainer;
    private Label paneLabel;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    	
    	List<String> lstItens = new ArrayList<String>();
    	lstItens.add("Segunda-Feira");
    	lstItens.add("Terça-Feira");
    	MultiSelectionCombo cbx = new MultiSelectionCombo("Dias Da Semana:", "[Vazio]", lstItens);
//        for(int i = 0; i<4; i++) {
//
//            paneLabel = new Label();
//            paneLabel.setText("it is..." + i);
//
//            paneContainer = new Pane();
//            paneContainer.setStyle("-fx-background-color: aqua; -fx-border-style: solid; -fx-border-width: 1px; -fx-border-color:#000; ");
//            paneContainer.setPrefWidth(200);
//            paneContainer.setPrefHeight(100);
//
//            paneContainer.getChildren().add(paneLabel);
//            gridPane.add(paneContainer, 0, i);
//        }
    	paneContainer = new Pane();
    	paneContainer.setPrefWidth(200);
    	paneContainer.setPrefHeight(100);
    	paneContainer.getChildren().add(cbx.build());
        gridPane.add(paneContainer, 0, 0);
    }
}