package core.interfaces.repository;

import java.util.List;

import core.entities.PorcaoDeAlimento;
import core.interfaces.repository.base.IRepository;

public interface IPorcaoDeAlimentoRepository extends IRepository<PorcaoDeAlimento> {
	void AssociarPorcaoRefeicoes(List<Integer> listIdRefeicao, int porcaoDeAlimentoID);
    void AssociarPorcaoAlimentoDieta(List<Integer> listIdProcaoAlimento, int dietaID);
    List<PorcaoDeAlimento> RetornaPorcaoDeAlimentoPeloIdDaDieta(int id);
}
