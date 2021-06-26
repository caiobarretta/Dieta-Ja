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
		if(this.executeQuery(query, map).size() > 0 ){
			System.out.printf("DefaultDAO.get\n");
			List<TEntity> retorno = super.executeQuery(query, map);
			System.out.printf("DefaultDAO.get retorno->size:%d\n", retorno.size());
			for (TEntity tEntity : retorno) {
				System.out.printf("DefaultDAO.get retorno->ID:%d\n", tEntity.getID());
				System.out.printf("DefaultDAO.get retorno->Nome:%s\n", tEntity.getNome());
			}
			TEntity retornoUnico = retorno.get(id);
			System.out.printf("DefaultDAO.get retornoUnico->%d\n", retornoUnico.getID());
			return retornoUnico;
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
		return super.executeUpdate(query, map);
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
