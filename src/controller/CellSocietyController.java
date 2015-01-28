package controller;

import java.util.Random;

import model.Simulation;
import javafx.animation.KeyFrame;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.util.Duration;

public class CellSocietyController {

	private Simulation myCurrentSimulation;
	private Scene myScene;
	private Group myGroup;
	public static final Random RANDOM_NUM_GEN = new Random();
	
	public CellSocietyController(int width, int height){
		//initialize splash screen and set up scene
	}
	
	public Scene getScene(){
		return myScene;
	}
	
	public KeyFrame getKeyFrame(int frameRate){
		return new KeyFrame(Duration.millis(1000/frameRate), e -> update());
	}

	private void update() {
		//update program, check for transition conditions at a high level
	}
	
	private void checkSimulationStop(){
		//checks if the current simulation has stopped or been stopped by user
		//does this by calling a method in Simulation that will return boolean
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
