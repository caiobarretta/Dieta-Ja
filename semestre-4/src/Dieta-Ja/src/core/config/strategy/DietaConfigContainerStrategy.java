package core.config.strategy;

import java.sql.Connection;

import core.abstractions.DAOFactoryMethod;
import core.abstractions.RepositoryFactoryMethod;
import core.abstractions.ServicesFactoryMethod;
import core.config.strategy.base.IConfigContainerStrategy;
import core.interfaces.dao.IDietaDAO;
import core.interfaces.repository.IDietaRepository;
import core.interfaces.service.IDietaService;
import core.ioc.Container;
import infrastructure.dao.DietaDAO;
import infrastructure.repository.DietaRepository;
import services.DietaService;

public class DietaConfigContainerStrategy implements IConfigContainerStrategy {

	@Override
	public void configContainer(Container container, Connection conn) {
		DietaDAO dietaDAORegister = new DietaDAO(conn);
		container.register(IDietaDAO.class, dietaDAORegister);
		DietaRepository dietaRepositoryRegister = new DietaRepository(new DietaDAO(conn));
		container.register(IDietaRepository.class, dietaRepositoryRegister);
		DietaService dietaServiceRegister = new DietaService(new DietaRepository(new DietaDAO(conn)));
		container.register(IDietaService.class, dietaServiceRegister);
	}
	
	@Override
	public void configContainer(Container container, Connection conn, DAOFactoryMethod daoFactory) {
		IDietaDAO dao = daoFactory.createDietaDAO(conn);
		
		DietaRepository dietaRepositoryRegister = new DietaRepository(dao);
		container.register(IDietaRepository.class, dietaRepositoryRegister);
		DietaService dietaServiceRegister = new DietaService(new DietaRepository(new DietaDAO(conn)));
		container.register(IDietaService.class, dietaServiceRegister);
	}

	@Override
	public void configContainer(Container container, Connection conn, DAOFactoryMethod daoFactory, 
			RepositoryFactoryMethod repositoryFactory) {
		IDietaRepository dietaRepositoryRegister = configContainerRepository(container, conn, 
				repositoryFactory, daoFactory);
		
		DietaService dietaServiceRegister = new DietaService(dietaRepositoryRegister);
		container.register(IDietaService.class, dietaServiceRegister);
	}

	@Override
	public void configContainer(Container container, Connection conn, DAOFactoryMethod daoFactory, 
			RepositoryFactoryMethod repositoryFactory, ServicesFactoryMethod servicesFactory) {
		IDietaRepository dietaRepositoryRegister = configContainerRepository(container, conn, 
				repositoryFactory, daoFactory);
		
		IDietaService dietaServiceRegister = servicesFactory.createDietaService(dietaRepositoryRegister);
		container.register(IDietaService.class, dietaServiceRegister);
	}
	
	private IDietaRepository configContainerRepository(Container container, Connection conn,
			 RepositoryFactoryMethod repositoryFactory, DAOFactoryMethod daoFactory) {
		
		IDietaDAO dao = daoFactory.createDietaDAO(conn);
		IDietaRepository dietaRepositoryRegister = repositoryFactory.createDietaRepository(dao);
		container.register(IDietaRepository.class, dietaRepositoryRegister);
		return dietaRepositoryRegister;
	}
}
