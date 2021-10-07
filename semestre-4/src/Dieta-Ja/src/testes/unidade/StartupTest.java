package testes.unidade;

import core.Startup;
import core.ioc.Container;
import infrastructure.dao.DAOFactory;
import infrastructure.repository.RepositoryFactory;
import junit.framework.TestCase;
import services.ServicesFactory;
import testes.DAOConnectionTest;

public class StartupTest  extends TestCase {

	Startup startup;
	public void setUp() throws Exception {
		startup = new Startup();
	}
	
	public void testStartup() {
		if(startup == null)
			fail("Classe Startup nula.");
	}

	public void testStartupConnection() {
		Startup startupConnection = new Startup(DAOConnectionTest.getConnection());
		if(startupConnection == null)
			fail("Classe Startup com conexão nula.");
	}
	
	public void testStartupConnectionDAOFactory() {
		Startup startupConnection = new Startup(DAOConnectionTest.getConnection(), new DAOFactory());
		if(startupConnection == null)
			fail("Classe Startup com conexão nula.");
	}
	
	public void testStartupConnectionDAORepositoryFactory() {
		Startup startupConnection = new Startup(DAOConnectionTest.getConnection(), new DAOFactory(), 
				new RepositoryFactory());
		if(startupConnection == null)
			fail("Classe Startup com conexão nula.");
	}
	
	public void testStartupConnectionDAORepositoryServicesFactory() {
		Startup startupConnection = new Startup(DAOConnectionTest.getConnection(), new DAOFactory(), 
				new RepositoryFactory(), new ServicesFactory());
		if(startupConnection == null)
			fail("Classe Startup com conexão nula.");
	}

	public void testGetContainer() {
		Container container = startup.getContainer();
		if(container == null)
			fail("Container do Startup nulo.");
	}

}
