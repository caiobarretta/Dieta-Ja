package services;

import java.util.List;

import core.entities.DiaDaSemana;
import core.interfaces.service.IDiasdaSemanaService;
import infrastructure.repository.base.DefaultRepository;

public class DiaDaSemanaService extends DefaultRepository<DiaDaSemana> implements IDiasdaSemanaService{
	
	public void associarDiasDaSemanaRefeicoes(List<Integer> listIdDiasdaSemana, int PorcAlimentoId) {
		//TODO : Implementar
        throw new UnsupportedOperationException("Implementar");
	}

    public List<DiaDaSemana> retornarDiaDaSemanaPeloIdDaPorcaoDeAlimento(int id){
    	//TODO : Implementar
        throw new UnsupportedOperationException("Implementar");
    }
}
