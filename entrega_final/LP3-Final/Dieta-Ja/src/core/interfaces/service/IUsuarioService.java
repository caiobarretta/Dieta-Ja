package core.interfaces.service;

import core.entities.Usuario;
import core.interfaces.repository.IUsuarioRepository;
import core.interfaces.service.base.IService;

public interface IUsuarioService extends IService<Usuario>, IUsuarioRepository{

}
