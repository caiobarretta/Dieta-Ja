package app;
	
import java.io.IOException;

import app.controller.FXMLLoginController;
import app.enums.FXMLScreen;
import core.Startup;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;

public class Main extends Application {
	
//	public static String usuarioNome = "";
//	public static Boolean usuarioLogado = false;
//	public static Integer codigoUsuario = 0;
//	
//	private static Stage stage;
//	
//	private static Scene SceneLogin;
//	private static Scene SceneDieta;
//	private static Scene ScenePaciente;
//	private static Scene ScenePrincipal;
//	private static Scene ScenePorcaoDeAlimentos;
	
	@Override
	public void start(Stage primaryStage) throws IOException {
//		FXMLLoader fxmlLoaderLogin = FXMLLoader.load(getClass().getClassLoader().getResource("view/FXMLLogin.fxml"));
//		
//		Startup startup = new Startup();
//		FXMLLoginController loginController = new FXMLLoginController();
//		loginController.setContainer(startup.getContainer());
//		fxmlLoaderLogin.setController(loginController);
//		
//		Parent root = fxmlLoaderLogin.load();
//	    Scene scene = new Scene(root);
//	    primaryStage.setScene(scene);
//	    primaryStage.show();
		
		FXMLLoginController loginController = new FXMLLoginController();
		FXMLLoader fxmlLoaderLogin = FXMLLoader.load(getClass().getResource("view/FXMLLogin.fxml"));
		
		fxmlLoaderLogin.setController(loginController);
		Parent root = fxmlLoaderLogin.load();
		Scene SceneLogin = new Scene(root);
		
		primaryStage.setScene(SceneLogin);
		primaryStage.show();
		
	}

//	private void startApplication(Stage primaryStage) {
//		primaryStage.setScene(SceneLogin);
//		primaryStage.show();
//	}

//	private void loadAllFXML() throws IOException {
//		
//		//Startup startup = new Startup();
//		//this.container = startup.getContainer();
//		Parent fxmlLogin = FXMLLoader.load(getClass().getResource("view/FXMLLogin.fxml"));
//		SceneLogin = new Scene(fxmlLogin);
//		
//		Parent fxmlDieta = FXMLLoader.load(getClass().getResource("view/FXMLDieta.fxml"));
//		SceneDieta = new Scene(fxmlDieta);
//		
//		Parent fxmlPaciente = FXMLLoader.load(getClass().getResource("view/FXMLPaciente.fxml"));
//		ScenePaciente = new Scene(fxmlPaciente);
//		
//		Parent fxmlPrincipal = FXMLLoader.load(getClass().getResource("view/FXMLPrincipal.fxml"));
//		ScenePrincipal = new Scene(fxmlPrincipal);
//		
//		Parent fxmlPorcaoDeAlimentos = FXMLLoader.load(getClass().getResource("view/FXMLPorcaoDeAlimentos.fxml"));
//		ScenePorcaoDeAlimentos = new Scene(fxmlPorcaoDeAlimentos);
//	}
	
	public static void main(String[] args) {
		launch(args);
	}


//	public static void setUsuarioLogado(Boolean usuarioLogado, int codigoUsuario, String nomeUsuario) {
//		Main.usuarioLogado = usuarioLogado;
//		Main.codigoUsuario = codigoUsuario;
//		Main.usuarioNome = nomeUsuario;
//	}

//	public static void switchScreen(FXMLScreen screen) throws Exception{
//		switch(screen){
//		case FXMLPorcaoDeAlimento:
//			stage.setScene(ScenePorcaoDeAlimentos);
//			break;
//		case FXMLLogin:
//			stage.setScene(SceneLogin);
//			break;
//		case FXMLDieta:
//			stage.setScene(SceneDieta);
//		case FXMLPaciente:
//			stage.setScene(ScenePaciente);
//			break;
//		case FXMLPrincipal:
//			stage.setScene(ScenePrincipal);
//			break;
//		default:
//			throw new Exception("Tela não implementada");
//		
//		}
//	}
//	public static void switchScreen(FXMLScreen screen, Object data) throws Exception{
//		switch(screen){
//		case FXMLPorcaoDeAlimento:
//			stage.setScene(ScenePorcaoDeAlimentos);
//			stage.setUserData(data);
//			break;
//		case FXMLLogin:
//			stage.setScene(SceneLogin);
//			stage.setUserData(data);
//			break;
//		case FXMLDieta:
//			stage.setScene(SceneDieta);
//			stage.setUserData(data);
//		case FXMLPaciente:
//			stage.setScene(ScenePaciente);
//			stage.setUserData(data);
//			break;
//		case FXMLPrincipal:
//			stage.setScene(ScenePrincipal);
//			stage.setUserData(data);
//			break;
//		default:
//			throw new Exception("Tela não implementada");
//		
//		}
//	}

}
