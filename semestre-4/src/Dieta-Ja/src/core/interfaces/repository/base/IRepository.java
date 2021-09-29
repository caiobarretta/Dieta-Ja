package core.interfaces.repository.base;

import core.entities.base.Entity;

public interface IRepository<TEntity extends Entity>  extends IReadRepository<TEntity>, IAlterRepository<TEntity> {

}
