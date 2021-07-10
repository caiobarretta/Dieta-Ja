package core.interfaces.repository;

import java.util.List;

import core.entities.PorcaoDeAlimento;
import core.interfaces.repository.base.IRepository;

public interface IPorcaoDeAlimentoRepository extends IRepository<PorcaoDeAlimento> {
	Integer associarPorcaoRefeicoes(List<Integer> listIdRefeicao, Integer porcaoDeAlimentoID);
	Integer associarPorcaoAlimentoDiaDaSemana(List<Integer> listDiaDaSemana, Integer porcaoDeAlimentoID);
	Integer associarPorcaoAlimentoDieta(List<Integer> listIdProcaoAlimento, Integer dietaID);
	
    List<PorcaoDeAlimento> retornaPorcaoDeAlimentoPeloIdDaDieta(Integer id);
    List<String> retornaDiasDaSemanaPeloIdPorcaoDeAlimento(Integer id);
    List<String> retornaRefeicaoPeloIdPorcaoDeAlimento(Integer id);
    List<String> retornaDiaDaSemanaPeloIDPorcaoDeAlimento(Integer id);
}
