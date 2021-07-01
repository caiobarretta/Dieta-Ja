package app.controller;

import java.net.URL;
import java.util.ResourceBundle;

import app.Main;
import app.enums.FXMLScreen;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

public class FXMLPrincipalController implements Initializable {

	@FXML
	public void clickCadastrarAlimento(ActionEvent event) throws Exception{
		Main.switchScreen(FXMLScreen.FXMLAlimentosCadastro);
	}
	
	@FXML
	public void clickConsultarAlimento(ActionEvent event) throws Exception{
		Main.switchScreen(FXMLScreen.FXMLAlimentosBusca);
	}
	
	@FXML
	public void clickCadastrarDieta(ActionEvent event){
		
	}
	
	@FXML
	public void clickConsultarDieta(ActionEvent event){
		
	}
	
	@FXML
	public void clickCadastrarPaciente(ActionEvent event) throws Exception{
		Main.switchScreen(FXMLScreen.FXMLPacientesCadastro);
	}
	
	@FXML
	public void clickConsultarPaciente(ActionEvent event) throws Exception{
		Main.switchScreen(FXMLScreen.FXMLPacientesConsulta);
	}
	
	@FXML
	public void clickRegistrarConsumo(ActionEvent event){
		
	}
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}
}
