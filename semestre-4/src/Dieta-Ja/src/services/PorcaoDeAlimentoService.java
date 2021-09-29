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
    public List<Integer> retornaIdDaDietaPeloIdPorcaoDeAlimento(Integer id){
    	return this._repo.retornaIdDaDietaPeloIdPorcaoDeAlimento(id);
    }
	@Override
	public List<String> retornaDiasDaSemanaPeloIdPorcaoDeAlimento(Integer id) {
		return this._repo.retornaDiasDaSemanaPeloIdPorcaoDeAlimento(id);
	}
	@Override
	public List<String> retornaRefeicaoPeloIdPorcaoDeAlimento(Integer id) {
		return this._repo.retornaRefeicaoPeloIdPorcaoDeAlimento(id);
	}
	@Override
	public Integer getLastIdInserted() {
		return this._repo.getLastIdInserted();
	}
	@Override
	public List<String> retornaDiaDaSemanaPeloIDPorcaoDeAlimento(Integer id) {
		// TODO Auto-generated method stub
		return this._repo.retornaDiaDaSemanaPeloIDPorcaoDeAlimento(id);
	}
	@Override
	public Integer associarPorcaoDeAlimentoDiasDaSemanaDietaRefeicao(Integer IdPorcaoDeAlimento,
			List<Integer> listDiaDaSemana, List<Integer> listIdRefeicao, Integer dietaID) {
		return this._repo.associarPorcaoDeAlimentoDiasDaSemanaDietaRefeicao(IdPorcaoDeAlimento, listDiaDaSemana, listIdRefeicao, dietaID);
	}
}
