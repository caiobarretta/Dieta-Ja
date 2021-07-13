package core.interfaces.repository;

import java.util.List;

import app.model.TreeViewPorcaoDeAlimentoDTO;
import core.entities.Dieta;
import core.interfaces.repository.base.IRepository;

public interface IDietaRepository extends IRepository<Dieta>{
	List<TreeViewPorcaoDeAlimentoDTO> retornaPorcaoDeAlimentoPeloIdDieta(Integer id);
	List<Integer> retornaPorcaoDeAlimentoPeloIdDietaAgrupado(Integer id);
}
