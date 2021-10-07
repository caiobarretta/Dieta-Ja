package app.controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import app.controller.base.DefaultControllerTemplateMethod;
import app.controller.helper.AlertHelper;
import app.model.DietaDTO;
import app.model.PacienteDTO;
import app.model.PorcaoDeAlimentoDTO;
import core.entities.Dieta;
import core.entities.PorcaoDeAlimento;
import core.entities.TipoUsuarioEnum;
import core.entities.Usuario;
import core.interfaces.service.IDietaService;
import core.interfaces.service.IUsuarioService;
import core.ioc.Container;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import services.DietaService;
import services.UsuarioService;

public class FXMLPacienteController extends DefaultControllerTemplateMethod<PacienteDTO, Usuario>{

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
	public FXMLPacienteController(Container container, Usuario usuario){
		super(container, usuario);
		service = (UsuarioService)super.getContainer().resolve(IUsuarioService.class);
		dietaService = (DietaService)super.getContainer().resolve(IDietaService.class);
	}
	
	private void carregaDietas(){
		List<Dieta> dietas = dietaService.get(0, 1000);
		cbxDieta.setItems(FXCollections.observableArrayList(dietas));
	}
	
	private void recarregaDietas(Integer id){
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
	protected ObservableList<PacienteDTO> tableViewSource() {
		List<PacienteDTO> lstDTO = new ArrayList<PacienteDTO>();
		List<Usuario> lst = service.get(0, 100);
		for (Usuario item : lst) {
			Integer dietaID = item.getDietaID();
			TipoUsuarioEnum tipoUsuario = item.getTipoUsuario();
			String dieta = null;
			if(dietaID <= 0)
				dieta = "Não Associado";
			if(tipoUsuario == TipoUsuarioEnum.Paciente)
				lstDTO.add(new PacienteDTO(item.getID(), item.getNome(), item.getDescricao(), dieta, item.getLogin()));
		}
        return FXCollections.observableArrayList(lstDTO);
	}

	@Override
	protected ObservableList<PacienteDTO> getSourceSearch(String search) {
		List<PacienteDTO> lstDTO = new ArrayList<PacienteDTO>();
		List<Usuario> lst = service.search(search);
		for (Usuario item : lst) {
			Integer dietaID = item.getDietaID();
			TipoUsuarioEnum tipoUsuario = item.getTipoUsuario();
			String dieta = null;
			if(dietaID <= 0)
				dieta = "Não Associado";
			if(tipoUsuario == TipoUsuarioEnum.Paciente)
				lstDTO.add(new PacienteDTO(item.getID(), item.getNome(), item.getDescricao(), dieta, item.getLogin()));
		}
        return FXCollections.observableArrayList(lstDTO);
	}

	@Override
	protected void actionEdit() {
		Usuario usuario = (Usuario)super.getEntity();
		
		lblIdEdit.setText(usuario.getID().toString());
		txtNome.setText(usuario.getNome());
		recarregaDietas(usuario.getDietaID());
		txtUsuario.setText(usuario.getLogin());
		txtSenha.setText(usuario.getSenha());
		txtObs.setText(usuario.getDescricao());
	}

	@Override
	protected void actionDelete(PacienteDTO dto) {
		this.clearFXML();
	}

	@Override
	protected void actionSave(Integer id) {
		AlertHelper.buildAlert(AlertType.INFORMATION, "Salvar", String.format("Dados salvos do código: %d com sucesso!", id)).showAndWait();
		this.clearFXML();
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		super.initialize(location, resources);
		carregaDietas();
		super.loadTableView();
	}

	@Override
	protected void loadOthersColumnsTableView() {
		usuarioCol.setCellValueFactory(new PropertyValueFactory<>("usuario"));
		dietaCol.setCellValueFactory(new PropertyValueFactory<>("dieta"));
	}

	@Override
	protected Usuario carregaEntidade(PacienteDTO dto) {
		List<PacienteDTO> lstDTO = new ArrayList<PacienteDTO>();
		List<Usuario> lst = new ArrayList<Usuario>();
		Usuario usuario= service.get(dto.getCodigo());
		lst.add(usuario);
		ConvertEntidadeEmModelo(lstDTO, lst);
		return lst.get(0);
	}
	
	private void ConvertEntidadeEmModelo(List<PacienteDTO> lstDTO, List<Usuario> lst) {
		for (Usuario usuario : lst) {
			lstDTO.add(ConvertEntidadeEmModelo(usuario));
		}
	}
	
	private PacienteDTO ConvertEntidadeEmModelo(Usuario ent) {
		return new PacienteDTO(ent.getID(), ent.getNome(), ent.getDescricao(), ent.getDietaID().toString(), ent.getLogin());
}

	@Override
	protected Usuario createNewInstance() {
		return new Usuario();
	}

	@Override
	protected void entityUpdate(Usuario entity) {
		service.update(entity);
	}

	@Override
	protected Integer entityAdd(Usuario entity) {
		service.add(entity);
		return service.getLastIdInserted();
	}

	@Override
	protected void entityDelete(Integer id) {
		service.delete(id);
	}

	@Override
	protected boolean loadFieldsRequiredEntity(Usuario entity, List<String> lstCamposInvalidos) {
		Dieta dieta =  (Dieta)cbxDieta.getSelectionModel().getSelectedItem();
		if(dieta == null){
			lstCamposInvalidos.add("Dieta");
			return false;
		}
		entity.setDietaID(dieta.getID());
		return true;
	}

	@Override
	protected void loadFieldsEntity(Usuario entity) {
		entity.setTipoUsuario(TipoUsuarioEnum.Paciente);
		entity.setLogin(txtUsuario.getText());
		entity.setSenha(txtSenha.getText());
	}
	
	@Override
	protected void clearFXML() {
		super.clearFXML();
		txtUsuario.setText("");
		txtSenha.setText("");
		super.loadTableView();
	}

}
