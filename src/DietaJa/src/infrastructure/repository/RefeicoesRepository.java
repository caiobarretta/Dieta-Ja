package infrastructure.repository;

import java.util.List;

import core.entities.Refeicao;
import core.interfaces.repository.IRefeicoesRepository;
import infrastructure.repository.base.DefaultRepository;

public class RefeicoesRepository extends DefaultRepository<Refeicao> implements IRefeicoesRepository{
	public List<Refeicao> retornaRefeicoesPeloIdDaProcaoDeAlimento(int id){
		//TODO : Implementar
        throw new UnsupportedOperationException("Implementar");
    }
}
