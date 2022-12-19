package application.views.card;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.FlowPane;
import javafx.scene.paint.Color;

public class Cards extends FlowPane {

	static Color backgroundColor = Color.web("#2C2F33");
	private static BackgroundFill fillCenter = new BackgroundFill(
			backgroundColor, null, Insets.EMPTY);

	Card returnCard = null;

	public Cards() {
		setAlignment(Pos.TOP_CENTER);
		setVgap(15);
		setHgap(15);
		setBackground(new Background(fillCenter));
		setPadding(new Insets(10, 10, 10, 10));
	}

	public boolean addCards(Card card) {
		return getChildren().add(card);
	}

	public Card getCardByName(String name) {
		getChildren().forEach(e -> {
			if(((Card)e).getNameIdentifier() == name) {
				returnCard = (Card)e;
			}
		});
		return returnCard;
	}
}	