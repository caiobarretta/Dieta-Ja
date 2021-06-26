package testes.integracao;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import core.Startup;
import core.entities.Dieta;
import core.interfaces.dao.IDietaDAO;
import core.interfaces.repository.IDietaRepository;
import core.interfaces.service.IDietaService;
import core.ioc.Container;
import infrastructure.dao.DietaDAO;
import infrastructure.dao.base.DAOConnection;
import infrastructure.repository.DietaRepository;
import junit.framework.TestCase;
import services.DietaService;
import testes.DAOConnectionTest;
import testes.helper.UtilHashMapTest;
import testes.mock.DietaDAOMock;

public class DietaServiceTest extends TestCase{

	private UUID uuid;
	private Dieta dieta;
	private Container container;
	private DietaDAO dietaDao;
	private DietaService dietaService;
	private DietaRepository dietaRepository;
	
	@Override
	protected void setUp() throws Exception {
		dieta = new Dieta();
		uuid = UUID.randomUUID();
		container = new Startup(DAOConnectionTest.getConnection()).getContainer();
		dietaDao = (DietaDAO)container.resolveSingleton(IDietaDAO.class);
		dietaRepository = (DietaRepository)container.resolveSingleton(IDietaRepository.class);
		dietaService = (DietaService)container.resolveSingleton(IDietaService.class);
	}
	
	@Override
	protected void tearDown() throws Exception {
		dietaDao.closeConn();
	}
	
	public void testDadoUmaDietaValidaQuandoOServicoAdicionarNoBDDevePersistirOsDados() {
		dieta.setNome(uuid.toString());
		int rows = dietaService.add(dieta);
		if(rows <= 0) 
			fail("A quantidade de linhas inseridas no banco não pode ser menor ou igual a zero.");
	}
	
	public void testDadoUmaPesquisaDeDietaInvalidaQuandoOServicoDePesquisaEChamadoDeveRetornaUmaListaVazia() {
		List<Dieta> retorno = dietaService.search("");
		if(!retorno.isEmpty())
			fail("Uma pesquisa invalida não pode retornar valor");
	}
	
	public void testDadoUmaDietaValidaAoconverterEntityParaHashMapComIDNaUltimaPosicaoDeveRetornaUmaMapValido() {
		DietaDAOMock mock = new DietaDAOMock(DAOConnection.getConnection());
		dieta.setID(1);
		dieta.setNome("Dieta Teste");
		dieta.setDescricao("Dieta Desc");
		dieta.setAtivo(true);
		Map<Integer, Object> map = mock.mockConverterEntityParaHashMapComIDNaUltimaPosicao(dieta);
		UtilHashMapTest.testMapCount(4, map);
		
		UtilHashMapTest.testContentMap(map, "Dieta Teste", "Dieta Desc", true, 1);
	}
	
	public void testDadoUmaDietaInvalidaQuandoServicoDeDeletarDietaEChamadoNenhumaDietaDeveSerDeletada() {
		Integer id= 0;
		Integer rows = dietaService.delete(id);
		if(rows > 0)
			fail("Uma dieta invalida não pode ser deletada");
	}
	
	public void testDadoUmaDietaValidaQuandoServiceDeAlterarDietaEChamadoDeveAtualizarADietaNoDB() {		
		dieta.setNome("Dieta Teste");
		dieta.setDescricao("Dieta Teste");
		dietaService.add(dieta);

		List<Dieta> dietas = dietaService.search("Dieta Teste");

		dieta.setID(dietas.get(0).getID());
		dieta.setNome("Dieta Teste Update");
		dieta.setDescricao("Dieta Update Desc");
		Integer rows = dietaService.update(dieta);
		if(rows <= 0)
			fail("A dieta N�O foi Atualizada");
	}
	
	public void testDadoUmIDValidoQuandoServiceDeRetornarDietaEChamadoDeveRetornaUmaDietaValida() {
		Integer id = 1;
		System.out.printf("testDadoUmIDValidoQuandoServiceDeRetornarDietaEChamadoDeveRetornaUmaDietaValida\n");
		Dieta dietaDB = dietaService.get(id);
		System.out.printf("dietaDB.getID(): %d", dietaDB.getID());
		assertEquals(dietaDB.getID(), id);
	}
	
	public void testDadoUmaPaginacaoValidaServiceDeRetornoDietaPaginadaEChamdoDeveRetornaUmaListaDeDietaValid() {
		List<Dieta> dietas = dietaService.get(10, 0);
		assertEquals(dietas.isEmpty(), false);
	}

}
