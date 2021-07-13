package core.interfaces.repository.base;

import java.util.List;

import core.entities.base.Entity;

public interface IReadRepository<TEntity extends Entity>{
	List<TEntity> get(Integer take, Integer skip);
    TEntity get(Integer id);
    List<TEntity> search(String search);
}
