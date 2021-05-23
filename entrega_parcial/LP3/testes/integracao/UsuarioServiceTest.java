package testes.integracao;

import java.util.UUID;

import core.Startup;
import core.entities.TipoUsuarioEnum;
import core.entities.Usuario;
import core.interfaces.dao.IUsuarioDAO;
import core.interfaces.repository.IUsuarioRepository;
import core.interfaces.service.IUsuarioService;
import core.ioc.Container;
import infrastructure.dao.UsuarioDAO;
import infrastructure.repository.UsuarioRepository;
import junit.framework.TestCase;
import services.UsuarioService;
import testes.DAOConnectionTest;

public class UsuarioServiceTest extends TestCase {

	private UUID uuid;
	private Usuario usuario;
	private Container container;
	private UsuarioDAO usuarioDao;
	private UsuarioService usuarioService;
	private UsuarioRepository usuarioRepository;
	public void setUp() throws Exception {
		usuario = new Usuario();
		uuid = UUID.randomUUID();
		container = new Startup(DAOConnectionTest.getConnection()).getContainer();
		usuarioDao = (UsuarioDAO)container.resolveSingleton(IUsuarioDAO.class);
		usuarioRepository = (UsuarioRepository)container.resolveSingleton(IUsuarioRepository.class);
		usuarioService = (UsuarioService)container.resolveSingleton(IUsuarioService.class);
	}

	public void testIsUsuario() {
		Integer codigoUsuario = 0;
		var isUsr = usuarioService.isUsuario("Teste", "1234", codigoUsuario);
		if(codigoUsuario == 0 || !isUsr)
			fail("Serviço usuário não está retornando código de usuário ou não está achando login.");
	}

	public void testAdd() {
		usuario.setNome(uuid.toString());
		usuario.setDescricao("Teste Desc");
		usuario.setLogin("Teste login");
		usuario.setSenha("1234");
		usuario.setTipoUsuario(TipoUsuarioEnum.Paciente);
		usuario.setAtivo(true);
		var rows = usuarioService.add(usuario);
		if(rows != 1)
			fail("O serviço de usuário está inserindo eroneamento um usuário.");
	}

	public void testDelete() {
		var rows = usuarioService.delete(1);
		if(rows != 1)
			fail("O serviço de usuário está deletando eroneamento um usuário.");
	}

	public void testGetIntegerInteger() {
		var lst = usuarioService.get(10, 0);
		if(lst.isEmpty())
			fail("O serviço de usuário não está páginando eroneamento um usuário.");
	}

	public void testGetInteger() {
		var usr = usuarioService.get(1);
		if(usr == null)
			fail("O serviço de usuário não retornando usuário pesquisado por id");
	}

	public void testSearch() {
		var lst = usuarioService.search("");
		if(lst.isEmpty())
			fail("O serviço de usuário não retornando usuário pesquisado por nome");
	}

	public void testUpdate() {
		usuario.setAtivo(true);
		usuario.setDescricao("Descrição");
		usuario.setID(1);
		usuario.setLogin("Login");
		usuario.setNome("Nome");
		usuario.setSenha("1234");
		usuario.setTipoUsuario(TipoUsuarioEnum.Paciente);
		var rows = usuarioService.update(usuario);
		if(rows != 1)
			fail("O serviço de usuário não atualizar usuário pesquisado por nome");
	}

}
