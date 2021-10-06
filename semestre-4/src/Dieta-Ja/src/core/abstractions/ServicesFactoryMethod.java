package core.abstractions;

import core.interfaces.repository.IDietaRepository;
import core.interfaces.repository.IPorcaoDeAlimentoRepository;
import core.interfaces.repository.IRegistroDeAtividadeRepository;
import core.interfaces.repository.IUsuarioRepository;
import core.interfaces.service.IDietaService;
import core.interfaces.service.IPorcaoDeAlimentoService;
import core.interfaces.service.IRegistroDeAtividadeService;
import core.interfaces.service.IUsuarioService;

public abstract class ServicesFactoryMethod {
	
	public abstract IDietaService createDietaService(IDietaRepository repo);
	public abstract IPorcaoDeAlimentoService createPorcaoDeAlimentoService(IPorcaoDeAlimentoRepository repo);
	public abstract IRegistroDeAtividadeService createRegistroDeAtividadeService(IRegistroDeAtividadeRepository repo);
	public abstract IUsuarioService createUsuarioService(IUsuarioRepository repo);
}
