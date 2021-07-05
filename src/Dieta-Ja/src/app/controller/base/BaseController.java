package app.controller.base;

import java.net.URL;
import java.util.ResourceBundle;

import core.Startup;
import core.ioc.Container;
import javafx.fxml.Initializable;

public abstract class BaseController implements Initializable {

	private Container container;
	public BaseController() {
		Startup startup = new Startup();
		this.container = startup.getContainer();
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
	}

	public Container getContainer() {
		return container;
	}

}
