package services;

import core.entities.Usuario;
import core.interfaces.service.IUsuarioService;
import infrastructure.repository.UsuarioRepository;
import services.base.DefaultService;

public class UsuarioService extends DefaultService<Usuario> implements IUsuarioService {
	
	protected final UsuarioRepository _repo;
	public UsuarioService(UsuarioRepository repo) {
		super(repo);
		this._repo = repo;
	}

	public boolean isUsuario(String usuario, String senha, Integer CodigoUsuario){
		return this._repo.isUsuario(usuario, senha, CodigoUsuario);
    }
}
