package application;

import java.io.File;
import java.io.IOException;
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
import javafx.concurrent.Task;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


public class Main extends Application {

	private Double xOffset = 0.0;
	private Double yOffset = 0.0;
	
	static final int max = 1000000;
	@Override
	public void start(Stage primaryStage) {
		BorderPane main = new BorderPane();
		
		Cards cards = new Cards();
		
		DetailedView detailedView = new DetailedView();

		CardsContainer scrollPane = new CardsContainer(cards);
		
		Header header = new Header();
		
		FileChooser fileChooser = new FileChooser();
		
		LinksFromFile lff = new LinksFromFile();
		
		TargetsController targetController = new TargetsController(cards, detailedView);
		
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
		
		header.getDeleteButton().setOnAction(e -> {
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
		
		header.getPingButton().setOnAction(e -> {

			Task<Void> task = new Task<Void>() {
				@Override
				public Void call() throws IOException {
					for (Target t : targetController.getTargets()) {
						t.checkStatus();
						targetController.getCardByName(
								t.getName()).changeBackGroundColor(t.isOnline());
					}

					for (int i = 1; i <= max; i++) {
						if (isCancelled()) {
							break;
						}
						updateProgress(i, max);
					}
					return null;
				}
			};

			header.getBar().progressProperty().bind(task.progressProperty());
			new Thread(task).start();
		});

		header.getClearButton().setOnAction(e -> {
			targetController.removeAllTargets();
		});
		
		header.getAddFromFileButton().setOnAction(e->{
			fileChooser.setTitle("Load file");
			File file = fileChooser.showOpenDialog(primaryStage);
			List<Target> targets = lff.getLinksList(file);
			targets.stream().forEach(target -> targetController.addTargetIntoSolution(target));
		});
		
		header.getSaveLinksButton().setOnAction(e -> {
			fileChooser.setTitle("Save file");
			File file = fileChooser.showSaveDialog(primaryStage);
			lff.saveFile(file, targetController.getAllTargetsToSaveFile());
		});
		
		header.getChangeViewButton().setOnAction(e -> {
			if(cards.isHide()) {
				cards.showCards();
				main.setCenter(scrollPane);
			}
			else {
				cards.hideCards();
				main.setCenter(detailedView);
			}
				
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
		///24Seven-Network-Monitor
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