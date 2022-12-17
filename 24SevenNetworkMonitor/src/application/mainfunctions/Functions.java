package application.mainfunctions;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;

public class Functions extends HBox {

	private Button addFromFile = new Button("Load");
	private Button clear = new Button("Clear all");
	private Button saveLinks = new Button("Save");
	private Button delete = new Button("Delete");
	private Button changeToDetailed = new Button("Detailed view");

	public Functions() {
		
		setMargin(clear, new Insets(0,0,0,20));
		setMargin(delete, new Insets(0,1100,0,0));
		
		setButtonStyle(clear);
		setButtonStyle(addFromFile);
		setButtonStyle(saveLinks);
		setButtonStyle(delete);
		setButtonStyle(changeToDetailed);
		
		setSpacing(20);
		getChildren().addAll(clear, addFromFile, saveLinks, delete, changeToDetailed);
	}

	public Button getAddFromFileButton() {
		return addFromFile;
	}

	public Button getClearButton() {
		return clear;
	}
	
	public Button getSaveLinksButton() {
		return saveLinks;
	}
	
	public Button getDeleteButton() {
		return delete;
	}
	
	public Button getChangeViewButton() {
		return changeToDetailed;
	}
	
	public void setButtonStyle(Button button) {
		button.setStyle(
				"-fx-background-color: #7289da;"
				+ "-fx-text-fill: #23272a;"
				);
	}
}