package view;

import controller.CellSocietyController;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;

public class SimulationScreen {
	private CellSocietyController myController;
	private int myWidth;
	private int myHeight;
	private HBox myTop;

	/**
	 * This function begins setting up the general simulation scene. This includes things like adding the four buttons
	 * at the top that load, step through, speed up, and stop/start the simulation.
	 * @returns a scene
	 */
	public Scene initSimScreen(int width, int height){
		BorderPane root = new BorderPane();
		myWidth = width;
		myHeight = height;
		myController = new CellSocietyController(width, height);
		root.getChildren().add(addButtons());
		return new Scene(root, width, height, Color.WHITE);
	}
	
	private HBox addButtons() {
		HBox myTop = new HBox();
		myTop.getChildren().add(addLoadButton());
		myTop.getChildren().add(addStepButton());
		myTop.getChildren().add(addSpeedUpButton());
		myTop.getChildren().add(addStopStartButton());
		return myTop;
		
	}

	/**
	 * This function adds the load button to the scene and creates the eventListener.
	 * @return Button that loads new file 
	 */
	private Button addLoadButton(){
		Button loadButton = new Button("Load");
		myTop.getChildren().add(loadButton);
		loadButton.setOnAction(e -> {
			//myController.transitionToFileLoaderScreen();
		});
		return loadButton;
	}
	
	/**
	 * This function adds the step button to the scene and creates the eventListener.
	 */
	private Button addStepButton(){
		Button stepButton = new Button ("Step");
		myTop.getChildren().add(stepButton);
		stepButton.setOnAction(e-> {
			//myController.stepThroughSimulation();
		});
		return stepButton;
	}

	
	/**
	 * Adds speed up button
	 */
	private Button addSpeedUpButton(){
		Button speedUpButton = new Button("Speed Up");
		myTop.getChildren().add(speedUpButton);
		speedUpButton.setOnAction(e -> {
			//myController.speedUpSimulation();
		});
		return speedUpButton; 
	}
	

	/**
	 * adds stop.start button
	 * @return 
	 */
	private Button addStopStartButton(){
		Button stopStartButton = new Button("Stop/Start");
		myTop.getChildren().add(stopStartButton);
		stopStartButton.setOnAction(e -> {
			//myController.stopOrStart();
		});
		return stopStartButton;
	}
	
}
