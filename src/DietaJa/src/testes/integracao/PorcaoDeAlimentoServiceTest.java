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
	
	public void setUp() throws Exception {
		uuid = UUID.randomUUID();
		porcaoDeAlimento = new PorcaoDeAlimento();
		container = new Startup(DAOConnectionTest.getConnection()).getContainer();
		porcaoDeAlimentoDao = (PorcaoDeAlimentoDAO)container.resolveSingleton(IPorcaoDeAlimentoDAO.class);
		porcaoDeAlimentoRepository = (PorcaoDeAlimentoRepository)container.resolveSingleton(IPorcaoDeAlimentoRepository.class);
		porcaoDeAlimentoService = (PorcaoDeAlimentoService)container.resolveSingleton(IPorcaoDeAlimentoService.class);
	}

	public void testAssociarPorcaoRefeicoes() {
		
		List<Integer> lst = new ArrayList<Integer>();
		lst.add(1);
		lst.add(2);
		int rows = porcaoDeAlimentoService.associarPorcaoRefeicoes(lst, 1);
		if(rows <= 1)
			fail("Porções de alimento não associadas à refeição");
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
		var porcao = porcaoDeAlimentoService.retornaPorcaoDeAlimentoPeloIdDaDieta(1);
		if(porcao.isEmpty())
			fail("Erro ao pesquisa uma porção de alimento válida"); // TODO
	}

	public void testAdd() {
		porcaoDeAlimento.setNome(uuid.toString());
		porcaoDeAlimento.setDescricao(uuid.toString());
		var rows = porcaoDeAlimentoService.add(porcaoDeAlimento);
		if(rows != 1)
			fail("Porção de alimentos não está sendo inserida no banco");
	}

	public void testDelete() {
		var rows = porcaoDeAlimentoService.delete(1);
		if(rows != 1)
			fail(String.format("Porção de alimentos não está sendo deletada corretamente, rows: %d", rows));
	}

	public void testGetTakeSkip() {
		var lst = porcaoDeAlimentoService.get(10, 0);
		if(lst.isEmpty())
			fail("Listagem porção de Alimento não está retornando valores");
	}

	public void testGetInteger() {
		var porcao = porcaoDeAlimentoService.get(1);
		if(porcao == null)
			fail("Porção de alimento não está carregando por ID");
	}

	public void testSearch() {
		var porcao = porcaoDeAlimentoService.search("");
		if(porcao.isEmpty())
			fail("A pesquisa da porção de alimento não está funcionando corretamente");
	}

	public void testUpdate() {
		porcaoDeAlimento.setNome("Update teste");
		porcaoDeAlimento.setDescricao("Update teste");
		var rows = porcaoDeAlimentoService.update(porcaoDeAlimento);
		if(rows != 1)
			fail(String.format("Porção de alimentos não está sendo atualizada corretamente, rows: %d", rows));
	}

}
