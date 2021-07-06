package app.controller;

import java.net.URL;
import java.util.ResourceBundle;

import app.Main;
import app.controller.base.BaseController;
import core.entities.Usuario;
import core.interfaces.service.IUsuarioService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Modality;
import javafx.stage.Stage;
import services.UsuarioService;

public class FXMLPrincipalController extends BaseController {
	
	@FXML
    private Label lblUsuario;

	@FXML
	public void clickCadastrarAlimento(ActionEvent event) throws Exception{
	}
	
	@FXML
	public void clickConsultarAlimento(ActionEvent event) throws Exception{
		Stage stage = new Stage();
	    Parent root = FXMLLoader.load(getClass().getResource("../view/FXMLPorcaoDeAlimentos.fxml"));
	    stage.setScene(new Scene(root));
	    stage.setTitle("Buscar Alimento");
	    stage.initModality(Modality.WINDOW_MODAL);
	    stage.initOwner(((Node)event.getSource()).getScene().getWindow());
	    stage.show();
	}
	
	@FXML
	public void clickCadastrarDieta(ActionEvent event){
	}
	
	@FXML
	public void clickConsultarDieta(ActionEvent event){
	}
	
	@FXML
	public void clickCadastrarPaciente(ActionEvent event) throws Exception{
//		Stage stage = new Stage();
//	    Parent root = FXMLLoader.load(getClass().getResource("../view/FXMLPacientesCadastro.fxml"));
//	    stage.setScene(new Scene(root));
//	    stage.setTitle("Cadastro Paciente");
//	    stage.initModality(Modality.WINDOW_MODAL);
//	    stage.initOwner(((Node)event.getSource()).getScene().getWindow() );
//	    stage.show();
	}
	
	@FXML
	public void clickConsultarPaciente(ActionEvent event) throws Exception{
		Stage stage = new Stage();
	    Parent root = FXMLLoader.load(getClass().getResource("../view/FXMLPaciente.fxml"));
		stage.setScene(new Scene(root));
	    stage.setTitle("Pacientes");
	    stage.initModality(Modality.WINDOW_MODAL);
	    stage.initOwner(((Node)event.getSource()).getScene().getWindow());
	    stage.show();
	}
	
	@FXML
	public void clickRegistrarConsumo(ActionEvent event){
		
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		super.initialize(location, resources);
		Usuario usuario = ((UsuarioService)super.getContainer().resolveSingleton(IUsuarioService.class)).get(Main.getCodigoUsuario());
		//lblUsuario.setText(usuario.getNome());
	}
	
	@FXML
    private void initialize() {
		lblUsuario = new Label("jhvajbdvjabdjvhbajvhbajsdvbh");
    }
}
