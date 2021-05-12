package core.interfaces.repository;

import java.util.List;

import core.entities.Refeicao;
import core.interfaces.repository.base.IRepository;

public interface IRefeicoesRepository extends IRepository<Refeicao> {
	List<Refeicao> RetornaRefeicoesPeloIdDaProcaoDeAlimento(int id);
}
