package view;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;

public class SimulationScreen {
	private Group myRoot;

	/**
	 * This function begins setting up the general simulation scene. This includes things like adding the four buttons
	 * at the top that load, step through, speed up, and stop/start the simulation.
	 * @returns a scene
	 */
	public Scene initSim(Group root){
		//myRoot = root
		//addLoadButton();
		//addStepButton();
		//addSpeedUpButton();
		//addStopStartButton();
		return null;
	}
	
	/**
	 * This function adds the load button to the scene and creates the eventListener.
	 */
	private void addLoadButton(){
		//button.onSetAction(e -> loadFile);
	}
	
	/**
	 * This function first will stop the current animation. Next it will call fileLoader which will allow the user
	 * to pick a new file and set up this screen again. 
	 * @returns null
	 */
	private Object loadFile(KeyEvent e){
		return null;
	}
	
	/**
	 * This function adds the step button to the scene and creates the eventListener.
	 */
	private void addStepButton(){
		//button.onSetAction(e->stepThroughSimulation()
	}
	
	/**
	 * This function calls for the individual simulation to step through by 1 event
	 * @returns null
	 */
	private Object stepThroughSimulation(){
		return null;
		
	}
	
	/**
	 * Adds speed up button
	 */
	private void addSpeedUpButton(){
		
	}
	
	/**
	 * event listener for speed up button
	 * @returns null
	 */
	private Object speedUpSimulation(){
		return null;
	}
	
	/**
	 * adds stop.start button
	 */
	private void addStopStartButton(){
		
	}
	
	/**
	 * stops or starts simulation
	 * @returns null
	 */
	private Object stopOrStart(){
		return null;
	}
}
