package app.controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import app.controller.base.DefaultControllerTemplateMethod;
import app.controller.helper.AlertHelper;
import app.controller.helper.GridPaneHelper;
import app.model.DietaDTO;
import app.view.component.MultiSelectionCombo;

import core.entities.Dieta;
import core.entities.PorcaoDeAlimento;
import core.entities.Usuario;
import core.interfaces.service.IDietaService;
import core.interfaces.service.IPorcaoDeAlimentoService;
import core.ioc.Container;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.GridPane;
import services.DietaService;
import services.PorcaoDeAlimentoService;

public class FXMLDietaController extends DefaultControllerTemplateMethod<DietaDTO, Dieta> {
	
	final DietaService dietaService;
	public FXMLDietaController(Container container, Usuario usuario) {
		super(container, usuario);
		// TODO Auto-generated constructor stub
		dietaService = (DietaService)super.getContainer().resolve(IDietaService.class);
	}
	
	@Override
	protected ObservableList<DietaDTO> tableViewSource() { 
		List<DietaDTO> lstDTO = new ArrayList<DietaDTO>();
		List<Dieta> lst = dietaService.get(0, 100);
		ConvertEntidadeEmModelo(lstDTO, lst);
        return FXCollections.observableArrayList(lstDTO);
	}
	
	@Override
    public void initialize() {
		super.initialize();
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		super.initialize(location, resources);
		super.loadTableView();
	}

	@Override
	protected void actionEdit() {
		Dieta dieta = (Dieta)super.getEntity();
		
		lblIdEdit.setText(dieta.getID().toString());
		txtNome.setText(dieta.getNome());
		txtObs.setText(dieta.getDescricao());
	}

	@Override
	protected void actionDelete(DietaDTO dto) {
		this.clearFXML();
	}

	@Override
	protected void actionSave(Integer id) {
		AlertHelper.buildAlert(AlertType.INFORMATION, "Salvar", String.format("Dados salvos do código: %d com sucesso!", id)).showAndWait();
		this.clearFXML();
	}
	
	@Override
	protected void clearFXML() {
		super.clearFXML();
		super.loadTableView();
	}

	@Override
	protected ObservableList<DietaDTO> getSourceSearch(String search) {
		List<DietaDTO> lstDTO = new ArrayList<DietaDTO>();
		List<Dieta> lst = dietaService.search(search);
		ConvertEntidadeEmModelo(lstDTO, lst);
        return FXCollections.observableArrayList(lstDTO);
	}

	private void ConvertEntidadeEmModelo(List<DietaDTO> lstDTO, List<Dieta> lstPorc) {
		for (Dieta dieta : lstPorc) {
			lstDTO.add(ConvertEntidadeEmModelo(dieta));
		}
	}
	
	private DietaDTO ConvertEntidadeEmModelo(Dieta ent) {
			return new DietaDTO(ent.getID(), ent.getNome(), ent.getDescricao());
	}

	@Override
	protected void loadOthersColumnsTableView() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected Dieta carregaEntidade(DietaDTO dto) {
		List<DietaDTO> lstDTO = new ArrayList<DietaDTO>();
		List<Dieta> lst = new ArrayList<Dieta>();
		Dieta dieta = dietaService.get(dto.getCodigo());
		lst.add(dieta);
		ConvertEntidadeEmModelo(lstDTO, lst);
		return lst.get(0);
	}

	@Override
	protected Dieta createNewInstance() {
		return new Dieta();
	}

	@Override
	protected void entityUpdate(Dieta entity) {
		dietaService.update(entity);
	}

	@Override
	protected Integer entityAdd(Dieta entity) {
		dietaService.add(entity);
		return dietaService.getLastIdInserted();
	}

	@Override
	protected void entityDelete(Integer id) {
		dietaService.delete(id);
	}

	@Override
	protected boolean loadFieldsRequiredEntity(Dieta entity, List<String> lstCamposInvalidos) {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	protected void loadFieldsEntity(Dieta entity) {
		// TODO Auto-generated method stub
	}
}
