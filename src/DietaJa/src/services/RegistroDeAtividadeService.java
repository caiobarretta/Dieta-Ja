package services;

import java.util.List;

import core.entities.PorcaoDeAlimento;
import core.entities.RegistroDeAtividade;
import core.interfaces.repository.IRegistroDeAtividadeRepository;
import core.interfaces.service.IRegistroDeAtividadeService;
import services.base.DefaultService;

public class RegistroDeAtividadeService implements IRegistroDeAtividadeService{
	
	protected final IRegistroDeAtividadeRepository _repo;
	public RegistroDeAtividadeService(IRegistroDeAtividadeRepository repo) {
		this._repo = repo;
	}

	public List<PorcaoDeAlimento> retornaProcaoDeAlimentoPeloRegistroDeAtividade(Integer id){
		return this._repo.retornaProcaoDeAlimentoPeloRegistroDeAtividade(id);
	}

    public Integer associarPorcaoRegistroDeAlimentos(List<Integer> lstIdPorcaoDeAlimento, Integer id) {
    	return this._repo.associarPorcaoRegistroDeAlimentos(lstIdPorcaoDeAlimento, id);
    }
}
