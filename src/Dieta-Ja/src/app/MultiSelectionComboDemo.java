package app;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.CheckMenuItem;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuButton;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import app.view.component.MultiSelectionCombo;

public class MultiSelectionComboDemo extends Application {
    final ListView<String> selectedItems = new ListView<>();
    
    @Override
    public void start(Stage primaryStage) {
    	List<String> lstItens = new ArrayList<String>();
    	lstItens.add("Segunda-Feira");
    	lstItens.add("Terça-Feira");
    	MultiSelectionCombo cbx = new MultiSelectionCombo("Dias Da Semana:", "[Vazio]", lstItens);
        primaryStage.setScene(new Scene(cbx.build(), 400, 100));
        primaryStage.show();
    }
    
    public static void main(String[] args) {
        launch(args);
    }
}