package core.config.strategy;

import java.sql.Connection;

import core.abstractions.DAOFactoryMethod;
import core.abstractions.RepositoryFactoryMethod;
import core.abstractions.ServicesFactoryMethod;
import core.config.strategy.base.IConfigContainerStrategy;
import core.ioc.Container;

public class ContextConfigContainerStrategy {
	private IConfigContainerStrategy _strategy;
	
	public ContextConfigContainerStrategy setStrategy(IConfigContainerStrategy strategy){
		this._strategy = strategy;
		return this;
	}
	
	public ContextConfigContainerStrategy executeStrategy(Container container, Connection conn){
		_strategy.configContainer(container, conn);
		return this;
	}
	
	public ContextConfigContainerStrategy executeStrategy(Container container, Connection conn, 
			DAOFactoryMethod daoFactory){
		_strategy.configContainer(container, conn, daoFactory);
		return this;
	}
	
	public ContextConfigContainerStrategy executeStrategy(Container container, Connection conn, 
			DAOFactoryMethod daoFactory, RepositoryFactoryMethod repositoryFactory){
		_strategy.configContainer(container, conn, daoFactory, repositoryFactory);
		return this;
	}
	
	public ContextConfigContainerStrategy executeStrategy(Container container, Connection conn, 
			DAOFactoryMethod daoFactory, RepositoryFactoryMethod repositoryFactory, ServicesFactoryMethod servicesFactory){
		_strategy.configContainer(container, conn, daoFactory, repositoryFactory, servicesFactory);
		return this;
	}
}
