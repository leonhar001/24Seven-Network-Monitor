package application.control;

import java.util.HashSet;
import java.util.LinkedHashSet;

import application.model.Target;
import application.views.card.Card;
import application.views.card.Cards;
import application.views.detailed.DetailedView;


public class TargetsController {

	private HashSet<Target> targets = null;
	private Cards cards = null;
	private DetailedView detailedView = null;
	private String allTargets = "";

	public TargetsController(Cards cards, DetailedView detailedView) {
		targets = new LinkedHashSet<>();
		this.cards = cards;
		this.detailedView = detailedView;
	}

	public HashSet<Target> getTargets() {
		return targets;
	}

	public boolean addTargetIntoSolution(Target target) {
		if(targets.add(target) 
				&& cards.addCards(new Card(target)) 
				&& detailedView.getItems().add(target))
			return true;
		return false;
	}

	public boolean removeTargetFromSolution(Target target) {
		return false;
	}

	public void removeSelectedTarget() {
		targets.forEach(t -> {
			if(((Target)t).isSelected())
				cards.getChildren().remove(getCardByName(t.getName()));		
		});
		detailedView.getItems().removeIf(t -> t.isSelected());
		targets.removeIf(t -> t.isSelected());
	}

	public void removeAllTargets() {
		detailedView.getItems().clear();
		cards.getChildren().clear();
		targets.clear();
	}

	public Card getCardByName(String name) {
		return cards.getCardByName(name);
	}

	public String getAllTargetsToSaveFile() {
		targets.forEach(e -> {
			allTargets += e.toStringSaveFormat() + "\n";
		});
		return allTargets;
	}

}
