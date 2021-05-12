package core.entities;

import java.util.ArrayList;
import java.util.List;

import core.entities.base.Entity;

public class Perfil extends Entity{
	
	protected List<Usuario> Usuarios;
	
	public Perfil(){
        setUsuarios(new ArrayList<Usuario>());
    }
	
	public List<Usuario> getUsuarios() {
		return Usuarios;
	}

	public void setUsuarios(List<Usuario> usuarios) {
		Usuarios = usuarios;
	}
}
