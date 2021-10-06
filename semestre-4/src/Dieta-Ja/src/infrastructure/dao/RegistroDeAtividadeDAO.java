package infrastructure.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import app.model.TreeViewPorcaoDeAlimentoDTO;
import core.entities.PorcaoDeAlimento;
import core.entities.RegistroDeAtividade;
import core.exception.InvalidTypeException;
import core.interfaces.dao.IRegistroDeAtividadeDAO;
import infrastructure.dao.base.DAOConnection;
import infrastructure.dao.base.DefaultDAOTemplateMethod;
import infrastructure.dao.helper.StrBuilderHelper;

public class RegistroDeAtividadeDAO implements IRegistroDeAtividadeDAO{

	@Override
	public List<PorcaoDeAlimento> retornaProcaoDeAlimentoPeloRegistroDeAtividade(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer associarPorcaoRegistroDeAlimentos(List<Integer> ids, Integer id) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public List<RegistroDeAtividade> getByRegistroEUsuario(String registro, Integer UsuarioID){
		List<RegistroDeAtividade> result = new ArrayList<RegistroDeAtividade>();
		String query = "select * from RegistroDeAtividade where (Registro between ? and  ?)  and ID_Usuario = ?;";
		
		ResultSet rs = null;
        Connection connection = null;
        PreparedStatement statement = null; 
        
        try {           
        	connection = DAOConnection.getConnection();
            statement = connection.prepareStatement(query);
            statement.setDate(1, java.sql.Date.valueOf(registro));
            statement.setDate(2, java.sql.Date.valueOf(registro));
            statement.setInt(3, UsuarioID);
            rs = statement.executeQuery();
            while(rs.next()) {
            	RegistroDeAtividade reg = new RegistroDeAtividade();
            	reg.setRegistroDeAtividadeID(rs.getInt("ID_RegistroDeAtividade"));
            	reg.setPorcaoDeAlimentoID(rs.getInt("ID_PorcaoDeAlimento"));
            	reg.setDietaID(rs.getInt("ID_Dieta"));
            	reg.setPorcaoDeAlimentoID(rs.getInt("ID_PorcaoDeAlimento"));
            	reg.setUsuarioID(rs.getInt("ID_Usuario"));
            	reg.setRegistro(rs.getDate("Registro").toString());
            	reg.setComentarios(rs.getString("Comentarios"));
            	reg.setSentimento(rs.getInt("Sentimento"));
            	reg.setRefeicaoID(rs.getInt("Refeicao"));
            	reg.setDiaDaSemanaID(rs.getInt("DiaDaSemana"));
            	result.add(reg);
    		}
        } catch (SQLException e) {
			e.printStackTrace();
		} catch (InvalidTypeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result;
	}
	
	public Integer Add(RegistroDeAtividade registro){
		
		this.Delete(registro.getRegistro(), registro.getUsuarioID());
		
		
		List<Integer> lstResult = new ArrayList<Integer>();
		String query = "INSERT INTO RegistroDeAtividade "
				+ " (ID_Dieta, ID_PorcaoDeAlimento, ID_Usuario, Registro, "
				+ " Comentarios, Sentimento, Refeicao, DiaDaSemana)"
				+ " VALUES"
				+ " (?, ?, ?, ?, "
				+ "  ?, ?, 0, ?);";
		
		ResultSet rs = null;
        Connection connection = null;
        PreparedStatement statement = null; 
        int id = 0;
         
        try {           
        	connection = DAOConnection.getConnection();
            statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            
            //ID_Dieta
            statement.setInt(1, registro.getDietaID());
            //ID_PorcaoDeAlimento
            statement.setInt(2, registro.getPorcaoDeAlimentoID());
            //ID_Usuario
            statement.setInt(3, registro.getUsuarioID());
            //Registro
            statement.setDate(4, java.sql.Date.valueOf(registro.getRegistro()));
            //Comentarios
            statement.setString(5, registro.getComentarios());
            //Sentimento
            statement.setInt(6, registro.getSentimento());
            //DiaDaSemana
            statement.setInt(7, registro.getDiaDaSemanaID());
            
            id = statement.executeUpdate();
        } catch (SQLException e) {
			System.out.printf("Erro: %s", e.getMessage()); 
		} catch (InvalidTypeException e) {
			// TODO Auto-generated catch block
			System.out.printf("Erro: %s", e.getMessage());
		}
        
        return id;
	}
	
	public void Delete(String registro, Integer UsuarioID){
		
		String query = "delete from RegistroDeAtividade where (Registro between ? and  ?)  and ID_Usuario = ?;";
		
		ResultSet rs = null;
        Connection connection = null;
        PreparedStatement statement = null; 
        try {           
        	connection = DAOConnection.getConnection();
            statement = connection.prepareStatement(query);
            //Registro
            statement.setDate(1, java.sql.Date.valueOf(registro));
            statement.setDate(2, java.sql.Date.valueOf(registro));
            statement.setInt(3, UsuarioID);
            statement.executeUpdate();
        } catch (SQLException e) {
			System.out.printf("Erro: %s", e.getMessage()); 
		} catch (InvalidTypeException e) {
			// TODO Auto-generated catch block
			System.out.printf("Erro: %s", e.getMessage());
		}
        
	}
	
}
