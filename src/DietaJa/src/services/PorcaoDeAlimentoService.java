package services;

import java.util.List;

import core.entities.PorcaoDeAlimento;
import core.interfaces.service.IPorcaoDeAlimentoService;
import services.base.DefaultService;

public class PorcaoDeAlimentoService extends DefaultService<PorcaoDeAlimento> implements IPorcaoDeAlimentoService{
	public void associarPorcaoRefeicoes(List<Integer> listIdRefeicao, int idPorcaoDeAlimento) {
		//TODO : Implementar
        throw new UnsupportedOperationException("Implementar");
	}

    public void associarPorcaoAlimentoDieta(List<Integer> listIdProcaoAlimento, int idDieta){
    	//TODO : Implementar
        throw new UnsupportedOperationException("Implementar");
    }
    public List<PorcaoDeAlimento> retornaPorcaoDeAlimentoPeloIdDaDieta(int id){
    	//TODO : Implementar
        throw new UnsupportedOperationException("Implementar");
    }
}
