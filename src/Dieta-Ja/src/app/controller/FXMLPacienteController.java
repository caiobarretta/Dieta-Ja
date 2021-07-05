package app.controller;

import app.controller.base.DefaultController;
import app.model.PacienteDTO;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;

public class FXMLPacienteController extends DefaultController<PacienteDTO>{

	@FXML
	protected TextField txtUsuario;
	@FXML
	protected TextField txtSenha;
	@FXML
	protected ChoiceBox<PacienteDTO> cbxDieta;
	@FXML
	protected TableColumn<PacienteDTO, Integer> usuarioCol;
	@FXML
	protected TableColumn<PacienteDTO, Integer> dietaCol;
	
	@Override
	protected ObservableList<PacienteDTO> tableViewSource() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected ObservableList<PacienteDTO> getSourceSearch(String search) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected void actionEdit(PacienteDTO dto) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void actionDelete(PacienteDTO dto) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void actionSave() {
		// TODO Auto-generated method stub	
	}

}
