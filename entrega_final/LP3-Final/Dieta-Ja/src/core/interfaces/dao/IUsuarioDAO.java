package core.interfaces.dao;

import core.entities.Usuario;
import core.interfaces.dao.base.IDAO;
import core.interfaces.repository.IUsuarioRepository;

public interface IUsuarioDAO extends IDAO<Usuario>, IUsuarioRepository{

}
