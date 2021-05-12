package core.interfaces.repository;

import java.util.List;

import core.entities.PorcaoDeAlimento;
import core.entities.RegistroDeAtividade;
import core.interfaces.repository.base.IRepository;

public interface IRegistroDeAtividadeRepository extends IRepository<RegistroDeAtividade>{
	List<PorcaoDeAlimento> RetornaProcaoDeAlimentoPeloRegistroDeAtividade(int id);
    void AssociarPorcaoRegistroDeAlimentos(List<Integer> ids, int id);
}
