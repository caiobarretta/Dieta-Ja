package services;

import java.util.List;

import core.entities.PorcaoDeAlimento;
import core.interfaces.repository.IPorcaoDeAlimentoRepository;
import core.interfaces.service.IPorcaoDeAlimentoService;
import services.base.DefaultService;

public class PorcaoDeAlimentoService extends DefaultService<PorcaoDeAlimento> implements IPorcaoDeAlimentoService{
	
	protected final IPorcaoDeAlimentoRepository _repo;
	public PorcaoDeAlimentoService(IPorcaoDeAlimentoRepository repo) {
		super(repo);
		this._repo = repo;
	}
	public Integer associarPorcaoRefeicoes(List<Integer> listIdRefeicao, Integer idPorcaoDeAlimento) {
		return this._repo.associarPorcaoRefeicoes(listIdRefeicao, idPorcaoDeAlimento);
	}

    public Integer associarPorcaoAlimentoDieta(List<Integer> listIdProcaoAlimento, Integer idDieta){
    	return this._repo.associarPorcaoAlimentoDieta(listIdProcaoAlimento, idDieta);
    }
    public List<PorcaoDeAlimento> retornaPorcaoDeAlimentoPeloIdDaDieta(Integer id){
    	return this._repo.retornaPorcaoDeAlimentoPeloIdDaDieta(id);
    }
}
