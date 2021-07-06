package app.controller.base;

import java.util.Optional;

import app.controller.helper.AlertHelper;
import app.enums.FXMLState;
import app.model.base.BaseDTO;
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

public abstract class DefaultController<T extends BaseDTO> extends BaseController {
	
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
	
	@FXML
	protected void clickSalvar(ActionEvent event) {
		actionSave();
    }
	
	@FXML
	protected void clickCancelar(ActionEvent event) {
		Stage stage = (Stage) btnCancelar.getScene().getWindow();
	    stage.close();
    }
	
	@FXML
	protected void clickPesquisar(ActionEvent event){
		ObservableList<T> lstSearch = getSourceSearch(txtPesquisar.getText());
		loadTableView(lstSearch);
	}
	
	
	protected abstract ObservableList<T> tableViewSource();
	protected abstract ObservableList<T> getSourceSearch(String search);
	protected abstract void actionEdit(T dto);
	protected abstract void actionDelete(T dto);
	protected abstract void actionSave();
	
	public DefaultController(){
		super();
	}
	
	protected void loadTableView(){
		codigoCol.setCellValueFactory(new PropertyValueFactory<>("codigo"));
		nomeCol.setCellValueFactory(new PropertyValueFactory<>("nome"));
		obsCol.setCellValueFactory(new PropertyValueFactory<>("obs"));
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
	
	private void configActionButtonEditToTable(String titleButton, TableColumn<T, Void> btnCol) {
        Callback<TableColumn<T, Void>, TableCell<T, Void>> cellFactory = new Callback<TableColumn<T, Void>, TableCell<T, Void>>() {
            @Override
            public TableCell<T, Void> call(final TableColumn<T, Void> param) {
                final TableCell<T, Void> cell = new TableCell<T, Void>() {

                    private final Button btn = new Button(titleButton);
                    {
                    	btn.setOnAction((ActionEvent event) -> {
                    		T selDTO = getTableView().getItems().get(getIndex());
                    		actionEdit(selDTO);
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
}
