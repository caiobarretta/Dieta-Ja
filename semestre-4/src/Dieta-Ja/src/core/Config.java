package core;

import java.sql.Connection;

import core.abstractions.DAOFactoryMethod;
import core.abstractions.RepositoryFactoryMethod;
import core.abstractions.ServicesFactoryMethod;
import core.config.strategy.ContextConfigContainerStrategy;
import core.config.strategy.DietaConfigContainerStrategy;
import core.config.strategy.PorcaoDeAlimentoConfigContainerStrategy;
import core.config.strategy.UsuarioConfigContainerStrategy;
import core.ioc.Container;
import infrastructure.dao.base.DAOConnection;

class Config {
	
	public static Container getConfigContainer() {
		Container container = new Container();
		Connection conn = DAOConnection.getConnection();
		ContextConfigContainerStrategy ctx = new ContextConfigContainerStrategy();
		
		getAllContainerConfig(ctx, container, conn);
		return container;
	}
	
	public static Container getConfigContainer(Connection conn) {
		Container container = new Container();
		ContextConfigContainerStrategy ctx = new ContextConfigContainerStrategy();
		getAllContainerConfig(ctx, container, conn);
		return container;
	}
	
	public static Container getConfigContainer(Connection conn, DAOFactoryMethod daoFactory) {
		Container container = new Container();
		ContextConfigContainerStrategy ctx = new ContextConfigContainerStrategy();
		getAllContainerConfig(ctx, container, conn, daoFactory);
		return container;
	}
	
	public static Container getConfigContainer(Connection conn, DAOFactoryMethod daoFactory, RepositoryFactoryMethod repoFactory) {
		Container container = new Container();
		ContextConfigContainerStrategy ctx = new ContextConfigContainerStrategy();
		getAllContainerConfig(ctx, container, conn, daoFactory, repoFactory);
		return container;
	}
	
	public static Container getConfigContainer(Connection conn, DAOFactoryMethod daoFactory, RepositoryFactoryMethod repoFactory, 
			ServicesFactoryMethod servicesFactory) {
		Container container = new Container();
		ContextConfigContainerStrategy ctx = new ContextConfigContainerStrategy();
		getAllContainerConfig(ctx, container, conn, daoFactory, repoFactory, servicesFactory);
		return container;
	}
	
	private static void getAllContainerConfig(ContextConfigContainerStrategy ctx, Container container, 
			Connection conn) {
		ctx.setStrategy(new DietaConfigContainerStrategy())
		   .executeStrategy(container, conn)
		   .setStrategy(new PorcaoDeAlimentoConfigContainerStrategy())
		   .executeStrategy(container, conn)
		   .setStrategy(new UsuarioConfigContainerStrategy())
		   .executeStrategy(container, conn);
	}
	
	private static void getAllContainerConfig(ContextConfigContainerStrategy ctx, Container container, 
			Connection conn, DAOFactoryMethod daoFactory) {
		ctx.setStrategy(new DietaConfigContainerStrategy())
		   .executeStrategy(container, conn, daoFactory)
		   .setStrategy(new PorcaoDeAlimentoConfigContainerStrategy())
		   .executeStrategy(container, conn, daoFactory)
		   .setStrategy(new UsuarioConfigContainerStrategy())
		   .executeStrategy(container, conn, daoFactory);
	}
	
	
	private static void getAllContainerConfig(ContextConfigContainerStrategy ctx,Container container, 
			Connection conn, DAOFactoryMethod daoFactory, RepositoryFactoryMethod repositoryFactory) {
		ctx.setStrategy(new DietaConfigContainerStrategy())
		   .executeStrategy(container, conn, daoFactory, repositoryFactory)
		   .setStrategy(new PorcaoDeAlimentoConfigContainerStrategy())
		   .executeStrategy(container, conn, daoFactory, repositoryFactory)
		   .setStrategy(new UsuarioConfigContainerStrategy())
		   .executeStrategy(container, conn, daoFactory, repositoryFactory);
	}
	
	private static void getAllContainerConfig(ContextConfigContainerStrategy ctx, Container container, 
			Connection conn, DAOFactoryMethod daoFactory, RepositoryFactoryMethod repositoryFactory, ServicesFactoryMethod servicesFactory) {
		ctx.setStrategy(new DietaConfigContainerStrategy())
		   .executeStrategy(container, conn, daoFactory, repositoryFactory, servicesFactory)
		   .setStrategy(new PorcaoDeAlimentoConfigContainerStrategy())
		   .executeStrategy(container, conn, daoFactory, repositoryFactory, servicesFactory)
		   .setStrategy(new UsuarioConfigContainerStrategy())
		   .executeStrategy(container, conn, daoFactory, repositoryFactory, servicesFactory);
	}

}
