package app.controller.helper;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;

public class AlertHelper {

	public static Alert buildAlert(AlertType type, String title, String Content) {
		Alert alert = new Alert(type);
		alert.setTitle(title);
		alert.setContentText(Content);
		return alert;
	}
	
	public static Alert buildAlert(AlertType type, String title, String content, ButtonType btnSim, ButtonType btnNao){
		Alert alert = new Alert(AlertType.WARNING, content, btnSim, btnNao);
		alert.setTitle(title);
		return alert;
	}

}
