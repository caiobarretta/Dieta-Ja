package testes.unidade;

import infrastructure.dao.helper.HelperHashMap;
import junit.framework.TestCase;
import testes.helper.UtilHashMapTest;

public class HelperHashMapTest extends TestCase{

	public void setUp() throws Exception {
	}

	public void testCriarHashMapComNInteirosSequenciais() {
		Integer[] params = new Integer[] {1,2,3};
		var map = HelperHashMap.criarHashMapComNInteirosSequenciais(params);
		UtilHashMapTest.testMapCount(params.length, map);
	}

	public void testCriarHashMapComNStringsSequenciais() {
		String[] params = new String[] {"","",""};
		var map = HelperHashMap.criarHashMapComNStringsSequenciais(params);
		UtilHashMapTest.testMapCount(params.length, map);
	}
	
	

}
