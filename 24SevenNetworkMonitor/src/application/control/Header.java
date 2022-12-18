package application.control;

import application.mainfunctions.Functions;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.TextAlignment;

public class Header extends VBox{

	private static Color backgroundColor = Color.web("#23272A");
	
	private static BackgroundFill fillTop = new BackgroundFill(
			backgroundColor, null, Insets.EMPTY);
	
	private Double linkEnteringPad = 10.0;
	private static Integer centerSize = 400;
	
	private Button addTarget = new Button("Submit");
	private ProgressBar progressBar = new ProgressBar();
	private Functions functions = new Functions();
	private TextField linkEntering = new TextField();
	private TextField nameEntering = new TextField();
	private Button ping = new Button("Ping");
	
	private TitleBar titleBar = new TitleBar();
	
	public Header() {

		setMinHeight(220);
		setMaxHeight(220);
		setBackground(new Background(fillTop));
		
		linkEntering.setPromptText("Enter a link");
		linkEntering.setMinSize(200, 35);
		linkEntering.setMaxSize(200, 35);
		setTextFieldStyle(linkEntering);
		
		nameEntering.setPromptText("Enter a name");
		nameEntering.setMinSize(200, 35);
		nameEntering.setMaxSize(200, 35);
		nameEntering.setAlignment(Pos.CENTER_LEFT);
		setTextFieldStyle(nameEntering);
		
		addTarget.setMinSize(80, 35);
		addTarget.setMaxSize(80,35);
		setHeadButtonsStyle(addTarget);

		ping.setMinSize(80, 20);
		setHeadButtonsStyle(ping);
		ping.setTextAlignment(TextAlignment.CENTER);
		
		progressBar.setMinSize(centerSize, 20);
		setProgressBarStyle(progressBar);
		titleBar.setAlignment(Pos.TOP_RIGHT);
		
		HBox titleBarCont = new HBox();
		titleBarCont.getChildren().addAll(titleBar);
		titleBarCont.setAlignment(Pos.TOP_RIGHT);
		
		HBox nameEnteringCont = new HBox();
		nameEnteringCont.getChildren().addAll(nameEntering);
		nameEnteringCont.setAlignment(Pos.CENTER);
		nameEnteringCont.setPadding(new Insets(0, addTarget.getMaxWidth()+linkEnteringPad, 0, 0));
		
		HBox linkEnteringCont = new HBox();
		linkEnteringCont.getChildren().addAll(linkEntering, addTarget);
		linkEnteringCont.setSpacing(linkEnteringPad);
		linkEnteringCont.setAlignment(Pos.CENTER);
		
		HBox pingProgressCont = new HBox();
		pingProgressCont.getChildren().addAll(progressBar);
		pingProgressCont.setAlignment(Pos.CENTER);
		pingProgressCont.setPadding(new Insets(15,0,0,0));
		setSpacing(5);
		
		HBox pingButtonCont = new HBox();
		pingButtonCont.getChildren().addAll(ping);
		pingButtonCont.setAlignment(Pos.CENTER);
		
		getChildren().addAll(titleBarCont, nameEnteringCont, linkEnteringCont, pingProgressCont,
				pingButtonCont, functions);
	}

	public void clearLabel() {
		linkEntering.clear();
		nameEntering.clear();
	}

	public Button getAddTargetButton() {
		return addTarget;
	}

	public ProgressBar getBar() {
		return progressBar;
	}
	public Functions getFunctions() {
		return functions;
	}

	public Button getCloseButton() {
		return titleBar.getCloseButton();
	}

	public String getLink() {
		return linkEntering.getText();
	}
	
	public String getName() {
		return nameEntering.getText();
	}
	
	public Button getMinimizeButton() {
		return titleBar.getMinimizeButton();
	}
	public Button getMaximizeButton() {
		return titleBar.getMaximizeButton();
	}
	
	public Button getPingButton() {
		return ping;
	}
	private void setTextFieldStyle(TextField textField) {
		textField.setStyle("-fx-background-color: #7289DA;"
				+ "-fx-font-size: 15;"
				+ "-fx-prompt-text-fill: #99aab5;"
				+ "-fx-text-fill: #23272a;");
	}
	
	private void setHeadButtonsStyle(Button button) {
		button.setStyle("-fx-background-color: #6A5ACD;"
				+"-fx-text-fill: #23272a;");
	}
	
	private void setProgressBarStyle(ProgressBar progressBar) {
		progressBar.setStyle("-fx-control-inner-background: #6A5ACD;"
				+"-fx-control-background: transparent;"
				+ "-fx-accent: #57f287");
	}
}
