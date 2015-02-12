package controller;

import java.util.HashMap;
import java.util.Random;
import java.util.ResourceBundle;

public abstract class SimGenerator {

	protected Integer[][] myGrid;
	protected HashMap<String, String> myParameters = new HashMap<String, String>();
	protected String mySimName;
	protected Random myRandom = new Random();
	protected ResourceBundle myProperties;
	
	public SimGenerator(int gridHeight, int gridWidth, String simName, HashMap<Integer, Integer> probabilities, String gridType){
		myProperties = ResourceBundle.getBundle("resources/resources");
		myParameters.put("simName", simName);
		myParameters.put("gridWidth", gridWidth + "");
		myParameters.put("gridHeight", gridHeight + "");
		myGrid = new Integer[gridHeight][gridWidth];
		mySimName = simName;
	}
	
	public Integer[][] getGrid() {
		return myGrid;
	}

	public HashMap<String, String> getParameters(){
		return myParameters;
	}

	protected abstract void createGrid(Integer[][] grid);
}