package core.entities;

import java.util.ArrayList;
import java.util.List;

import core.entities.base.Entity;

public class Dieta extends Entity {
	protected List<PorcaoDeAlimentoDieta> PorcaoDeAlimentoDieta;
	protected List<Usuario> Usuarios;
	
	public Dieta() {
		setPorcaoDeAlimentoDieta(new ArrayList<PorcaoDeAlimentoDieta>());
		setUsuarios(new ArrayList<Usuario>());
	}
	
	public List<PorcaoDeAlimentoDieta> getPorcaoDeAlimentoDieta() {
		return PorcaoDeAlimentoDieta;
	}
	public void setPorcaoDeAlimentoDieta(List<PorcaoDeAlimentoDieta> porcaoDeAlimentoDieta) {
		PorcaoDeAlimentoDieta = porcaoDeAlimentoDieta;
	}
	public List<Usuario> getUsuarios() {
		return Usuarios;
	}
	public void setUsuarios(List<Usuario> usuarios) {
		Usuarios = usuarios;
	}
	
}
