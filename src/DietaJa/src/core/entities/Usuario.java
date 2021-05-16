package core.entities;

import core.entities.base.Entity;

public class Usuario extends Entity {
	protected String Login;
	protected String Senha;
	protected Integer DietaID;
	protected TipoUsuarioEnum TipoUsuario;
	
	public Usuario() {
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
	public Integer getDietaID() {
		return DietaID;
	}
	public void setDietaID(Integer dietaID) {
		DietaID = dietaID;
	}
	public TipoUsuarioEnum getTipoUsuario() {
		return TipoUsuario;
	}
	public void setTipoUsuario(TipoUsuarioEnum tipoUsuario) {
		TipoUsuario = tipoUsuario;
	}
}
