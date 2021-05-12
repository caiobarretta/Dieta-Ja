package core.interfaces.repository;

import java.util.List;

import core.entities.DiaDaSemana;
import core.interfaces.repository.base.IRepository;

public interface IDiasdaSemanaRepository extends IRepository<DiaDaSemana>{
	void associarDiasDaSemanaRefeicoes(List<Integer> listIdDiasdaSemana, int PorcAlimentoID);
    List<DiaDaSemana> retornarDiaDaSemanaPeloIdDaPorcaoDeAlimento(int id);
}
