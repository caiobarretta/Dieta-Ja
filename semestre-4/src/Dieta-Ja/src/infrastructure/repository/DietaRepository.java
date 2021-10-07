package infrastructure.repository;

import java.util.List;

import app.model.TreeViewPorcaoDeAlimentoDTO;
import core.entities.Dieta;
import core.interfaces.dao.IDietaDAO;
import core.interfaces.dao.base.IDAO;
import core.interfaces.repository.IDietaRepository;
import infrastructure.repository.base.DefaultRepositoryTemplateMethod;

public class DietaRepository extends DefaultRepositoryTemplateMethod<Dieta> implements IDietaRepository{

	protected final IDietaDAO _idao;
	public DietaRepository(IDietaDAO dao) {
		this._idao = dao;
		
	}
	@Override
	public IDAO<Dieta> getDAO() {
		return _idao;
	}
	@Override
	public Integer getLastIdInserted() {
		return _idao.getLastIdInserted();
	}
	@Override
	public List<TreeViewPorcaoDeAlimentoDTO> retornaPorcaoDeAlimentoPeloIdDieta(Integer id) {
		return _idao.retornaPorcaoDeAlimentoPeloIdDieta(id);
	}
	@Override
	public List<Integer> retornaPorcaoDeAlimentoPeloIdDietaAgrupado(Integer id) {
		return _idao.retornaPorcaoDeAlimentoPeloIdDietaAgrupado(id);
	}
}
