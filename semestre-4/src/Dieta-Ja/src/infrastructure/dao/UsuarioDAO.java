package infrastructure.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import core.entities.PorcaoDeAlimento;
import core.entities.TipoUsuarioEnum;
import core.entities.Usuario;
import core.interfaces.dao.IUsuarioDAO;
import infrastructure.dao.base.DAOConnection;
import infrastructure.dao.base.DefaultDAOTemplateMethod;
import infrastructure.dao.helper.HelperHashMap;

public class UsuarioDAO extends DefaultDAOTemplateMethod<Usuario> implements IUsuarioDAO{

	public UsuarioDAO(Connection conn) {
		super(conn);
		// TODO Auto-generated constructor stub
	}

	@Override
	public List<Usuario> get(Integer take, Integer skip) {
		String query = "SELECT * FROM Usuario WHERE Ativo = 1 LIMIT ?,? ";
		return super.get(query, take, skip);
	}

	@Override
	public Usuario get(Integer id) {
		String query = "SELECT * FROM Usuario WHERE Id_Usuario = ?  AND Ativo = 1";
		return super.get(query, id);
	}

	@Override
	public List<Usuario> search(String search) {
		String query = "SELECT * FROM Usuario WHERE (Nome like ? OR Descricao like ?) AND Ativo = 1";
		return super.search(query, search);
	}

	@Override
	public Integer add(Usuario entity) {
		String query = "INSERT INTO Usuario (Nome, Descricao, Ativo, Id_Dieta, Login, TipoUsuario, Senha) values (?, ?, ?, ?, ? ,? ,?);";
		return super.add(query, entity);
	}

	@Override
	public Integer update(Usuario entity) {
		String query = "UPDATE Usuario SET Nome = ?, Descricao = ?, Ativo = ?, ID_Dieta = ?, Login = ?, TipoUsuario = ?, Senha = ? WHERE Id_Usuario = ?;";
		return super.update(query, entity);
	}

	@Override
	public Integer delete(Integer id) {
		String query = "UPDATE Usuario SET Ativo = 0 WHERE Id_Usuario = ?;";
		return super.delete(query, id);
	}

	@Override
	public Integer getLoginUsuario(String usuario, String senha) {
		String query = "SELECT * FROM Usuario WHERE login = ? and senha = ?  AND Ativo = 1";
		Map<Integer, Object> map = HelperHashMap.criarHashMapComNStringsSequenciais(usuario, senha);
		List<Usuario> lst = super.executeQuery(query, map);
		if(lst.isEmpty())
			return 0;
		return lst.get(0).getID();
	}

	@Override
	protected Map<Integer, Object> converterEntityParaHashMapSemID(Usuario entity) {
		Map<Integer, Object> map = new HashMap<Integer, Object>();
		map.put(1, entity.getNome());
		map.put(2, entity.getDescricao());
		map.put(3, entity.isAtivo());
		map.put(4, entity.getDietaID());
		map.put(5, entity.getLogin());
		map.put(6, TipoUsuarioEnum.retornaIdPeloEnum(entity.getTipoUsuario()));
		map.put(7, entity.getSenha());
		return map;
	}

	@Override
	protected List<Usuario> LoadEntityFromResultSet(ResultSet rs) throws SQLException {
		List<Usuario> lst = new ArrayList<Usuario>();
		while(rs.next()) {
			Usuario usuario = new Usuario();
			usuario.setID(rs.getInt("Id_Usuario"));
			usuario.setNome(rs.getString("Nome"));
			usuario.setDescricao(rs.getString("Descricao"));
			usuario.setAtivo(rs.getBoolean("Ativo"));
			
			usuario.setDietaID(rs.getInt("ID_Dieta"));
			usuario.setLogin(rs.getString("Login"));
			usuario.setTipoUsuario(TipoUsuarioEnum.retornaEnumPeloId(rs.getInt("TipoUsuario")));
			usuario.setSenha(rs.getString("Senha"));
			lst.add(usuario);
		}
		return lst;
	}

	@Override
	public Integer getLastIdInserted() {
		return super.getLastIdInserted();
	}

}
