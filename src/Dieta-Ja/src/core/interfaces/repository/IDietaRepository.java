package core.interfaces.repository;

import java.util.List;

import core.entities.Dieta;
import core.interfaces.repository.base.IRepository;

public interface IDietaRepository extends IRepository<Dieta>{
	List<Integer> retornaPorcaoDeAlimentoPeloIdDieta(Integer id);
}
