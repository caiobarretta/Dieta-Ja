package app.controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import app.controller.base.DefaultController;
import app.controller.helper.AlertHelper;
import app.controller.helper.GridPaneHelper;
import app.enums.FXMLState;
import app.model.DietaDTO;
import app.model.PorcaoDeAlimentoDTO;
import app.view.component.MultiSelectionCombo;

import core.entities.DiaDaSemanaEnum;
import core.entities.Dieta;
import core.entities.PorcaoDeAlimento;
import core.entities.RefeicaoEnum;
import core.entities.Usuario;
import core.interfaces.service.IDietaService;
import core.interfaces.service.IPorcaoDeAlimentoService;
import core.ioc.Container;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.GridPane;
import services.DietaService;
import services.PorcaoDeAlimentoService;

public class FXMLDietaController extends DefaultController<DietaDTO, Dieta> {
	@FXML
	private GridPane gpPorcoesDeAlimento;
	@FXML
	private GridPane gpRefeicao;
	private MultiSelectionCombo cbxPorcaoDeAlimento;
	
	final DietaService dietaService;
	final PorcaoDeAlimentoService porcaoDeAlimentoService;
	public FXMLDietaController(Container container, Usuario usuario) {
		super(container, usuario);
		// TODO Auto-generated constructor stub
		dietaService = (DietaService)super.getContainer().resolveSingleton(IDietaService.class);
		porcaoDeAlimentoService = (PorcaoDeAlimentoService)super.getContainer().resolveSingleton(IPorcaoDeAlimentoService.class);
	}
	
	private void carregarPorcaoDeAlimento(){
		cbxPorcaoDeAlimento = new MultiSelectionCombo("Porção de alimento:", "[Vazio]", porcaoDeAlimentoService.get(0, 100));
    	GridPaneHelper.loadGridPane(gpPorcoesDeAlimento, cbxPorcaoDeAlimento.build(), 200, 100);
	}
	
	private void recarregarPorcaoDeAlimento(List<PorcaoDeAlimento> porcaoDeAlimento){
		cbxPorcaoDeAlimento = new MultiSelectionCombo("Porção de alimento:", "[Vazio]", porcaoDeAlimentoService.get(0, 100));
    	GridPaneHelper.reloadGridPane(gpPorcoesDeAlimento, cbxPorcaoDeAlimento.build(porcaoDeAlimento), 200, 100);
	}
	
	private void recarregarPorcaoDeAlimento(){
		cbxPorcaoDeAlimento = new MultiSelectionCombo("Porção de alimento:", "[Vazio]", porcaoDeAlimentoService.get(0, 100));
    	GridPaneHelper.reloadGridPane(gpPorcoesDeAlimento, cbxPorcaoDeAlimento.build(), 200, 100);
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
		carregarPorcaoDeAlimento();
		super.loadTableView();
	}

	@Override
	protected void actionEdit() {
		Dieta dieta = (Dieta)super.getEntity();
		
		List<Integer> lstPorcaoDeAlimentoId = dietaService.retornaPorcaoDeAlimentoPeloIdDieta(dieta.getID());
		List<PorcaoDeAlimento> lstPorcaoDeAlimento = new ArrayList<PorcaoDeAlimento>();
		
		for (Integer id : lstPorcaoDeAlimentoId) {
			PorcaoDeAlimento porc = porcaoDeAlimentoService.get(id);
			if(porc == null)
				continue;
			lstPorcaoDeAlimento.add(porc);
		}
		
		lblIdEdit.setText(dieta.getID().toString());
		txtNome.setText(dieta.getNome());
		recarregarPorcaoDeAlimento(lstPorcaoDeAlimento);
		txtObs.setText(dieta.getDescricao());
	}

	@Override
	protected void actionDelete(DietaDTO dto) {
		this.clearFXML();
	}

	@Override
	protected void actionSave(Integer id) {
		List<Object> listPorcaoDeAlimentoSelecionada = cbxPorcaoDeAlimento.getSelectedItemsSource();
		List<Integer> listIdPorcaoAlimento = new ArrayList<Integer>();
		
		for (Object porcao : listPorcaoDeAlimentoSelecionada) {
			 PorcaoDeAlimento porc = (PorcaoDeAlimento)porcao;
			 listIdPorcaoAlimento.add(porc.getID());
		}
		
		try{
			porcaoDeAlimentoService.associarPorcaoAlimentoDieta(listIdPorcaoAlimento, id);
		}catch (Exception e) {
			AlertHelper.buildAlert(AlertType.ERROR, "Salvar", String.format("Erro ao Associar os dados: %s", e.getMessage())).showAndWait();
			return;
		}
		AlertHelper.buildAlert(AlertType.INFORMATION, "Salvar", String.format("Dados salvos do código: %d com sucesso!", id)).showAndWait();
		this.clearFXML();
	}
	
	@Override
	protected void clearFXML() {
		super.clearFXML();
		recarregarPorcaoDeAlimento();
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
