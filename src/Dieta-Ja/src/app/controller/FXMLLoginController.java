package app.controller;

import app.Main;
import app.controller.base.BaseController;
import app.enums.FXMLScreen;
import core.interfaces.service.IUsuarioService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextField;
import services.UsuarioService;

public class FXMLLoginController extends BaseController {

	@FXML
    private TextField txtLogin;

    @FXML
    private TextField txtPassword;

	@FXML
	private void efetuarLogin(ActionEvent event) throws Exception{
		int codigoUsuario = ((UsuarioService)super.getContainer().resolveSingleton(IUsuarioService.class)).getLoginUsuario(txtLogin.getText(), txtPassword.getText());
		
		if(codigoUsuario > 0){
			Main.setUsuarioLogado(true, codigoUsuario);
			Main.switchScreen(FXMLScreen.FXMLPrincipal);
		}
		else{
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Login");
			alert.setContentText("Login incorreto, por favor tente novamente.");
			alert.showAndWait();
		}
		
	}
}
