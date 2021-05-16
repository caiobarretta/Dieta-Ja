package infrastructure.dao.helper;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Map;

import com.sun.jdi.InvalidTypeException;

import infrastructure.dao.helper.statementchain.StatementBooleanChain;
import infrastructure.dao.helper.statementchain.StatementCloseChain;
import infrastructure.dao.helper.statementchain.StatementIntegerChain;
import infrastructure.dao.helper.statementchain.StatementNullChain;
import infrastructure.dao.helper.statementchain.StatementStringChain;
import infrastructure.dao.helper.statementchain.base.StatementBaseChain;

public class HelperExecuteStatementChain {
	//Padrão de Singleton
	protected static HelperExecuteStatementChain instance = new HelperExecuteStatementChain();
	
	private static StatementBaseChain chain;
	private HelperExecuteStatementChain() {
		var finalChain = new StatementCloseChain(null);
		var nullChain = new StatementNullChain(finalChain);
		var boolChain = new StatementBooleanChain(nullChain);
		var intChain = new StatementIntegerChain(boolChain);
		var strChain = new StatementStringChain(intChain);
		chain = new StatementIntegerChain(strChain);
	}
	
	public static void Execute(PreparedStatement statement, Map<Integer, Object> params) throws InvalidTypeException, SQLException {
		
		for(var param : params.entrySet()) {
			chain.verificaStatement(statement, param.getKey(), param.getValue());
		}
		
	}
}
