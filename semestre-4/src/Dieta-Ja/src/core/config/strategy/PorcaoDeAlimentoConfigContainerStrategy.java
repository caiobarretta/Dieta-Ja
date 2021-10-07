package core.config.strategy;

import java.sql.Connection;

import core.abstractions.DAOFactoryMethod;
import core.abstractions.RepositoryFactoryMethod;
import core.abstractions.ServicesFactoryMethod;
import core.config.strategy.base.IConfigContainerStrategy;
import core.interfaces.dao.IPorcaoDeAlimentoDAO;
import core.interfaces.repository.IPorcaoDeAlimentoRepository;
import core.interfaces.service.IPorcaoDeAlimentoService;
import core.ioc.Container;
import infrastructure.dao.PorcaoDeAlimentoDAO;
import infrastructure.repository.PorcaoDeAlimentoRepository;
import services.PorcaoDeAlimentoService;

public class PorcaoDeAlimentoConfigContainerStrategy implements IConfigContainerStrategy{

	@Override
	public void configContainer(Container container, Connection conn) {
		PorcaoDeAlimentoDAO porcaoDeAlimentoDAORegister = new PorcaoDeAlimentoDAO(conn);
	    container.register(IPorcaoDeAlimentoDAO.class, porcaoDeAlimentoDAORegister);
	    PorcaoDeAlimentoRepository porcaoDeAlimentoRepositoryRegister = new PorcaoDeAlimentoRepository(new PorcaoDeAlimentoDAO(conn));
	    container.register(IPorcaoDeAlimentoRepository.class, porcaoDeAlimentoRepositoryRegister);
	    PorcaoDeAlimentoService porcaoDeAlimentoServiceRegister = new PorcaoDeAlimentoService(new PorcaoDeAlimentoRepository(new PorcaoDeAlimentoDAO(conn)));
	    container.register(IPorcaoDeAlimentoService.class, porcaoDeAlimentoServiceRegister);
		
	}
	
	@Override
	public void configContainer(Container container, Connection conn, DAOFactoryMethod daoFactory) {
			    
		IPorcaoDeAlimentoDAO dao = daoFactory.createPorcaoDeAlimentoDAO(conn);
	    
	    PorcaoDeAlimentoRepository porcaoDeAlimentoRepositoryRegister = new PorcaoDeAlimentoRepository(dao);
	    container.register(IPorcaoDeAlimentoRepository.class, porcaoDeAlimentoRepositoryRegister);
	    
	    PorcaoDeAlimentoService porcaoDeAlimentoServiceRegister = new PorcaoDeAlimentoService(new PorcaoDeAlimentoRepository(new PorcaoDeAlimentoDAO(conn)));
	    container.register(IPorcaoDeAlimentoService.class, porcaoDeAlimentoServiceRegister);
	}

	@Override
	public void configContainer(Container container, Connection conn, DAOFactoryMethod daoFactory, 
			RepositoryFactoryMethod repositoryFactory) {
		// TODO Auto-generated method stub
		IPorcaoDeAlimentoRepository porcaoDeAlimentoRepositoryRegister = configContainerRepository(container, conn,
				daoFactory, repositoryFactory);
	    
	    PorcaoDeAlimentoService porcaoDeAlimentoServiceRegister = new PorcaoDeAlimentoService(porcaoDeAlimentoRepositoryRegister);
	    container.register(IPorcaoDeAlimentoService.class, porcaoDeAlimentoServiceRegister);
	}

	@Override
	public void configContainer(Container container, Connection conn, DAOFactoryMethod daoFactory,
			RepositoryFactoryMethod repositoryFactory, ServicesFactoryMethod servicesFactory) {
		
		IPorcaoDeAlimentoRepository porcaoDeAlimentoRepositoryRegister = configContainerRepository(container, conn,
				daoFactory, repositoryFactory);
	    
	    IPorcaoDeAlimentoService porcaoDeAlimentoServiceRegister = servicesFactory.createPorcaoDeAlimentoService(porcaoDeAlimentoRepositoryRegister);
	    container.register(IPorcaoDeAlimentoService.class, porcaoDeAlimentoServiceRegister);
	}
	
	
	private IPorcaoDeAlimentoRepository configContainerRepository(Container container, Connection conn,
			DAOFactoryMethod daoFactory, RepositoryFactoryMethod repositoryFactory) {
		IPorcaoDeAlimentoDAO dao = daoFactory.createPorcaoDeAlimentoDAO(conn);
		
		IPorcaoDeAlimentoRepository porcaoDeAlimentoRepositoryRegister = repositoryFactory.createPorcaoDeAlimentoRepository(dao);
	    container.register(IPorcaoDeAlimentoRepository.class, porcaoDeAlimentoRepositoryRegister);
		return porcaoDeAlimentoRepositoryRegister;
	}

}
