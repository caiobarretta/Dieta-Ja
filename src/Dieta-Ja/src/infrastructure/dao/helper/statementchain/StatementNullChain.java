package infrastructure.dao.helper.statementchain;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import core.exception.InvalidTypeException;

import infrastructure.dao.helper.statementchain.base.StatementBaseChain;

public class StatementNullChain extends StatementBaseChain{

	public StatementNullChain(StatementBaseChain next) {
		super(next);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void verificaStatement(PreparedStatement statement, int index, Object value)
			throws InvalidTypeException, SQLException {
		if(value == null) {
			statement.setString(index, null);
			return;
		}
		super.getNext().verificaStatement(statement, index, value);
		
	}
	
}
