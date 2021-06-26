package core.interfaces.dao.base;

import core.entities.base.Entity;
import core.interfaces.repository.base.IRepository;

public interface IDAO<TEntity extends Entity> extends IRepository<TEntity>{

}
