package core.interfaces.repository;

import core.entities.Usuario;
import core.interfaces.repository.base.IRepository;

public interface IUsuarioRepository extends IRepository<Usuario>{
	boolean IsUsuario(String usuario, String senha, int CodigoUsuario);
}
