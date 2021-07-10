package infrastructure.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import core.entities.DiaDaSemanaEnum;
import core.entities.PorcaoDeAlimento;
import core.entities.RefeicaoEnum;
import core.interfaces.dao.IPorcaoDeAlimentoDAO;
import infrastructure.dao.base.DefaultDAO;

public class PorcaoDeAlimentoDAO extends DefaultDAO<PorcaoDeAlimento> implements IPorcaoDeAlimentoDAO {

	public PorcaoDeAlimentoDAO(Connection conn) {
		super(conn);
		// TODO Auto-generated constructor stub
	}

	@Override
	public List<PorcaoDeAlimento> get(Integer take, Integer skip) {
		String query = "SELECT * FROM PorcaoDeAlimento where Ativo = 1 LIMIT ?,?;";
		return super.get(query, take, skip);
	}

	@Override
	public PorcaoDeAlimento get(Integer id) {
		String query = "SELECT * FROM PorcaoDeAlimento WHERE Id_PorcaoAlimento = ? and Ativo = 1";
		return super.get(query, id);
	}

	@Override
	public List<PorcaoDeAlimento> search(String search) {
		String query = "SELECT * FROM PorcaoDeAlimento WHERE (Nome like ? OR Descricao like ?) AND Ativo = 1";
		return super.search(query, search);
	}

	@Override
	public Integer add(PorcaoDeAlimento entity) {
		String query = "INSERT INTO PorcaoDeAlimento (Nome, Descricao, Ativo) values (?, ?, ?);";
		return super.add(query, entity);

	}

	@Override
	public Integer update(PorcaoDeAlimento entity) {
		String query = "UPDATE PorcaoDeAlimento SET Nome = ?, Descricao = ?, Ativo = ? WHERE Id_PorcaoAlimento = ?";
		return super.update(query, entity);
	}

	@Override
	public Integer associarPorcaoRefeicoes(List<Integer> listIdRefeicao, Integer porcaoDeAlimentoID) {
		String queryDelete = "DELETE FROM RefeicaoPorcaoDeAlimento WHERE ID_PorcaoAlimento = ?";
		super.delete(queryDelete, porcaoDeAlimentoID);

		String query = "INSERT INTO RefeicaoPorcaoDeAlimento (ID_PorcaoAlimento, Refeicao) VALUES (?, ?)";
		return associar(query, listIdRefeicao, porcaoDeAlimentoID);
	}

	@Override
	public Integer associarPorcaoAlimentoDieta(List<Integer> listIdProcaoAlimento, Integer dietaID) {
		String queryDelete = "DELETE FROM PorcaoDeAlimentoDieta WHERE ID_DIETA = ?";
		super.delete(queryDelete, dietaID);

		String query = "INSERT INTO PorcaoDeAlimentoDieta (ID_PorcaoAlimento, ID_DIETA) VALUES (?, ?)";
		return associar(query, listIdProcaoAlimento, dietaID);
	}

	@Override
	public Integer associarPorcaoAlimentoDiaDaSemana(List<Integer> listDiaDaSemana, Integer porcaoDeAlimentoID) {
		String queryDelete = "DELETE FROM PorcaoDeAlimentoDiasDaSemana WHERE ID_PorcaoAlimento = ?";
		super.delete(queryDelete, porcaoDeAlimentoID);

		String query = "INSERT INTO PorcaoDeAlimentoDiasDaSemana (ID_PorcaoAlimento, DiaDaSemana) VALUES (?, ?)";
		return associar(query, listDiaDaSemana, porcaoDeAlimentoID);
	}

	@Override
	public List<PorcaoDeAlimento> retornaPorcaoDeAlimentoPeloIdDaDieta(Integer id) {
		List<PorcaoDeAlimento> lstResult = new ArrayList<PorcaoDeAlimento>();
		String query = "SELECT Id_Dieta FROM PorcaoDeAlimentoDieta WHERE ID_PorcaoAlimento = ?";
		List<Integer> lst = loadFieldIntegerFromEnumAsList(query, "Id_Dieta", id);
		for (Integer index : lst) {
			lstResult.add(this.get(index));
		}
		return lstResult;
	}
	
	public List<String> retornaDiaDaSemanaPeloIDPorcaoDeAlimento(Integer id) {
		List<String> lstResult = new ArrayList<String>();
		String query = "SELECT DiaDaSemana FROM PorcaoDeAlimentoDiasDaSemana WHERE ID_PorcaoAlimento = ?";
		List<Integer> lst = loadFieldIntegerFromEnumAsList(query, "DiaDaSemana", id);
		for (Integer index : lst) {
			lstResult.add(DiaDaSemanaEnum.retornaNomeEnumPeloId(index));
		}
		return lstResult;
	}
	
	public List<String> retornaRefeicaoPeloIDPorcaoDeAlimento(Integer id) {
		List<String> lstResult = new ArrayList<String>();
		String query = "SELECT Refeicao FROM RefeicaoPorcaoDeAlimento WHERE ID_PorcaoAlimento = ?";
		List<Integer> lst = loadFieldIntegerFromEnumAsList(query, "Refeicao", id);
		for (Integer index : lst) {
			lstResult.add(RefeicaoEnum.retornaNomeEnumPeloId(index));
		}
		return lstResult;
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
		while (rs.next()) {
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
		String query = "UPDATE PorcaoDeAlimento SET Ativo = 0 WHERE Id_PorcaoAlimento = ?";
		return super.delete(query, id);
	}

	@Override
	public List<String> retornaDiasDaSemanaPeloIdPorcaoDeAlimento(Integer id) {
		List<String> lstResult = new ArrayList<String>();
		String query = "SELECT DiaDaSemana FROM PorcaoDeAlimentoDiasDaSemana WHERE Id_PorcaoAlimento = ?";
		List<Integer> lst = loadFieldIntegerFromEnumAsList(query, "DiaDaSemana", id);
		for (Integer index : lst) {
			lstResult.add(DiaDaSemanaEnum.retornaNomeEnumPeloId(index));
		}
		return lstResult;
	}

	@Override
	public Integer getLastIdInserted() {
		return super.getLastIdInserted();
	}

	@Override
	public List<String> retornaRefeicaoPeloIdPorcaoDeAlimento(Integer id) {
		return retornaRefeicaoPeloIDPorcaoDeAlimento(id);
	}
}
