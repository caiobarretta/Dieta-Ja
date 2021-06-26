package infrastructure.dao.helper.statementchain;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import core.exception.InvalidTypeException;

import infrastructure.dao.helper.statementchain.base.StatementBaseChain;

public class StatementIntegerChain  extends StatementBaseChain{

	public StatementIntegerChain(StatementBaseChain next) {
		super(next);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void verificaStatement(PreparedStatement statement, int index, Object value)
			throws InvalidTypeException, SQLException {
		
		String typeName = value.getClass().getSimpleName();
		Class<Integer> cls = Integer.class;
		if(value instanceof Integer || cls.isInstance(value) || typeName.equals("Integer")) {
			statement.setInt(index, (Integer)value);
			return;
		}
		
		
		super.getNext().verificaStatement(statement, index, value);
	}

}
