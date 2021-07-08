package app.controller.base;

import java.net.URL;
import java.util.ResourceBundle;

import app.enums.FXMLState;
import core.Startup;
import core.ioc.Container;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

public abstract class BaseController implements Initializable {

	private Container container;
	private FXMLState state;
	private Integer idEditing;
	
	public BaseController() {
		state = FXMLState.Inserir;
		idEditing = 0;
		Startup startup = new Startup();
		this.container = startup.getContainer();
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
	}
	
	@FXML
    public void initialize(){
		
	}

	public Container getContainer() {
		return container;
	}
	
	public FXMLState getState() {
		return state;
	}

	public void setState(FXMLState state) {
		this.state = state;
	}
	
	public Integer getIdEditing() {
		return idEditing;
	}

	public void setIdEditing(Integer idEditing) {
		this.idEditing = idEditing;
	}

}
