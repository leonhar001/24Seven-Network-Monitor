package application.control;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;

public class TitleBar extends HBox {

	private Button close = new Button("×");
	private Button minimize = new Button("-");
	private Button maximize = new Button("□");

	public TitleBar() {

		setButtonStyle(close, "#23272A");
		setButtonStyle(minimize, "#23272A");
		setButtonStyle(maximize, "#23272A");

		close.setOnMouseEntered( e -> {
			setButtonStyle(close, "#990000");
		});

		close.setOnMouseExited(e -> {
			setButtonStyle(close, "#23272A");
		});

		minimize.setOnMouseEntered( e -> {
			setButtonStyle(minimize, "#b9bbbe");
		});

		minimize.setOnMouseExited(e -> {
			setButtonStyle(minimize, "#23272A");
		});

		maximize.setOnMouseEntered( e -> {
			setButtonStyle(maximize, "#b9bbbe");
		});

		maximize.setOnMouseExited(e -> {
			setButtonStyle(maximize, "#23272A");
		});
		getChildren().addAll(minimize, maximize, close);
	}

	public Button getCloseButton() {
		return close;
	}

	public Button getMinimizeButton() {
		return minimize;
	}

	public Button getMaximizeButton() {
		return maximize;
	}

	private void setButtonStyle(Button button, String color) {
		button.setStyle(
				"-fx-min-width: 45px; " +
						"-fx-min-height: 30px; " +
						"-fx-max-width: 45px; " +
						"-fx-max-height: 30px;"+
						"-fx-background-color:"+color+";"+
						"-fx-text-fill: white;"+
						"-fx-font-size: 16;"+
				"-fx-background-radius: 0");
		button.setAlignment(Pos.TOP_CENTER);
	}
}
