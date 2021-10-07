package infrastructure.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import app.model.TreeViewPorcaoDeAlimentoDTO;
import core.entities.DiaDaSemanaEnum;
import core.entities.Dieta;
import core.entities.PorcaoDeAlimento;
import core.exception.InvalidTypeException;
import core.interfaces.dao.IDietaDAO;
import infrastructure.dao.base.DAOConnection;
import infrastructure.dao.base.DefaultDAOTemplateMethod;
import infrastructure.dao.base.BaseDAOTemplateMethod;
import infrastructure.dao.helper.HelperExecuteStatementChain;
import infrastructure.dao.helper.HelperHashMap;
import infrastructure.dao.helper.StrBuilderHelper;

public class DietaDAO extends DefaultDAOTemplateMethod<Dieta> implements IDietaDAO{

	public DietaDAO(Connection conn) {
		super(conn);
	}

	@Override
	public List<Dieta> get(Integer take, Integer skip) {
		String query = "SELECT * FROM Dieta WHERE Ativo = 1 LIMIT ?,?";
		return super.get(query, take, skip);
	}

	@Override
	public Dieta get(Integer id) {
		String query = "SELECT * FROM Dieta WHERE Id_Dieta = ? AND Ativo = 1";
		return get(query, id);
	}

	@Override
	public List<Dieta> search(String search) {
		String query = "SELECT * FROM Dieta WHERE (Nome like ? OR Descricao like ?) AND Ativo = 1";
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
		return super.getLastIdInserted();
	}

	@Override
	public List<TreeViewPorcaoDeAlimentoDTO> retornaPorcaoDeAlimentoPeloIdDieta(Integer id) {
		List<TreeViewPorcaoDeAlimentoDTO> lstResult = new ArrayList<TreeViewPorcaoDeAlimentoDTO>();
		
		String query = "SELECT porc.Nome AS PorcaoDeAlimento, CONVERT_ENUM_DIA_DA_SEMANA_STR(DiaDaSemana) AS DiaDaSemana, "
				+ " CONVERT_ENUM_REFEICAO_STR(Refeicao) AS Refeicao "
				+ " FROM PorcaoDeAlimentoDiasDaSemanaDietaRefeicao AS padsr "
				+ " INNER JOIN PorcaoDeAlimento AS porc ON padsr.ID_PorcaoDeAlimento = porc.ID_PorcaoDeAlimento "
				+ " INNER JOIN Dieta AS dt ON padsr.ID_Dieta = dt.ID_Dieta "
				+ "WHERE dt.ID_Dieta = ? AND porc.Ativo = true; ";
		
		ResultSet rs = null;
        Connection connection = null;
        PreparedStatement statement = null; 
         
        try {           
        	connection = this.conn;
            statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            rs = statement.executeQuery();
            while(rs.next()) {
            	TreeViewPorcaoDeAlimentoDTO tree = new TreeViewPorcaoDeAlimentoDTO();
            	tree.setPorcaoDeAlimento(rs.getString("PorcaoDeAlimento"));
            	tree.setDiaDaSemana(rs.getString("DiaDaSemana"));
            	tree.setRefeicao(rs.getString("Refeicao"));
            	lstResult.add(tree);
    		}
        } catch (SQLException e) {
			e.printStackTrace();
		} catch (InvalidTypeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return lstResult;
	}

	@Override
	public List<Integer> retornaPorcaoDeAlimentoPeloIdDietaAgrupado(Integer id) {
		List<Integer> lstResult = new ArrayList<Integer>();
		
		String query = " SELECT DISTINCT porc.ID_PorcaoDeAlimento "
				+ " FROM PorcaoDeAlimentoDiasDaSemanaDietaRefeicao AS padsr "
				+ " 	INNER JOIN PorcaoDeAlimento AS porc "
				+ " 		ON padsr.ID_PorcaoDeAlimento = porc.ID_PorcaoDeAlimento "
				+ " 	INNER JOIN Dieta AS dt "
				+ " 		ON padsr.ID_Dieta = dt.ID_Dieta "
				+ " WHERE dt.ID_Dieta = ? AND porc.Ativo = true "
				+ " GROUP BY porc.Nome ";
		
		ResultSet rs = null;
        Connection connection = null;
        PreparedStatement statement = null; 
         
        try {           
        	connection = this.conn;
            statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            rs = statement.executeQuery();
            while(rs.next()) {
            	lstResult.add(rs.getInt("ID_PorcaoDeAlimento"));
    		}
        } catch (SQLException e) {
			e.printStackTrace();
		} catch (InvalidTypeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return lstResult;
	}
}
