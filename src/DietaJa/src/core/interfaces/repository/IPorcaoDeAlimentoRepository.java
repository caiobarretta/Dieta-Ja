package core.interfaces.repository;

import java.util.List;

import core.entities.PorcaoDeAlimento;
import core.interfaces.repository.base.IRepository;

public interface IPorcaoDeAlimentoRepository extends IRepository<PorcaoDeAlimento> {
	void associarPorcaoRefeicoes(List<Integer> listIdRefeicao, int porcaoDeAlimentoID);
    void associarPorcaoAlimentoDieta(List<Integer> listIdProcaoAlimento, int dietaID);
    List<PorcaoDeAlimento> retornaPorcaoDeAlimentoPeloIdDaDieta(int id);
}
