package core.abstractions;

import core.interfaces.dao.IDietaDAO;
import core.interfaces.dao.IPorcaoDeAlimentoDAO;
import core.interfaces.dao.IUsuarioDAO;
import core.interfaces.repository.IDietaRepository;
import core.interfaces.repository.IPorcaoDeAlimentoRepository;
import core.interfaces.repository.IUsuarioRepository;

public abstract class RepositoryFactoryMethod {
	
	public abstract IDietaRepository createDietaRepository(IDietaDAO dao);
	public abstract IPorcaoDeAlimentoRepository createPorcaoDeAlimentoRepository(IPorcaoDeAlimentoDAO dao);
	public abstract IUsuarioRepository createUsuarioRepository(IUsuarioDAO dao);
	
}
