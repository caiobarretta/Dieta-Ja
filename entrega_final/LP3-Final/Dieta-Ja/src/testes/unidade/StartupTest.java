package testes.unidade;

import core.Startup;
import core.ioc.Container;
import junit.framework.TestCase;
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

	public void testGetContainer() {
		Container container = startup.getContainer();
		if(container == null)
			fail("Container do Startup nulo.");
	}

}
