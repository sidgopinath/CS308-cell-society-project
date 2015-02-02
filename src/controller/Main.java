package controller;

import model.SimulationFire;
import view.SimulationScreen;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

	//private CellSocietyController myCellSocietyController;
	public static final int HEIGHT = 600;
	public static final int WIDTH = 600;

	@Override
	public void start(Stage stage) throws Exception {
		stage.setTitle("Cell Society");
		//myCellSocietyController = new CellSocietyController(WIDTH, HEIGHT);
		SimulationScreen simScreen = new SimulationScreen();
		stage.setScene(simScreen.initSimScreen(WIDTH, HEIGHT));
		stage.show();
		Integer[][] treeGrid = new Integer[][]{
				{0,0,0,0},
				{0,1,1,0},
				{0,2,2,0},
				{0,0,0,0},	
		};
		SimulationFire simFire = new SimulationFire(null, treeGrid, simScreen);
		simFire.updateGrid();
//		KeyFrame frame = myCellSocietyController.getKeyFrame(60);
//		Timeline animationTimeline = new Timeline();
//		animationTimeline.setCycleCount(Timeline.INDEFINITE);
//		animationTimeline.getKeyFrames().add(frame);
//		animationTimeline.play();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
