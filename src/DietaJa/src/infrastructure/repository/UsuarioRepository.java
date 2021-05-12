package infrastructure.repository;

import core.entities.Usuario;
import core.interfaces.repository.IUsuarioRepository;
import infrastructure.repository.base.DefaultRepository;

public class UsuarioRepository extends DefaultRepository<Usuario> implements IUsuarioRepository{
	
	public boolean isUsuario(String usuario, String senha, int CodigoUsuario){
		//TODO : Implementar
        throw new UnsupportedOperationException("Implementar");
    }
}
