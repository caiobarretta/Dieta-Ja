package core;

import java.sql.Connection;

import core.interfaces.dao.IDietaDAO;
import core.interfaces.dao.IPorcaoDeAlimentoDAO;
import core.interfaces.dao.IUsuarioDAO;
import core.interfaces.repository.IDietaRepository;
import core.interfaces.repository.IPorcaoDeAlimentoRepository;
import core.interfaces.repository.IUsuarioRepository;
import core.interfaces.service.IDietaService;
import core.interfaces.service.IPorcaoDeAlimentoService;
import core.interfaces.service.IUsuarioService;
import core.ioc.Container;
import infrastructure.dao.DietaDAO;
import infrastructure.dao.PorcaoDeAlimentoDAO;
import infrastructure.dao.UsuarioDAO;
import infrastructure.dao.base.DAOConnection;
import infrastructure.repository.DietaRepository;
import infrastructure.repository.PorcaoDeAlimentoRepository;
import infrastructure.repository.UsuarioRepository;
import services.DietaService;
import services.PorcaoDeAlimentoService;
import services.UsuarioService;

class Config {
	
	public static Container getConfigContainer() {
		Container container = new Container();
		Connection conn = DAOConnection.getConnection();
		getAllContainerConfig(container, conn);
		return container;
	}
	
	public static Container getConfigContainer(Connection conn) {
		Container container = new Container();
		getAllContainerConfig(container, conn);
		return container;
	}
	
	private static void getAllContainerConfig(Container container, Connection conn) {
		dietaContainerConfig(container, conn);
		porcaoDeAlimentoContainerConfig(container, conn);
		usuarioContainerConfig(container, conn);
	}

	private static void dietaContainerConfig(Container container, Connection conn) {
		DietaDAO dietaDAORegister = new DietaDAO(conn);
		container.register(IDietaDAO.class, dietaDAORegister);
		DietaRepository dietaRepositoryRegister = new DietaRepository(new DietaDAO(conn));
		container.register(IDietaRepository.class, dietaRepositoryRegister);
		DietaService dietaServiceRegister = new DietaService(new DietaRepository(new DietaDAO(conn)));
		container.register(IDietaService.class, dietaServiceRegister);
	}
	
	private static void porcaoDeAlimentoContainerConfig(Container container, Connection conn) {
	    PorcaoDeAlimentoDAO porcaoDeAlimentoDAORegister = new PorcaoDeAlimentoDAO(conn);
	    container.register(IPorcaoDeAlimentoDAO.class, porcaoDeAlimentoDAORegister);
	    PorcaoDeAlimentoRepository porcaoDeAlimentoRepositoryRegister = new PorcaoDeAlimentoRepository(new PorcaoDeAlimentoDAO(conn));
	    container.register(IPorcaoDeAlimentoRepository.class, porcaoDeAlimentoRepositoryRegister);
	    PorcaoDeAlimentoService porcaoDeAlimentoServiceRegister = new PorcaoDeAlimentoService(new PorcaoDeAlimentoRepository(new PorcaoDeAlimentoDAO(conn)));
	    container.register(IPorcaoDeAlimentoService.class, porcaoDeAlimentoServiceRegister);
	}
	
	private static void usuarioContainerConfig(Container container, Connection conn) {
		UsuarioDAO usuarioDAORegister = new UsuarioDAO(conn);
		container.register(IUsuarioDAO.class, usuarioDAORegister);
		UsuarioRepository usuarioRepositoryRegister = new UsuarioRepository(new UsuarioDAO(conn));
		container.register(IUsuarioRepository.class, usuarioRepositoryRegister);
		UsuarioService usuarioServiceRegister = new UsuarioService(new UsuarioRepository(new UsuarioDAO(conn)));
		container.register(IUsuarioService.class, usuarioServiceRegister);
	}

}
