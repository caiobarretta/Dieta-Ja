package app.controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import app.controller.base.DefaultControllerTemplateMethod;
import app.controller.helper.AlertHelper;
import app.controller.helper.GridPaneHelper;
import app.model.PorcaoDeAlimentoDTO;
import app.view.component.MultiSelectionCombo;

import core.entities.DiaDaSemanaEnum;
import core.entities.Dieta;
import core.entities.PorcaoDeAlimento;
import core.entities.RefeicaoEnum;
import core.entities.TipoMedidaEnum;
import core.entities.Usuario;
import core.interfaces.service.IDietaService;
import core.interfaces.service.IPorcaoDeAlimentoService;
import core.ioc.Container;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import services.DietaService;
import services.PorcaoDeAlimentoService;

public class FXMLPorcaoDeAlimentosController extends DefaultControllerTemplateMethod<PorcaoDeAlimentoDTO, PorcaoDeAlimento> {
	
	@FXML
	private GridPane gpDiasDaSemana;
	@FXML
	private GridPane gpRefeicao;
	@FXML
	private TextField txtQuantidade;
	@FXML
	private ChoiceBox<String> cbxTipoMedida;
	@FXML
	private ChoiceBox<Dieta> cbxDieta;
	
	private MultiSelectionCombo cbxDiasDaSemana;
	private MultiSelectionCombo cbxRefeicao;
	final PorcaoDeAlimentoService service;
	final DietaService dietaService;
	
	public FXMLPorcaoDeAlimentosController(Container container, Usuario usuario){
		super(container, usuario);
		service = (PorcaoDeAlimentoService)super.getContainer().resolve(IPorcaoDeAlimentoService.class);
		dietaService = (DietaService)super.getContainer().resolve(IDietaService.class);
	}
	
	private void carregarDiasDaSemana(){
		cbxDiasDaSemana = new MultiSelectionCombo("Dias Da Semana:", "[Vazio]", DiaDaSemanaEnum.asListOrderedById());
    	GridPaneHelper.loadGridPane(gpDiasDaSemana, cbxDiasDaSemana.build(), 200, 100);
	}
	
	private void carregarRefeicoes(){
		cbxRefeicao = new MultiSelectionCombo("Refeições:", "[Vazio]", RefeicaoEnum.asListOrderedById());
		GridPaneHelper.loadGridPane(gpRefeicao, cbxRefeicao.build(), 200, 100);
	}
	
	private void carregarTipoMedida(){
		List<String> lstTipoMedida = TipoMedidaEnum.asListOrderedById();
		cbxTipoMedida.setItems(FXCollections.observableArrayList(lstTipoMedida));
	}
	
	private void carregarDieta(){
		List<Dieta> dietas = dietaService.get(0, 1000);
		cbxDieta.setItems(FXCollections.observableArrayList(dietas));
	}
	
	
	private void recarregarDiasDaSemana(){
		cbxDiasDaSemana = new MultiSelectionCombo("Dias Da Semana:", "[Vazio]", DiaDaSemanaEnum.asListOrderedById());
    	GridPaneHelper.reloadGridPane(gpDiasDaSemana, cbxDiasDaSemana.build(), 200, 100);
	}
	
	private void recarregaRefeicoes(){
		cbxRefeicao = new MultiSelectionCombo("Refeições:", "[Vazio]", RefeicaoEnum.asListOrderedById());
		GridPaneHelper.reloadGridPane(gpRefeicao, cbxRefeicao.build(), 200, 100);
	}
	
	
	private void recarregarDiasDaSemana(List<String> diasDaSemana){
		cbxDiasDaSemana = new MultiSelectionCombo("Dias Da Semana:", "[Vazio]", DiaDaSemanaEnum.asListOrderedById());
    	GridPaneHelper.reloadGridPane(gpDiasDaSemana, cbxDiasDaSemana.build(diasDaSemana), 200, 100);
	}
	
	private void recarregarRefeicoes(List<String> refeicoes){
		cbxRefeicao = new MultiSelectionCombo("Refeições:", "[Vazio]", RefeicaoEnum.asListOrderedById());
		GridPaneHelper.reloadGridPane(gpRefeicao, cbxRefeicao.build(refeicoes), 200, 100);
	}
	
	private void recarregarTipoMedida(TipoMedidaEnum tipoMedida){
		String enumSelected = TipoMedidaEnum.retornaNomeEnumPeloId(TipoMedidaEnum.retornaIdPeloEnum(tipoMedida));
		cbxTipoMedida.setValue(enumSelected);
	}
	
	private void recarregarDieta(Integer id){
		List<Dieta> dietas = dietaService.get(0, 1000);
		Dieta dLoad = null;
		for (Dieta d : dietas) {
			if(id == d.getID()){
				dLoad = d;
			}
		}
		cbxDieta.setItems(FXCollections.observableArrayList(dietas));
		cbxDieta.setValue(dLoad);
	}
	
	@Override
	protected ObservableList<PorcaoDeAlimentoDTO> tableViewSource() { 
		List<PorcaoDeAlimentoDTO> lstDTO = new ArrayList<PorcaoDeAlimentoDTO>();
		List<PorcaoDeAlimento> lst = service.get(0, 100);
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
		carregarDiasDaSemana();
		carregarRefeicoes();
		carregarTipoMedida();
		carregarDieta();
		super.loadTableView();
	}

	@Override
	protected void actionEdit() {
		PorcaoDeAlimento porc = (PorcaoDeAlimento)super.getEntity();
		
		Integer idDieta = service.retornaIdDaDietaPeloIdPorcaoDeAlimento(porc.getID()).get(0);
		List<String> diasDaSemana = service.retornaDiasDaSemanaPeloIdPorcaoDeAlimento(porc.getID());
		List<String> refeicoes = service.retornaRefeicaoPeloIdPorcaoDeAlimento(porc.getID());
		
		lblIdEdit.setText(porc.getID().toString());
		txtNome.setText(porc.getNome());
		txtQuantidade.setText(porc.getQuantidade().toString());
		recarregarDiasDaSemana(diasDaSemana);
		recarregarRefeicoes(refeicoes);
		recarregarTipoMedida(porc.getTipoMedida());
		recarregarDieta(idDieta);
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
		
		Dieta dieta =  (Dieta)cbxDieta.getSelectionModel().getSelectedItem();
		
		try{
			service.associarPorcaoDeAlimentoDiasDaSemanaDietaRefeicao(id, listDiaDaSemana, listIdRefeicao, dieta.getID());
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
		carregarDieta();
		carregarTipoMedida();
		txtQuantidade.setText("");
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
		Dieta dieta =  (Dieta)cbxDieta.getSelectionModel().getSelectedItem();
		String tipoMedida =  cbxTipoMedida.getSelectionModel().getSelectedItem();
		if(dieta == null){
			lstCamposInvalidos.add("Dieta");
			return false;
		}
		if(tipoMedida == null){
			lstCamposInvalidos.add("Tipo Medida");
			return false;
		}
		return true;
	}

	@Override
	protected void loadFieldsEntity(PorcaoDeAlimento entity) {
		int quantidade;
		try {
			quantidade = Integer.parseInt(txtQuantidade.getText());
		}
		catch (NumberFormatException e)
		{
			AlertHelper.buildAlert(AlertType.ERROR, "Salvar", "O valor do campo quantidade deve ser um número inteiro válido.").showAndWait();
			return;
		}
		entity.setQuantidade(quantidade);
		String tipoMedida =  cbxTipoMedida.getSelectionModel().getSelectedItem();
		entity.setTipoMedida(TipoMedidaEnum.retornaEnumPeloNome(tipoMedida));
	}

}
