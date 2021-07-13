package core.interfaces.repository.base;

import core.entities.base.Entity;

public interface IAlterRepository<TEntity extends Entity> {
	Integer getLastIdInserted();
	Integer add(TEntity entity);
	Integer update(TEntity entity);
	Integer delete(Integer id);
}
