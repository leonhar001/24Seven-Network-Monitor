package application.control;

import javafx.scene.control.Button;
import javafx.scene.layout.HBox;

public class ChangeView extends HBox{
	
	private Button changeView = new Button("Change");
	
	public ChangeView() {
		changeView.setStyle(
				"-fx-background-color: #7289da;"
				+ "-fx-text-fill: black;"
				);
		
		getChildren().add(changeView);
	}

	public Button getChangeViewButton() {
		return changeView;
	}
	
}
