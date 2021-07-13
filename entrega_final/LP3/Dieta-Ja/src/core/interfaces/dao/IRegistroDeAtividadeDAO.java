package core.interfaces.dao;

import core.entities.RegistroDeAtividade;
import core.interfaces.dao.base.IDAO;
import core.interfaces.repository.IRegistroDeAtividadeRepository;

public interface IRegistroDeAtividadeDAO extends IRegistroDeAtividadeRepository{
	
	Integer Add(RegistroDeAtividade registro);

}
