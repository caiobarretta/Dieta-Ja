package core.interfaces.repository;

import java.util.List;

import core.entities.PorcaoDeAlimento;
import core.entities.RegistroDeAtividade;
import core.interfaces.repository.base.IRepository;

public interface IRegistroDeAtividadeRepository{
	List<PorcaoDeAlimento> retornaProcaoDeAlimentoPeloRegistroDeAtividade(Integer id);
	Integer associarPorcaoRegistroDeAlimentos(List<Integer> ids, Integer id);
}
