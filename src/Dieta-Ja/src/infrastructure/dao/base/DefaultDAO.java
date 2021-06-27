package infrastructure.dao.base;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

import core.entities.base.Entity;
import infrastructure.dao.helper.HelperHashMap;
import infrastructure.dao.helper.StrBuilderHelper;

public abstract class DefaultDAO<TEntity extends Entity> extends BaseDAO<TEntity> {
	public DefaultDAO(Connection conn) {
		super(conn);
	}
	
	protected List<TEntity> get(String query, Integer take, Integer skip){
		Map<Integer, Object> map = HelperHashMap.criarHashMapComNInteirosSequenciais(take, skip);
		return super.executeQuery(query, map);
	}
	
	protected TEntity get(String query, Integer id) {
		Map<Integer, Object> map = HelperHashMap.criarHashMapComNInteirosSequenciais(id);
		if(this.executeQuery(query, map).size() > 0 )
			return  super.executeQuery(query, map).get(0);
		return null;
	}
	
	protected List<TEntity> search(String query, String search){
		String searchLike = StrBuilderHelper.criarString("%", search, "%");
		Map<Integer, Object> map = HelperHashMap.criarHashMapComNStringsSequenciais(searchLike, searchLike);
		return super.executeQuery(query, map);
	}
	
	protected Integer add(String query, TEntity entity) {
		Map<Integer, Object> map = this.converterEntityParaHashMapSemID(entity);
		Integer retorno = super.executeUpdate(query, map);
		return retorno;
	}
	
	protected Integer update(String query, TEntity entity) {
		Map<Integer, Object> map = converterEntityParaHashMapComIDNaUltimaPosicao(entity);
		return super.executeUpdate(query, map);
	}
	
	protected Integer delete(String query, Integer id) {
		Map<Integer, Object> map = HelperHashMap.criarHashMapComNInteirosSequenciais(id);
		return super.executeUpdate(query, map);
	}
}
