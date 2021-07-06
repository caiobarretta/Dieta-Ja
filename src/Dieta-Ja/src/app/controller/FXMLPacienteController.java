package app.controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import app.controller.base.DefaultController;
import app.controller.helper.AlertHelper;
import app.model.DietaDTO;
import app.model.PacienteDTO;
import core.entities.Dieta;
import core.interfaces.service.IDietaService;
import core.interfaces.service.IUsuarioService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import services.DietaService;
import services.UsuarioService;

public class FXMLPacienteController extends DefaultController<PacienteDTO>{

	@FXML
	protected TextField txtUsuario;
	@FXML
	protected TextField txtSenha;
	@FXML
	protected ChoiceBox<Dieta> cbxDieta;
	@FXML
	protected TableColumn<PacienteDTO, Integer> usuarioCol;
	@FXML
	protected TableColumn<PacienteDTO, Integer> dietaCol;
	
	final UsuarioService service;
	final DietaService dietaService;
	public FXMLPacienteController(){
		service = (UsuarioService)super.getContainer().resolveSingleton(IUsuarioService.class);
		dietaService = (DietaService)super.getContainer().resolveSingleton(IDietaService.class);
	}
	
	private void carregaDietas(){
		List<Dieta> dietas = dietaService.get(0, 1000);
		cbxDieta.setItems(FXCollections.observableArrayList(dietas));
	}
	
	private void recarregaDietas(Dieta dieta){
		List<Dieta> dietas = dietaService.get(0, 1000);
		Dieta dLoad = null;
		for (Dieta d : dietas) {
			if(dieta.getID() == d.getID()){
				dLoad = d;
			}
			
		}
		cbxDieta.setItems(FXCollections.observableArrayList(dietas));
		cbxDieta.setValue(dLoad);
	}
	
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
	
	@FXML
	protected void clickSalvar(ActionEvent event) {
		Dieta dieta =  (Dieta)cbxDieta.getSelectionModel().getSelectedItem();
		String message = String.format("Erro ao Salvar os dados: %s", dieta.getNome());
		AlertHelper.buildAlert(AlertType.INFORMATION, "Salvar", message).showAndWait();
    }
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		super.initialize(location, resources);
		Dieta dieta = new Dieta();
		dieta.setID(1);
		recarregaDietas(dieta);
		super.loadTableView();
	}

}
