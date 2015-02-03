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
	private SplashScreen mySplashScreen;
	private SimulationScreen myCurrentSimulationScreen;
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
		mySplashScreen = new SplashScreen(this);
		Node splashNode = mySplashScreen.getNode(width, height);
		myGroup = new Group();
		myGroup.getChildren().add(mySplashScreen.getNode(width, height));
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
		if(mySplashScreen.goToFileLoaderScreen()){
			transitionToFileLoaderScreen();
			mySplashScreen.setFileLoader(false);
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
		myGroup.getChildren().add(fileLoaderScreen.getNode());
		File inputFile = fileLoaderScreen.getFile();
		readXML(inputFile);
	}

	public void stepThroughSimulation(){
		myTimeline.setCycleCount(1);
		myTimeline.play();
	}
	
	/**
	 * Speeds up simulation. Calls model maybe?
	 */
	public void speedUpSimulation(){
		myTimeline.stop();
		myTimeline.getKeyFrames().clear();
		myTimeline.getKeyFrames().add(getKeyFrame(2*myFrameRate));
		myTimeline.play();
		myFrameRate = 2*myFrameRate;
	}
	
	public void slowDownSimulation(){
		myTimeline.stop();
		myTimeline.getKeyFrames().clear();
		if(!(myFrameRate/2 == 1)){
			myFrameRate = myFrameRate/2;
			myTimeline.getKeyFrames().add(getKeyFrame(myFrameRate));
		}
		myTimeline.play();
	}
	
	private void restartSimulation() {
		myTimeline.play();
	}


	private void pauseSimulation() {
		myTimeline.stop();
	}
	
	/**
	 * Called after the XML file has been made. Transitions to new simulation
	 * DUPLICATED CODE, REFACTOR
	 */
	private void transitionToSimulation() {
		if (myParameters.get("simName").equals("fire")) {
			myCurrentSimulationScreen = new SimulationScreen();
			myCurrentSimulationScreen.initSimScreen(Integer.parseInt(myParameters.get("windowWidth")), Integer.parseInt(myParameters.get("windowHeight")),this);
			myCurrentSimulation = new SimulationFire(myParameters, myGrid, myCurrentSimulationScreen);
			myGroup.getChildren().clear();
			myGroup.getChildren().add(myCurrentSimulationScreen.getNode());
		}
		
		else if(myParameters.get("simName").equals("segregation")){
			myCurrentSimulationScreen = new SimulationScreen();
			myCurrentSimulationScreen.initSimScreen(Integer.parseInt(myParameters.get("windowWidth")), Integer.parseInt(myParameters.get("windowHeight")),this);
			myCurrentSimulation = new SimulationSegregation(myParameters, myGrid,myCurrentSimulationScreen);
			myGroup.getChildren().clear();
			myGroup.getChildren().add(myCurrentSimulationScreen.getNode());
		}
		else if(myParameters.get("simName").equals("life")){
			myCurrentSimulationScreen = new SimulationScreen();
			myCurrentSimulationScreen.initSimScreen(Integer.parseInt(myParameters.get("windowWidth")), Integer.parseInt(myParameters.get("windowHeight")),this);
			myCurrentSimulation = new SimulationLife(myParameters, myGrid, myCurrentSimulationScreen);
			myGroup.getChildren().clear();
			myGroup.getChildren().add(myCurrentSimulationScreen.getNode());
		}
		else if(myParameters.get("simName").equals("predator")){
			myCurrentSimulationScreen = new SimulationScreen();
			myCurrentSimulationScreen.initSimScreen(Integer.parseInt(myParameters.get("windowWidth")), Integer.parseInt(myParameters.get("windowHeight")),this);
			myCurrentSimulation = new SimulationPredator(myParameters, myGrid,myCurrentSimulationScreen);
			myGroup.getChildren().clear();
			myGroup.getChildren().add(myCurrentSimulationScreen.getNode());
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
