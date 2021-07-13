package core.interfaces.service.base;

import core.entities.base.Entity;
import core.interfaces.repository.base.IRepository;

public interface IService<TEntity extends Entity> extends IRepository<TEntity> {

}
