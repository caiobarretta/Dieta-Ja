package app.view.component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javafx.scene.control.CheckMenuItem;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuButton;
import javafx.scene.layout.BorderPane;

public class MultiSelectionCombo {
	
	final MenuButton choices;
	final String sMenuTextStart;
    final String sMenuTextEmpty;
    final String sMenuTextInitial;
    final List<Object> itemsSource = new ArrayList<Object>();
    final ListView<String> selectedItems = new ListView<>();
    final List<CheckMenuItem> items = new ArrayList<CheckMenuItem>();
    final List<Object> selectedItemsSource = new ArrayList<Object>();
    final Map<String, Object> mapSelectedItemsSource = new HashMap<>();
    final Map<String, CheckMenuItem> mapObjectSelectedItemsSource = new HashMap<>();
    
	
	public MultiSelectionCombo(String startText, String emptyText, List lstObj) {
		sMenuTextStart = startText;
		sMenuTextEmpty = emptyText;
		sMenuTextInitial = startText + " " + emptyText;
		choices = new MenuButton(sMenuTextInitial);
		for (Object item : lstObj) {
			CheckMenuItem checkMenuItem = new CheckMenuItem(item.toString());
			items.add(checkMenuItem);
			itemsSource.add(item);
			mapSelectedItemsSource.put(item.toString(), item);
			mapObjectSelectedItemsSource.put(item.toString(), checkMenuItem);
		}
	}
	
	public BorderPane build(){
        setupList();
        return setupBorderPane();
	}
	
	public BorderPane build(final List lstObj){
		setupList();
      //Procura objeto no menu
        for (final Object item : lstObj) {
        	CheckMenuItem checkMenuItem = mapObjectSelectedItemsSource.get(item.toString());
        	//Caso encontrado
        	if(checkMenuItem != null){
        		selectedItems.getItems().add(checkMenuItem.getText());
        		checkMenuItem.setSelected(true);
        	}
        	setupMenuText();
        }
        
        return setupBorderPane();
	}

	private void setupList() {
		choices.getItems().addAll(items);
		for (final CheckMenuItem item : items) {
            item.selectedProperty().addListener((observableValue, oldValue, newValue) -> {
                if (newValue) {
                	if(!selectedItems.getItems().contains(item.getText())){
                		selectedItems.getItems().add(item.getText());
                        selectedItemsSource.add(mapSelectedItemsSource.get(item.getText()));
                	}
                } else {
                    selectedItems.getItems().remove(item.getText());
                    selectedItemsSource.remove(mapSelectedItemsSource.get(item.getText()));
                }
                setupMenuText();
            });
        }
	}

	private void setupMenuText() {
		String sMenuText = sMenuTextStart + (selectedItems.getItems().size()>0?"":sMenuTextEmpty);
		choices.setText(sMenuText+String.join(", ", selectedItems.getItems()));
	}
	
	private BorderPane setupBorderPane(){
		BorderPane borderPane = new BorderPane();
        borderPane.setTop(choices);
        borderPane.setCenter(selectedItems);
        return borderPane;
	}
	
	public ListView<String> getSelectedItems(){
		return selectedItems;
	}
	
	public List<String> getSelectedItemsAsString(){
		List<String> lst = new ArrayList<String>();
		for (String item : selectedItems.getItems()) {
			lst.add(item);
		}
		return lst;
	}
	
	public List<Object> getSelectedItemsSource(){
		return selectedItemsSource;
	}

}
