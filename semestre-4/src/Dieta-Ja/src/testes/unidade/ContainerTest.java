package testes.unidade;


import core.interfaces.dao.IDietaDAO;
import core.interfaces.repository.IDietaRepository;
import core.interfaces.service.IDietaService;
import core.ioc.Container;
import infrastructure.dao.DietaDAO;
import infrastructure.dao.base.DAOConnection;
import infrastructure.repository.DietaRepository;
import infrastructure.repository.RepositoryFactory;
import junit.framework.TestCase;
import services.DietaService;
import services.ServicesFactory;

public class ContainerTest extends TestCase {

	private Container container;
	private DietaDAO dietaDAO;
	public void setUp() throws Exception {
		container = new Container();
		dietaDAO = new DietaDAO(DAOConnection.getConnection());
	}

	public void testDadoUmaInterfaceImpletamentadaEmUmaClasseAoAdicionarNoContainerEleDeveConseguirResolvela() {
		DietaRepository dietaRepository = new DietaRepository(dietaDAO);
		DietaService dietaService = new DietaService(dietaRepository);
		container.register(IDietaService.class, dietaService);
		
		DietaService dietaServiceInstance = (DietaService)container.resolve(IDietaService.class);
		if(dietaServiceInstance == null)
			fail("Container n„o retornou os objetos");
	}
	
	public void testDadoVariasInterfacesImplementadasEmVariasClassesAoAdcionalasEmUmContainerEleDeveConseguirResolvelas() {
		
		DietaDAO dietaDAORegister = new DietaDAO(DAOConnection.getConnection());
		container.register(IDietaDAO.class, dietaDAORegister);
		DietaRepository dietaRepositoryRegister = new DietaRepository(new DietaDAO(DAOConnection.getConnection()));
		container.register(IDietaRepository.class, dietaRepositoryRegister);
		DietaService dietaServiceRegister = new DietaService(new DietaRepository(new DietaDAO(DAOConnection.getConnection())));
		container.register(IDietaService.class, dietaServiceRegister);
		
		DietaDAO dietaDAO = (DietaDAO)container.resolve(IDietaDAO.class);
		DietaRepository dietaRepository = (DietaRepository)container.resolve(IDietaRepository.class);
		DietaService dietaService = (DietaService)container.resolve(IDietaService.class);
		
		if(dietaDAO == null || dietaRepository == null || dietaService == null) {
			String str = String.format("dietaDAO: %b dietaRepository: %b dietaService: %b", dietaDAO == null, dietaRepository == null, dietaService == null);
			fail(String.format("Container n√£o retornou os objetos: %s", str));
		}
	}

	
	public void testDadoUmRepositoryFactoryValidoEUmaInterfaceImplementadaEmUmaClasseAoAdicionarNOContainerEleDeveConseguirResolvela(){
		RepositoryFactory factory = new RepositoryFactory();
		IDietaRepository dietaRepository = factory.createDietaRepository(dietaDAO);
		
		DietaService dietaService = new DietaService(dietaRepository);
		container.register(IDietaService.class, dietaService);
		
		DietaService dietaServiceInstance = (DietaService)container.resolve(IDietaService.class);
		if(dietaServiceInstance == null)
			fail("Container n„o retornou os objetos");
	}
	public void testDadoUmRepositoryEServiceFactoryValidosEUmaInterfaceImplementadaEmUmaClasseAoAdicionarNOContainerEleDeveConseguirResolvela(){
		RepositoryFactory repoFactory = new RepositoryFactory();
		ServicesFactory serviceFactory = new ServicesFactory();
		
		IDietaRepository dietaRepository = repoFactory.createDietaRepository(dietaDAO);
		IDietaService dietaService = serviceFactory.createDietaService(dietaRepository);
		
		container.register(IDietaService.class, dietaService);
		
		DietaService dietaServiceInstance = (DietaService)container.resolve(IDietaService.class);
		if(dietaServiceInstance == null)
			fail("Container n„o retornou os objetos");
	}
}
