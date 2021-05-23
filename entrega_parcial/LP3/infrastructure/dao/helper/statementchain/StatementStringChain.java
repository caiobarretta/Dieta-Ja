package infrastructure.dao.helper.statementchain;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import com.sun.jdi.InvalidTypeException;

import infrastructure.dao.helper.statementchain.base.StatementBaseChain;

public class StatementStringChain extends StatementBaseChain{

	public StatementStringChain(StatementBaseChain next) {
		super(next);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void verificaStatement(PreparedStatement statement, int index, Object value) throws InvalidTypeException, SQLException {
		if(value instanceof String) {
			statement.setString(index, (String)value);
			return;
		}
		super.getNext().verificaStatement(statement, index, value);
	}
	
}
