package services.base;

import java.util.List;

import core.entities.base.Entity;
import core.interfaces.service.base.IService;

public abstract class DefaultService<TEntity extends Entity> implements IService<TEntity> {
	
	public void add(TEntity entity) {
		//TODO : Implementar
        throw new UnsupportedOperationException("Implementar");
	}

    public void delete(TEntity entity) {
    	//TODO : Implementar
        throw new UnsupportedOperationException("Implementar");
    }

    public List<TEntity> get(int take, int skip){
    	//TODO : Implementar
        throw new UnsupportedOperationException("Implementar");
    }
    public TEntity get(int id) {
    	//TODO : Implementar
        throw new UnsupportedOperationException("Implementar");
    }

    public TEntity get(TEntity entity) {
    	//TODO : Implementar
        throw new UnsupportedOperationException("Implementar");
    }

    public List<TEntity> search(TEntity entity, String search){
    	//TODO : Implementar
        throw new UnsupportedOperationException("Implementar");
    }

    public void update(TEntity entity) {
    	//TODO : Implementar
        throw new UnsupportedOperationException("Implementar");
    }
}
