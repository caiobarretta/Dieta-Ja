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
import infrastructure.dao.DAOFactory;
import infrastructure.dao.DietaDAO;
import infrastructure.dao.base.DAOConnection;
import infrastructure.repository.DietaRepository;
import infrastructure.repository.RepositoryFactory;
import junit.framework.TestCase;
import services.DietaService;
import services.ServicesFactory;
import testes.DAOConnectionTest;
import testes.helper.UtilHashMapTest;
import testes.mock.DietaDAOMock;

public class DietaServiceTest extends TestCase{

	private UUID uuid;
	private Dieta dieta;
	private Container container;
	private DietaService dietaService;
	
	@Override
	protected void setUp() throws Exception {
		dieta = new Dieta();
		uuid = UUID.randomUUID();
		container = new Startup(DAOConnectionTest.getConnection(), new DAOFactory(), 
				new RepositoryFactory(), new ServicesFactory()).getContainer();
		dietaService = (DietaService)container.resolve(IDietaService.class);
	}
	
	public void testDadoUmaDietaValidaQuandoOServicoAdicionarNoBDDevePersistirOsDados() {
		dieta.setNome(uuid.toString());
		dieta.setDescricao(uuid.toString());
		dieta.setAtivo(true);
		int rows = dietaService.add(dieta);
		if(rows <= 0) 
			fail("A quantidade de linhas inseridas no banco n„o pode ser menor ou igual a zero.");
	}
	
	public void testDadoUmaPesquisaDeDietaInvalidaQuandoOServicoDePesquisaEChamadoDeveRetornaUmaListaVazia() {
		
		StringBuilder sb = new StringBuilder();
		sb.append(UUID.randomUUID().toString());
		sb.append(UUID.randomUUID().toString());
		
		List<Dieta> retorno = dietaService.search(sb.toString());
		if(!retorno.isEmpty())
			fail("Uma pesquisa invalida n„o pode retornar valor");
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
			fail("Uma dieta invalida n√£o pode ser deletada");
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
			fail("A dieta N√O foi Atualizada");
	}
	
	public void testDadoUmIDValidoQuandoServiceDeRetornarDietaEChamadoDeveRetornaUmaDietaValida() {
		Integer id = 1;
		Dieta dietaDB = dietaService.get(id);
		assertEquals(dietaDB.getID(), id);
	}
	
	public void testDadoUmaPaginacaoValidaServiceDeRetornoDietaPaginadaEChamdoDeveRetornaUmaListaDeDietaValid() {
		dieta.setNome("Dieta Teste");
		dieta.setDescricao("Dieta Teste");
		dietaService.add(dieta);
		
		List<Dieta> dietas = dietaService.get(0, 10);
		if(dietas.size() <= 0)
			fail("A listagem n„o encontrou valores no banco de dados.");
	}

}
