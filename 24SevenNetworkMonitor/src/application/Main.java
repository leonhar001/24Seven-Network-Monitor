package application;

import java.io.File;
import java.util.List;

import application.control.Header;
import application.control.TargetsController;
import application.mainfunctions.LinksFromFile;
import application.model.Target;
import application.views.card.Cards;
import application.views.card.CardsContainer;
import application.views.detailed.DetailedView;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.concurrent.ScheduledService;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import services.Scheduler;

//TODO watch: https://youtu.be/dw8se4uA1qc
public class Main extends Application {

	private Double xOffset = 0.0;
	private Double yOffset = 0.0;

	BorderPane main = new BorderPane();

	Cards cards = new Cards();

	DetailedView detailedView = new DetailedView();

	CardsContainer scrollPane = new CardsContainer(cards);

	Header header = new Header();

	FileChooser fileChooser = new FileChooser();

	LinksFromFile lff = new LinksFromFile();

	TargetsController targetController = new TargetsController(cards, detailedView);

	ScheduledService<Void> scheduledService = new Scheduler(targetController);

	static final int max = 1000000;
	@Override
	public void start(Stage primaryStage) {

		ObservableList<Screen> screenSizes = Screen.getScreens();
		Double screenHeight = screenSizes.get(0).getBounds().getHeight();
		Double screenWidth = screenSizes.get(0).getBounds().getWidth();

		header.setOnMousePressed(e -> {
			xOffset = primaryStage.getX() - e.getScreenX();
			yOffset = primaryStage.getY() - e.getScreenY();
		});

		header.setOnMouseDragged(e -> {
			primaryStage.setX(e.getScreenX() + xOffset);
			primaryStage.setY(e.getScreenY() + yOffset);
		});

		header.getFunctions().getDeleteButton().setOnAction(e -> {
			targetController.removeSelectedTarget();
		});

		header.getAddTargetButton().setOnAction(e -> {
			String link = header.getLink();
			String name = header.getName();
			if (!link.isEmpty() && !name.isEmpty()) {
				targetController.addTargetIntoSolution(new Target(name, link));
				header.clearLabel();
			}
		});

		scheduledService.setPeriod(Duration.millis(5000));

		header.getPingButton().setOnAction(e -> {
			scheduledService.start();
			header.getPingButton().setDisable(true);
		});

		header.getFunctions().getClearButton().setOnAction(e -> {
			targetController.removeAllTargets();
		});

		header.getFunctions().getAddFromFileButton().setOnAction(e->{
			fileChooser.setTitle("Load file");
			File file = fileChooser.showOpenDialog(primaryStage);
			List<Target> targets = lff.getLinksList(file);
			targets.stream().forEach(target -> targetController.addTargetIntoSolution(target));
		});

		header.getFunctions().getSaveLinksButton().setOnAction(e -> {
			fileChooser.setTitle("Save file");
			File file = fileChooser.showSaveDialog(primaryStage);
			lff.saveFile(file, targetController.getAllTargetsToSaveFile());
		});

		header.getFunctions().getChangeToDetailedButton().setOnAction(e -> {
			header.getFunctions().getChangeToDetailedButton().setDisable(true);
			header.getFunctions().getChangeToCardViewButton().setDisable(false);
			main.setCenter(detailedView);
		});

		header.getFunctions().getChangeToCardViewButton().setOnAction(e -> {
			header.getFunctions().getChangeToCardViewButton().setDisable(true);
			header.getFunctions().getChangeToDetailedButton().setDisable(false);
			main.setCenter(scrollPane);	
		});


		header.getCloseButton().setOnAction(e -> {
			System.exit(0);
		});

		header.getMinimizeButton().setOnAction(e -> {
			primaryStage.setIconified(true);
		});

		header.getMaximizeButton().setOnAction(e -> {
			if(primaryStage.isFullScreen())
				primaryStage.setFullScreen(false);
			else
				primaryStage.setFullScreen(true);
		});

		//AUTOLOAD FILES (JUST FOR TESTING)
		List<Target> targets = lff.getLinksList(new File("../24Seven-Network-Monitor/24SevenNetworkMonitor/src/linkList.txt"));
		targets.stream().forEach(target -> targetController.addTargetIntoSolution(target));
		//AUTOLOAD FILES (JUST FOR TESTING)

		main.setCenter(scrollPane);
		main.setTop(header);

		Scene scene = new Scene(main, screenWidth*0.8, screenHeight*0.8);

		primaryStage.getIcons().add(new Image("icon.png"));
		primaryStage.setScene(scene);
		primaryStage.initStyle(StageStyle.TRANSPARENT);
		primaryStage.setTitle("24Seven Network Monitor");
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}


}