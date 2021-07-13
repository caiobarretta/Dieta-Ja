package app.controller.helper;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import app.controller.FXMLLoginController;
import app.controller.base.BaseController;
import core.Startup;
import core.entities.Usuario;
import core.ioc.Container;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;

public class WindowHelper {
	
	public static void Open(URL location, String title) throws IOException{
		Stage stage = new Stage();
	    Parent root = FXMLLoader.load(location);
	    stage.setScene(new Scene(root));
	    stage.setTitle(title);
	    stage.show();
	}
	
	public static void Open(InputStream is, String title, BaseController baseController) throws IOException{
		Stage stage = new Stage();
		FXMLLoader loader = new FXMLLoader();
		loader.setController(baseController);
		Parent root = loader.load(is);
	    stage.setScene(new Scene(root));
	    stage.setTitle(title);
	    stage.show();
	}
	
	public static void OpenModal(URL location, String title, Window window) throws IOException{
		Stage stage = new Stage();
	    Parent root = FXMLLoader.load(location);
	    stage.setScene(new Scene(root));
	    stage.setTitle(title);
	    stage.initModality(Modality.WINDOW_MODAL);
	    stage.initOwner(window);
	    stage.show();
	}
	
	public static void OpenModal(InputStream is, String title, Window window, BaseController baseController) throws IOException{
		Stage stage = new Stage();
		FXMLLoader loader = new FXMLLoader();
		loader.setController(baseController);
		Parent root = loader.load(is);
		stage.setScene(new Scene(root));
	    stage.setTitle(title);
	    stage.initModality(Modality.WINDOW_MODAL);
	    stage.initOwner(window);
	    stage.show();
	}
	
	public static void OpenModal(InputStream is, String title, Window window, Container container, Usuario usuario, BaseController baseController) throws IOException{
		Stage stage = new Stage();
		FXMLLoader loader = new FXMLLoader();
		loader.setController(baseController);
		Parent root = loader.load(is);
		stage.setScene(new Scene(root));
	    stage.setTitle(title);
	    stage.initModality(Modality.WINDOW_MODAL);
	    stage.initOwner(window);
	    stage.show();
	}
	
	
}
