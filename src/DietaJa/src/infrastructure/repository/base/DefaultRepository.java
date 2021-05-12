package infrastructure.repository.base;

import java.util.List;
import core.entities.base.Entity;
import core.interfaces.repository.base.IRepository;

public abstract class DefaultRepository<TEntity extends Entity> implements IRepository<TEntity>{

	public void add(TEntity entity)
    {
        //TODO : Implementar
        throw new UnsupportedOperationException("Implementar");
    }

    public void delete(TEntity entity)
    {
        //TODO : Implementar
    	throw new UnsupportedOperationException("Implementar");
    }

    public List<TEntity> get(int take, int skip)
    {
    	//TODO : Implementar
    	throw new UnsupportedOperationException("Implementar");
    }

    public TEntity get(int id)
    {
    	//TODO : Implementar
        throw new UnsupportedOperationException("Implementar");
    }

    public TEntity get(TEntity entity)
    {
    	//TODO : Implementar
        throw new UnsupportedOperationException("Implementar");
    }

    public List<TEntity> search(TEntity entity, String search)
    {
    	//TODO : Implementar
        throw new UnsupportedOperationException("Implementar");
    }

    public void update(TEntity entity)
    {
    	//TODO : Implementar
        throw new UnsupportedOperationException("Implementar");
    }
}
