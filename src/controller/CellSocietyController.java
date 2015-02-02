package controller;

import java.io.File;
import java.util.HashMap;
import java.util.Random;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;
import model.Simulation;
import model.SimulationFire;
import model.SimulationLife;
import model.SimulationPredator;
import model.SimulationSegregation;
import view.FileLoaderScreen;
import view.SimulationScreen;
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
	private int myFrameRate;
	private Timeline myTimeline;
	private Stage myStage;
	
	public CellSocietyController(int width, int height, Stage stage) {
		splashWidth = width;
		splashHeight = height;
		myStage = stage;
		displaySplashScreen(splashWidth, splashHeight);
		myScene = new Scene((Parent) myGroup, width, height, Color.WHITE);
	}

	
	/**
	 * Extracted method that displays splash screen
	 * Called from constructor to set up first screen
	 */
	private void displaySplashScreen(int width, int height) {
		SplashScreen splash = new SplashScreen();
		Node splashNode = splash.getNode(width, height);
		myGroup = new Group();
		myGroup.getChildren().add(splash.getNode(width, height));
		splashNode.setTranslateX(width / 2
				- splashNode.getBoundsInLocal().getWidth() / 2);
		splashNode.setTranslateY(height / 2
				- splashNode.getBoundsInLocal().getHeight() / 2);
	}

	public Scene getScene() {
		return myScene;
	}

	public void setFrameRate(int frameRate){
		myFrameRate = frameRate;
	}
	
	public KeyFrame getKeyFrame(int frameRate) {
		return new KeyFrame(Duration.millis(1000 /frameRate), e -> update());
	
	}
	
	/**
	 * Update method called every frame
	 * Calls simulation's update method
	 */
	private void update() {
		if (myCurrentSimulation != null) {
			myCurrentSimulation.updateGrid();
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
	public void transitionToFileLoaderScreen() {
		FileLoaderScreen fileLoaderScreen = new FileLoaderScreen(myStage);
		myGroup.getChildren().clear();
		Node node = fileLoaderScreen.getNode();
		myGroup.getChildren().add(fileLoaderScreen.getNode());
		node.setTranslateX(Main.WIDTH / 2 - node.getBoundsInLocal().getWidth()
				/ 2);
		node.setTranslateY(Main.HEIGHT / 2
				- node.getBoundsInLocal().getHeight() / 2);
		File inputFile = fileLoaderScreen.getFile();
		readXML(inputFile);
	}

	public void stepThroughSimulation(){
		myTimeline.stop();
		myTimeline.setCycleCount(1);
		myTimeline.play();
	}
	
	/**
	 * Speeds up simulation. Calls model maybe?
	 */
	public void speedUpSimulation(){
		myTimeline.stop();
		myTimeline.setCycleCount(Timeline.INDEFINITE);
		manageTimeline(myTimeline, (2*myFrameRate));
		
	}
	
	private void restartSimulation() {
		myTimeline.setCycleCount(Timeline.INDEFINITE);
		myTimeline.play();
	}


	private void pauseSimulation() {
		myTimeline.pause();
	}
	
	/**
	 * Called after the XML file has been made. Transitions to new simulation
	 * Potential for many bad if statements here. How to avoid? 
	 * DUPLICATED CODE, REFACTOR
	 */
	private void transitionToSimulation() {
		if (myParameters.get("simType") == "fire") {
			myCurrentSimulation = new SimulationFire(myParameters, myGrid, new SimulationScreen());
		}
		else if(myParameters.get("simType") == "segregation"){
			myCurrentSimulation = new SimulationSegregation(myParameters, myGrid,new SimulationScreen());
		}
		else if(myParameters.get("simType") == "life"){
			myCurrentSimulation = new SimulationLife(myParameters, myGrid, new SimulationScreen());
		}
		else if(myParameters.get("simType") == "predator"){
			myCurrentSimulation = new SimulationPredator(new SimulationScreen(), myParameters, myGrid);
		}
	}
	
	public void stopOrStart(boolean stopStart) {
		if(stopStart == false){
			pauseSimulation();
		}
		else{
			restartSimulation();
		}
	}

	public void manageTimeline(Timeline animationTimeline, int frameRate) {
		myTimeline = animationTimeline;
		KeyFrame frame = getKeyFrame(frameRate);
		myTimeline.setCycleCount(Timeline.INDEFINITE);
		myTimeline.getKeyFrames().add(frame);
		myTimeline.play();	
	}

}
