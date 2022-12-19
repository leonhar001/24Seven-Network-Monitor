package services;


import application.control.TargetsController;
import application.model.Target;
import javafx.concurrent.ScheduledService;
import javafx.concurrent.Task;

public class Scheduler extends ScheduledService<Void>{

	private TargetsController targetController = null;
	public Scheduler(TargetsController targetController) {
		this.targetController = targetController;
	}

	@SuppressWarnings("unchecked")
	@Override
	protected Task<Void> createTask() {
		Task<?> task = new Task<>() {
			@Override
			protected Void call() throws Exception {
				for (Target t : targetController.getTargets()) {
					t.checkStatus();
					targetController.getCardByName(
							t.getName()).changeBackGroundColor(t.isOnline());
				}
				System.out.println("PING...");
				return null;
			}
		};
		//header.getBar().progressProperty().bind(task.progressProperty());
		return (Task<Void>) task;
	}

}
