package testes.unidade;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import core.exception.InvalidTypeException;
import infrastructure.dao.base.DAOConnection;
import infrastructure.dao.helper.HelperExecuteStatementChain;
import junit.framework.TestCase;

public class HelperExecuteStatementChainTest extends TestCase{

	private String query;
	private ResultSet rs;
	private Connection connection;
	private Map<Integer, Object> params;
	private PreparedStatement statement; 
	private DAOConnection conn;
	
	public void setUp() throws Exception {
		params = new HashMap<Integer, Object>();
		connection = DAOConnection.getConnection();
		query = "SELECT 1 = ?";
		statement = connection.prepareStatement(query);
	}
	
	public void testDadoUmInteiroValidoQuandoHelperExecutarCadeiaDeveSetaroParametroComOTipoDeDadoCorretoExecute() {
		Integer teste = 1;
	    params.put(1, teste);
	    ExecuteHelperExecuteStatementChain(statement, params);
	}

	public void testDadoUmaQueryValidaQuandoHelperExecutarCadeiaDeveSetaroParametroComOTipoDeDadoCorretoExecute() {
		 Object teste = "1";
	     params.put(1, teste);
	     
	     ExecuteHelperExecuteStatementChain(statement, params);
	}

	private void ExecuteHelperExecuteStatementChain(PreparedStatement statement, Map<Integer, Object> params) {
		try {
			 HelperExecuteStatementChain.Execute(statement, params);
			 rs = statement.executeQuery(); 
			 if(rs.next()) 
				 assertEquals(true, true);
			 else 
				 fail("A pesquisa não retornou dados");
			 
		} catch ( SQLException e) {
			fail(e.getMessage());
		} catch (InvalidTypeException e) {
			// TODO Auto-generated catch block
			fail(e.getMessage());
		}
	}

}
