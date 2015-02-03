package view;

import java.util.ArrayList;

import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import controller.CellSocietyController;

public class SimulationScreen {
	private CellSocietyController myController;
	private int myWidth;
	private int myHeight;
	private HBox myTop;
	private GridPane myGridPane;
	private boolean myStart;
	private BorderPane root;
	private ArrayList<Button> myButtons;

	/**
	 * This function begins setting up the general simulation scene. This
	 * includes things like adding the four buttons at the top that load, step
	 * through, speed up, and stop/start the simulation. Note: all strings used
	 * to create buttons must be retrieved from a .properties file. Right now I
	 * have them hard coded into the program.
	 * 
	 * @returns a scene
	 */
	public void initSimScreen(int width, int height, CellSocietyController controller){
		myController = controller;
		root = new BorderPane();
		myWidth = width;
		myHeight = height;
		myGridPane = new GridPane();
		myStart = true;
		myButtons = new ArrayList<>();
		//myController = new CellSocietyController(width, height);
		root.setTop(addButtons());
		root.setCenter(myGridPane);
	}
	
	public BorderPane getNode(){
		return root;
	}

	private HBox addButtons() {
		myTop = new HBox();
		myTop.getChildren().addAll(addLoadButton(), addStepButton(),
				addSpeedUpButton(), addStopStartButton(), addSlowDownButton());
		for(Button button: myButtons){
			button.setPrefWidth(myWidth / (myButtons.size()));
		}
		double length = myTop.getChildren().size(); 
		double buttonWidth = myButtons.get(0).getPrefWidth();
		System.out.println(buttonWidth);
		myTop.setSpacing((myWidth - length * buttonWidth) / (length));
		return myTop;

	}

	/**
	 * This function adds the load button to the scene and creates the
	 * eventListener.
	 * 
	 * @return Button that loads new file
	 */
	private Button addLoadButton() {
		Button loadButton = new Button("Load");
		myButtons.add(loadButton);
		loadButton.setOnAction(e -> {
			myController.transitionToFileLoaderScreen();
		});
		return loadButton;
	}

	private Button addSlowDownButton(){
		Button slowDownButton = new Button("Slow Down");
		myButtons.add(slowDownButton);
		slowDownButton.setOnAction(e -> {
			myController.slowDownSimulation();
		});
		return slowDownButton;
	}
	/**
	 * This function adds the step button to the scene and creates the
	 * eventListener.
	 */
	private Button addStepButton() {
		Button stepButton = new Button("Step");
		myButtons.add(stepButton);
		stepButton.setOnAction(e -> {
			myController.stepThroughSimulation();
		});
		return stepButton;
	}

	/**
	 * Adds speed up button
	 */
	private Button addSpeedUpButton() {
		Button speedUpButton = new Button("Speed Up");
		myButtons.add(speedUpButton);
		speedUpButton.setOnAction(e -> {
			myController.speedUpSimulation();
		});
		return speedUpButton;
	}

	/**
	 * adds stop.start button
	 * 
	 * @return
	 */
	private Button addStopStartButton() {
		Button stopStartButton = new Button("Stop/Start");
		myButtons.add(stopStartButton);
		stopStartButton.setOnAction(e -> {
			//if false it is stopped
			myStart = !myStart;
			myController.stopOrStart(myStart);
		});
		return stopStartButton;
	}

	/**
	 * must be passed a grid of some type so that it can determine the colors of
	 * each square It will then go through each square and set its appropriate
	 * color
	 * 
	 * @param colorGrid
	 */
	public void updateScreen(Color[][] colorGrid) {
		for (int j = 0; j < colorGrid.length; j++) {
			for (int i = 0; i < colorGrid[0].length; i++) {
				// get color and update square with that color
				getChild(j, i).setFill(colorGrid[j][i]);
			}
		}
	}

	/**
	 * goes through myGridPane and creates a new rectangle object at each spot
	 * in the grid
	 * @param gridHeight
	 * @param gridWidth
	 */
	public void initSimView(int gridHeight, int gridWidth) {
		for (int j = 0; j < gridHeight; j++) {
			for (int i = 0; i < gridWidth; i++) {
				Rectangle rect = new Rectangle(myWidth / gridWidth,
						(myHeight - myTop.getHeight()) / gridHeight);
				rect.setFill(Color.BLACK);
				rect.setStroke(Color.BLACK);
				myGridPane.add(rect, i, j);
			}
		}
	}

	// http://stackoverflow.com/questions/20825935/javafx-get-node-by-row-and-column
	private Shape getChild(int row, int column) {
		for (Node child : myGridPane.getChildren()) {
			if (GridPane.getRowIndex(child) == row
					&& GridPane.getColumnIndex(child) == column) {
				// trust me, it will be a rectangle...
				return (Shape) child;
			}
		}
		System.out.println("error, getChild() method not working");
		return null;

	}
}
