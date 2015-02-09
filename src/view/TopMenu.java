package view;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import controller.CellSocietyController;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class TopMenu {
	
	private ResourceBundle myProperties;
	private int myHeight;
	private int myWidth;
	private CellSocietyController myController;
	private List<Button> myButtons;
	private boolean myStart;

	public TopMenu(int width, int height, CellSocietyController controller){
		myWidth = width;
		myHeight = height;
		myProperties = ResourceBundle.getBundle("resources/resources");
		myController = controller;
		myButtons = new ArrayList<>();
		myStart = true;
	}
	
	public List<Button> createTopButtons(){
		addLoadButton();
		addSlowDownButton();
		addStepButton();
		addSpeedUpButton();
		addStopStartButton();
		addGraphButton();
		return myButtons;
		
	}
	
	private void addGraphButton() {
		Button graphButton = new Button(myProperties.getString("graph_button_name"));
		myButtons.add(graphButton);
		graphButton.setOnAction(e -> {
			Group graph = new Group();
			//open new window with graph
			Stage stage = new Stage();
			stage.setTitle("Graph");
			stage.setScene(new Scene(graph, myWidth/2, myHeight/2));
			stage.show();
		});
	}

	/**
	 * This function adds the load button to the scene and creates the
	 * eventListener.
	 * 
	 * @return Button that loads new file
	 */
	private void addLoadButton() {
		Button loadButton = new Button(myProperties.getString("load_button_name"));
		myButtons.add(loadButton);
		loadButton.setOnAction(e -> {
			myController.transitionToFileLoaderScreen();
		});
	}

	private void addSlowDownButton(){
		Button slowDownButton = new Button(myProperties.getString("slow_down_button_name"));
		myButtons.add(slowDownButton);
		slowDownButton.setOnAction(e -> {
			myController.changeSimulationSpeed(false);
		});
	}
	/**
	 * This function adds the step button to the scene and creates the
	 * eventListener.
	 */
	private void addStepButton() {
		Button stepButton = new Button(myProperties.getString("step_button_name"));
		myButtons.add(stepButton);
		stepButton.setOnAction(e -> {
			myController.stepThroughSimulation();
		});
	}

	/**
	 * Adds speed up button
	 */
	private void addSpeedUpButton() {
		Button speedUpButton = new Button(myProperties.getString("speed_up_button_name"));
		myButtons.add(speedUpButton);
		speedUpButton.setOnAction(e -> {
			myController.changeSimulationSpeed(true);
		});
	}

	/**
	 * adds stop.start button
	 * 
	 * @return
	 */
	private void addStopStartButton() {
		Button stopStartButton = new Button(myProperties.getString("stopstart_button_name"));
		myButtons.add(stopStartButton);
		stopStartButton.setOnAction(e -> {
			//if false it is stopped
			myStart = !myStart;
			myController.stopOrStart(myStart);
		});
	}
}
