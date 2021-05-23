package core;

import java.sql.Connection;

import core.ioc.Container;

public class Startup {
	
	private static Container container;
	public Startup() {
		container = Config.getConfigContainer();
	}
	
	public Startup(Connection conn) {
		container = Config.getConfigContainer(conn);
	}
	
	public Container getContainer() {
		return container;
	}

}
