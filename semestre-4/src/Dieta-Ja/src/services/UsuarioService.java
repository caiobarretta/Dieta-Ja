package services;

import core.entities.Usuario;
import core.interfaces.repository.IUsuarioRepository;
import core.interfaces.service.IUsuarioService;
import services.base.DefaultService;

public class UsuarioService extends DefaultService<Usuario> implements IUsuarioService {
	
	protected final IUsuarioRepository _repo;
	public UsuarioService(IUsuarioRepository repo) {
		super(repo);
		this._repo = repo;
	}

	public Integer getLoginUsuario(String usuario, String senha){
		return this._repo.getLoginUsuario(usuario, senha);
    }

	@Override
	public Integer getLastIdInserted() {
		// TODO Auto-generated method stub
		return null;
	}
}
