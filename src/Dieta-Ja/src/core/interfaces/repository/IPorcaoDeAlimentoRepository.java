package core.interfaces.repository;

import java.util.List;

import core.entities.PorcaoDeAlimento;
import core.interfaces.repository.base.IRepository;

public interface IPorcaoDeAlimentoRepository extends IRepository<PorcaoDeAlimento> {
	Integer associarPorcaoDeAlimentoDiasDaSemanaDietaRefeicao(Integer IdPorcaoDeAlimento, List<Integer> listDiaDaSemana, List<Integer> listIdRefeicao, Integer dietaID);
	
	List<Integer> retornaIdDaDietaPeloIdPorcaoDeAlimento(Integer id);
    List<String> retornaDiasDaSemanaPeloIdPorcaoDeAlimento(Integer id);
    List<String> retornaRefeicaoPeloIdPorcaoDeAlimento(Integer id);
    List<String> retornaDiaDaSemanaPeloIDPorcaoDeAlimento(Integer id);
}
