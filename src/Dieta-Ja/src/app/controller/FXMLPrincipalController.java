package app.controller;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

import app.controller.base.BaseController;
import app.controller.helper.GridPaneHelper;
import app.controller.helper.WindowHelper;
import app.view.component.MultiSelectionCombo;
import core.entities.DiaDaSemanaEnum;
import core.entities.PorcaoDeAlimento;
import core.entities.TipoUsuarioEnum;
import core.entities.Usuario;
import core.interfaces.service.IDietaService;
import core.interfaces.service.IPorcaoDeAlimentoService;
import core.interfaces.service.IUsuarioService;
import core.ioc.Container;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
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
	private GridPane gpPorcaoDeAlimento;
	@FXML
    private Label lblUsuario;
	@FXML
    private Label lblDieta;
	@FXML
	private ImageView imgLogo;
	@FXML
	private ImageView imgPanelDieta;
	
	private MultiSelectionCombo cbxPorcaoDeAlimento; 
	
	private final UsuarioService usuarioService;
	private final PorcaoDeAlimentoService porcaoDeAlimentoService;
	private final DietaService dietaService;
	
	public FXMLPrincipalController(Container container, Usuario usuario){
		super(container, usuario);
		usuarioService = (UsuarioService)super.getContainer().resolveSingleton(IUsuarioService.class);
		porcaoDeAlimentoService = (PorcaoDeAlimentoService)super.getContainer().resolveSingleton(IPorcaoDeAlimentoService.class);
		dietaService = (DietaService)super.getContainer().resolveSingleton(IDietaService.class);
	}
	
	public void carregaPorcaoDeAlimentoForm(Integer idDieta){
		List<Integer>  lstIdPorcaoAlimento = dietaService.retornaPorcaoDeAlimentoPeloIdDieta(idDieta);
		TreeItem<String> root = new TreeItem<String>("Porções de alimento");
		
		List<PorcaoDeAlimento> lstPorcaoAlimento = new ArrayList<PorcaoDeAlimento>();
		for (Integer idPorc : lstIdPorcaoAlimento) {
			PorcaoDeAlimento porc = porcaoDeAlimentoService.get(idPorc);
			if(porc == null)
				continue;
			lstPorcaoAlimento.add(porc);
		}
		List<PorcaoDeAlimento> lstPorcaoAlimentoDistinct = lstPorcaoAlimento.stream().distinct().collect(Collectors.toList());
		cbxPorcaoDeAlimento = new MultiSelectionCombo("Porção de Alimento:", "[Vazio]", lstPorcaoAlimentoDistinct);
		GridPaneHelper.loadGridPane(gpPorcaoDeAlimento, cbxPorcaoDeAlimento.build(), 200, 100);
		
		for (PorcaoDeAlimento porc : lstPorcaoAlimento) {
			TreeItem<String> nodePorcaoDeAlimento = new TreeItem<String>(porc.getNome());
			root.getChildren().add(nodePorcaoDeAlimento);
			List<String>lstDiasDaSemana = porcaoDeAlimentoService.retornaDiaDaSemanaPeloIDPorcaoDeAlimento(porc.getID());
			for (String diaDaSemana : lstDiasDaSemana) {
				TreeItem<String> nodeDiaDaSemana = new TreeItem<String>(diaDaSemana);
				nodePorcaoDeAlimento.getChildren().add(nodeDiaDaSemana);
			}
			List<String> lstRefeicoes = porcaoDeAlimentoService.retornaRefeicaoPeloIdPorcaoDeAlimento(porc.getID());
			for (String refeicao : lstRefeicoes) {
				TreeItem<String> nodeDiaDaSemana = new TreeItem<String>(refeicao);
				nodePorcaoDeAlimento.getChildren().add(nodeDiaDaSemana);
			}
		}
		treeView.setRoot(root);
	}
	
	private List<PorcaoDeAlimento> removePorcaoDeAlimentoRepetida(List<PorcaoDeAlimento> lstPorcaoAlimento) {
		List<PorcaoDeAlimento> aux = new ArrayList<PorcaoDeAlimento>();
		for (PorcaoDeAlimento porcaoDeAlimento : lstPorcaoAlimento) {
			if(!aux.contains(porcaoDeAlimento)){
				aux.add(porcaoDeAlimento);
			}
		}
		return aux;
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
			carregaPorcaoDeAlimentoForm(super.getUsuario().getDietaID());
		}
	}
	

	@FXML
	public void clickRegistro(ActionEvent event){
		if(dtRegistro != null)
			System.out.println(dtRegistro.getValue().toString());
	}
}
