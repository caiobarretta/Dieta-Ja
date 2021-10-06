package core.abstractions;

import java.sql.Connection;

import core.interfaces.dao.IDietaDAO;
import core.interfaces.dao.IPorcaoDeAlimentoDAO;
import core.interfaces.dao.IRegistroDeAtividadeDAO;
import core.interfaces.dao.IUsuarioDAO;

public abstract class DAOFactoryMethod {
	
	public abstract IDietaDAO createDietaDAO(Connection conn);
	public abstract IUsuarioDAO createUsuarioDAO(Connection conn);
	public abstract IPorcaoDeAlimentoDAO createPorcaoDeAlimentoDAO(Connection conn);
	public abstract IRegistroDeAtividadeDAO createRegistroDeAtividadeDAO();
}
