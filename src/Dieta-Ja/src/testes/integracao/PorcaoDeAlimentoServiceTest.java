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
			fail("por��es de alimento n�o est� associadas � refei��o");
	}

	public void testAssociarPorcaoAlimentoDieta() {
		List<Integer> lst = new ArrayList<Integer>();
		lst.add(1);
		lst.add(2);
		int rows = porcaoDeAlimentoService.associarPorcaoAlimentoDieta(lst, 1);
		if(rows <= 0)
			fail("Por��es de alimento n�o associadas � Dieta");
	}

	public void testRetornaPorcaoDeAlimentoPeloIdDaDieta() {
		List<PorcaoDeAlimento> porcao = porcaoDeAlimentoService.retornaPorcaoDeAlimentoPeloIdDaDieta(1);
		if(porcao == null || porcao.isEmpty())
			fail("Erro ao pesquisa uma Por��es de alimento v�lida"); // TODO
	}

	public void testAdd() {
		porcaoDeAlimento.setNome(uuid.toString());
		porcaoDeAlimento.setDescricao(uuid.toString());
		Integer rows = porcaoDeAlimentoService.add(porcaoDeAlimento);
		if(rows != 1)
			fail("Por��es de alimentos n�o est� sendo inserida no banco");
	}

	public void testDelete() {
		Integer rows = porcaoDeAlimentoService.delete(1);
		if(rows != 1)
			fail(String.format("Por��es de alimentos n�o est� sendo deletada corretamente, rows: %d", rows));
	}

	public void testGetTakeSkip() {
		List lst = porcaoDeAlimentoService.get(0, 10);
		if(lst.isEmpty())
			fail("Listagem Por��es de Alimento n�o est� retornando valores");
	}

	public void testGetInteger() {
		PorcaoDeAlimento porcao = porcaoDeAlimentoService.get(1);
		if(porcao == null)
			fail("por��o de alimento n�o est� carregando por ID");
	}

	public void testSearch() {
		StringBuilder sb = new StringBuilder();
		sb.append(UUID.randomUUID().toString());
		sb.append(UUID.randomUUID().toString());
		List<PorcaoDeAlimento> porcao = porcaoDeAlimentoService.search(sb.toString());
		if(!porcao.isEmpty())
			fail("A pesquisa da por��o de alimento n�o est� funcionando corretamente");
	}

	public void testUpdate() {
		porcaoDeAlimento.setID(1);
		porcaoDeAlimento.setNome("Update teste");
		porcaoDeAlimento.setDescricao("Update teste");
		Integer rows = porcaoDeAlimentoService.update(porcaoDeAlimento);
		if(rows != 1)
			fail(String.format("Por��es de alimentos n�o est� sendo atualizada corretamente, rows: %d", rows));
	}
	
	

}
