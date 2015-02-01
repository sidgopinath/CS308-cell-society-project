package controller;

import java.io.File;
import java.util.HashMap;
import java.util.Random;

import javafx.animation.KeyFrame;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.util.Duration;
import model.Simulation;
import model.SimulationFire;
import view.FileLoaderScreen;
import view.SplashScreen;

public class CellSocietyController {

	private Simulation myCurrentSimulation;
	private Scene myScene;
	private Group myGroup;
	public static final Random RANDOM_NUM_GEN = new Random();
	private HashMap<String, String> myParameters = new HashMap<String, String>();
	private Integer myGrid[][];
	private int splashWidth;
	private int splashHeight;
	
	public CellSocietyController(int width, int height) {
		// initialize splash screen and set up scene
		splashWidth = width;
		splashHeight = height;
		displaySplashScreen(splashWidth, splashHeight);
		myScene = new Scene((Parent) myGroup, width, height, Color.WHITE);
	}

	
	/**
	 * Extracted method that displays splash screen
	 * Called from constructor to set up first screen
	 */
	private void displaySplashScreen(int width, int height) {
		SplashScreen splash = new SplashScreen();
		splash.setLoadButtonAction(e -> transitionToFileLoaderScreen());
		Node splashNode = splash.getNode();
		myGroup = new Group();
		myGroup.getChildren().add(splash.getNode());
		splashNode.setTranslateX(width / 2
				- splashNode.getBoundsInLocal().getWidth() / 2);
		splashNode.setTranslateY(height / 2
				- splashNode.getBoundsInLocal().getHeight() / 2);
	}

	public Scene getScene() {
		return myScene;
	}

	public KeyFrame getKeyFrame(int frameRate) {
		return new KeyFrame(Duration.millis(1000 / frameRate), e -> update());
	}

	
	/**
	 * Update method called every frame
	 * Calls simulation's update method
	 * Checks for a stopped simulation, transitions, and the program end
	 */
	private void update() {
		if (myCurrentSimulation != null) {
			myCurrentSimulation.updateGrid();
			checkSimulationStop();
		}
		checkForTransition();
		checkProgramEnd();

	}

	
	/**
	 * Not certain on this method yet
	 * Thought it could check for a transition between screens
	 */
	private void checkForTransition() {

	}

	/**
	 * Checks if the current simluation has stopped or ben stopped
	 * Calls a method in Simulation that will return a boolean
	 * If it has been set to stop, controller will stop simulation
	 */
	private void checkSimulationStop() {
		if (myCurrentSimulation.isStopped()) {
			myCurrentSimulation.stopSim();
		}
	}

	
	/**
	 * Called when an XML file needs to be read. Reads an XML file using the
	 * parser and stores the Int grid and Parameters
	 */
	private void readXML(File XMLFile) {
		XMLParser newParser = new XMLParser(XMLFile);
		myGrid = newParser.getGrid();
		myParameters = newParser.getParameters();
		transitionToSimulation();
	}

	
	/**
	 * Method called when the load button has been pressed Opens up the file
	 * loader screen by calling the view class There may be an easier way to
	 * load files. This is a framework that can be changed. This method gets a
	 * File that will then be passed into XMLParser.
	 */
	private void transitionToFileLoaderScreen() {
		FileLoaderScreen fileLoaderScreen = new FileLoaderScreen();
		myGroup.getChildren().clear();
		Node node = fileLoaderScreen.getNode();
		myGroup.getChildren().add(fileLoaderScreen.getNode());
		node.setTranslateX(Main.WIDTH / 2 - node.getBoundsInLocal().getWidth()
				/ 2);
		node.setTranslateY(Main.HEIGHT / 2
				- node.getBoundsInLocal().getHeight() / 2);
		File inputFile = fileLoaderScreen.getFile(); // not sure if we should
														// implement this in the
														// view?
		readXML(inputFile);
	}

	
	/**
	 * Called after the XML file has been made. Transitions to new simulation
	 * Potential for many bad if statements here. How to avoid? 
	 * Examples of bad code below
	 */
	private void transitionToSimulation() {
		if (myParameters.get("simType") == "fire") {
			myCurrentSimulation = new SimulationFire();
		}
	}
	
	/**
	 * For transitioning back to splash screen
	 * Called at end of program perhaps? Might not be necessary
	 */
	private void transitionToSplash(){
		myGroup.getChildren().clear();
		displaySplashScreen(splashWidth, splashHeight);
	}

	
	/**
	 * Checks if the program has ended
	 * If it has ended, will call program end
	 * Likely unnecessary method
	 */
	private void checkProgramEnd() {
	
		// check if program should stop. if so, call end program
	}

	
	/**
	 * Called to end program. Likely unnecessary.
	 */
	private void endProgram() {
		System.exit(0);
	}
}
