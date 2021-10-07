package core.config.strategy.base;

import java.sql.Connection;

import core.abstractions.DAOFactoryMethod;
import core.abstractions.RepositoryFactoryMethod;
import core.abstractions.ServicesFactoryMethod;
import core.ioc.Container;

public interface IConfigContainerStrategy {
	void configContainer(Container container, Connection conn);
	void configContainer(Container container, Connection conn, DAOFactoryMethod daoFactory);
	void configContainer(Container container, Connection conn, DAOFactoryMethod daoFactory, RepositoryFactoryMethod repositoryFactory);
	void configContainer(Container container, Connection conn, DAOFactoryMethod daoFactory, RepositoryFactoryMethod repositoryFactory, ServicesFactoryMethod servicesFactory);
}
