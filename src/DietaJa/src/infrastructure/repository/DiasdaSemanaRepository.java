package infrastructure.repository;

import java.util.List;

import core.entities.DiaDaSemana;
import core.interfaces.repository.IDiasdaSemanaRepository;
import infrastructure.repository.base.DefaultRepository;

public class DiasdaSemanaRepository extends DefaultRepository<DiaDaSemana> implements IDiasdaSemanaRepository {
	
	public void associarDiasDaSemanaRefeicoes(List<Integer> listIdDiasdaSemana, int PorcaoAlimentoID){
		//TODO : Implementar
        throw new UnsupportedOperationException("Implementar");
    }

    public List<DiaDaSemana> retornarDiaDaSemanaPeloIdDaPorcaoDeAlimento(int id){
    	//TODO : Implementar
        throw new UnsupportedOperationException("Implementar");
    }
}
