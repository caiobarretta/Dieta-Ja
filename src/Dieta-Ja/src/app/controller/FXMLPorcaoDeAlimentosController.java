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
import app.model.PorcaoDeAlimentoDTO;
import app.view.component.MultiSelectionCombo;

import core.entities.DiaDaSemanaEnum;
import core.entities.PorcaoDeAlimento;
import core.entities.RefeicaoEnum;
import core.interfaces.service.IPorcaoDeAlimentoService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.GridPane;
import services.PorcaoDeAlimentoService;

public class FXMLPorcaoDeAlimentosController extends DefaultController<PorcaoDeAlimentoDTO, PorcaoDeAlimento> {
	
	@FXML
	private GridPane gpDiasDaSemana;
	@FXML
	private GridPane gpRefeicao;
	private MultiSelectionCombo cbxDiasDaSemana;
	private MultiSelectionCombo cbxRefeicao;
	final PorcaoDeAlimentoService service;
	public FXMLPorcaoDeAlimentosController(){
		service = (PorcaoDeAlimentoService)super.getContainer().resolveSingleton(IPorcaoDeAlimentoService.class);
	}
	
	private void carregarDiasDaSemana(){
		cbxDiasDaSemana = new MultiSelectionCombo("Dias Da Semana:", "[Vazio]", DiaDaSemanaEnum.asListOrderedById());
    	GridPaneHelper.loadGridPane(gpDiasDaSemana, cbxDiasDaSemana.build(), 200, 100);
	}
	
	private void recarregarDiasDaSemana(List<String> diasDaSemana){
		cbxDiasDaSemana = new MultiSelectionCombo("Dias Da Semana:", "[Vazio]", DiaDaSemanaEnum.asListOrderedById());
    	GridPaneHelper.reloadGridPane(gpDiasDaSemana, cbxDiasDaSemana.build(diasDaSemana), 200, 100);
	}
	
	private void recarregarDiasDaSemana(){
		cbxDiasDaSemana = new MultiSelectionCombo("Dias Da Semana:", "[Vazio]", DiaDaSemanaEnum.asListOrderedById());
    	GridPaneHelper.reloadGridPane(gpDiasDaSemana, cbxDiasDaSemana.build(), 200, 100);
	}
	
	private void carregaRefeicoes(){
		cbxRefeicao = new MultiSelectionCombo("Refeições:", "[Vazio]", RefeicaoEnum.asListOrderedById());
		GridPaneHelper.loadGridPane(gpRefeicao, cbxRefeicao.build(), 200, 100);
	}
	
	private void recarregaRefeicoes(List<String> refeicoes){
		cbxRefeicao = new MultiSelectionCombo("Refeições:", "[Vazio]", RefeicaoEnum.asListOrderedById());
		GridPaneHelper.reloadGridPane(gpRefeicao, cbxRefeicao.build(refeicoes), 200, 100);
	}
	
	private void recarregaRefeicoes(){
		cbxRefeicao = new MultiSelectionCombo("Refeições:", "[Vazio]", RefeicaoEnum.asListOrderedById());
		GridPaneHelper.reloadGridPane(gpRefeicao, cbxRefeicao.build(), 200, 100);
	}
	
	@Override
	protected ObservableList<PorcaoDeAlimentoDTO> tableViewSource() { 
		List<PorcaoDeAlimentoDTO> lstDTO = new ArrayList<PorcaoDeAlimentoDTO>();
		List<PorcaoDeAlimento> lst = service.get(0, 100);
		ConvertEntidadeEmModelo(lstDTO, lst);
        return FXCollections.observableArrayList(lstDTO);
	}
	
	@FXML
    private void initialize() {
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		super.initialize(location, resources);
		carregarDiasDaSemana();
		carregaRefeicoes();
		super.loadTableView();
	}

	@Override
	protected void actionEdit() {
		PorcaoDeAlimento porc = (PorcaoDeAlimento)super.getEntity();
		
		List<String> diasDaSemana = service.retornaDiasDaSemanaPeloIdPorcaoDeAlimento(porc.getID());
		List<String> refeicoes = service.retornaRefeicaoPeloIdPorcaoDeAlimento(porc.getID());
		
		lblIdEdit.setText(porc.getID().toString());
		txtNome.setText(porc.getNome());
		recarregarDiasDaSemana(diasDaSemana);
		recarregaRefeicoes(refeicoes);
		txtObs.setText(porc.getDescricao());
	}

	@Override
	protected void actionDelete(PorcaoDeAlimentoDTO dto) {
		this.clearFXML();
	}

	@Override
	protected void actionSave(Integer id) {
		List<String> listDiaDaSemanaSelecionado = cbxDiasDaSemana.getSelectedItemsAsString();
		List<String> listRefeicaoSelecionado = cbxRefeicao.getSelectedItemsAsString();
		
		List<Integer> listDiaDaSemana = DiaDaSemanaEnum.convertListStringToListInt(listDiaDaSemanaSelecionado);
		List<Integer> listIdRefeicao = RefeicaoEnum.convertListStringToListInt(listRefeicaoSelecionado);
		
		try{
			service.associarPorcaoAlimentoDiaDaSemana(listDiaDaSemana, id);
			service.associarPorcaoRefeicoes(listIdRefeicao, id);
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
		recarregarDiasDaSemana();
		recarregaRefeicoes();
		super.loadTableView();
	}

	@Override
	protected ObservableList<PorcaoDeAlimentoDTO> getSourceSearch(String search) {
		List<PorcaoDeAlimentoDTO> lstDTO = new ArrayList<PorcaoDeAlimentoDTO>();
		List<PorcaoDeAlimento> lst = service.search(search);
		ConvertEntidadeEmModelo(lstDTO, lst);
        return FXCollections.observableArrayList(lstDTO);
	}

	private void ConvertEntidadeEmModelo(List<PorcaoDeAlimentoDTO> lstDTO, List<PorcaoDeAlimento> lstPorc) {
		for (PorcaoDeAlimento porc : lstPorc) {
			lstDTO.add(ConvertEntidadeEmModelo(porc));
		}
	}
	
	private PorcaoDeAlimentoDTO ConvertEntidadeEmModelo(PorcaoDeAlimento ent) {
			return new PorcaoDeAlimentoDTO(ent.getID(), ent.getNome(), ent.getDescricao());
	}

	@Override
	protected void loadOthersColumnsTableView() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected PorcaoDeAlimento carregaEntidade(PorcaoDeAlimentoDTO dto) {
		List<PorcaoDeAlimentoDTO> lstDTO = new ArrayList<PorcaoDeAlimentoDTO>();
		List<PorcaoDeAlimento> lst = new ArrayList<PorcaoDeAlimento>();
		PorcaoDeAlimento porc = service.get(dto.getCodigo());
		lst.add(porc);
		ConvertEntidadeEmModelo(lstDTO, lst);
		return lst.get(0);
	}

	@Override
	protected PorcaoDeAlimento createNewInstance() {
		return new PorcaoDeAlimento();
	}

	@Override
	protected void entityUpdate(PorcaoDeAlimento entity) {
		service.update(entity);
	}

	@Override
	protected Integer entityAdd(PorcaoDeAlimento entity) {
		service.add(entity);
		return service.getLastIdInserted();
	}

	@Override
	protected void entityDelete(Integer id) {
		service.delete(id);
	}

	@Override
	protected boolean loadFieldsRequiredEntity(PorcaoDeAlimento entity, List<String> lstCamposInvalidos) {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	protected void loadFieldsEntity(PorcaoDeAlimento entity) {
		// TODO Auto-generated method stub
		
	}

}
