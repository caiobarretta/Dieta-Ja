package app.controller;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import app.controller.base.BaseController;
import app.controller.helper.AlertHelper;
import app.controller.helper.GridPaneHelper;
import app.controller.helper.WindowHelper;
import app.model.TreeViewPorcaoDeAlimentoDTO;
import app.view.component.MultiSelectionCombo;
import core.entities.DiaDaSemanaEnum;
import core.entities.Dieta;
import core.entities.PorcaoDeAlimento;
import core.entities.RefeicaoEnum;
import core.entities.RegistroDeAtividade;
import core.entities.SentimentoEnum;
import core.entities.TipoMedidaEnum;
import core.entities.TipoUsuarioEnum;
import core.entities.Usuario;
import core.interfaces.service.IDietaService;
import core.interfaces.service.IPorcaoDeAlimentoService;
import core.interfaces.service.IUsuarioService;
import core.ioc.Container;
import infrastructure.dao.RegistroDeAtividadeDAO;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Window;
import services.DietaService;
import services.PorcaoDeAlimentoService;
import services.UsuarioService;

public class FXMLPrincipalController extends BaseController{
	
	@FXML
	private TreeView<String> treeView;
	@FXML
	private DatePicker dtRegistro;
	@FXML
	private TextArea txtAreaComment;
	@FXML
	private Pane panelRegistroAtividade;
	@FXML
	private Pane panelDieta;
	@FXML
	private Pane panelDietaLogo;
	@FXML
	private GridPane gpPorcaoDeAlimento;
	@FXML
    private Label lblUsuario;
	@FXML
    private Label lblDieta;
	@FXML
	private ImageView imgLogo;
	@FXML
	private ImageView imgPanelDieta;
	@FXML
	private ChoiceBox<String> cbxSentimento;
	@FXML
	private Button btnPorcaoAlimento;
	@FXML
	private Button btnPaciente;
	@FXML
	private Button btnDieta;
	
	
	private MultiSelectionCombo cbxPorcaoDeAlimento;
	private Integer idDieta;
	
	private final UsuarioService usuarioService;
	private final PorcaoDeAlimentoService porcaoDeAlimentoService;
	private final DietaService dietaService;
	
	public Integer getIdDieta() {
		return idDieta;
	}

	public void setIdDieta(Integer idDieta) {
		this.idDieta = idDieta;
	}
	
	public FXMLPrincipalController(Container container, Usuario usuario){
		super(container, usuario);
		usuarioService = (UsuarioService)super.getContainer().resolve(IUsuarioService.class);
		porcaoDeAlimentoService = (PorcaoDeAlimentoService)super.getContainer().resolve(IPorcaoDeAlimentoService.class);
		dietaService = (DietaService)super.getContainer().resolve(IDietaService.class);
	}
	
	public void carregaPorcaoDeAlimentoForm(Integer idDieta){
		List<TreeViewPorcaoDeAlimentoDTO> lstTree = dietaService.retornaPorcaoDeAlimentoPeloIdDieta(idDieta);
		
		Map<String, List<TreeViewPorcaoDeAlimentoDTO>> lstTreeGrouped =
				lstTree.stream().collect(Collectors.groupingBy(w -> w.getPorcaoDeAlimento()));
		
		TreeItem<String> root = new TreeItem<String>("Porções de alimento");
		
		for (Entry<String, List<TreeViewPorcaoDeAlimentoDTO>> tree : lstTreeGrouped.entrySet()) {
			TreeItem<String> nodePorcaoDeAlimento = new TreeItem<String>(tree.getKey());
			root.getChildren().add(nodePorcaoDeAlimento);
			for (TreeViewPorcaoDeAlimentoDTO dto : tree.getValue()) {
				TreeItem<String> nodeDiaDaSemana = new TreeItem<String>(dto.getDiaDaSemana());
				nodePorcaoDeAlimento.getChildren().add(nodeDiaDaSemana);
				
				TreeItem<String> nodeRefeicao = new TreeItem<String>(dto.getRefeicao());
				nodePorcaoDeAlimento.getChildren().add(nodeRefeicao);
			}
		}
		treeView.setRoot(root);
		
		carregarPorcaoDeAlimento();
		carregarSentimento();
	}

	private void carregarPorcaoDeAlimento() {
		List<Integer> lstIdPorcao = dietaService.retornaPorcaoDeAlimentoPeloIdDietaAgrupado(idDieta);
		List<PorcaoDeAlimento> lstPorcaoAlimento = new ArrayList<PorcaoDeAlimento>();
		for (Integer idPorc : lstIdPorcao) {
			lstPorcaoAlimento.add(porcaoDeAlimentoService.get(idPorc));
		}
		
		cbxPorcaoDeAlimento = new MultiSelectionCombo("Porção de Alimento:", "[Vazio]", lstPorcaoAlimento);
		GridPaneHelper.loadGridPane(gpPorcaoDeAlimento, cbxPorcaoDeAlimento.build(), 200, 100);
	}
	
	private void recarregarPorcaoDeAlimento() {
		List<Integer> lstIdPorcao = dietaService.retornaPorcaoDeAlimentoPeloIdDietaAgrupado(idDieta);
		List<PorcaoDeAlimento> lstPorcaoAlimento = new ArrayList<PorcaoDeAlimento>();
		for (Integer idPorc : lstIdPorcao) {
			lstPorcaoAlimento.add(porcaoDeAlimentoService.get(idPorc));
		}
		
		cbxPorcaoDeAlimento = new MultiSelectionCombo("Porção de Alimento:", "[Vazio]", lstPorcaoAlimento);
		GridPaneHelper.reloadGridPane(gpPorcaoDeAlimento, cbxPorcaoDeAlimento.build(), 200, 100);
	}
	
	private void recarregarSentimento(SentimentoEnum sentimento){
		String enumSelected = SentimentoEnum.retornaNomeEnumPeloId(SentimentoEnum.retornaIdPeloEnum(sentimento));
		cbxSentimento.setValue(enumSelected);
	}
	
	private void carregarSentimento(){
		List<String> lstSentimento = SentimentoEnum.asListOrderedById();
		cbxSentimento.setItems(FXCollections.observableArrayList(lstSentimento));
	}

	@FXML
	public void clickPorcaoAlimento(ActionEvent event) throws Exception{
		InputStream is = getClass().getResource("../view/FXMLPorcaoDeAlimentos.fxml").openStream();
		Window window = ((Node)event.getSource()).getScene().getWindow();
		
		FXMLPorcaoDeAlimentosController controller = new FXMLPorcaoDeAlimentosController(super.getContainer(), super.getUsuario());
	    WindowHelper.OpenModal(is, "Porção Alimento", window, controller);
	}
	
	@FXML
	public void clickDieta(ActionEvent event) throws IOException{
		InputStream is = getClass().getResource("../view/FXMLDieta.fxml").openStream();
		Window window = ((Node)event.getSource()).getScene().getWindow();
		
		FXMLDietaController controller = new FXMLDietaController(super.getContainer(), super.getUsuario());
		WindowHelper.OpenModal(is, "Dieta", window, controller);
	}
	
	@FXML
	public void clickPaciente(ActionEvent event) throws Exception{
		InputStream is = getClass().getResource("../view/FXMLPaciente.fxml").openStream();
		Window window = ((Node)event.getSource()).getScene().getWindow();
		FXMLPacienteController controller = new FXMLPacienteController(super.getContainer(), super.getUsuario());
		WindowHelper.OpenModal(is, "Pacientes", window, controller);
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		super.initialize(location, resources);	
		lblUsuario.setText(super.getUsuario().getNome());
		
		Image imageLogo = new Image(getClass().getResource("../resources/dieta-ja-bwh-removebg-preview.png").toExternalForm());
		imgLogo.setImage(imageLogo);
		
		Image imagePanelDieta = new Image(getClass().getResource("../resources/dieta-ja-bwh.png").toExternalForm());
		imgPanelDieta.setImage(imagePanelDieta);
		
		
		if(super.getUsuario().getTipoUsuario() == TipoUsuarioEnum.Paciente){
			panelDietaLogo.setVisible(false);
			panelDieta.setVisible(true);
			this.setIdDieta(super.getUsuario().getDietaID());
			carregaPorcaoDeAlimentoForm(this.getIdDieta());
			
			btnDieta.setVisible(false);
			btnPaciente.setVisible(false);
			btnPorcaoAlimento.setVisible(false);
		}
	}
	

	@FXML
	public void clickRegistro(ActionEvent event){
		if(dtRegistro != null){
			List<RegistroDeAtividade> lst = new RegistroDeAtividadeDAO().getByRegistroEUsuario(dtRegistro.getValue().toString(), super.getUsuario().getID());
			if(!lst.isEmpty()){
				txtAreaComment.setText(lst.get(0).getComentarios());
				recarregarSentimento(SentimentoEnum.retornaEnumPeloId(lst.get(0).getSentimento()));
				
				
				List<Integer> lstIdPorcao = dietaService.retornaPorcaoDeAlimentoPeloIdDietaAgrupado(idDieta);
				List<PorcaoDeAlimento> lstPorcaoAlimento = new ArrayList<PorcaoDeAlimento>();
				List<PorcaoDeAlimento> lstPorcaoAlimentoSelecionada = new ArrayList<PorcaoDeAlimento>();
				for (Integer idPorc : lstIdPorcao) {
					lstPorcaoAlimento.add(porcaoDeAlimentoService.get(idPorc));
				}
				
				for (RegistroDeAtividade reg : lst) {
					lstPorcaoAlimentoSelecionada.add(porcaoDeAlimentoService.get(reg.getPorcaoDeAlimentoID()));
				}
				
				cbxPorcaoDeAlimento = new MultiSelectionCombo("Porção de Alimento:", "[Vazio]", lstPorcaoAlimento);
				GridPaneHelper.reloadGridPane(gpPorcaoDeAlimento, cbxPorcaoDeAlimento.build(lstPorcaoAlimentoSelecionada), 200, 100);
				
			}
			else{
				clearForm();
			}
		}
	}
	
	private void clearForm(){
		recarregarPorcaoDeAlimento();
		txtAreaComment.setText("");
		carregarSentimento();
	}
	
	@FXML
	protected void clickSalvar(ActionEvent event){
		List<Object> lstPorcaoDeAlimentoSelecionada =  cbxPorcaoDeAlimento.getSelectedItemsSource();
		String sentimento =  cbxSentimento.getSelectionModel().getSelectedItem();
		try{
			for (Object porcaoDeAlimento : lstPorcaoDeAlimentoSelecionada) {
				PorcaoDeAlimento porc = (PorcaoDeAlimento)porcaoDeAlimento;
				RegistroDeAtividade registro = new RegistroDeAtividade();
				//ID_Dieta
				registro.setDietaID(this.idDieta);
				//ID_PorcaoDeAlimento
				registro.setPorcaoDeAlimentoID(porc.getID());
				//ID_Usuario
				registro.setUsuarioID(super.getUsuario().getID());
				//Registro
				registro.setRegistro(dtRegistro.getValue().toString());
				//Comentarios
				registro.setComentarios(txtAreaComment.getText());
				//Sentimento
				registro.setSentimento(SentimentoEnum.retornaIdPeloEnum(SentimentoEnum.retornaEnumPeloNome(sentimento)));
				//DiaDaSemana
				registro.setDiaDaSemanaID(DiaDaSemanaEnum.retornaIdPeloNome(getWeekDayPtBr(dtRegistro.getValue().toString())));
				
				RegistroDeAtividadeDAO registroDAO = new RegistroDeAtividadeDAO();
				registroDAO.Add(registro);
			}
		AlertHelper.buildAlert(AlertType.INFORMATION, "Salvar", "Dados salvos com sucesso!").showAndWait();
		}
		catch(Exception ex){
			AlertHelper.buildAlert(AlertType.ERROR, "Erro", "Erro ao salvar os dados").showAndWait();
		}
		
	}
	
	public String getWeekDayPtBr(String date){ //2021-07-12
		// TODO Auto-generated method stub
		SimpleDateFormat format1=new SimpleDateFormat("yyyy-MM-dd");
		try {
			Date dt1 = format1.parse(date);
			DateFormat format2=new SimpleDateFormat("EEEE"); 
			String finalDay=format2.format(dt1);
			return finalDay;
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
		
    }
	
	@FXML
	protected void clickCancelar(ActionEvent event) {
	}
}
