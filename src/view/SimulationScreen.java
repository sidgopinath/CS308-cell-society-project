package view;

import controller.CellSocietyController;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;

public class SimulationScreen {
	private CellSocietyController myController;
	private int myWidth;
	private int myHeight;
	private HBox myTop;

	/**
	 * This function begins setting up the general simulation scene. This includes things like adding the four buttons
	 * at the top that load, step through, speed up, and stop/start the simulation. Note: all strings used to create
	 * buttons must be retrieved from a .properties file. Right now I have them hard coded into the program.
	 * @returns a scene
	 */
	public Scene initSimScreen(int width, int height){
		BorderPane root = new BorderPane();
		myWidth = width;
		myHeight = height;
		myController = new CellSocietyController(width, height);
		root.setTop(addButtons());
		root.setCenter(addSimulation());
		return new Scene(root, width, height, Color.WHITE);
	}
	
	private HBox addButtons() {
		HBox myTop = new HBox();
		myTop.getChildren().addAll(addLoadButton(), addStepButton(), addSpeedUpButton(), addStopStartButton());
		double length = myTop.getChildren().size();
		double buttonWidth = myTop.getChildren().get(0).getLayoutBounds().getWidth();
		myTop.setSpacing((myWidth - length * buttonWidth) / (length + 1));
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
	
	/**
	 * sets up the gridpane for the simulation. 
	 * and update each cell with a color.
	 * @param grid
	 * @return
	 */
	private Node addSimulation(){
		GridPane simGridPane = new GridPane();
		return simGridPane;
	}
	
	/**
	 * must be passed a grid of some type so that it can determine the colors of each square
	 * @param grid
	 */
	public void updateScreen(Color[][] grid){
		
	}
	
}
