package core.interfaces.repository.base;

import java.util.List;

import core.entities.base.Entity;

public interface IReadRepository<TEntity extends Entity>{
	List<TEntity> get(int take, int skip);
    TEntity get(int id);
    TEntity get(TEntity entity);
    List<TEntity> Search(TEntity entity, String search);
}
