package controller;

import model.SimulationFire;
import model.SimulationLife;
import model.SimulationSegregation;
import view.SimulationScreen;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
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
		Group root = new Group();
		root.getChildren().add(simScreen.initSimScreen(WIDTH, HEIGHT));
		stage.setScene(new Scene(root, WIDTH, HEIGHT));
		stage.show();
		Integer[][] treeGrid = new Integer[][]{
				{0,0,0,0},
				{0,1,1,1},
				{0,0,1,1},
				{0,0,0,0},	
		};
		SimulationSegregation simFire = new SimulationSegregation(null, treeGrid, simScreen);
		for(int i = 0; i < 2; i++){
			simFire.updateGrid();
		}
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
