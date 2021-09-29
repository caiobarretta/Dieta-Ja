package app.model.base;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public abstract class BaseDTO {
	
	private final SimpleIntegerProperty codigo;
    private final SimpleStringProperty nome;
    private final SimpleStringProperty obs;

	public BaseDTO(int codigo, String nome, String obs) {
		// TODO Auto-generated constructor stub
		this.codigo = new SimpleIntegerProperty(codigo);
		this.nome = new SimpleStringProperty(nome);
		this.obs = new SimpleStringProperty(obs);
	}
	
	public int getCodigo() {
		return codigo.get();
	}

	public void setCodigo(int codigo) {
		this.codigo.set(codigo);
	}

	public String getNome() {
		return nome.get();
	}

	public void setNome(String nome) {
		this.nome.set(nome);
	}

	public String getObs() {
		return obs.get();
	}

	public void setObs(String obs) {
		this.obs.set(obs);
	}
	
	@Override
	public String toString() {
		return String.format("%d-%s", this.getCodigo(), this.getNome());
	}
}
