package app.controller;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;

import app.Main;
import app.controller.base.BaseController;
import core.entities.Dieta;
import core.entities.PorcaoDeAlimento;
import core.entities.TipoUsuarioEnum;
import core.entities.Usuario;
import core.interfaces.service.IDietaService;
import core.interfaces.service.IPorcaoDeAlimentoService;
import core.interfaces.service.IUsuarioService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import services.DietaService;
import services.PorcaoDeAlimentoService;
import services.UsuarioService;

public class FXMLPrincipalController extends BaseController{
	
	@FXML
	TreeView<String> treeView;
	
	@FXML
	DatePicker dtRegistro;
	
	@FXML
	TextArea txtAreaComment;
	
	@FXML
	Pane panelRegistroAtividade;
	
	@FXML
	Pane panelDieta;
	
	@FXML
    private Label lblUsuario;
	@FXML
    private Label lblDieta;
	
	final UsuarioService usuarioService;
	final PorcaoDeAlimentoService porcaoDeAlimentoService;
	final DietaService dietaService;
	
	final Timer timer;
	
	public FXMLPrincipalController(){
		timer = new Timer();
		dietaService = (DietaService)super.getContainer().resolveSingleton(IDietaService.class);
		usuarioService = (UsuarioService)super.getContainer().resolveSingleton(IUsuarioService.class);
		porcaoDeAlimentoService = (PorcaoDeAlimentoService)super.getContainer().resolveSingleton(IPorcaoDeAlimentoService.class);
	}
	
	public void carregaPorcaoDeAlimentoTreeView(Integer idDieta){
		
		System.out.println("carregaPorcaoDeAlimentoTreeView");
		List<Integer>  lstIdPorcaoAlimento = dietaService.retornaPorcaoDeAlimentoPeloIdDieta(idDieta);
		TreeItem<String> root = new TreeItem<>("Porções de alimento");
		
		for (Integer idPorc : lstIdPorcaoAlimento) {
			PorcaoDeAlimento porc = porcaoDeAlimentoService.get(idPorc);
			TreeItem<String> node = new TreeItem<>(porc.getNome());
			root.getChildren().add(node);
		}
		
		treeView.setRoot(root);
	}
	
	@FXML
	public void clickPorcaoAlimento(ActionEvent event) throws Exception{
		Stage stage = new Stage();
	    Parent root = FXMLLoader.load(getClass().getResource("../view/FXMLPorcaoDeAlimentos.fxml"));
	    stage.setScene(new Scene(root));
	    stage.setTitle("Porção Alimento");
	    stage.initModality(Modality.WINDOW_MODAL);
	    stage.initOwner(((Node)event.getSource()).getScene().getWindow());
	    stage.show();
	}
	
	@FXML
	public void clickDieta(ActionEvent event) throws IOException{
		Stage stage = new Stage();
	    Parent root = FXMLLoader.load(getClass().getResource("../view/FXMLDieta.fxml"));
		stage.setScene(new Scene(root));
	    stage.setTitle("Dieta");
	    stage.initModality(Modality.WINDOW_MODAL);
	    stage.initOwner(((Node)event.getSource()).getScene().getWindow());
	    stage.show();
	}
	
	@FXML
	public void clickPaciente(ActionEvent event) throws Exception{
		Stage stage = new Stage();
	    Parent root = FXMLLoader.load(getClass().getResource("../view/FXMLPaciente.fxml"));
		stage.setScene(new Scene(root));
	    stage.setTitle("Pacientes");
	    stage.initModality(Modality.WINDOW_MODAL);
	    stage.initOwner(((Node)event.getSource()).getScene().getWindow());
	    stage.show();
	}
	
	
	@Override
    public void initialize() {
		
    }
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		super.initialize(location, resources);
		timer.schedule(taskInitialize, 1000);
		
	}
	

	@FXML
	public void clickRegistro(ActionEvent event){
		if(dtRegistro != null)
			System.out.println(dtRegistro.getValue().toString());
	}
	
	private TimerTask taskInitialize = new TimerTask()
	{
	        public void run()
	        {
	        	Stage stage = (Stage)lblUsuario.getScene().getWindow();
	    		Usuario usuario = (Usuario)stage.getUserData();
	    		System.out.printf("Nome Usuário: ", usuario.getNome());
//	        	lblUsuario.setText(Main.usuarioNome);
//	        	Integer codigoUsuario = Main.codigoUsuario;
//	        	Usuario usuario = usuarioService.get(codigoUsuario);
//	        	
//	    		if(usuario.getTipoUsuario() == TipoUsuarioEnum.Paciente){
//	    			Dieta dieta = dietaService.get(usuario.getDietaID());
//	    			lblDieta.setText(dieta.getNome());
//	    			carregaPorcaoDeAlimentoTreeView(usuario.getDietaID());
//	    		}
//	    		else{
//	    			panelRegistroAtividade.setVisible(false);
//	    			panelDieta.setVisible(false);
//	    		}
	        }

	};
	
}
