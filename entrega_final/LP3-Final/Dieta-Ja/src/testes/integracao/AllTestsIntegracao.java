package testes.integracao;

import junit.framework.Test;
import junit.framework.TestSuite;

public class AllTestsIntegracao {

	public static Test suite() {
		TestSuite suite = new TestSuite(AllTestsIntegracao.class.getName());
		//$JUnit-BEGIN$
		suite.addTestSuite(DietaServiceTest.class);
		suite.addTestSuite(PorcaoDeAlimentoServiceTest.class);
		suite.addTestSuite(UsuarioServiceTest.class);
		//$JUnit-END$
		return suite;
	}

}
