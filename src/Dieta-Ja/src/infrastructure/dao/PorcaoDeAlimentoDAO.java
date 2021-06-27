package infrastructure.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import core.entities.Dieta;
import core.entities.PorcaoDeAlimento;
import core.interfaces.dao.IPorcaoDeAlimentoDAO;
import infrastructure.dao.base.DAOConnection;
import infrastructure.dao.base.DefaultDAO;
import infrastructure.dao.base.BaseDAO;

public class PorcaoDeAlimentoDAO extends DefaultDAO<PorcaoDeAlimento> implements IPorcaoDeAlimentoDAO{

	public PorcaoDeAlimentoDAO(Connection conn) {
		super(conn);
		// TODO Auto-generated constructor stub
	}

	@Override
	public List<PorcaoDeAlimento> get(Integer take, Integer skip) {
		String query = "SELECT * FROM PorcaoDeAlimento LIMIT ?,?;";
		return super.get(query, take, skip);
	}

	@Override
	public PorcaoDeAlimento get(Integer id) {
		String query = "SELECT * FROM PorcaoDeAlimento WHERE Id_PorcaoAlimento = ?";
		return super.get(query, id);
	}

	@Override
	public List<PorcaoDeAlimento> search(String search) {
		String query = "SELECT * FROM PorcaoDeAlimento WHERE Nome like ? OR Descricao like ? AND Ativo = 1";
		return super.search(query, search);
	}

	@Override
	public Integer add(PorcaoDeAlimento entity) {
		String query = "INSERT INTO PorcaoDeAlimento (Nome, Descricao, Ativo) values (?, ?, ?);";
		return super.add(query, entity);
		
	}

	@Override
	public Integer update(PorcaoDeAlimento entity) {
		String query = "UPDATE PorcaoDeAlimento SET Nome = ?, Descricao = ?, Ativo = ? WHERE Id_PorcaoAlimento = ?;";
		return super.update(query, entity);
	}

	@Override
	public Integer associarPorcaoRefeicoes(List<Integer> listIdRefeicao, Integer porcaoDeAlimentoID) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Integer associarPorcaoAlimentoDieta(List<Integer> listIdProcaoAlimento, Integer dietaID) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<PorcaoDeAlimento> retornaPorcaoDeAlimentoPeloIdDaDieta(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected Map<Integer, Object> converterEntityParaHashMapSemID(PorcaoDeAlimento entity) {
		Map<Integer, Object> map = new HashMap<Integer, Object>();
		map.put(1, entity.getNome());
		map.put(2, entity.getDescricao());
		map.put(3, entity.isAtivo());
		return map;
	}

	@Override
	protected List<PorcaoDeAlimento> LoadEntityFromResultSet(ResultSet rs) throws SQLException {
		List<PorcaoDeAlimento> lst = new ArrayList<PorcaoDeAlimento>();
		while(rs.next()) {
			PorcaoDeAlimento porcaoDeAlimento = new PorcaoDeAlimento();
			porcaoDeAlimento.setID(rs.getInt("Id_PorcaoAlimento"));
			porcaoDeAlimento.setNome(rs.getString("Nome"));
			porcaoDeAlimento.setDescricao(rs.getString("Descricao"));
			porcaoDeAlimento.setAtivo(rs.getBoolean("Ativo"));
			lst.add(porcaoDeAlimento);
		}
		return lst;
	}

	@Override
	public Integer delete(Integer id) {
		String query = "UPDATE PorcaoDeAlimento SET Ativo = 0 WHERE Id_PorcaoAlimento = ?;";
		return super.delete(query, id);
	}

}
