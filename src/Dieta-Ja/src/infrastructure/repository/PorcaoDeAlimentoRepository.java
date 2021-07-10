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


	@Override
	public List<String> retornaDiasDaSemanaPeloIdPorcaoDeAlimento(Integer id) {
		return this._idao.retornaDiasDaSemanaPeloIdPorcaoDeAlimento(id);
	}

	@Override
	public List<String> retornaRefeicaoPeloIdPorcaoDeAlimento(Integer id) {
		return this._idao.retornaRefeicaoPeloIdPorcaoDeAlimento(id);
	}

	@Override
	public Integer associarPorcaoAlimentoDiaDaSemana(List<Integer> listDiaDaSemana, Integer porcaoDeAlimentoID) {
		return this._idao.associarPorcaoAlimentoDiaDaSemana(listDiaDaSemana, porcaoDeAlimentoID);
	}

	@Override
	public Integer getLastIdInserted() {
		return this._idao.getLastIdInserted();
	}

	@Override
	public List<String> retornaDiaDaSemanaPeloIDPorcaoDeAlimento(Integer id) {
		return this._idao.retornaDiaDaSemanaPeloIDPorcaoDeAlimento(id);
	}
}