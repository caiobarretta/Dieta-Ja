package infrastructure.dao.helper.statementchain;

import java.sql.PreparedStatement;
import com.sun.jdi.InvalidTypeException;

import infrastructure.dao.helper.statementchain.base.StatementBaseChain;

public class StatementCloseChain extends StatementBaseChain {
	
	public StatementCloseChain(StatementBaseChain next) {
		super(next);
	}

	@Override
	public void verificaStatement(PreparedStatement statement, int index, Object value) throws InvalidTypeException {
		throw new InvalidTypeException("Tipo de Statement não implementado object: " + value.getClass().getSimpleName());
		
	}
	
}
