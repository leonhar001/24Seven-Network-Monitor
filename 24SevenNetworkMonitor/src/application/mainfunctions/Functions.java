package application.mainfunctions;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;

public class Functions extends HBox {

	private Button addFromFile = new Button("Load");
	private Button clear = new Button("Clear all");
	private Button saveLinks = new Button("Save");
	private Button delete = new Button("Delete");
	private Button changeToDetailed = new Button("Detailed view");
	private Button changeToCardView = new Button("Card view");
	
	public Functions() {
		
		setMargin(changeToDetailed, new Insets(0,0,0,900));
		changeToCardView.setDisable(true);
		setAlignment(Pos.CENTER);
		setButtonStyle(clear,"#7289da");
		setButtonStyle(addFromFile,"#7289da");
		setButtonStyle(saveLinks,"#7289da");
		setButtonStyle(delete,"#7289da");
		setButtonStyle(changeToDetailed,"#5464ac" );
		setButtonStyle(changeToCardView,"#5464ac");
		setSpacing(20);
		getChildren().addAll(clear, addFromFile, saveLinks, delete, changeToDetailed, changeToCardView);
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
	
	public Button getChangeToDetailedButton() {
		return changeToDetailed;
	}
	
	public Button getChangeToCardViewButton() {
		return changeToCardView;
	}
	
	private void setButtonStyle(Button button, String color) {
		button.setStyle(
				"-fx-background-color:"+color+";"
				+"-fx-text-fill: #23272a;"
				);
	}
}