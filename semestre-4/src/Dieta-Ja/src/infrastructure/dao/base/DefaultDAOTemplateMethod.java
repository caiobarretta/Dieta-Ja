package infrastructure.dao.base;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import core.entities.base.Entity;
import core.exception.InvalidTypeException;
import infrastructure.dao.helper.HelperExecuteStatementChain;
import infrastructure.dao.helper.HelperHashMap;
import infrastructure.dao.helper.StrBuilderHelper;

public abstract class DefaultDAOTemplateMethod<TEntity extends Entity> extends BaseDAOTemplateMethod<TEntity> {
	public DefaultDAOTemplateMethod(Connection conn) {
		super(conn);
	}
	
	protected List<TEntity> get(String query, Integer take, Integer skip){
		Map<Integer, Object> map = HelperHashMap.criarHashMapComNInteirosSequenciais(take, skip);
		return super.executeQuery(query, map);
	}
	
	protected TEntity get(String query, Integer id) {
		Map<Integer, Object> map = HelperHashMap.criarHashMapComNInteirosSequenciais(id);
		if(this.executeQuery(query, map).size() > 0 ){
			return  super.executeQuery(query, map).get(0);
		}
		return null;
	}
	
	protected List<TEntity> search(String query, String search){
		String searchLike = StrBuilderHelper.criarString("%", search, "%");
		Map<Integer, Object> map = HelperHashMap.criarHashMapComNStringsSequenciais(searchLike, searchLike);
		return super.executeQuery(query, map);
	}
	
	protected Integer add(String query, TEntity entity) {
		Map<Integer, Object> map = this.converterEntityParaHashMapSemID(entity);
		Integer retorno = super.executeInsert(query, map);
		return retorno;
	}
	
	protected Integer add(String query, Integer idEntity, Integer idAssociation) {
		Map<Integer, Object> map = HelperHashMap.criarHashMapComNInteirosSequenciais(idEntity, idAssociation);
		Integer retorno = super.executeUpdate(query, map);
		return retorno;
	}
	
	protected Integer add(String query, Integer... params) {
		Map<Integer, Object> map = HelperHashMap.criarHashMapComNInteirosSequenciais(params);
		Integer retorno = super.executeUpdate(query, map);
		return retorno;
	}
	
	
	protected Integer update(String query, TEntity entity) {
		Map<Integer, Object> map = converterEntityParaHashMapComIDNaUltimaPosicao(entity);
		return super.executeUpdate(query, map);
	}
	
	protected Integer delete(String query, Integer... params) {
		Map<Integer, Object> map = HelperHashMap.criarHashMapComNInteirosSequenciais(params);
		return super.executeUpdate(query, map);
	}
	
	protected List<Integer> loadFieldIntegerFromEnumAsList(String query, String fieldName, Integer id){
		ResultSet rs = null;
        Connection connection = null;
        PreparedStatement statement = null; 
        List<Integer> result = new ArrayList<Integer>();
        Map<Integer, Object> params = HelperHashMap.criarHashMapComNInteirosSequenciais(id);
        try {           
        	connection = this.conn;
            statement = connection.prepareStatement(query);
            HelperExecuteStatementChain.Execute(statement, params);
            rs = statement.executeQuery();
            while(rs.next()) {
            	result.add(rs.getInt(fieldName));
            }
        } catch (SQLException e) {
			e.printStackTrace();
		} catch (InvalidTypeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return result;
	}
	
	protected Integer associar(String query, List<Integer> listId, Integer IdEntity){
		int counterRows = 0;
		for (Integer id : listId) {
			counterRows += this.add(query, IdEntity, id);
		}
		return counterRows;
	}

}
