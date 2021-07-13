package infrastructure.repository.base;

import java.util.List;
import core.entities.base.Entity;
import core.interfaces.dao.base.IDAO;
import core.interfaces.repository.base.IRepository;

public abstract class DefaultRepository<TEntity extends Entity> implements IRepository<TEntity>{

	public Integer add(TEntity entity){
		return this.getDAO().add(entity);
    }

    public Integer delete(Integer id){
    	return this.getDAO().delete(id);
    }

    public List<TEntity> get(Integer take, Integer skip){
    	return this.getDAO().get(take, skip);
    }

    public TEntity get(Integer id){
    	return this.getDAO().get(id);
    }

    public List<TEntity> search(String search){
    	return this.getDAO().search(search);
    }

    public Integer update(TEntity entity){
    	return getDAO().update(entity);
    }
    
    public abstract IDAO<TEntity> getDAO();
}
