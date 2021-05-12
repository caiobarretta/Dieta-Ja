package core.interfaces.repository.base;

import core.entities.base.Entity;

public interface IAlterRepository<TEntity extends Entity> {
	void add(TEntity entity);
    void update(TEntity entity);
    void delete(TEntity entity);
}
