package controller;

import java.util.HashMap;
import java.util.Random;
import java.util.ResourceBundle;

public class ProbabilitySimGenerator {

	//THIS CAN ALL BE REFACTORED INTO A SUPERCLASS
	private Integer[][] myGrid;
	private HashMap<String, String> myParameters = new HashMap<String, String>();
	private String mySimName;
	private ResourceBundle myProperties;
	private Random myRandom = new Random();
	private HashMap<Integer, Integer> myProbabilities;
	
	public ProbabilitySimGenerator(int gridHeight, int gridWidth, String simName, HashMap<Integer, Integer> probabilities){
		myProperties = ResourceBundle.getBundle("resources/resources");
		myParameters.put("simName", simName);
		myParameters.put("gridWidth", gridWidth + "");
		myParameters.put("gridHeight", gridHeight + "");
		mySimName = simName;
		myGrid = new Integer[gridHeight][gridWidth];
		myProbabilities = new HashMap<Integer, Integer>();
		myProbabilities.put(0, 20);
		myProbabilities.put(1, 30);
		myProbabilities.put(2, 50);
		createGrid(myGrid);
	}
	
	public Integer[][] getGrid() {
		return myGrid;
	}

	private void createGrid(Integer[][] grid) {
		for(int i=0; i<grid.length; i++){
			for(int j=0; j<grid[i].length; j++){
				if (mySimName.equals(myProperties
						.getObject("fire_simulation_name"))) {
					
					Integer random = myRandom.nextInt(100);
					Integer currentCheck = 0;
					while(currentCheck != 100){ //maybe 99?
						for(int key: myProbabilities.keySet()){
							if(random <= (currentCheck+myProbabilities.get(key))){
								myGrid[i][j] = key;
							}
							currentCheck += myProbabilities.get(key);
						}
					}
					
				}
			}
		}
		System.out.println("done ");
		
	}
	
}
