package services;

import core.abstractions.ServicesFactoryMethod;
import core.interfaces.repository.IDietaRepository;
import core.interfaces.repository.IPorcaoDeAlimentoRepository;
import core.interfaces.repository.IRegistroDeAtividadeRepository;
import core.interfaces.repository.IUsuarioRepository;
import core.interfaces.service.IDietaService;
import core.interfaces.service.IPorcaoDeAlimentoService;
import core.interfaces.service.IRegistroDeAtividadeService;
import core.interfaces.service.IUsuarioService;

public class ServicesFactory extends ServicesFactoryMethod{

	@Override
	public IDietaService createDietaService(IDietaRepository repo) {
		return new DietaService(repo);
	}

	@Override
	public IPorcaoDeAlimentoService createPorcaoDeAlimentoService(IPorcaoDeAlimentoRepository repo) {
		return new PorcaoDeAlimentoService(repo);
	}

	@Override
	public IRegistroDeAtividadeService createRegistroDeAtividadeService(IRegistroDeAtividadeRepository repo) {
		return new RegistroDeAtividadeService(repo);
	}

	@Override
	public IUsuarioService createUsuarioService(IUsuarioRepository repo) {
		return new UsuarioService(repo);
	}

}
