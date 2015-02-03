package controller;

import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

	private CellSocietyController myCellSocietyController;
	public static final int HEIGHT = 600;
	public static final int WIDTH = 600;

	@Override
	public void start(Stage stage) throws Exception {
		stage.setTitle("Cell Society");
		myCellSocietyController = new CellSocietyController(WIDTH, HEIGHT, stage);
		stage.setScene(myCellSocietyController.getScene());
		stage.show();
		Timeline animationTimeline = new Timeline();
		int frameRate = 1;
		myCellSocietyController.setFrameRate(1);
		myCellSocietyController.manageTimeline(animationTimeline, frameRate);
	}

	public static void main(String[] args) {
		launch(args);
	}
}
