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
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

public class SimulationScreen {
	private CellSocietyController myController;
	private int myWidth;
	private int myHeight;
	private HBox myTop;
	private GridPane myGridPane;

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
		//myController = new CellSocietyController(width, height);
		root.setTop(addButtons());
		root.setCenter(addSimulation());
		return new Scene(root, width, height, Color.WHITE);
	}
	
	private HBox addButtons() {
		myTop = new HBox();
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
		myGridPane = simGridPane;
		return simGridPane;
	}
	
	/**
	 * must be passed a grid of some type so that it can determine the colors of each square
	 * It will then go through each square and set its appropriate color
	 * @param colorGrid
	 */
	public void updateScreen(Color[][] colorGrid){
		for(int j = 0; j < colorGrid.length; j++){
			for(int i = 0; i < colorGrid[0].length; i++){
				//get color and update square with that color
				getChild(i, j).setFill(colorGrid[j][i]);
			}
		}
	}
	
	/**
	 * goes through myGridPane and creates a new rectangle object at each spot in the grid
	 * @param gridHeight
	 * @param gridWidth
	 */
	public void initSimView(int gridHeight, int gridWidth){
		for(int j = 0; j < gridHeight; j++){
			for(int i = 0; i < gridWidth; i++){
				myGridPane.add(new Rectangle(), j, i);
			}
		}
	}
	
	//http://stackoverflow.com/questions/20825935/javafx-get-node-by-row-and-column
	private Shape getChild(int row, int column){
		for(Node child: myGridPane.getChildren()){
			if(GridPane.getRowIndex(child) == row && GridPane.getColumnIndex(child) == column){
				//trust me, it will be a rectangle...
				return (Shape) child;
			}
		}
		System.out.println("error, getChild() method not working");
		return null;
		
	}
}
