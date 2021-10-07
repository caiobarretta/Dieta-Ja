package core.config.strategy;

import java.sql.Connection;

import core.abstractions.DAOFactoryMethod;
import core.abstractions.RepositoryFactoryMethod;
import core.abstractions.ServicesFactoryMethod;
import core.config.strategy.base.IConfigContainerStrategy;
import core.interfaces.dao.IUsuarioDAO;
import core.interfaces.repository.IUsuarioRepository;
import core.interfaces.service.IUsuarioService;
import core.ioc.Container;
import infrastructure.dao.UsuarioDAO;
import infrastructure.repository.UsuarioRepository;
import services.UsuarioService;

public class UsuarioConfigContainerStrategy implements IConfigContainerStrategy{

	@Override
	public void configContainer(Container container, Connection conn) {
		UsuarioDAO usuarioDAORegister = new UsuarioDAO(conn);
		container.register(IUsuarioDAO.class, usuarioDAORegister);
		UsuarioRepository usuarioRepositoryRegister = new UsuarioRepository(new UsuarioDAO(conn));
		container.register(IUsuarioRepository.class, usuarioRepositoryRegister);
		UsuarioService usuarioServiceRegister = new UsuarioService(new UsuarioRepository(new UsuarioDAO(conn)));
		container.register(IUsuarioService.class, usuarioServiceRegister);
	}
	
	@Override
	public void configContainer(Container container, Connection conn, DAOFactoryMethod daoFactory) {
		
		IUsuarioDAO usuarioDAORegister = daoFactory.createUsuarioDAO(conn);
		
		UsuarioRepository usuarioRepositoryRegister = new UsuarioRepository(usuarioDAORegister);
		container.register(IUsuarioRepository.class, usuarioRepositoryRegister);
		
		UsuarioService usuarioServiceRegister = new UsuarioService(new UsuarioRepository(new UsuarioDAO(conn)));
		container.register(IUsuarioService.class, usuarioServiceRegister);
	}
	

	@Override
	public void configContainer(Container container, Connection conn, DAOFactoryMethod daoFactory,
			RepositoryFactoryMethod repositoryFactory) {
		
		IUsuarioRepository usuarioRepositoryRegister = configContainerRepository(container, conn, daoFactory, 
				repositoryFactory);
		
		UsuarioService usuarioServiceRegister = new UsuarioService(usuarioRepositoryRegister);
		container.register(IUsuarioService.class, usuarioServiceRegister);
	}

	@Override
	public void configContainer(Container container, Connection conn, DAOFactoryMethod daoFactory, 
			RepositoryFactoryMethod repositoryFactory, ServicesFactoryMethod servicesFactory) {
		
		IUsuarioRepository usuarioRepositoryRegister = configContainerRepository(container, conn, daoFactory, 
				repositoryFactory);
		
		IUsuarioService usuarioServiceRegister = servicesFactory.createUsuarioService(usuarioRepositoryRegister);
		container.register(IUsuarioService.class, usuarioServiceRegister);
	}

	private IUsuarioRepository configContainerRepository(Container container, Connection conn,
			DAOFactoryMethod daoFactory, RepositoryFactoryMethod repositoryFactory) {
		
		IUsuarioDAO dao = daoFactory.createUsuarioDAO(conn);
		
		IUsuarioRepository usuarioRepositoryRegister = repositoryFactory.createUsuarioRepository(dao);
		container.register(IUsuarioRepository.class, usuarioRepositoryRegister);
		return usuarioRepositoryRegister;
	}

}
