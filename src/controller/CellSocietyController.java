package controller;

import java.util.Random;

import view.SplashScreen;
import javafx.animation.KeyFrame;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.util.Duration;
import model.Simulation;

public class CellSocietyController {

	private Simulation myCurrentSimulation;
	private Scene myScene;
	private Group myGroup;
	public static final Random RANDOM_NUM_GEN = new Random();
	
	public CellSocietyController(int width, int height){
		//initialize splash screen and set up scene
		SplashScreen splash = new SplashScreen();
		splash.setLoadButtonAction(e -> transitionToFileLoaderScreen());
		Node splashNode = splash.getNode();
		myGroup = new Group();
		myGroup.getChildren().add(splash.getNode());
		splashNode.setTranslateX(width / 2
				- splashNode.getBoundsInLocal().getWidth() / 2);
		splashNode.setTranslateY(height / 2
				- splashNode.getBoundsInLocal().getHeight() / 2);
		myScene = new Scene((Parent) myGroup, width, height, Color.WHITE);
	}
	
	public Scene getScene(){
		return myScene;
	}
	
	public KeyFrame getKeyFrame(int frameRate){
		return new KeyFrame(Duration.millis(1000/frameRate), e -> update());
	}

	private void update() {
		//update program, check for transition conditions at a high level
		myCurrentSimulation.updateGrid();
		checkForTransition();
		checkSimulationStop();
		checkProgramEnd();
		
	}
	
	private void checkProgramEnd() {
		//check if program should stop. if so, call end program
	}

	private void checkForTransition() {
		//check for transition to a new simulation or different screen?
		
	}

	private void checkSimulationStop(){
		//checks if the current simulation has stopped or been stopped by user
		//does this by calling a method in Simulation that will return boolean
		if(myCurrentSimulation.isStopped()){
			//do stuff
		}
	}
	
	private void transitionToFileLoaderScreen(){
		//opens file loader screen
		//not sure if this is a necessary method
	}
	
	private void transitionToSimulationScreen(){
		//transitions to chosen simulation screen
	}
	
	private void endProgram(){
		System.exit(0);
	}	
}
