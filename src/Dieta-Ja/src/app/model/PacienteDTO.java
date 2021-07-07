package app.model;

import app.model.base.BaseDTO;
import javafx.beans.property.SimpleStringProperty;

public class PacienteDTO extends BaseDTO {

	
    private final SimpleStringProperty usuario;
    private final SimpleStringProperty dieta;
	public PacienteDTO(int codigo, String nome, String obs, String dieta, String usuario) {
		super(codigo, nome, obs);
		this.usuario = new SimpleStringProperty(usuario);
		this.dieta  = new SimpleStringProperty(dieta);
	}
	public String getUsuario() {
		return usuario.get();
	}
	public void setUsuario(String usuario) {
		this.usuario.set(usuario);
	}
	public String getDieta() {
		return dieta.get();
	}
	public void setDieta(String dieta) {
		this.dieta.set(dieta);
	}
	
	
}
