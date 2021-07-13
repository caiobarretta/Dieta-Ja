package app;
	
import java.io.IOException;
import java.io.InputStream;

import app.controller.FXMLLoginController;
import core.Startup;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;

public class Main extends Application {
	
	@Override
	public void start(Stage primaryStage) throws IOException {
		
		Startup startup = new Startup();
		FXMLLoader loader = new FXMLLoader();
		InputStream is = getClass().getResource("view/FXMLLogin.fxml").openStream();
		
		loader.setController(new FXMLLoginController(startup.getContainer(), null));
		Parent parent = loader.load(is);
		Scene scene = new Scene(parent);
	    primaryStage.setScene(scene);
	    primaryStage.show();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
