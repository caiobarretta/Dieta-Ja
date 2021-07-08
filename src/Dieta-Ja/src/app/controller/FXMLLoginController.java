package app.controller;

import app.Main;
import app.controller.base.BaseController;
import app.enums.FXMLScreen;
import core.entities.Usuario;
import core.interfaces.service.IUsuarioService;
import core.ioc.Container;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextField;
import services.UsuarioService;

public class FXMLLoginController{

	@FXML
    private TextField txtLogin;

    @FXML
    private TextField txtPassword;
    
    private Container container;
    
    public void setContainer(Container container){
    	this.container = container;
    }

	@FXML
	private void efetuarLogin(ActionEvent event) throws Exception{
//		UsuarioService usuarioService = ((UsuarioService)container.resolveSingleton(IUsuarioService.class));
//		int codigoUsuario = usuarioService.getLoginUsuario(txtLogin.getText(), txtPassword.getText());
//		
//		if(codigoUsuario > 0){
//			Usuario usuario = usuarioService.get(codigoUsuario);
//			//Main.usuarioLogado = true;
//			//Main.codigoUsuario = codigoUsuario;
//			//Main.usuarioNome = usuario.getNome();
//			//Main.setUsuarioLogado(true, codigoUsuario, usuario.getNome());
//			//Main.switchScreen(FXMLScreen.FXMLPrincipal, usuario);
//		}
//		else{
//			Alert alert = new Alert(AlertType.INFORMATION);
//			alert.setTitle("Login");
//			alert.setContentText("Login incorreto, por favor tente novamente.");
//			alert.showAndWait();
//		}
		
	}
}
