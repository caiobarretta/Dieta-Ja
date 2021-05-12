package core.entities;

import java.util.ArrayList;
import java.util.List;

import core.entities.base.Entity;

public class Usuario extends Entity {
	protected String Login;
	protected String Senha;
	protected int PerfilID;
	protected int DietaID;
	protected Dieta Dieta;
	protected Perfil Perfil;
	protected List<RegistroDeAtividade> RegistroDeAtividade;
	
	public Usuario() {
		setRegistroDeAtividade(new ArrayList<RegistroDeAtividade>());
	}
	
	public String getLogin() {
		return Login;
	}
	public void setLogin(String login) {
		Login = login;
	}
	public String getSenha() {
		return Senha;
	}
	public void setSenha(String senha) {
		Senha = senha;
	}
	public int getPerfilID() {
		return PerfilID;
	}
	public void setPerfilID(int perfilID) {
		PerfilID = perfilID;
	}
	public int getDietaID() {
		return DietaID;
	}
	public void setDietaID(int dietaID) {
		DietaID = dietaID;
	}
	public Dieta getDieta() {
		return Dieta;
	}
	public void setDieta(Dieta dieta) {
		Dieta = dieta;
	}
	public Perfil getPerfil() {
		return Perfil;
	}
	public void setPerfil(Perfil perfil) {
		Perfil = perfil;
	}
	public List<RegistroDeAtividade> getRegistroDeAtividade() {
		return RegistroDeAtividade;
	}
	public void setRegistroDeAtividade(List<RegistroDeAtividade> registroDeAtividade) {
		RegistroDeAtividade = registroDeAtividade;
	}
	
}
