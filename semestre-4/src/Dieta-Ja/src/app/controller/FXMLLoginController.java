package app.controller;

import java.io.InputStream;
import java.net.URL;
import java.util.ResourceBundle;

import app.Main;
import app.controller.base.BaseController;
import app.controller.helper.AlertHelper;
import app.controller.helper.WindowHelper;
import app.enums.FXMLScreen;
import core.entities.Usuario;
import core.interfaces.service.IUsuarioService;
import core.ioc.Container;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import services.UsuarioService;

public class FXMLLoginController extends BaseController{

	public FXMLLoginController(Container container, Usuario usuario) {
		super(container, usuario);
		// TODO Auto-generated constructor stub
	}

	@FXML
    private TextField txtLogin;

    @FXML
    private TextField txtPassword;
    
    @FXML
    private ImageView imgLogin;

	@FXML
	private void efetuarLogin(ActionEvent event) throws Exception{
		UsuarioService usuarioService = ((UsuarioService)super.getContainer().resolve(IUsuarioService.class));
		int codigoUsuario = usuarioService.getLoginUsuario(txtLogin.getText(), txtPassword.getText());
		
		if(codigoUsuario > 0){
			Usuario usuario = usuarioService.get(codigoUsuario);
			FXMLPrincipalController controller = new FXMLPrincipalController(super.getContainer(), usuario);
			InputStream is = getClass().getResource("../view/FXMLPrincipal.fxml").openStream();
		    WindowHelper.Open(is, "Porção Alimento", controller);
		    ((Stage)((Node)event.getSource()).getScene().getWindow()).close();
		}
		else{
			AlertHelper.buildAlert(AlertType.INFORMATION, "Login", "Login incorreto, por favor tente novamente.").showAndWait();
		}
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		super.initialize(location, resources);
		//
		Image image = new Image(getClass().getResource("../resources/dieta-ja-go.jpeg").toExternalForm());
		imgLogin.setImage(image);
	}

	
}
