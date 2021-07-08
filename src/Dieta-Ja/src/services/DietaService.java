package services;

import java.util.List;

import core.entities.Dieta;
import core.interfaces.repository.IDietaRepository;
import core.interfaces.service.IDietaService;
import services.base.DefaultService;

public class DietaService extends DefaultService<Dieta> implements IDietaService{
	protected final IDietaRepository _repo;
	public DietaService(IDietaRepository repo) {
		super(repo);
		this._repo = repo;
	}
	
	@Override
	public Integer getLastIdInserted() {
		return this._repo.getLastIdInserted();
	}
	
	@Override
	public List<Integer> retornaPorcaoDeAlimentoPeloIdDieta(Integer id) {
		return this._repo.retornaPorcaoDeAlimentoPeloIdDieta(id);
	}
}
