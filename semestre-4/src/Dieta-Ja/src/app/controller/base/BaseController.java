package app.controller.base;

import java.net.URL;
import java.sql.Connection;
import java.util.ResourceBundle;

import core.Startup;
import core.entities.Usuario;
import core.ioc.Container;
import infrastructure.dao.DAOFactory;
import infrastructure.dao.base.DAOConnection;
import infrastructure.repository.RepositoryFactory;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import services.ServicesFactory;

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
		Connection conn = DAOConnection.getConnection();
		DAOFactory daoFactory = new DAOFactory();
		RepositoryFactory repositoryFactory = new RepositoryFactory();
		ServicesFactory servicesFactory = new ServicesFactory();
		Container container = new Startup(conn, daoFactory, repositoryFactory, servicesFactory).getContainer();
		return container;
	}

	public Usuario getUsuario() {
		return usuario;
	}
}
