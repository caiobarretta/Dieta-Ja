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
		DietaDAO dietaDAO = new DietaDAO(DAOConnection.getConnection());
		DietaRepository dietaRepository = new DietaRepository(dietaDAO);
		DietaService dietaService = new DietaService(dietaRepository);
		container.register(IDietaService.class, dietaService);
		
		DietaService dietaServiceInstance = (DietaService)container.resolveSingleton(IDietaService.class);
		if(dietaServiceInstance == null)
			fail("Container não retornou os objetos");
	}
	
	public void testDadoVariasInterfacesImplementadasEmVariasClassesAoAdcionalasEmUmContainerEleDeveConseguirResolvelas() {
		
		DietaDAO dietaDAORegister = new DietaDAO(DAOConnection.getConnection());
		container.register(IDietaDAO.class, dietaDAORegister);
		DietaRepository dietaRepositoryRegister = new DietaRepository(new DietaDAO(DAOConnection.getConnection()));
		container.register(IDietaRepository.class, dietaRepositoryRegister);
		DietaService dietaServiceRegister = new DietaService(new DietaRepository(new DietaDAO(DAOConnection.getConnection())));
		container.register(IDietaService.class, dietaServiceRegister);
		
		DietaDAO dietaDAO = (DietaDAO)container.resolveSingleton(IDietaDAO.class);
		DietaRepository dietaRepository = (DietaRepository)container.resolveSingleton(IDietaRepository.class);
		DietaService dietaService = (DietaService)container.resolveSingleton(IDietaService.class);
		
		if(dietaDAO == null || dietaRepository == null || dietaService == null) {
			String str = String.format("dietaDAO: %b dietaRepository: %b dietaService: %b", dietaDAO == null, dietaRepository == null, dietaService == null);
			fail(String.format("Container não retornou os objetos: %s", str));
		}
	}

}
