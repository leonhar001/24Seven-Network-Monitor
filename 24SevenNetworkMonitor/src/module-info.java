module SoMuchToPing {
	requires javafx.controls;
	requires javafx.graphics;
	requires javafx.base;

	opens application to javafx.graphics, javafx.fxml, javafx.controls;
	opens application.model to javafx.base;
}
