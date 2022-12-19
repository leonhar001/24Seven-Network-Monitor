package application.views.detailed;

import application.model.Target;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class DetailedView extends TableView<Target>{

	private TableColumn<Target, String> name = new TableColumn<>("Name");
	private TableColumn<Target, String> link = new TableColumn<>("Link");
	private TableColumn<Target, Boolean> isOnline = new TableColumn<>("Status");

	@SuppressWarnings("unchecked")
	public DetailedView() {
		name.setCellValueFactory(new PropertyValueFactory<>("name"));
		link.setCellValueFactory(new PropertyValueFactory<>("link"));
		isOnline.setCellValueFactory(new PropertyValueFactory<>("online"));
		getColumns().addAll(name, link, isOnline);
	}
}