package infrastructure.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import core.entities.PorcaoDeAlimento;
import core.entities.RegistroDeAtividade;
import core.exception.InvalidTypeException;
import core.interfaces.dao.IRegistroDeAtividadeDAO;
import infrastructure.dao.base.DAOConnection;
import infrastructure.dao.base.DefaultDAO;

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
	
	public Integer Add(RegistroDeAtividade registro){
		List<Integer> lstResult = new ArrayList<Integer>();
		//ID_RegistroDeAtividade, ID_Dieta, ID_PorcaoDeAlimento, ID_Usuario, Registro, Comentarios, Sentimento, Refeicao, DiaDaSemana
		String data = registro.getRegistro()+ " 00:00:00";
		
		String query = "INSERT INTO RegistroDeAtividade "
				+ " (ID_Dieta, ID_PorcaoDeAlimento, ID_Usuario, Registro, "
				+ " Comentarios, Sentimento, Refeicao, DiaDaSemana)"
				+ " VALUES"
				+ " (?, ?, ?, NOW(), "
				+ " ?, ?, ?, ?);";
		
		ResultSet rs = null;
        Connection connection = null;
        PreparedStatement statement = null; 
         
        try {           
        	connection = DAOConnection.getConnection();
            statement = connection.prepareStatement(query);
            
            //ID_Dieta
            statement.setInt(1, registro.getDietaID());
            //ID_PorcaoDeAlimento
            statement.setInt(2, registro.getPorcaoDeAlimentoID());
            //ID_Usuario
            statement.setInt(3, registro.getUsuarioID());
            
            //'2021-04-21 00:00:00'
            //2021-07-11
            //Registro
            //statement.setDate(4,);
            //Comentarios
            statement.setString(4, registro.getComentarios());
            //Sentimento
            statement.setInt(5, registro.getSentimento());
            //Refeicao
            statement.setInt(6, registro.getRefeicaoID());
            //DiaDaSemana
            statement.setInt(7, registro.getDiaDaSemanaID());
            
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
        
        return 0;
	}
	
}
