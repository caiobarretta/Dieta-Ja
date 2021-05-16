package infrastructure.repository;

import java.util.List;
import core.entities.PorcaoDeAlimento;
import core.interfaces.dao.IPorcaoDeAlimentoDAO;
import core.interfaces.dao.base.IDAO;
import core.interfaces.repository.IPorcaoDeAlimentoRepository;
import infrastructure.repository.base.DefaultRepository;

public class PorcaoDeAlimentoRepository extends DefaultRepository<PorcaoDeAlimento> implements IPorcaoDeAlimentoRepository{
	
	protected final IPorcaoDeAlimentoDAO _idao;
	public PorcaoDeAlimentoRepository(IPorcaoDeAlimentoDAO dao) {
		this._idao = dao;
	}
	
	
	public Integer associarPorcaoAlimentoDieta(List<Integer> listIdProcaoAlimento, Integer idDieta){
		return this._idao.associarPorcaoAlimentoDieta(listIdProcaoAlimento, idDieta);
    }

    public Integer associarPorcaoRefeicoes(List<Integer> listIdRefeicao, Integer idPorcaoDeAlimento){
    	return this._idao.associarPorcaoRefeicoes(listIdRefeicao, idPorcaoDeAlimento);
    }

    public List<PorcaoDeAlimento> retornaPorcaoDeAlimentoPeloIdDaDieta(Integer id){
    	return this._idao.retornaPorcaoDeAlimentoPeloIdDaDieta(id);
    }

	@Override
	public IDAO<PorcaoDeAlimento> getDAO() {
		return this._idao;
	}
}