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
import core.entities.TipoMedidaEnum;
import core.interfaces.dao.IPorcaoDeAlimentoDAO;
import infrastructure.dao.base.DefaultDAOTemplateMethod;

public class PorcaoDeAlimentoDAO extends DefaultDAOTemplateMethod<PorcaoDeAlimento> implements IPorcaoDeAlimentoDAO {

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
		String query = "SELECT * FROM PorcaoDeAlimento WHERE Id_PorcaoDeAlimento = ? and Ativo = 1";
		return super.get(query, id);
	}

	@Override
	public List<PorcaoDeAlimento> search(String search) {
		String query = "SELECT * FROM PorcaoDeAlimento WHERE (Nome like ? OR Descricao like ?) AND Ativo = 1";
		return super.search(query, search);
	}

	@Override
	public Integer add(PorcaoDeAlimento entity) {
		//# ID_PorcaoDeAlimento, Nome, Descricao, Ativo, Quantidade, TipoMedida
		String query = "INSERT INTO PorcaoDeAlimento (Nome, Descricao, Ativo, Quantidade, TipoMedida) values (?, ?, ?, ?, ?);";
		return super.add(query, entity);

	}

	@Override
	public Integer update(PorcaoDeAlimento entity) {
		//# ID_PorcaoDeAlimento, Nome, Descricao, Ativo, Quantidade, TipoMedida
		String query = "UPDATE PorcaoDeAlimento SET Nome = ?, Descricao = ?, Ativo = ?, Quantidade = ?, TipoMedida = ? WHERE Id_PorcaoDeAlimento = ?";
		return super.update(query, entity);
	}

	@Override
	public List<Integer> retornaIdDaDietaPeloIdPorcaoDeAlimento(Integer id) {
		List<PorcaoDeAlimento> lstResult = new ArrayList<PorcaoDeAlimento>();
		String query = "SELECT Id_Dieta FROM PorcaoDeAlimentoDiasDaSemanaDietaRefeicao WHERE Id_PorcaoDeAlimento = ?";
		return loadFieldIntegerFromEnumAsList(query, "Id_Dieta", id);
	}
	
	public List<String> retornaDiaDaSemanaPeloIDPorcaoDeAlimento(Integer id) {
		List<String> lstResult = new ArrayList<String>();
		String query = "SELECT DiaDaSemana FROM PorcaoDeAlimentoDiasDaSemanaDietaRefeicao WHERE Id_PorcaoDeAlimento = ?";
		List<Integer> lst = loadFieldIntegerFromEnumAsList(query, "DiaDaSemana", id);
		for (Integer index : lst) {
			lstResult.add(DiaDaSemanaEnum.retornaNomeEnumPeloId(index));
		}
		return lstResult;
	}
	
	@Override
	public List<String> retornaRefeicaoPeloIdPorcaoDeAlimento(Integer id) {
		List<String> lstResult = new ArrayList<String>();
		String query = "SELECT Refeicao FROM PorcaoDeAlimentoDiasDaSemanaDietaRefeicao WHERE Id_PorcaoDeAlimento = ?";
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
		map.put(4, entity.getQuantidade());
		map.put(5, TipoMedidaEnum.retornaIdPeloEnum(entity.getTipoMedida()));
		return map;
	}

	@Override
	protected List<PorcaoDeAlimento> LoadEntityFromResultSet(ResultSet rs) throws SQLException {
		List<PorcaoDeAlimento> lst = new ArrayList<PorcaoDeAlimento>();
		//# ID_PorcaoDeAlimento, Nome, Descricao, Ativo, Quantidade, TipoMedida
		while (rs.next()) {
			PorcaoDeAlimento porcaoDeAlimento = new PorcaoDeAlimento();
			porcaoDeAlimento.setID(rs.getInt("Id_PorcaoDeAlimento"));
			porcaoDeAlimento.setNome(rs.getString("Nome"));
			porcaoDeAlimento.setDescricao(rs.getString("Descricao"));
			porcaoDeAlimento.setAtivo(rs.getBoolean("Ativo"));
			porcaoDeAlimento.setQuantidade(rs.getInt("Quantidade"));
			porcaoDeAlimento.setTipoMedida(TipoMedidaEnum.retornaEnumPeloId(rs.getInt("TipoMedida")));
			lst.add(porcaoDeAlimento);
		}
		return lst;
	}

	@Override
	public Integer delete(Integer id) {
		String query = "UPDATE PorcaoDeAlimento SET Ativo = 0 WHERE Id_PorcaoDeAlimento = ?";
		return super.delete(query, id);
	}

	@Override
	public List<String> retornaDiasDaSemanaPeloIdPorcaoDeAlimento(Integer id) {
		List<String> lstResult = new ArrayList<String>();
		String query = "SELECT DiaDaSemana FROM PorcaoDeAlimentoDiasDaSemanaDietaRefeicao WHERE Id_PorcaoDeAlimento = ?";
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
	public Integer associarPorcaoDeAlimentoDiasDaSemanaDietaRefeicao(Integer IdPorcaoDeAlimento,
			List<Integer> listDiaDaSemana, List<Integer> listIdRefeicao, Integer dietaID) {
		
		//# ID_PorcaoDeAlimento, ID_Dieta, DiaDaSemana, Refeicao
		String queryDelete = "DELETE FROM PorcaoDeAlimentoDiasDaSemanaDietaRefeicao WHERE Id_PorcaoDeAlimento = ? AND ID_Dieta = ?";
		super.delete(queryDelete, IdPorcaoDeAlimento, dietaID);
		
		String query = "INSERT INTO PorcaoDeAlimentoDiasDaSemanaDietaRefeicao (Id_PorcaoDeAlimento, DiaDaSemana, Refeicao, ID_Dieta) VALUES(?, ?, ?, ?);";
		int counter = 0;
		for (Integer dia : listDiaDaSemana) {
			for (Integer ref : listIdRefeicao) {
				counter += super.add(query, IdPorcaoDeAlimento, dia, ref, dietaID);
			}
		}
		return counter;
	}
}
