package core.entities.base;

public abstract class Entity {
	protected int ID;
	protected String Descricao;
    protected String Nome;
    protected boolean Ativo;
    
    public int getID() {
		return ID;
	}
	public void setID(int iD) {
		ID = iD;
	}
	public String getDescricao() {
		return Descricao;
	}
	public void setDescricao(String descricao) {
		Descricao = descricao;
	}
	public String getNome() {
		return Nome;
	}
	public void setNome(String nome) {
		Nome = nome;
	}
	public boolean isAtivo() {
		return Ativo;
	}
	public void setAtivo(boolean ativo) {
		Ativo = ativo;
	}
	
}
