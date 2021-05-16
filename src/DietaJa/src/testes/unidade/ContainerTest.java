package testes.unidade;


import core.interfaces.dao.IDietaDAO;
import core.interfaces.repository.IDietaRepository;
import core.interfaces.service.IDietaService;
import core.ioc.Container;
import infrastructure.dao.DietaDAO;
import infrastructure.dao.base.DAOConnection;
import infrastructure.repository.DietaRepository;
import junit.framework.TestCase;
import services.DietaService;

public class ContainerTest extends TestCase {

	private Container container;
	public void setUp() throws Exception {
		container = new Container();
	}

	public void testDadoUmaInterfaceImpletamentadaEmUmaClasseAoAdicionarNoConteinerEleDeveConseguirResolvela() {
		var dietaDAO = new DietaDAO(DAOConnection.getConnection());
		var dietaRepository = new DietaRepository(dietaDAO);
		var dietaService = new DietaService(dietaRepository);
		container.register(IDietaService.class, dietaService);
		
		var dietaServiceInstance = (DietaService)container.resolveSingleton(IDietaService.class);
		if(dietaServiceInstance == null)
			fail("Container não retornou os objetos");
	}
	
	public void testDadoVariasInterfacesImplementadasEmVariasClassesAoAdcionalasEmUmContainerEleDeveConseguirResolvelas() {
		
		var dietaDAORegister = new DietaDAO(DAOConnection.getConnection());
		container.register(IDietaDAO.class, dietaDAORegister);
		var dietaRepositoryRegister = new DietaRepository(new DietaDAO(DAOConnection.getConnection()));
		container.register(IDietaRepository.class, dietaRepositoryRegister);
		var dietaServiceRegister = new DietaService(new DietaRepository(new DietaDAO(DAOConnection.getConnection())));
		container.register(IDietaService.class, dietaServiceRegister);
		
		var dietaDAO = (DietaDAO)container.resolveSingleton(IDietaDAO.class);
		var dietaRepository = (DietaRepository)container.resolveSingleton(IDietaRepository.class);
		var dietaService = (DietaService)container.resolveSingleton(IDietaService.class);
		
		if(dietaDAO == null || dietaRepository == null || dietaService == null) {
			var str = String.format("dietaDAO: %b dietaRepository: %b dietaService: %b", dietaDAO == null, dietaRepository == null, dietaService == null);
			fail(String.format("Container não retornou os objetos: %s", str));
		}
		
	}

}
