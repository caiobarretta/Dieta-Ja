package infrastructure.repository;

import java.util.List;
import core.entities.PorcaoDeAlimento;
import core.interfaces.repository.IPorcaoDeAlimentoRepository;
import infrastructure.repository.base.DefaultRepository;

public class PorcaoDeAlimentoRepository extends DefaultRepository<PorcaoDeAlimento> implements IPorcaoDeAlimentoRepository{
	
	
	public void associarPorcaoAlimentoDieta(List<Integer> listIdProcaoAlimento, int idDieta){
		//TODO : Implementar
        throw new UnsupportedOperationException("Implementar");
    }

    public void associarPorcaoRefeicoes(List<Integer> listIdRefeicao, int idPorcaoDeAlimento){
    	//TODO : Implementar
        throw new UnsupportedOperationException("Implementar");
    }

    @Override
    public PorcaoDeAlimento get(int id){
    	//TODO : Implementar
        throw new UnsupportedOperationException("Implementar");
    }

    public List<PorcaoDeAlimento> retornaPorcaoDeAlimentoPeloIdDaDieta(int id){
    	//TODO : Implementar
        throw new UnsupportedOperationException("Implementar");
    }

    @Override
    public PorcaoDeAlimento get(PorcaoDeAlimento entity){
    	//TODO : Implementar
        throw new UnsupportedOperationException("Implementar");
    }
}