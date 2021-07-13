package infrastructure.repository;

import java.util.List;

import core.entities.PorcaoDeAlimento;
import core.entities.RegistroDeAtividade;
import core.interfaces.dao.IRegistroDeAtividadeDAO;
import core.interfaces.dao.base.IDAO;
import core.interfaces.repository.IRegistroDeAtividadeRepository;
import infrastructure.repository.base.DefaultRepository;

public class RegistroDeAtividadeRepository implements IRegistroDeAtividadeRepository {
	
	protected final IRegistroDeAtividadeDAO _idao;
	public RegistroDeAtividadeRepository(IRegistroDeAtividadeDAO dao) {
		this._idao = dao;
	}
	
	public Integer associarPorcaoRegistroDeAlimentos(List<Integer> lstIdPorcaoDeAlimento, Integer id){
		return this._idao.associarPorcaoRegistroDeAlimentos(lstIdPorcaoDeAlimento, id);
    }

    public List<PorcaoDeAlimento> retornaProcaoDeAlimentoPeloRegistroDeAtividade(Integer id){
    	return this._idao.retornaProcaoDeAlimentoPeloRegistroDeAtividade(id);
    }
}
