package infrastructure.dao.helper.statementchain.base;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.sun.jdi.InvalidTypeException;

public abstract class StatementBaseChain {
	
	protected StatementBaseChain next;
	
	public StatementBaseChain(StatementBaseChain next) {
		this.next = next;
	}
	
	public abstract void verificaStatement(PreparedStatement statement, int index, Object value) throws InvalidTypeException, SQLException;

	public StatementBaseChain getNext() {
		return next;
	}

	public void setNext(StatementBaseChain next) {
		this.next = next;
	}
	
}
