package infrastructure.dao.helper.statementchain;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.sun.jdi.InvalidTypeException;

import infrastructure.dao.helper.statementchain.base.StatementBaseChain;

public class StatementBooleanChain extends StatementBaseChain {

	public StatementBooleanChain(StatementBaseChain next) {
		super(next);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void verificaStatement(PreparedStatement statement, int index, Object value)
			throws InvalidTypeException, SQLException {
		// TODO Auto-generated method stub
		if (value instanceof Boolean) {
			statement.setBoolean(index, (Boolean)value);
			return;
		}
		next.verificaStatement(statement, index, value);
	}

}
