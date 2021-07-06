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

public class FXMLPorcaoDeAlimentosController extends DefaultController<PorcaoDeAlimentoDTO> {
	
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
		List<PorcaoDeAlimento> lstPorc = service.get(0, 100);
		for (PorcaoDeAlimento porc : lstPorc) {
			lstDTO.add(new PorcaoDeAlimentoDTO(porc.getID(), porc.getNome(), porc.getDescricao()));
		}
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
	protected void actionEdit(PorcaoDeAlimentoDTO dto) {
		PorcaoDeAlimento porc = service.get(dto.getCodigo());
		if(porc == null){
			AlertHelper.buildAlert(AlertType.INFORMATION, "Erro ao Editar", "A porção de alimento não foi encontrada.").showAndWait();
			return;
		}
		
		super.setState(FXMLState.Editar);
		super.setIdEditing(dto.getCodigo());
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
		try {
			service.delete(dto.getCodigo());
		} catch (Exception e) {
			AlertHelper.buildAlert(AlertType.ERROR, "Salvar", String.format("Erro: %s\n Ao deletar registro:", e.getMessage(), dto.getNome())).showAndWait();
			return;
		}
		AlertHelper.buildAlert(AlertType.CONFIRMATION, "Salvar",String.format("%s deletado com sucesso.", dto.getNome())).showAndWait();
		this.clearFXML();
	}

	@Override
	protected void actionSave() {
		PorcaoDeAlimento porc = new PorcaoDeAlimento();
		porc.setNome(txtNome.getText());
		porc.setDescricao(txtObs.getText());
		
		List<String> listDiaDaSemanaSelecionado = cbxDiasDaSemana.getSelectedItemsAsString();
		List<String> listRefeicaoSelecionado = cbxRefeicao.getSelectedItemsAsString();
		
		List<Integer> listDiaDaSemana = DiaDaSemanaEnum.convertListStringToListInt(listDiaDaSemanaSelecionado);
		List<Integer> listIdRefeicao = RefeicaoEnum.convertListStringToListInt(listRefeicaoSelecionado);
		
		Integer idPorcao = 0;
		if(super.getState() == FXMLState.Editar){
			idPorcao = super.getIdEditing();
			porc.setID(super.getIdEditing());
			try {
				service.update(porc);
			} catch (Exception e) {
				AlertHelper.buildAlert(AlertType.ERROR, "Salvar",String.format("Erro ao Editar os dados: %s", e.getMessage())).showAndWait();
				return;
			}
		}
		else if (super.getState() == FXMLState.Inserir) {			
			try {
				service.add(porc);
				idPorcao = service.getLastIdInserted();
			} catch (Exception e) {
				AlertHelper.buildAlert(AlertType.ERROR, "Salvar", String.format("Erro ao Salvar os dados: %s", e.getMessage())).showAndWait();
				return;
			}
		}
		else{
			AlertHelper.buildAlert(AlertType.ERROR, "Salvar", String.format("Erro de implementação ao salvar os dados.")).showAndWait();
			return;
		}
		
		try{
			service.associarPorcaoAlimentoDiaDaSemana(listDiaDaSemana, idPorcao);
			service.associarPorcaoRefeicoes(listIdRefeicao, idPorcao);
		}catch (Exception e) {
			AlertHelper.buildAlert(AlertType.ERROR, "Salvar", String.format("Erro ao Associar os dados: %s", e.getMessage())).showAndWait();
			return;
		}
		AlertHelper.buildAlert(AlertType.INFORMATION, "Salvar", String.format("Dados salvos do código: %d com sucesso!", idPorcao)).showAndWait();
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
		List<PorcaoDeAlimento> lstPorc = service.search(search);
		for (PorcaoDeAlimento porc : lstPorc) {
			lstDTO.add(new PorcaoDeAlimentoDTO(porc.getID(), porc.getNome(), porc.getDescricao()));
		}
        return FXCollections.observableArrayList(lstDTO);
	}

}
