package core.interfaces.repository;

import core.entities.Usuario;
import core.interfaces.repository.base.IRepository;

public interface IUsuarioRepository extends IRepository<Usuario>{
	Integer getLoginUsuario(String usuario, String senha);
}
