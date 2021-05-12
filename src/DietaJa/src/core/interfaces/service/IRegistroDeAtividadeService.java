package core.interfaces.service;

import java.util.List;

import core.entities.PorcaoDeAlimento;
import core.entities.RegistroDeAtividade;
import core.interfaces.service.base.IService;

public interface IRegistroDeAtividadeService extends IService<RegistroDeAtividade> {
	List<PorcaoDeAlimento> RetornaProcaoDeAlimentoPeloRegistroDeAtividade(int id);
}
