package infrastructure.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import core.entities.Dieta;
import core.interfaces.dao.IDietaDAO;
import infrastructure.dao.base.DAOConnection;
import infrastructure.dao.base.DefaultDAO;
import infrastructure.dao.base.BaseDAO;
import infrastructure.dao.helper.HelperHashMap;
import infrastructure.dao.helper.StrBuilderHelper;

public class DietaDAO extends DefaultDAO<Dieta> implements IDietaDAO{

	public DietaDAO(Connection conn) {
		super(conn);
	}

	@Override
	public List<Dieta> get(Integer take, Integer skip) {
		String query = "SELECT * FROM Dieta LIMIT ?,?;";
		return super.get(query, take, skip);
	}

	@Override
	public Dieta get(Integer id) {
		String query = "SELECT * FROM Dieta WHERE Id_Dieta = ?";
		return get(query, id);
	}

	@Override
	public List<Dieta> search(String search) {
		String query = "SELECT * FROM Dieta WHERE Nome like ? OR Descricao like ? AND Ativo = 1";
		return search(query, search);
	}

	@Override
	public Integer add(Dieta entity) {
		String query = "INSERT INTO Dieta (Nome, Descricao, Ativo) VALUES (?, ?, ?);";
		return super.add(query, entity);
	}

	@Override
	public Integer update(Dieta entity) {
		String query = "UPDATE Dieta SET Nome = ?, Descricao = ?, Ativo = ? WHERE Id_Dieta = ?;";
		return super.update(query, entity);
	}
	
	@Override
	public Integer delete(Integer id) {
		String query = "UPDATE Dieta SET Ativo = 0 where Id_Dieta = ?;";
		return super.delete(query, id);
	}

	@Override
	protected List<Dieta> LoadEntityFromResultSet(ResultSet rs) throws SQLException {
		List<Dieta> lstDieta = new ArrayList<Dieta>();
		while(rs.next()) {
			Dieta dieta = new Dieta();
			dieta.setID(rs.getInt("Id_Dieta"));
			dieta.setNome(rs.getString("Nome"));
			dieta.setDescricao(rs.getString("Descricao"));
			dieta.setAtivo(rs.getBoolean("Ativo"));
			lstDieta.add(dieta);
		}
		return lstDieta;
	}

	@Override
	protected Map<Integer, Object> converterEntityParaHashMapSemID(Dieta entity) {
		Map<Integer, Object> map = new HashMap<Integer, Object>();
		map.put(1, entity.getNome());
		map.put(2, entity.getDescricao());
		map.put(3, entity.isAtivo());
		return map;
	}

	@Override
	public Integer getLastIdInserted() {
		// TODO Auto-generated method stub
		return null;
	}
}
