package core.entities;

import core.entities.base.Entity;

//Classe que representa uma entidade usuário
public class Usuario extends Entity {
	protected String Login;
	protected String Senha;
	protected Integer DietaID;
	protected TipoUsuarioEnum TipoUsuario;
	
	public Usuario() {
		this.setAtivo(true);
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
	
	@Override
	public String toString() {
		String basicDescription = String.format("ID:%d Nome:%s Descricao:%s Ativo:%b", this.getID(), this.getNome(), this.getDescricao(), this.isAtivo());
		String tipoUsuario = TipoUsuarioEnum.retornaNomeEnumPeloId(TipoUsuarioEnum.retornaIdPeloEnum(this.getTipoUsuario()));
		String description = String.format("%s Login:%s Senha:%s DietaID:%d TipoUsuario:%s",basicDescription, this.getLogin(), this.getSenha(), this.getDietaID(), tipoUsuario);
		return description;
	}
}
