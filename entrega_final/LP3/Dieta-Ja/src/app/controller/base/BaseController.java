package app.controller.base;

import java.net.URL;
import java.util.ResourceBundle;

import app.enums.FXMLState;
import core.Startup;
import core.entities.Usuario;
import core.ioc.Container;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

public abstract class BaseController implements Initializable {

	private Container container;
	private Usuario usuario;
	
	public BaseController(Container container, Usuario usuario) {
		this.container = container;
		this.usuario = usuario;
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
	}
	
	@FXML
    public void initialize(){
		
	}

	public Container getContainer() {
		return new Startup().getContainer();
	}

	public Usuario getUsuario() {
		return usuario;
	}
}
