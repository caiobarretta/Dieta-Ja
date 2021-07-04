package app;
	
import java.io.IOException;
import java.net.URL;

import app.enums.FXMLScreen;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;


public class Main extends Application {
	
	private static Boolean usuarioLogado = false;
	
	private static Stage stage;
	
	private static Scene SceneAlimentosBusca;
	private static Scene SceneAlimentosCadastro;
	private static Scene SceneErroLogin;
	private static Scene SceneLogin;
	private static Scene ScenePacientesCadastro;
	private static Scene ScenePacientesConsulta;
	private static Scene ScenePrincipal;
	
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
		Parent fxmlAlimentosBusca = FXMLLoader.load(getClass().getResource("view/FXMLAlimentosBusca.fxml"));
		SceneAlimentosBusca = new Scene(fxmlAlimentosBusca);
		
		Parent fxmlAlimentosCadastro = FXMLLoader.load(getClass().getResource("view/FXMLAlimentosCadastro.fxml"));
		SceneAlimentosCadastro = new Scene(fxmlAlimentosCadastro);
		
		Parent fxmlErroLogin = FXMLLoader.load(getClass().getResource("view/FXMLErroLogin.fxml"));
		SceneErroLogin = new Scene(fxmlErroLogin);
		
		Parent fxmlLogin = FXMLLoader.load(getClass().getResource("view/FXMLLogin.fxml"));
		SceneLogin = new Scene(fxmlLogin);
		
		Parent fxmlPacientesCadastro = FXMLLoader.load(getClass().getResource("view/FXMLPacientesCadastro.fxml"));
		ScenePacientesCadastro = new Scene(fxmlPacientesCadastro);
		
		Parent fxmlPacientesConsulta = FXMLLoader.load(getClass().getResource("view/FXMLPacientesConsulta.fxml"));
		ScenePacientesConsulta = new Scene(fxmlPacientesConsulta);
		
		Parent fxmlPrincipal = FXMLLoader.load(getClass().getResource("view/FXMLPrincipal.fxml"));
		ScenePrincipal = new Scene(fxmlPrincipal);
	}
	
	public static void main(String[] args) {
		launch(args);
	}

	public static Boolean getUsuarioLogado() {
		return usuarioLogado;
	}

	public static void setUsuarioLogado(Boolean usuarioLogado) {
		Main.usuarioLogado = usuarioLogado;
	}

	public static void switchScreen(FXMLScreen screen) throws Exception{
		switch(screen){
		case FXMLAlimentosBusca:
			stage.setScene(SceneAlimentosBusca);
			break;
		case FXMLAlimentosCadastro:
			stage.setScene(SceneAlimentosCadastro);
			break;
		case FXMLErroLogin:
			stage.setScene(SceneErroLogin);
			break;
		case FXMLLogin:
			stage.setScene(SceneLogin);
			break;
		case FXMLPacientesCadastro:
			stage.setScene(ScenePacientesCadastro);
			break;
		case FXMLPacientesConsulta:
			stage.setScene(ScenePacientesConsulta);
			break;
		case FXMLPrincipal:
			stage.setScene(ScenePrincipal);
			break;
		default:
			throw new Exception("Tela não implementada");
		
		}
	}
}
