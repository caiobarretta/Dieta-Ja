package core;

import java.sql.Connection;

import core.abstractions.DAOFactoryMethod;
import core.abstractions.RepositoryFactoryMethod;
import core.abstractions.ServicesFactoryMethod;
import core.ioc.Container;

public class Startup {
	
	private static Container container;
	public Startup() {
		container = Config.getConfigContainer();
	}
	
	public Startup(Connection conn) {
		container = Config.getConfigContainer(conn);
	}
	
	public Startup(Connection conn, DAOFactoryMethod daoFactory) {
		container = Config.getConfigContainer(conn, daoFactory);
	}
	
	public Startup(Connection conn, DAOFactoryMethod daoFactory, RepositoryFactoryMethod repoFactory) {
		container = Config.getConfigContainer(conn, daoFactory, repoFactory);
	}
	
	public Startup(Connection conn, DAOFactoryMethod daoFactory, RepositoryFactoryMethod repoFactory, ServicesFactoryMethod serviceFactory) {
		container = Config.getConfigContainer(conn, daoFactory, repoFactory, serviceFactory);
	}
	
	public Container getContainer() {
		return container;
	}

}
