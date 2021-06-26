package infrastructure.dao.helper;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Map;
import java.util.Map.Entry;

import core.exception.InvalidTypeException;
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
		StatementCloseChain finalChain = new StatementCloseChain(null);
		StatementNullChain nullChain = new StatementNullChain(finalChain);
		StatementBooleanChain boolChain = new StatementBooleanChain(nullChain);
		StatementIntegerChain intChain = new StatementIntegerChain(boolChain);
		StatementStringChain strChain = new StatementStringChain(intChain);
		chain = new StatementIntegerChain(strChain);
	}
	
	public static void Execute(PreparedStatement statement, Map<Integer, Object> params) throws InvalidTypeException, SQLException {
		
		for(Entry<Integer, Object> param : params.entrySet()) {
			chain.verificaStatement(statement, param.getKey(), param.getValue());
		}
		
	}
}
