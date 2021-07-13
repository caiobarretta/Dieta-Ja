package services.base;

import java.util.List;

import core.entities.base.Entity;
import core.interfaces.repository.base.IRepository;
import core.interfaces.service.base.IService;

public abstract class DefaultService<TEntity extends Entity> implements IService<TEntity> {
	
	protected final IRepository<TEntity> _repo;
	 public DefaultService(IRepository<TEntity> repo) {
		 _repo =  repo;
	 }
	
	public Integer add(TEntity entity) {
		return _repo.add(entity);
	}

    public Integer delete(Integer id) {
    	return _repo.delete(id);
    }

    public List<TEntity> get(Integer take, Integer skip){
    	return _repo.get(take, skip);
    }
    
    public TEntity get(Integer id) {
    	return _repo.get(id);
    }

    public List<TEntity> search(String search){
    	return _repo.search(search);
    }

    public Integer update(TEntity entity) {
    	return _repo.update(entity);
    }
}
