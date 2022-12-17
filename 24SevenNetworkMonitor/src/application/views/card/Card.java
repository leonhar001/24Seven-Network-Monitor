package application.views.card;

import java.io.IOException;
import java.net.InetAddress;

import application.model.Target;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
//import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class Card extends VBox {

	private BackgroundFill fillGray = new BackgroundFill(
			Color.web("#b9bbbe"), CornerRadii.EMPTY, Insets.EMPTY);

	private BackgroundFill fillGreen = new BackgroundFill(
			Color.web("#57f287"), CornerRadii.EMPTY, Insets.EMPTY);

	private BackgroundFill fillRed = new BackgroundFill(
			Color.web("#ed4245"), CornerRadii.EMPTY, Insets.EMPTY);

	@SuppressWarnings("unused")
	private InetAddress inet = null;
	private String nameIdentifier = "";
	private Double xOffset = 0.0;
	private Double yOffset = 0.0;

	public Card(Target target){

		this.nameIdentifier = target.getName();
		setEffect(new DropShadow());
		setPrefSize(200, 80);
		setAlignment(Pos.CENTER);
		setStyle("-fx-border-color: black;");

		Label linkAdress = new Label(target.getLink().toLowerCase());
		linkAdress.setTextFill(Color.BLACK);
		linkAdress.setStyle("-fx-text-fill: #32210f;"
				+ "-fx-font-size: 12;"
				+ "-fx-font-family: \"Verdana\";");

		Label linkName = new Label(target.getName().toUpperCase());
		linkName.setStyle("-fx-text-fill: #202225;"
				+ "-fx-font-size: 16;"
				+ "-fx-font-family: \"Verdana\";"
				+ "-fx-font-weight: bold");

		setBackground(new Background(fillGray));

		setOnMouseClicked(e -> {
			if(target.isSelected()) {
				setOpacity(1.0);
				target.setSelected(false);
			}
			else {
				setOpacity(0.5);
				target.setSelected(true);
			}
		});

		getChildren().addAll(linkName,linkAdress);
	}

	public void alterBackgroundColor(BackgroundFill fill) {
		setBackground(new Background(fill));
	}

	public void changeBackGroundColor(boolean isOnline) throws IOException {
		if(isOnline)
			alterBackgroundColor(fillGreen);
		else
			alterBackgroundColor(fillRed);
	}
	
	public String getNameIdentifier() {
		return nameIdentifier;
	}

	@SuppressWarnings("unused")
	private void draggable() {
		setOnMousePressed(e -> {
			System.out.println();
			xOffset = e.getSceneX() - getTranslateX();
			yOffset = e.getSceneY() - getTranslateY();
		});

		setOnMouseDragged(e -> {
			setTranslateX(e.getScreenX() - xOffset);
			setTranslateY(e.getScreenY() - yOffset);
		});
	}
}
