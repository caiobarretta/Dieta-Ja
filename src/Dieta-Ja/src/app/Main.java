package app;
	
import java.io.IOException;
import app.enums.FXMLScreen;
import core.Startup;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;


public class Main extends Application {
	
	private static Boolean usuarioLogado = false;
	private static int codigoUsuario = 0;
	
	private static Stage stage;
	
	private static Scene SceneLogin;
	private static Scene ScenePaciente;
	private static Scene ScenePrincipal;
	private static Scene ScenePorcaoDeAlimentos;
	
	@Override
	public void start(Stage primaryStage) throws IOException {
		stage = primaryStage;
		this.loadAllFXML();
		this.startApplication(primaryStage);
	}

	private void startApplication(Stage primaryStage) {
		if(usuarioLogado)
			primaryStage.setScene(ScenePrincipal);
		else 
			primaryStage.setScene(SceneLogin);
		primaryStage.show();
	}

	private void loadAllFXML() throws IOException {
		Parent fxmlLogin = FXMLLoader.load(getClass().getResource("view/FXMLLogin.fxml"));
		SceneLogin = new Scene(fxmlLogin);
		
		Parent fxmlPaciente = FXMLLoader.load(getClass().getResource("view/FXMLPaciente.fxml"));
		ScenePaciente = new Scene(fxmlPaciente);
		
		Parent fxmlPrincipal = FXMLLoader.load(getClass().getResource("view/FXMLPrincipal.fxml"));
		ScenePrincipal = new Scene(fxmlPrincipal);
		
		Parent fxmlPorcaoDeAlimentos = FXMLLoader.load(getClass().getResource("view/FXMLPorcaoDeAlimentos.fxml"));
		ScenePorcaoDeAlimentos = new Scene(fxmlPorcaoDeAlimentos);
	}
	
	public static void main(String[] args) {
		launch(args);
	}

	public static Boolean getUsuarioLogado() {
		return usuarioLogado;
	}
	
	public static int getCodigoUsuario(){
		return codigoUsuario;
	}

	public static void setUsuarioLogado(Boolean usuarioLogado, int codigoUsuario) {
		Main.usuarioLogado = usuarioLogado;
		Main.codigoUsuario = codigoUsuario;
	}

	public static void switchScreen(FXMLScreen screen) throws Exception{
		switch(screen){
		case FXMLPorcaoDeAlimento:
			stage.setScene(ScenePorcaoDeAlimentos);
			break;
		case FXMLLogin:
			stage.setScene(SceneLogin);
			break;
		case FXMLPaciente:
			stage.setScene(ScenePaciente);
			break;
		case FXMLPrincipal:
			stage.setScene(ScenePrincipal);
			break;
		default:
			throw new Exception("Tela não implementada");
		
		}
	}

}
