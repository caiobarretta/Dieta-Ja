package infrastructure.repository;

import core.entities.Usuario;
import core.interfaces.dao.IUsuarioDAO;
import core.interfaces.dao.base.IDAO;
import core.interfaces.repository.IUsuarioRepository;
import infrastructure.repository.base.DefaultRepositoryTemplateMethod;

public class UsuarioRepository extends DefaultRepositoryTemplateMethod<Usuario> implements IUsuarioRepository{
	
	protected final IUsuarioDAO _idao;
	public UsuarioRepository(IUsuarioDAO dao) {
		this._idao = dao;
	}
	public Integer getLoginUsuario(String usuario, String senha){
		return this._idao.getLoginUsuario(usuario, senha);
    }

	@Override
	public IDAO<Usuario> getDAO() {
		return this._idao;
	}
	@Override
	public Integer getLastIdInserted() {
		return this._idao.getLastIdInserted();
	}
}
