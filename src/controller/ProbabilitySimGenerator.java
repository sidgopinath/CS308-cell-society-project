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
		myProbabilities.put(0, 10);
		myProbabilities.put(1, 50);
		myProbabilities.put(2, 40);
		createGrid(myGrid);
	}
	
	public Integer[][] getGrid() {
		System.out.println(myGrid[0][1] + " " + myGrid[0][2] + " " + myGrid[0][3]);
		System.out.println("GRID");
		return myGrid;
	}

	private void createGrid(Integer[][] grid) {
		for(int i=0; i<grid.length; i++){
			for(int j=0; j<grid[i].length; j++){
				if (mySimName.equals(myProperties
						.getObject("fire_simulation_name"))) {
					
					Integer random = myRandom.nextInt(100);
					Integer currentCheck = 0;
					while(currentCheck < 100){ //maybe 99?
						
						for(int key: myProbabilities.keySet()){
							if(random <= (currentCheck+myProbabilities.get(key))){
								myGrid[i][j] = key;
								System.out.println(i + " " + j + " " + "KEY:" + key);
								currentCheck = 101;
								//System.out.println(key);
								break;
							}
							else{
								currentCheck += myProbabilities.get(key);
							}
								
						}
						
					}
					
				}
			}
		}
		System.out.println("loop" + myGrid[0][1] + " " + myGrid[0][2] + " " + myGrid[0][3]);
	}
	
}
