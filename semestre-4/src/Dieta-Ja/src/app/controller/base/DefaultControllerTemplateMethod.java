package app.controller.base;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import app.controller.helper.AlertHelper;
import app.enums.FXMLState;
import app.model.base.BaseDTO;
import core.entities.PorcaoDeAlimento;
import core.entities.Usuario;
import core.entities.base.Entity;
import core.ioc.Container;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.util.Callback;

public abstract class DefaultControllerTemplateMethod<T extends BaseDTO, TEntity extends Entity> extends BaseController {
	
	private FXMLState state;
	private Integer idEditing;
	
	public DefaultControllerTemplateMethod(Container container, Usuario usuario) {
		super(container, usuario);
		state = FXMLState.Inserir;
		idEditing = 0;
	}

	@FXML
	protected Label lblIdEdit;
	@FXML
	protected TextField txtPesquisar;
	@FXML
	protected TextField txtNome;
	@FXML
	protected TextArea txtObs;
	@FXML
	protected TableView<T> tableView;
	@FXML
	protected TableColumn<T, Integer> codigoCol;
	@FXML
	protected TableColumn<T, String> nomeCol;
	@FXML
	protected TableColumn<T, String> obsCol;
	@FXML
	protected TableColumn<T, Void> acaoCol;
	@FXML
	protected TableColumn<T, Void> editButtonCol;
	@FXML
	protected TableColumn<T, Void> deleteButtonCol;
	@FXML
	protected Button btnPesquisar;
	@FXML
	protected Button btnCancelar;
	@FXML
	protected Button btnSalvar;
	
	private TEntity entity; 
	
	@FXML
	protected void clickSalvar(ActionEvent event) {
		TEntity entity = createNewInstance();
		entity.setNome(txtNome.getText());
		entity.setDescricao(txtObs.getText());
		loadFieldsEntity(entity);
		List<String> lstCamposInvalidos = new ArrayList<String>();
		boolean camposValidos = loadFieldsRequiredEntity(entity, lstCamposInvalidos);
		if(!camposValidos){
			String mensagemCampos = String.format("Existem campos obrigatórios não preenchidos:%s", String.join("\n", lstCamposInvalidos));
			AlertHelper.buildAlert(AlertType.ERROR, "Salvar", mensagemCampos).showAndWait();
			return;
		}
		Integer id = 0;
		if(this.getState() == FXMLState.Editar){
			id = this.getIdEditing();
			entity.setID(this.getIdEditing());
			try {
				entityUpdate(entity);
			} catch (Exception e) {
				AlertHelper.buildAlert(AlertType.ERROR, "Salvar",String.format("Erro ao Editar os dados: %s", e.getMessage())).showAndWait();
				return;
			}
		}
		else if (this.getState() == FXMLState.Inserir) {			
			try {
				id = entityAdd(entity);
			} catch (Exception e) {
				AlertHelper.buildAlert(AlertType.ERROR, "Salvar", String.format("Erro ao Salvar os dados: %s", e.getMessage())).showAndWait();
				return;
			}
		}
		else{
			AlertHelper.buildAlert(AlertType.ERROR, "Salvar", String.format("Erro de implementação ao salvar os dados.")).showAndWait();
			return;
		}
		actionSave(id);
    }
	
	@FXML
	protected void clickCancelar(ActionEvent event) {
		Stage stage = (Stage)btnCancelar.getScene().getWindow();
	    stage.close();
    }
	
	@FXML
	protected void clickPesquisar(ActionEvent event){
		ObservableList<T> lstSearch = getSourceSearch(txtPesquisar.getText());
		loadTableView(lstSearch);
	}
	
	
	protected abstract ObservableList<T> tableViewSource();
	protected abstract ObservableList<T> getSourceSearch(String search);
	protected abstract TEntity carregaEntidade(T dto);
	protected abstract void loadOthersColumnsTableView();
	protected abstract void actionEdit();
	protected abstract void actionDelete(T dto);
	protected abstract void actionSave(Integer id);
	protected abstract TEntity createNewInstance();
	protected abstract void entityUpdate(TEntity entity);
	protected abstract Integer entityAdd(TEntity entity);
	protected abstract void entityDelete(Integer id);
	protected abstract void loadFieldsEntity(TEntity entity);
	protected abstract boolean loadFieldsRequiredEntity(TEntity entity, List<String> lstCamposInvalidos);
	
	
	
	protected void loadTableView(){
		codigoCol.setCellValueFactory(new PropertyValueFactory<>("codigo"));
		nomeCol.setCellValueFactory(new PropertyValueFactory<>("nome"));
		obsCol.setCellValueFactory(new PropertyValueFactory<>("obs"));
		loadOthersColumnsTableView();
		configActionButtonEditToTable("Editar", editButtonCol);
		configActionButtonDeleteToTable("Deletar", deleteButtonCol);
		tableView.setItems(tableViewSource());
	}
	protected void loadTableView(ObservableList<T> lstSearch){
		codigoCol.setCellValueFactory(new PropertyValueFactory<>("codigo"));
		nomeCol.setCellValueFactory(new PropertyValueFactory<>("nome"));
		obsCol.setCellValueFactory(new PropertyValueFactory<>("obs"));
		configActionButtonEditToTable("Editar", editButtonCol);
		configActionButtonDeleteToTable("Deletar", deleteButtonCol);
		tableView.setItems(lstSearch);
	}
	
	
	protected boolean validaEdit(T dto){
		TEntity entity = carregaEntidade(dto);
		if(entity == null){
			AlertHelper.buildAlert(AlertType.INFORMATION, "Erro ao Editar", "O registro não foi encontrado.").showAndWait();
			return false;
		}
		
		this.setEntity(entity);
		this.setState(FXMLState.Editar);
		this.setIdEditing(dto.getCodigo());
		return true;
	}
	
	private void configActionButtonEditToTable(String titleButton, TableColumn<T, Void> btnCol) {
        Callback<TableColumn<T, Void>, TableCell<T, Void>> cellFactory = new Callback<TableColumn<T, Void>, TableCell<T, Void>>() {
            @Override
            public TableCell<T, Void> call(final TableColumn<T, Void> param) {
                final TableCell<T, Void> cell = new TableCell<T, Void>() {

                    private final Button btn = new Button(titleButton);
                    {
                    	btn.setOnAction((ActionEvent event) -> {
                    		T selDTO = getTableView().getItems().get(getIndex());
                    		boolean ehValido = validaEdit(selDTO);
                    		if(ehValido)
                    			actionEdit();
                        });
                    }

                    @Override
                    public void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            setGraphic(btn);
                        }
                    }
                };
                return cell;
            }
        };

        btnCol.setCellFactory(cellFactory);
    }
	
	private void configActionButtonDeleteToTable(String titleButton, TableColumn<T, Void> btnCol) {
        Callback<TableColumn<T, Void>, TableCell<T, Void>> cellFactory = new Callback<TableColumn<T, Void>, TableCell<T, Void>>() {
            @Override
            public TableCell<T, Void> call(final TableColumn<T, Void> param) {
                final TableCell<T, Void> cell = new TableCell<T, Void>() {

                    private final Button btn = new Button(titleButton);
                    {
                    	btn.setOnAction((ActionEvent event) -> {
                    		T selDTO = getTableView().getItems().get(getIndex());
                    		String message = String.format("Deseja realmente deletar o registro: %d", selDTO.getCodigo());
                    		ButtonType btnSim = new ButtonType("Sim", ButtonData.OK_DONE);
                    		ButtonType btnNao = new ButtonType("Não", ButtonData.CANCEL_CLOSE);
                    		Alert alert =  AlertHelper.buildAlert(AlertType.WARNING, message, "Deletar", btnSim, btnNao);
                    		Optional<ButtonType> result = alert.showAndWait();
                    		if (result.orElse(btnNao) == btnSim) {
                    			try {
                    				entityDelete(selDTO.getCodigo());
                    			} catch (Exception e) {
                    				AlertHelper.buildAlert(AlertType.ERROR, "Salvar", String.format("Erro: %s\n Ao deletar registro:", e.getMessage(), selDTO.getNome())).showAndWait();
                    				return;
                    			}
                    			AlertHelper.buildAlert(AlertType.CONFIRMATION, "Salvar",String.format("%s deletado com sucesso.", selDTO.getNome())).showAndWait();
                    			
                    			actionDelete(selDTO);
                    		}
                        });
                    }

                    @Override
                    public void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            setGraphic(btn);
                        }
                    }
                };
                return cell;
            }
        };

        btnCol.setCellFactory(cellFactory);
    }

	protected void clearFXML(){
		txtNome.setText("");
		txtObs.setText("");
		this.setIdEditing(0);
		this.setState(FXMLState.Inserir);
		lblIdEdit.setText("0");
	}

	public TEntity getEntity() {
		return entity;
	}

	public void setEntity(TEntity entity) {
		this.entity = entity;
	}
	
	public FXMLState getState() {
		return state;
	}

	public void setState(FXMLState state) {
		this.state = state;
	}
	
	public Integer getIdEditing() {
		return idEditing;
	}

	public void setIdEditing(Integer idEditing) {
		this.idEditing = idEditing;
	}
}
