package controller;

import java.io.File;
import java.security.InvalidParameterException;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;
import jdk.nashorn.internal.runtime.regexp.joni.exception.ValueException;
import model.simulations.Simulation;
import model.simulations.SimulationFire;
import model.simulations.SimulationLife;
import model.simulations.SimulationPredator;
import model.simulations.SimulationSegregation;
import view.FileLoaderScreen;
import view.SimulationScreen;
import view.SplashScreen;

/**
 * Main controller that is called by main
 * @author Sid
 *
 */

public class CellSocietyController {

	private Simulation myCurrentSimulation;
	private SimulationScreen myCurrentSimulationScreen;
	private Scene myScene;
	private Group myGroup;
	private HashMap<String, String> myParameters = new HashMap<String, String>();
	private Map<String, String> myStyles = new HashMap<String, String>();
	private Integer myGrid[][];
	private int myFrameRate;
	private Timeline myTimeline;
	private Stage myStage;
	private ResourceBundle myProperties;
	
	public CellSocietyController(int width, int height, Stage stage) {
		myStage = stage;
		displaySplashScreen(width, height);
		myScene = new Scene((Parent) myGroup, width, height, Color.WHITE);
		myProperties = ResourceBundle.getBundle("resources/resources");
	}

	/**
	 * Extracted method that displays splash screen
	 * Called from constructor to set up first screen
	 */
	private void displaySplashScreen(int width, int height) {
		SplashScreen splash = new SplashScreen(this);
		myGroup = new Group();
		myGroup.getChildren().add(splash.getNode(width, height));

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
	 * Method to be called from the view
	 * Generates fully random grid from parameters passed in
	 * Default values will be used for parameters
	 * @param gridHeight
	 * @param gridWidth
	 * @param simName
	 * @param fullyRandom
	 */
	public void generateRandomGrid(int gridHeight, int gridWidth, String simName){
		RandomSimGenerator rsg = new RandomSimGenerator(gridHeight, gridWidth, simName);
		myGrid = rsg.getGrid();
		transitionToSimulation();
	}
	
	public void generateProbabilityRandomGrid(int gridHeight, int gridWidth, String simName, HashMap<Integer, Integer> probabilities){
		
	}
	
	/**
	 * Reads an XML file and stores the Int grid and Parameters
	 */
	private void readXML(File XMLFile) throws InvalidParameterException {
		try{
			XMLParser newParser = new XMLParser(XMLFile);
			if(newParser.getGrid() != null){
				myGrid = newParser.getGrid();
//				RandomSimGenerator rsg = new RandomSimGenerator(10, 10, "life", true); //to test random sim generator
//				myGrid = rsg.getGrid(); //to test random sim generator
//				ProbabilitySimGenerator psg = new ProbabilitySimGenerator(10, 10, "fire", null); //to test probability sim generator
//				myGrid = psg.getGrid(); //to test prob sim generator
				myParameters = newParser.getParameters();
				transitionToSimulation();
			}
			else{
				myStyles = newParser.getParameters();
			}
		}
		catch(ValueException e){
			System.out.println("Invalid grid values. Try again.");
			transitionToFileLoaderScreen();
		}
		catch(InvalidParameterException e){
			System.out.println("Invalid file. Try again.");
			transitionToFileLoaderScreen();
		}
		
		//XMLWriter writer = new XMLWriter(myParameters, myGrid); //to test xml writer
		
	}
	
	/**
	 * Method called when the load button has been pressed Opens up the file
	 * loader screen by calling the view class There may be an easier way to
	 * load files. This is a framework that can be changed. This method gets a
	 * File that will then be passed into XMLParser.
	 */
	public void transitionToFileLoaderScreen() {
		FileLoaderScreen fileLoaderScreen = new FileLoaderScreen(myStage);
		myGroup.getChildren().add(fileLoaderScreen.getNode());
		File inputFile = fileLoaderScreen.getFile();
		readXML(inputFile);
	}
	
	/**
	 * Steps through simulation one frame at a time.
	 * Lags a bit before performing the step...
	 */
	public void stepThroughSimulation(){
		myTimeline.stop();
		myTimeline.setCycleCount(1);
		myTimeline.play();
	}
	
	/**
	 * Speeds up simulation by a factor of 2
	 */
	public void speedUpSimulation(){
		myTimeline.stop();
		myTimeline.getKeyFrames().clear();
		myFrameRate = 2*myFrameRate;
		myTimeline.getKeyFrames().add(getKeyFrame(myFrameRate));
		myTimeline.play();
	}
	
	/**
	 * Slow down simulation by a factor of 2
	 * Will not go below 1 frame per second
	 */
	public void slowDownSimulation(){
		myTimeline.stop();
		myTimeline.getKeyFrames().clear();
		if(myFrameRate >= 2){
			myFrameRate = myFrameRate/2;
		}
		myTimeline.getKeyFrames().add(getKeyFrame(myFrameRate));
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
	 */
	private void transitionToSimulation() throws ValueException {
		initializeSimulationScreen();
		initializeSimulation(myParameters.get("simName"));
		stopOrStart(true);
	}

	/**
	 * Initializes the view.
	 */
	private void initializeSimulationScreen() {
		myCurrentSimulationScreen = new SimulationScreen();
		myCurrentSimulationScreen.initSimScreen(Main.WIDTH, Main.HEIGHT,this);
		myGroup.getChildren().clear();
		myGroup.getChildren().add(myCurrentSimulationScreen.getNode());
	}

	/**
	 * Initializes the model
	 * @param simName
	 */
	private void initializeSimulation(String simName) throws ValueException {
		if (simName.equals(myProperties.getObject("fire_simulation_name"))) {
			System.out.println("fire");
			System.out.println(myGrid[0][1] + " " + myGrid[0][2] + " " + myGrid[0][3]);
			myCurrentSimulation = new SimulationFire(myParameters, myStyles, myGrid, myCurrentSimulationScreen);	
		}
		else if(simName.equals(myProperties.getObject("segregation_simulation_name"))){
			myCurrentSimulation = new SimulationSegregation(myParameters, myStyles, myGrid,myCurrentSimulationScreen);
			
		}
		else if(simName.equals(myProperties.getObject("life_simulation_name"))){
			myCurrentSimulation = new SimulationLife(myParameters, myStyles, myGrid, myCurrentSimulationScreen);
			
		}
		else if(simName.equals(myProperties.getObject("predator_simulation_name"))){
			myCurrentSimulation = new SimulationPredator(myParameters, myStyles, myGrid, myCurrentSimulationScreen);
		}
	}

	/**
	 * Tells simulation to pause or restart
	 * @param stopStart
	 */
	public void stopOrStart(boolean stopStart) {
		if(stopStart == false){
			pauseSimulation();
		}
		else{
			restartSimulation();
		}
	}

	/**
	 * Method to start and manage the animation timeline
	 * @param animationTimeline
	 * @param frameRate
	 */
	public void manageTimeline(Timeline animationTimeline, int frameRate) {
		myTimeline = animationTimeline;
		KeyFrame frame = getKeyFrame(frameRate);
		myTimeline.setCycleCount(Timeline.INDEFINITE);
		myTimeline.getKeyFrames().add(frame);
		myTimeline.play();	
	}
}
