package infrastructure.repository;

import java.util.List;

import core.entities.PorcaoDeAlimento;
import core.entities.RegistroDeAtividade;
import core.interfaces.repository.IRefeicoesRepository;
import core.interfaces.repository.IRegistroDeAtividadeRepository;
import infrastructure.repository.base.DefaultRepository;

public class RegistroDeAtividadeRepository extends DefaultRepository<RegistroDeAtividade> implements IRegistroDeAtividadeRepository {
	
	public void associarPorcaoRegistroDeAlimentos(List<Integer> lstIdPorcaoDeAlimento, int id){
		//TODO : Implementar
        throw new UnsupportedOperationException("Implementar");
    }

    public List<PorcaoDeAlimento> retornaProcaoDeAlimentoPeloRegistroDeAtividade(int id){
    	//TODO : Implementar
        throw new UnsupportedOperationException("Implementar");
    }
}
