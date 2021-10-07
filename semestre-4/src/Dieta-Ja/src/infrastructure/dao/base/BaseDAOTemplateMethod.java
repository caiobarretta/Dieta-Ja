package infrastructure.dao.base;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import core.entities.Dieta;
import core.entities.base.Entity;
import core.exception.InvalidTypeException;
import infrastructure.dao.helper.HelperExecuteStatementChain;
import infrastructure.dao.helper.HelperHashMap;
import infrastructure.dao.helper.StrBuilderHelper;

public abstract class BaseDAOTemplateMethod<TEntity extends Entity> {
	
	protected Connection conn;
	private Integer lastIdInserted;
	
	public BaseDAOTemplateMethod(Connection conn) {
		this.conn = conn;
	}
	
	public Integer getLastIdInserted() {
		return lastIdInserted;
	}

	public void setLastIdInserted(Integer lastIdInserted) {
		this.lastIdInserted = lastIdInserted;
	}
	
	protected abstract Map<Integer, Object> converterEntityParaHashMapSemID(TEntity entity);
	
	protected Map<Integer, Object> converterEntityParaHashMapComIDNaUltimaPosicao(TEntity entity){
		Map<Integer, Object> map = converterEntityParaHashMapSemID(entity);
    	Integer key = Collections.max(map.entrySet(), Map.Entry.comparingByKey()).getKey();
    	map.put(key+1 , entity.getID());
    	return map;
	}
	
	protected abstract List<TEntity> LoadEntityFromResultSet(ResultSet rs) throws SQLException;

	protected List<TEntity> executeQuery(String query) {
		ResultSet rs = null;
        Connection connection = null;
        Statement statement = null; 
        List<TEntity> result = null;
         
        try {
        	connection = this.conn;
            statement = connection.createStatement();
            rs = statement.executeQuery(query);
            result = LoadEntityFromResultSet(rs);
        } catch (SQLException e) {
			e.printStackTrace();
		}
        return result;
	}
	
	protected List<TEntity> executeQuery(String query, Map<Integer, Object> params) {
		ResultSet rs = null;
        Connection connection = null;
        PreparedStatement statement = null; 
        List<TEntity> result = null;
         
        try {           
        	connection = this.conn;
            statement = connection.prepareStatement(query);
            HelperExecuteStatementChain.Execute(statement, params);
            rs = statement.executeQuery();
            result = LoadEntityFromResultSet(rs);
        } catch (SQLException e) {
			e.printStackTrace();
		} catch (InvalidTypeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return result;
	}
	
	protected Integer executeInsert(String query, Map<Integer, Object> params) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
        int rows = 0;
        try {
        	connection = this.conn;
        	preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
        	HelperExecuteStatementChain.Execute(preparedStatement, params);
        	rows = preparedStatement.executeUpdate();
        	ResultSet rs = preparedStatement.getGeneratedKeys();
        	if (rs.next()){
        		setLastIdInserted(rs.getInt(1));
        	}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (InvalidTypeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return rows;
	}
	
	protected Integer executeUpdate(String query, Map<Integer, Object> params) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
        int rows = 0;
        try {
        	connection = this.conn;
        	preparedStatement = connection.prepareStatement(query);
        	HelperExecuteStatementChain.Execute(preparedStatement, params);
        	rows = preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (InvalidTypeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return rows;
	}
	
	public void closeConn(){
		if (this.conn != null) {
            try {
            	this.conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
	}
}
