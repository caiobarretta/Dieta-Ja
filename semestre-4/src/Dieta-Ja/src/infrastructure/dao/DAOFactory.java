package infrastructure.dao;

import java.sql.Connection;

import core.abstractions.DAOFactoryMethod;
import core.interfaces.dao.IDietaDAO;
import core.interfaces.dao.IPorcaoDeAlimentoDAO;
import core.interfaces.dao.IRegistroDeAtividadeDAO;
import core.interfaces.dao.IUsuarioDAO;

public class DAOFactory extends DAOFactoryMethod{

	@Override
	public IDietaDAO createDietaDAO(Connection conn) {
		return new DietaDAO(conn);
	}

	@Override
	public IPorcaoDeAlimentoDAO createPorcaoDeAlimentoDAO(Connection conn) {
		return new PorcaoDeAlimentoDAO(conn);
	}

	@Override
	public IRegistroDeAtividadeDAO createRegistroDeAtividadeDAO() {
		return new RegistroDeAtividadeDAO();
	}

	@Override
	public IUsuarioDAO createUsuarioDAO(Connection conn) {
		// TODO Auto-generated method stub
		return new UsuarioDAO(conn);
	}
	
}
