package services;

import core.entities.Usuario;
import core.interfaces.service.IUsuarioService;
import services.base.DefaultService;

public class UsuarioService extends DefaultService<Usuario> implements IUsuarioService {
	
	public boolean isUsuario(String usuario, String senha, int CodigoUsuario){
		//TODO : Implementar
        throw new UnsupportedOperationException("Implementar");
    }
}
