package testes.integracao;


import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import core.Startup;
import core.entities.PorcaoDeAlimento;
import core.interfaces.dao.IPorcaoDeAlimentoDAO;
import core.interfaces.repository.IPorcaoDeAlimentoRepository;
import core.interfaces.service.IPorcaoDeAlimentoService;
import core.ioc.Container;
import infrastructure.dao.PorcaoDeAlimentoDAO;
import infrastructure.repository.PorcaoDeAlimentoRepository;
import junit.framework.TestCase;
import services.PorcaoDeAlimentoService;
import testes.DAOConnectionTest;

public class PorcaoDeAlimentoServiceTest extends TestCase {

	private UUID uuid;
	private Container container;
	private PorcaoDeAlimento porcaoDeAlimento;
	private PorcaoDeAlimentoDAO porcaoDeAlimentoDao;
	private PorcaoDeAlimentoService porcaoDeAlimentoService;
	private PorcaoDeAlimentoRepository porcaoDeAlimentoRepository;
	
	@Override
	protected void setUp() throws Exception {
		uuid = UUID.randomUUID();
		porcaoDeAlimento = new PorcaoDeAlimento();
		container = new Startup(DAOConnectionTest.getConnection()).getContainer();
		porcaoDeAlimentoDao = (PorcaoDeAlimentoDAO)container.resolveSingleton(IPorcaoDeAlimentoDAO.class);
		porcaoDeAlimentoRepository = (PorcaoDeAlimentoRepository)container.resolveSingleton(IPorcaoDeAlimentoRepository.class);
		porcaoDeAlimentoService = (PorcaoDeAlimentoService)container.resolveSingleton(IPorcaoDeAlimentoService.class);
	}
	
	@Override
	protected void tearDown() throws Exception {
		porcaoDeAlimentoDao.closeConn();
	}

	public void testAssociarPorcaoRefeicoes() {
		List<Integer> lst = new ArrayList<Integer>();
		lst.add(1);
		lst.add(2);
		int rows = porcaoDeAlimentoService.associarPorcaoRefeicoes(lst, 1);
		if(rows <= 1)
			fail("porções de alimento não está associadas à refeição");
	}

	public void testAssociarPorcaoAlimentoDieta() {
		List<Integer> lst = new ArrayList<Integer>();
		lst.add(1);
		lst.add(2);
		int rows = porcaoDeAlimentoService.associarPorcaoAlimentoDieta(lst, 1);
		if(rows <= 0)
			fail("Porções de alimento não associadas à Dieta");
	}

	public void testRetornaPorcaoDeAlimentoPeloIdDaDieta() {
		List<PorcaoDeAlimento> porcao = porcaoDeAlimentoService.retornaPorcaoDeAlimentoPeloIdDaDieta(1);
		if(porcao == null || porcao.isEmpty())
			fail("Erro ao pesquisa uma Porções de alimento válida"); // TODO
	}

	public void testAdd() {
		porcaoDeAlimento.setNome(uuid.toString());
		porcaoDeAlimento.setDescricao(uuid.toString());
		Integer rows = porcaoDeAlimentoService.add(porcaoDeAlimento);
		if(rows != 1)
			fail("Porções de alimentos não está sendo inserida no banco");
	}

	public void testDelete() {
		Integer rows = porcaoDeAlimentoService.delete(1);
		if(rows != 1)
			fail(String.format("Porções de alimentos não está sendo deletada corretamente, rows: %d", rows));
	}

	public void testGetTakeSkip() {
		List lst = porcaoDeAlimentoService.get(0, 10);
		if(lst.isEmpty())
			fail("Listagem Porções de Alimento não está retornando valores");
	}

	public void testGetInteger() {
		PorcaoDeAlimento porcao = porcaoDeAlimentoService.get(1);
		if(porcao == null)
			fail("porção de alimento não está carregando por ID");
	}

	public void testSearch() {
		StringBuilder sb = new StringBuilder();
		sb.append(UUID.randomUUID().toString());
		sb.append(UUID.randomUUID().toString());
		List<PorcaoDeAlimento> porcao = porcaoDeAlimentoService.search(sb.toString());
		if(!porcao.isEmpty())
			fail("A pesquisa da porção de alimento não está funcionando corretamente");
	}

	public void testUpdate() {
		porcaoDeAlimento.setID(1);
		porcaoDeAlimento.setNome("Update teste");
		porcaoDeAlimento.setDescricao("Update teste");
		Integer rows = porcaoDeAlimentoService.update(porcaoDeAlimento);
		if(rows != 1)
			fail(String.format("Porções de alimentos não está sendo atualizada corretamente, rows: %d", rows));
	}
	
	

}
