package core.entities.base;

//Classe base para herança das entidades.
public abstract class Entity {
	protected Integer ID;
	protected String Descricao;
    protected String Nome;
    protected boolean Ativo;
    
    public Integer getID() {
		return ID;
	}
	public void setID(Integer iD) {
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
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return String.format("%d-%s", this.getID(), this.getNome());
	}
	
}
