package infrastructure.repository;

import core.abstractions.RepositoryFactoryMethod;
import core.interfaces.dao.IDietaDAO;
import core.interfaces.dao.IPorcaoDeAlimentoDAO;
import core.interfaces.dao.IUsuarioDAO;
import core.interfaces.repository.IDietaRepository;
import core.interfaces.repository.IPorcaoDeAlimentoRepository;
import core.interfaces.repository.IUsuarioRepository;

public class RepositoryFactory extends RepositoryFactoryMethod{
	
	@Override
	public IDietaRepository createDietaRepository(IDietaDAO dao) {
		return new DietaRepository(dao);
	}

	@Override
	public IPorcaoDeAlimentoRepository createPorcaoDeAlimentoRepository(IPorcaoDeAlimentoDAO dao) {
		return new PorcaoDeAlimentoRepository(dao);
	}

	@Override
	public IUsuarioRepository createUsuarioRepository(IUsuarioDAO dao) {
		return new UsuarioRepository(dao);
	}

}
