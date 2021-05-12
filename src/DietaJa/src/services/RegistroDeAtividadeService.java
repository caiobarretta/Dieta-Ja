package services;

import java.util.List;

import core.entities.PorcaoDeAlimento;
import core.entities.RegistroDeAtividade;
import core.interfaces.service.IRegistroDeAtividadeService;
import services.base.DefaultService;

public class RegistroDeAtividadeService extends DefaultService<RegistroDeAtividade> implements IRegistroDeAtividadeService{
	
	public List<PorcaoDeAlimento> RetornaProcaoDeAlimentoPeloRegistroDeAtividade(int id){
		//TODO : Implementar
        throw new UnsupportedOperationException("Implementar");
	}

    public void AssociarPorcaoRegistroDeAlimentos(List<Integer> lstIdPorcaoDeAlimento, int id) {
    	//TODO : Implementar
        throw new UnsupportedOperationException("Implementar");
    }
}
