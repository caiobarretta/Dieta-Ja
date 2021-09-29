package testes.unidade;

import junit.framework.Test;
import junit.framework.TestSuite;

public class AllTestsUnidade {

	public static Test suite() {
		TestSuite suite = new TestSuite(AllTestsUnidade.class.getName());
		//$JUnit-BEGIN$
		suite.addTestSuite(ContainerTest.class);
		suite.addTestSuite(HelperExecuteStatementChainTest.class);
		suite.addTestSuite(HelperHashMapTest.class);
		suite.addTestSuite(StartupTest.class);
		//$JUnit-END$
		return suite;
	}

}
