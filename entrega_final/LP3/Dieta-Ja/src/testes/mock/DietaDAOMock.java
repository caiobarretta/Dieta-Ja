package testes.mock;

import java.sql.Connection;
import java.util.Map;

import core.entities.Dieta;
import infrastructure.dao.DietaDAO;
import infrastructure.dao.base.DAOConnection;

public class DietaDAOMock extends DietaDAO{

	public DietaDAOMock(Connection conn) {
		super(conn);
		// TODO Auto-generated constructor stub
	}
	
	public Map<Integer, Object> mockConverterEntityParaHashMapComIDNaUltimaPosicao(Dieta entity) {
		return super.converterEntityParaHashMapComIDNaUltimaPosicao(entity);
	}

}
