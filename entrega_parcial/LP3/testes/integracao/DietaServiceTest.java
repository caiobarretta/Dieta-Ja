package testes.integracao;

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
	public void setUp() throws Exception {
		dieta = new Dieta();
		uuid = UUID.randomUUID();
		container = new Startup(DAOConnectionTest.getConnection()).getContainer();
		dietaDao = (DietaDAO)container.resolveSingleton(IDietaDAO.class);
		dietaRepository = (DietaRepository)container.resolveSingleton(IDietaRepository.class);
		dietaService = (DietaService)container.resolveSingleton(IDietaService.class);
	}

	public void testDadoUmaDietaValidaQuandoOServicoAdicionarNoBDDevePersistirOsDados() {
		dieta.setNome(uuid.toString());
		int rows = dietaService.add(dieta);
		if(rows <= 0) 
			fail("A quantidade de linhas inseridas no banco não pode ser menor ou igual a zero.");
	}
	
	public void testDadoUmaPesquisaDeDietaInvalidaQuandoOServicoDePesquisaEChamadoDeveRetornaUmaListaVazia() {
		var retorno = dietaService.search("");
		if(!retorno.isEmpty())
			fail("Uma pesquisa invalida não pode retornar valor");
	}
	
	public void testDadoUmaDietaValidaAoconverterEntityParaHashMapComIDNaUltimaPosicaoDeveRetornaUmaMapValido() {
		DietaDAOMock mock = new DietaDAOMock(DAOConnection.getConnection());
		dieta.setID(1);
		dieta.setNome("Dieta Teste");
		dieta.setDescricao("Dieta Desc");
		dieta.setAtivo(true);
		var map = mock.mockConverterEntityParaHashMapComIDNaUltimaPosicao(dieta);
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
		dieta.setID(1);
		dieta.setNome("Dieta Teste");
		dieta.setDescricao("Dieta Desc");
		Integer rows = dietaService.update(dieta);
		if(rows <= 0)
			fail("A dieta Não Atualizada");
	}
	
	public void testDadoUmIDValidoQuandoServiceDeRetornarDietaEChamadoDeveRetornaUmaDietaValida() {
		Integer id = 1;
		var dietaDB = dietaService.get(id);
		assertEquals(dietaDB.getID(), id);
	}
	
	public void testDadoUmaPaginacaoValidaServiceDeRetornoDietaPaginadaEChamdoDeveRetornaUmaListaDeDietaValid() {
		var dietas = dietaService.get(10, 0);
		assertEquals(dietas.isEmpty(), false);
	}

}
