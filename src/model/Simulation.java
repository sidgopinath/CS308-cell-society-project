package model;

import java.util.HashMap;

import javafx.scene.Scene;

public abstract class Simulation {
	
	/**
	 * starts initSimScreen in Simulation Screen, then calls the methods to fill grid
	 * @return
	 */
	public Scene initSim(){
		return null;
	}
	
	/**
	 * fill grid with squares that have the right values given the parameters 
	 */
	public void fillGrid(HashMap<String, Integer> param){
		
	}
}
