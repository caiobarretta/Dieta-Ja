package app.controller;

import java.net.URL;
import java.util.ResourceBundle;

import app.Main;
import app.enums.FXMLScreen;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class FXMLLoginController implements Initializable {

	
	
	@FXML
	private void efetuarLogin(ActionEvent event) throws Exception{
		Main.setUsuarioLogado(true);
		Main.switchScreen(FXMLScreen.FXMLPrincipal);
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
	}
}
